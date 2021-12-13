package org.navistack.framework.captcha.simplecaptcha;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAttempt implements Serializable {
    private static final long serialVersionUID = 1L;

    private String answer;
    private boolean validated;
}
