package org.navistack.boot.autoconfigure.cloudservice.tencentcloud;

import lombok.Data;

@Data
public class TencentCloudClientProfileProperties {
    private TencentCloudHttpProfileProperties httpProfile = new TencentCloudHttpProfileProperties();
    private String signMethod;
    private boolean unsignedPayload;
    private String language;
    private boolean debug;
}
