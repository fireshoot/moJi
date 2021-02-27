package com.osyangxin.moji.service;

import com.osyangxin.moji.common.constants.Constants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangxin
 * @类描述
 * @time 2021/2/27  12:17
 */
@Slf4j
@Service
public class CaptchaService {

    private int imageWidth = 70;
    private int imageHigh = 26;
    @Autowired
    private CacheService cacheService;

    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        /*
         * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
         */
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        if (StringUtils.isNumeric(width) && StringUtils.isNumeric(height)) {
            imageWidth = NumberUtils.toInt(width);
            imageHigh = NumberUtils.toInt(height);
        }

        BufferedImage image = new BufferedImage(imageWidth, imageHigh, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        createBackground(g);

        String captchaString = createCharacter(g);

        log.debug("sessionId:{}, createCaptchaString:{}", request.getSession().getId(), captchaString);
        String uuid = UUID.randomUUID().toString();
        cacheService.setVal(String.format(Constants.CACHE_LOGIN_CAPTCHA_PREFIX, uuid), captchaString, Constants.CAPTCHA_EXPIRE_TIME);
        response.addHeader("UUID", uuid);

        g.dispose();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.close();
    }

    private String createCharacter(Graphics g) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] fontTypes = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
            g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 26));
            g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
            s.append(r);
        }
        return s.toString();
    }

    private Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    private void createBackground(Graphics g) {
        // 填充背景
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, imageWidth, imageHigh);
        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            g.setColor(getRandColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(imageWidth);
            int y = random.nextInt(imageHigh);
            int x1 = random.nextInt(imageWidth);
            int y1 = random.nextInt(imageHigh);
            g.drawLine(x, y, x1, y1);
        }
    }
}
