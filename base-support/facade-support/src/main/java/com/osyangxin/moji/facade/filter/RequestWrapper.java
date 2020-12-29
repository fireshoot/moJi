package com.osyangxin.moji.facade.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 方法实现说明 ： HttpServletRequest包装类 用于实现对RequestBody的重复读
 * @author      yangxin
 * @date        2020/12/29 18:26
*/
public class RequestWrapper extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestWrapper.class);
    private byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        if (ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("RequestWrapper: multipart/form-data is not supported!");
        }
        try {
            body = IOUtils.toByteArray(request.getInputStream());
        } catch (Exception ex) {
            logger.error("RequestWrapper read from inputStream error!", ex);
            body = new byte[0];
        } finally {
            try {
                request.getInputStream().close();
            } catch (Exception ex) { }
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream(), StandardCharsets.UTF_8));
    }

    public byte[] getRequestBody() {
        return this.body;
    }

}
