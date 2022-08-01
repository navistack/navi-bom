package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {
    public String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int i = indexOfLeadingNonWhitespace(str);
        if (i == str.length()) {
            return "";
        }
        int j = indexOfTrailingNonWhitespace(str);
        return str.substring(i, j + 1);
    }

    public String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int idx = indexOfLeadingNonWhitespace(str);
        if (idx == str.length()) {
            return "";
        }
        return str.substring(idx);
    }

    public String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int idx = indexOfTrailingNonWhitespace(str);
        if (idx == -1) {
            return "";
        }
        return str.substring(0, idx + 1);
    }

    public boolean hasLength(String str) {
        return str != null && !str.isEmpty();
    }

    public boolean hasText(String str) {
        return hasLength(str) && containsText(str);
    }

    private int indexOfLeadingNonWhitespace(String str) {
        int i = 0, len = str.length();
        while (i < len) {
            char ch = str.charAt(i);
            if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
                break;
            }
            ++i;
        }
        return i;
    }

    private int indexOfTrailingNonWhitespace(String str) {
        int i = str.length() - 1;
        while (i >= 0) {
            char ch = str.charAt(i);
            if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
                break;
            }
            --i;
        }
        return i;
    }

    private boolean containsText(String str) {
        return indexOfLeadingNonWhitespace(str) < str.length();
    }
}
