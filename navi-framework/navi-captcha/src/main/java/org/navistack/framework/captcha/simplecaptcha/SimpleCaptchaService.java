package org.navistack.framework.captcha.simplecaptcha;

import java.awt.image.RenderedImage;

public interface SimpleCaptchaService {
    /**
     * Start a captcha challenge.
     *
     * @return Challenge ID
     */
    String challenge();

    /**
     * Answer a challenge.
     *
     * @return Response ID
     */
    UserAttemptResult answer(String challenge, String answer);

    boolean validate(String ticket);

    RenderedImage draw(String challenge);
}
