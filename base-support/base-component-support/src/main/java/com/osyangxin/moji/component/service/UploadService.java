package com.osyangxin.moji.component.service;


import com.osyangxin.moji.common.enums.FileType;
import com.osyangxin.moji.common.exception.ApplicationException;
import com.osyangxin.moji.common.utils.StringUtil;
import com.osyangxin.moji.component.bean.FTPConfig;
import com.osyangxin.moji.component.msg.RetStubMsg;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); //JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47");  //PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638");  //GIF (gif)
        FILE_TYPE_MAP.put("bmp", "424D"); //Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("apk", "504B0"); // Android APK
    }

    public long getRemoteFileSize(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            return Long.parseLong(conn.getHeaderField("Content-Length"));
        } catch (IOException e) {
            logger.error("获取文件大小失败，url为：<{}>，原因为：{}", url, e.getMessage());
            return 0;
        }
    }

    private final FTPConfig ftpConfig;

    @Autowired
    public UploadService(FTPConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    public Map uploadFile(MultipartFile file, FileType fileType) throws IOException {

        Map<String, Object> map = new HashMap<>();
        if (file.isEmpty()) {
            throw new ApplicationException(RetStubMsg.FILE_IS_EMPTY);
        }
        byte[] bytes = file.getBytes();
        // 将校验文件格式是否支持上传
        String fileSuffix = "", dir;
        String fileName = StringUtil.dateToString(new Date(), "yyyyMMddHHmmss") + StringUtil.generateRandomNumber(10)  + ".";
        if (fileType == FileType.ALL) {
            fileName = file.getOriginalFilename().replaceFirst(".*\\.", fileName);
        } else {
            fileSuffix = getFileTypeByFileHeadBytes(bytes);
            fileName =  fileName + fileSuffix;
        }
        if (fileType == FileType.ALL) {
            dir = ftpConfig.getAlldir();
            map.put("url", ftpConfig.getAllurl() + fileName);
        } else if (fileType.getType() == FileType.APK.getType() && "apk".equalsIgnoreCase(fileSuffix)) {
            dir = ftpConfig.getApkdir();
            map.put("url", ftpConfig.getApkurl() + fileName);
        } else if (fileType.getType() == FileType.IMAGE.getType() &&
                ("bmp".equalsIgnoreCase(fileSuffix)
                        || "jpg".equalsIgnoreCase(fileSuffix)
                        || "png".equalsIgnoreCase(fileSuffix))) {
            dir = ftpConfig.getImagedir();
            map.put("url", ftpConfig.getImageurl() + fileName);
        } else {
            throw new ApplicationException(RetStubMsg.FILE_FORMAT_NOT_SUPPORT);
        }
        uploadFileToFtpServer(dir, fileName, bytes);
        return map;
    }

    /**
     * 文件上传到FTP服务器
     * @param dir 文件存储文件夹
     * @param name 文件名
     * @param bytes 文件数据
     */
    private void uploadFileToFtpServer(String dir, String name, byte[] bytes) {
        logger.info("begin to upload file to ftp server for file {}", name);
        FTPClient client = new FTPClient();
        try {
            client.connect(ftpConfig.getIp(), ftpConfig.getPort());
            boolean login = client.login(ftpConfig.getUsername(), ftpConfig.getPassword());
            logger.info("login ftp server result {}", login);
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
//            int replay = client.getReply();
//            if (!FTPReply.isPositiveCompletion(replay)) {
//                logger.info("login ftp server failed");
//                client.disconnect();
//            }
            boolean enteredDir = client.changeWorkingDirectory(dir);
            if (!enteredDir) {
                client.makeDirectory(dir);
                enteredDir = client.changeWorkingDirectory(dir);
            }
            logger.info("cd dir <{}> result : {}", dir, enteredDir);
            client.enterLocalPassiveMode();
            boolean success = client.storeFile(name, new ByteArrayInputStream(bytes));
            logger.info("upload file to ftp server status : {}", success ? "successfully" : "failed");
        } catch (Exception e) {
            logger.error("login or upload file to ftp server failed,相应错误栈信息", e);
        } finally {
            if (client.isConnected()) {
                try {
                    client.logout();
                    client.disconnect();
                } catch (Exception e) {
                    logger.warn("disconnect from ftp server failed");
                }
            }
        }
    }

    /**
     * 通过每个文件的魔数来进行文件类型判断
     * @param headBytes 文件头部字节，可用于判断文件类型即可
     */
    private String getFileTypeByFileHeadBytes(byte[] headBytes) {
        headBytes = Arrays.copyOf(headBytes, 10);
        String type = getFileTypeByStream(headBytes);
        logger.info("file type --> {}", type);
        if (StringUtils.isEmpty(type)) {
            throw new ApplicationException(RetStubMsg.FILE_FORMAT_NOT_SUPPORT);
        }
        return type;
    }

    private String getFileTypeByStream(byte[] b) {
        String fileHeadHex = getStringFromBytes(b);
        logger.info("file header bytes to string --> {}", fileHeadHex);
        if (StringUtils.isEmpty(fileHeadHex)) {
            return null;
        }

        for (Map.Entry<String, String> entry : FILE_TYPE_MAP.entrySet()) {
            String magicNumberHex = entry.getValue();
            if (fileHeadHex.toUpperCase().startsWith(magicNumberHex)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String getStringFromBytes(byte[] b) {
        StringBuilder sb = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 上传文件到本地服务器
     *
     * @param bytes
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    @Deprecated
    public void uploadFileToLocalServer(byte[] bytes, String filePath, String fileName) throws IOException {
        File fileTemp = new File(filePath);
        if (!fileTemp.exists()) {
            fileTemp.mkdirs();
        }
        if (!fileTemp.isDirectory()) {
            throw new ApplicationException(RetStubMsg.FILE_PATH_ILLEGAL);
        }
        File file = new File(filePath + fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        try {
            outputStream.write(bytes);
            outputStream.flush();
        } finally {
            outputStream.close();
        }
    }
}
