package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

import java.util.StringJoiner;

@UtilityClass
public class Strings {
    public String strip(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int i = indexOfNonWhitespace(str);
        if (i == str.length()) {
            return "";
        }
        int j = lastIndexOfNonWhitespace(str);
        return str.substring(i, j + 1);
    }

    public String stripLeading(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int idx = indexOfNonWhitespace(str);
        if (idx == str.length()) {
            return "";
        }
        return str.substring(idx);
    }

    public String stripTrailing(String str) {
        if (!hasLength(str)) {
            return str;
        }
        int idx = lastIndexOfNonWhitespace(str);
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

    public String[] split(String str, String regex) {
        return hasLength(str) ? str.split(regex) : new String[]{};
    }

    public String[] split(String str, String regex, int limit) {
        return hasLength(str) ? str.split(regex, limit) : new String[]{};
    }

    public String join(String delimiter, String... strings) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (String string : strings) {
            joiner.add(string);
        }
        return joiner.toString();
    }

    private int indexOfNonWhitespace(String str) {
        int i = 0;
        int len = str.length();
        while (i < len) {
            char ch = str.charAt(i);
            if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
                break;
            }
            ++i;
        }
        return i;
    }

    private int lastIndexOfNonWhitespace(String str) {
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
        return indexOfNonWhitespace(str) < str.length();
    }
}
