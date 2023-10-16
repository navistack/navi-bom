package org.navistack.boot.autoconfigure.captcha;

import com.aliyun.captcha20230305.Client;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import org.navistack.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.navistack.boot.autoconfigure.cloudservice.aliyun.captcha.AliyunCaptchaProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaProperties;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.navistack.framework.captcha.CaptchaTestInterceptor;
import org.navistack.framework.captcha.CaptchaTester;
import org.navistack.framework.captcha.CaptchaTesterComposite;
import org.navistack.framework.captcha.aliyun.AliyunCaptchaTester;
import org.navistack.framework.captcha.simplecaptcha.DefaultSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaTester;
import org.navistack.framework.captcha.tencentcloud.TencentCloudCaptchaTester;
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
@ConditionalOnClass(CaptchaTester.class)
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
        return new MappedInterceptor(properties.getUrlPatterns().stream().toArray(String[]::new), interceptor);
    }

    @Configuration
    @ConditionalOnClass(CaptchaClient.class)
    public static class TencentCloudCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnBean({TencentCloudCaptchaProperties.class, CaptchaClient.class})
        public TencentCloudCaptchaTester tencentCloudCaptchaTester(TencentCloudCaptchaProperties properties, CaptchaClient captchaClient) {
            return new TencentCloudCaptchaTester(properties.getCaptchaAppId(), properties.getAppSecretKey(), captchaClient);
        }
    }

    @Configuration
    @ConditionalOnClass(Client.class)
    public static class AliyunCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnBean({AliyunCaptchaProperties.class, Client.class})
        public AliyunCaptchaTester aliyunCaptchaTester(Client client) {
            return new AliyunCaptchaTester(client);
        }
    }

    @Configuration
    @ConditionalOnClass(SimpleCaptchaService.class)
    public static class SimpleCaptchaTesterConfiguration {
        @Bean
        @ConditionalOnMissingBean(SimpleCaptchaService.class)
        @ConditionalOnBean(ScopedCacheServiceBuilder.class)
        public DefaultSimpleCaptchaService defaultSimpleCaptchaService(ScopedCacheServiceBuilder cacheServiceBuilder) {
            return new DefaultSimpleCaptchaService(cacheServiceBuilder);
        }

        @Bean
        @ConditionalOnBean(SimpleCaptchaService.class)
        public SimpleCaptchaTester simpleCaptchaTester(SimpleCaptchaService simpleCaptchaService) {
            return new SimpleCaptchaTester(simpleCaptchaService);
        }
    }
}
