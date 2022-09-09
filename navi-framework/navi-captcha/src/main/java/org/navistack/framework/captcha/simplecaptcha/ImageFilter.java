package org.navistack.framework.captcha.simplecaptcha;

import java.awt.image.BufferedImage;

@FunctionalInterface
public interface ImageFilter {
    BufferedImage apply(BufferedImage image);
}
