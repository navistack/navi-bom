package org.navistack.framework.captcha.simplecaptcha.textgenerators;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.navistack.framework.captcha.simplecaptcha.TextGenerator;
import org.navistack.framework.random.RandomGenerator;
import org.navistack.framework.random.UnsecureRandomGenerator;

public class AlphanumericTextGenerator implements TextGenerator {
    private static final String DEFAULT_CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Getter
    @Setter
    @NonNull
    private String charset = DEFAULT_CHAR_SET;

    @Getter
    @Setter
    private int length = 5;

    @Getter
    @Setter
    @NonNull
    private RandomGenerator random = new UnsecureRandomGenerator();

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();

        int charsetLength = charset.length();
        for (int i = 0; i < length; ++i) {
            int charIndex = random.nextInt(charsetLength);
            builder.append(charset.charAt(charIndex));
        }

        return builder.toString();
    }
}
