package org.navistack.boot.autoconfigure.captcha;

import com.aliyuncs.IAcsClient;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import org.navistack.boot.autoconfigure.cache.KvCacheAutoConfiguration;
import org.navistack.boot.autoconfigure.captcha.aliyun.AliyunAfsProperties;
import org.navistack.boot.autoconfigure.captcha.tencentcloud.TencentCloudCaptchaProperties;
import org.navistack.framework.cache.KvCacheService;
import org.navistack.framework.captcha.CaptchaTestInterceptor;
import org.navistack.framework.captcha.CaptchaTester;
import org.navistack.framework.captcha.CaptchaTesterComposite;
import org.navistack.framework.captcha.afs.AfsCaptchaTester;
import org.navistack.framework.captcha.simplecaptcha.KaptchaSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaTester;
import org.navistack.framework.captcha.tcc.TccCaptchaTester;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.Collection;

@Configuration
@EnableConfigurationProperties(org.navistack.boot.autoconfigure.captcha.CaptchaProperties.class)
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter(KvCacheAutoConfiguration.class)
public class CaptchaAutoConfiguration {
    @Bean
    public CaptchaTesterComposite CaptchaTesterComposite(Collection<CaptchaTester> captchaTesters) {
        return new CaptchaTesterComposite(captchaTesters);
    }

    @Bean
    public MappedInterceptor captchaTestInterceptor(CaptchaTesterComposite captchaTesterComposite) {
        return new MappedInterceptor(null, new CaptchaTestInterceptor(captchaTesterComposite));
    }

    @Bean
    @ConditionalOnBean(CaptchaClient.class)
    public TccCaptchaTester tccCaptchaServiceAdaptor(TencentCloudCaptchaProperties properties, CaptchaClient captchaClient) {
        return new TccCaptchaTester(properties.getAppId(), properties.getAppSecret(), captchaClient);
    }

    @Bean
    @ConditionalOnBean(IAcsClient.class)
    public AfsCaptchaTester afsCaptchaServiceAdaptor(AliyunAfsProperties properties, IAcsClient acsClient) {
        return new AfsCaptchaTester(properties.getAppKey(), properties.getScene(), acsClient);
    }

    @Bean
    @ConditionalOnMissingBean(SimpleCaptchaService.class)
    public KaptchaSimpleCaptchaService kaptchaSimpleCaptchaService(KvCacheService kvCacheService) {
        return new KaptchaSimpleCaptchaService(kvCacheService);
    }

    @Bean
    @ConditionalOnBean(SimpleCaptchaService.class)
    public SimpleCaptchaTester simpleCaptchaServiceAdaptor(SimpleCaptchaService simpleCaptchaService) {
        return new SimpleCaptchaTester(simpleCaptchaService);
    }
}
