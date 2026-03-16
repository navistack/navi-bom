package org.navistack.boot.autoconfigure.security;

import lombok.Data;

@Data
public class AntiSamyProperties {
    private static final String DEFAULT_POLICY_LOCATION = "classpath:antisamy-slashdot.xml";

    private String policyLocation = DEFAULT_POLICY_LOCATION;
}
