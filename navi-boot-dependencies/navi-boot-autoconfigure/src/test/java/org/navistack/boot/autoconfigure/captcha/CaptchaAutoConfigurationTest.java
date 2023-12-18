package org.navistack.boot.autoconfigure.captcha;

import com.aliyun.captcha20230305.Client;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import org.junit.jupiter.api.Test;
import org.navistack.boot.autoconfigure.cloudservice.aliyun.captcha.AliyunCaptchaProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha.TencentCloudCaptchaProperties;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.navistack.framework.captcha.CaptchaTesterComposite;
import org.navistack.framework.captcha.aliyun.AliyunCaptchaTester;
import org.navistack.framework.captcha.simplecaptcha.DefaultSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaTester;
import org.navistack.framework.captcha.tencentcloud.TencentCloudCaptchaTester;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.web.servlet.handler.MappedInterceptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CaptchaAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CaptchaAutoConfiguration.class));

    @Test
    void testWithProperties() {
        contextRunner.withBean(ScopedCacheServiceBuilder.class, () -> mock(ScopedCacheServiceBuilder.class))
                .withPropertyValues(CaptchaProperties.PROPERTIES_PREFIX + ".url-patterns=/login")
                .run(context -> {
                    assertThat(context).hasBean("captchaTestInterceptor");
                    MappedInterceptor interceptor = context.getBean("captchaTestInterceptor", MappedInterceptor.class);
                    String[] urlPatterns = interceptor.getIncludePathPatterns();
                    assertThat(urlPatterns).containsExactly("/login");
                });
    }

    @Test
    void testSimpleCaptchaTester() {
        contextRunner.withBean(ScopedCacheServiceBuilder.class, () -> mock(ScopedCacheServiceBuilder.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaService.class);
                    assertThat(context.getBean(SimpleCaptchaService.class)).isInstanceOf(DefaultSimpleCaptchaService.class);
                    assertThat(context).hasSingleBean(SimpleCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasSize(1).hasOnlyElementsOfType(SimpleCaptchaTester.class);
                });
    }

    @Test
    void testAliyunCaptchaTester() {
        contextRunner.withBean(ScopedCacheServiceBuilder.class, () -> mock(ScopedCacheServiceBuilder.class))
                .withBean(Client.class, () -> mock(Client.class))
                .withBean(AliyunCaptchaProperties.class, () -> mock(AliyunCaptchaProperties.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasAtLeastOneElementOfType(AliyunCaptchaTester.class);
                });
    }

    @Test
    void testTencentCloudCaptchaTester() {
        contextRunner.withBean(ScopedCacheServiceBuilder.class, () -> mock(ScopedCacheServiceBuilder.class))
                .withBean(CaptchaClient.class, () -> mock(CaptchaClient.class))
                .withBean(TencentCloudCaptchaProperties.class, () -> mock(TencentCloudCaptchaProperties.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(TencentCloudCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasAtLeastOneElementOfType(TencentCloudCaptchaTester.class);
                });
    }
}
