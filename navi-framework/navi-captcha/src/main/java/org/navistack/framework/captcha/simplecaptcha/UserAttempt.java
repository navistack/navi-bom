package org.navistack.framework.captcha.simplecaptcha;

import lombok.Data;

@Data
public class UserAttempt {
    private String answer;
    private boolean validated;
}
