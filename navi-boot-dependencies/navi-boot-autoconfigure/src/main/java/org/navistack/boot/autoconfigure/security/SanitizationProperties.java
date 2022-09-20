package org.navistack.boot.autoconfigure.security;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;

@ConfigurationProperties(prefix = SanitizationProperties.PROPERTIES_PREFIX)
@Data
public class SanitizationProperties implements InitializingBean {
    public static final String PROPERTIES_PREFIX = "navi.security.sanitization";

    private Collection<String> urlPatterns = Collections.emptyList();

    private Collection<String> bypassedHeaders = Collections.emptyList();

    private Collection<String> bypassedParameters = Collections.emptyList();

    private boolean returnContentAsIsOnError;

    private String defaultContentOnError;

    private AntiSamyProperties antiSamy = new AntiSamyProperties();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(urlPatterns, "urlPatterns must not be null");
        Assert.notNull(bypassedHeaders, "bypassedHeaders must not be null");
        Assert.notNull(bypassedParameters, "bypassedParameters must not be null");
        Assert.hasText(antiSamy.getPolicyLocation(), "antiSamy.policyLocation must be specified");
    }
}
