package org.navistack.smoketest.captcha;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.captcha.simplecaptcha.DefaultSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.TextGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleCaptchaApplication {

    @Bean
    public SimpleCaptchaService simpleCaptchaService(CacheService cacheService) {
        DefaultSimpleCaptchaService service = new DefaultSimpleCaptchaService(cacheService);
        service.setTextGenerator(() -> "FIXED");
        return service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleCaptchaApplication.class, args);
    }

}
