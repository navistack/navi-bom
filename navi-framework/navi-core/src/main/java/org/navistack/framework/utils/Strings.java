package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {
    public String trimWhitespace(String str) {
        return hasLength(str)
                ? str.substring(indexOfLeadingNonWhitespace(str), indexOfTrailingNonWhitespace(str))
                : str
                ;
    }

    public String trimLeadingWhitespace(String str) {
        return hasLength(str)
                ? str.substring(indexOfLeadingNonWhitespace(str))
                : str
                ;
    }

    public String trimTrailingWhitespace(String str) {
        return hasLength(str)
                ? str.substring(0, indexOfTrailingNonWhitespace(str) + 1)
                : str
                ;
    }

    public boolean hasLength(String str) {
        return str != null && !str.isEmpty();
    }

    public boolean hasText(String str) {
        return hasLength(str) && containsText(str);
    }

    private int indexOfLeadingNonWhitespace(String str) {
        int i = 0;
        int len = str.length();
        while (i < len) {
            char ch = str.charAt(i);
            if (Character.isWhitespace(ch)) {
                ++i;
            } else {
                break;
            }
        }
        return i;
    }

    private int indexOfTrailingNonWhitespace(String str) {
        int i = str.length() - 1;
        while (i >= 0) {
            char ch = str.charAt(i);
            if (Character.isWhitespace(ch)) {
                --i;
            } else {
                break;
            }
        }
        return i;
    }

    private boolean containsText(CharSequence str) {
        int len = str.length();
        for (int i = 0; i < len; ++i) {
            char ch = str.charAt(i);
            if (!Character.isWhitespace(ch)) {
                return true;
            }
        }
        return false;
    }
}
