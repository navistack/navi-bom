package org.navistack.framework.captcha.simplecaptcha;

import java.awt.image.BufferedImage;

public interface TextRender {
    BufferedImage render(String text);
}
