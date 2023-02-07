package org.navistack.framework.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class SanitizingHttpServletRequestWrapper extends HttpServletRequestWrapper {
    @Getter
    @Setter
    private ContentSanitizer sanitizer = PlaceholderContentSanitizer.of();

    @Getter
    private final Set<String> bypassedHeaders = new HashSet<>();

    @Getter
    private final Set<String> bypassedParameters = new HashSet<>();

    public SanitizingHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        return sanitizeHeader(name, header);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        Enumeration<String> headers = super.getHeaders(name);
        return new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() {
                return headers.hasMoreElements();
            }

            @Override
            public String nextElement() {
                String header = headers.nextElement();
                return sanitizeParameter(name, header);
            }
        };
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        return sanitizeParameter(name, parameter);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        if (parameterMap == null || parameterMap.isEmpty()) {
            return parameterMap;
        }
        Map<String, String[]> sanitizedParameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> parameterEntry : parameterMap.entrySet()) {
            String parameterKey = parameterEntry.getKey();
            String[] parameterValues = parameterEntry.getValue();
            sanitizedParameterMap.put(parameterKey, sanitizeParameters(parameterKey, parameterValues));
        }
        return sanitizedParameterMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        return sanitizeParameters(name, parameterValues);
    }

    public void addBypassedHeader(String header) {
        this.bypassedHeaders.add(header);
    }

    public void setBypassedHeaders(Collection<String> headers) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        this.bypassedHeaders.addAll(headers);
    }

    public void addBypassedParameter(String parameter) {
        this.bypassedParameters.add(parameter);
    }

    public void setBypassedParameters(Collection<String> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return;
        }
        this.bypassedParameters.addAll(parameters);
    }

    private String sanitizeHeader(String name, String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        if (bypassedHeaders.contains(name)) {
            return value;
        }
        return sanitizer.sanitize(value);
    }

    private String sanitizeParameter(String name, String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        if (bypassedParameters.contains(name)) {
            return value;
        }
        return sanitizer.sanitize(value);
    }

    private String[] sanitizeParameters(String name, String[] values) {
        if (values == null || values.length <= 0) {
            return values;
        }
        String[] sanitizedValues = new String[values.length];
        for (int i = 0; i < values.length; ++i) {
            String parameterValue = values[i];
            sanitizedValues[i] = sanitizeParameter(name, parameterValue);
        }
        return sanitizedValues;
    }
}
