package org.navistack.framework.captcha;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

public class CaptchaTesterComposite implements CaptchaTester {
    @Getter
    private final Collection<CaptchaTester> captchaTesters = new ArrayList<>();

    public CaptchaTesterComposite(Collection<? extends CaptchaTester> captchaTesters) {
        this.captchaTesters.addAll(captchaTesters);
    }

    @Override
    public boolean test(HttpServletRequest request) {
        for (CaptchaTester captchaTester : captchaTesters) {
            if (captchaTester.test(request)) {
                return true;
            }
        }
        return false;
    }
}
