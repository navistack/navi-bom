package org.navistack.framework.sms;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
public class SimpleShortMessage implements ShortMessage {
    private String phoneNumber;
    private String signature;
    private String templateCode;
    private String message;
    private Map<String, Object> arguments = Collections.emptyMap();
    private Map<String, Object> attributes = Collections.emptyMap();
}
