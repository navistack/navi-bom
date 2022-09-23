package org.navistack.boot.autoconfigure.captcha;

import com.aliyuncs.IAcsClient;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import org.navistack.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.navistack.boot.autoconfigure.cloudservice.aliyun.AliyunAfsProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaProperties;
import org.navistack.framework.cache.CacheService;
import org.navistack.framework.captcha.CaptchaTestInterceptor;
import org.navistack.framework.captcha.CaptchaTester;
import org.navistack.framework.captcha.CaptchaTesterComposite;
import org.navistack.framework.captcha.afs.AfsCaptchaTester;
import org.navistack.framework.captcha.simplecaptcha.DefaultSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaTester;
import org.navistack.framework.captcha.tcc.TccCaptchaTester;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.Collection;

@Configuration
@EnableConfigurationProperties(CaptchaProperties.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter(CacheAutoConfiguration.class)
public class CaptchaAutoConfiguration {
    @Bean
    @ConditionalOnBean(CaptchaTester.class)
    public CaptchaTesterComposite CaptchaTesterComposite(Collection<CaptchaTester> captchaTesters) {
        return new CaptchaTesterComposite(captchaTesters);
    }

    @Bean
    @ConditionalOnBean(CaptchaTesterComposite.class)
    public MappedInterceptor captchaTestInterceptor(CaptchaTesterComposite captchaTesterComposite, CaptchaProperties properties) {
        CaptchaTestInterceptor interceptor = new CaptchaTestInterceptor(captchaTesterComposite);
        interceptor.setCheckForAnnotation(properties.isCheckForAnnotation());
        return new MappedInterceptor(null, interceptor);
    }

    @Configuration
    @ConditionalOnClass(CaptchaClient.class)
    public static class TccCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnBean({TencentCloudCaptchaProperties.class, CaptchaClient.class})
        public TccCaptchaTester tccCaptchaTester(TencentCloudCaptchaProperties properties, CaptchaClient captchaClient) {
            return new TccCaptchaTester(properties.getCaptchaAppId(), properties.getAppSecretKey(), captchaClient);
        }
    }

    @Configuration
    @ConditionalOnClass(IAcsClient.class)
    public static class AfsCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnBean({AliyunAfsProperties.class, IAcsClient.class})
        public AfsCaptchaTester afsCaptchaTester(AliyunAfsProperties properties, IAcsClient acsClient) {
            return new AfsCaptchaTester(properties.getAppKey(), properties.getScene(), acsClient);
        }
    }

    @Configuration
    @ConditionalOnClass(SimpleCaptchaService.class)
    public static class SimpleCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnMissingBean(SimpleCaptchaService.class)
        @ConditionalOnBean(CacheService.class)
        public DefaultSimpleCaptchaService defaultSimpleCaptchaService(CacheService cacheService) {
            return new DefaultSimpleCaptchaService(cacheService);
        }

        @Bean
        @ConditionalOnBean(SimpleCaptchaService.class)
        public SimpleCaptchaTester simpleCaptchaTester(SimpleCaptchaService simpleCaptchaService) {
            return new SimpleCaptchaTester(simpleCaptchaService);
        }
    }
}
