package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import lombok.Data;

@Data
public class TencentCloudHttpProfileProperties {
    private String reqMethod;
    private String endpoint;
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connTimeout;
    private String protocol;
    private String proxyHost;
    private Integer proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private String rootDomain;
}
