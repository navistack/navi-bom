package org.navistack.framework.security;

public final class PlaceholderContentSanitizer implements ContentSanitizer {
    private static final PlaceholderContentSanitizer instance = new PlaceholderContentSanitizer();

    private PlaceholderContentSanitizer() {
    }

    @Override
    public String sanitize(String content) {
        return content;
    }

    public static PlaceholderContentSanitizer of() {
        return instance;
    }
}
