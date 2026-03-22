package org.navistack.boot.security.autoconfigure;

import lombok.Data;

@Data
public class AntiSamyProperties {
    private static final String DEFAULT_POLICY_LOCATION = "classpath:antisamy-slashdot.xml";

    private String policyLocation = DEFAULT_POLICY_LOCATION;
}
