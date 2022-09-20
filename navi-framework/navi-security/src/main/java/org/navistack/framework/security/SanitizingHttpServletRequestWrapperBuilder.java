package org.navistack.framework.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.navistack.framework.servlet.HttpServletRequestWrapperBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Getter
@Setter
@Accessors(fluent = true)
public class SanitizingHttpServletRequestWrapperBuilder implements HttpServletRequestWrapperBuilder<SanitizingHttpServletRequestWrapper> {

    private ContentSanitizer contentSanitizer;
    private Collection<String> bypassedHeaders;
    private Collection<String> bypassedParameters;

    @Override
    public SanitizingHttpServletRequestWrapper build(HttpServletRequest request) {
        if (contentSanitizer == null) {
            throw new NullPointerException("contentSanitizer must not be null");
        }
        SanitizingHttpServletRequestWrapper wrapper = new SanitizingHttpServletRequestWrapper(request);
        wrapper.setSanitizer(contentSanitizer);
        wrapper.setBypassedHeaders(bypassedHeaders);
        wrapper.setBypassedParameters(bypassedParameters);
        return wrapper;
    }
}
