package com.osyangxin.moji.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="ftp")
public class FTPConfig {

    private String ip;

    private Integer port;

    private String username;

    private String password;

    private String workPath;

    private String apkdir;

    private String apkurl;

    private String imagedir;

    private String imageurl;

    private String alldir;

    private String allurl;
}

