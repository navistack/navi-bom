package org.navistack.boot.tencent.cloud.autoconfigure;

import lombok.Data;

@Data
public class TencentCloudClientProfileProperties {
    private TencentCloudHttpProfileProperties httpProfile = new TencentCloudHttpProfileProperties();
    private String signMethod;
    private boolean unsignedPayload;
    private String language;
    private boolean debug;
}
