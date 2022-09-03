package org.navistack.boot.autoconfigure.captcha;

import com.aliyuncs.IAcsClient;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import org.junit.jupiter.api.Test;
import org.navistack.boot.autoconfigure.cloudservice.aliyun.AliyunAfsProperties;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.TencentCloudCaptchaProperties;
import org.navistack.framework.cache.CacheService;
import org.navistack.framework.captcha.CaptchaTesterComposite;
import org.navistack.framework.captcha.afs.AfsCaptchaTester;
import org.navistack.framework.captcha.simplecaptcha.KaptchaSimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaService;
import org.navistack.framework.captcha.simplecaptcha.SimpleCaptchaTester;
import org.navistack.framework.captcha.tcc.TccCaptchaTester;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class CaptchaAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CaptchaAutoConfiguration.class));

    @Test
    void testSimpleCaptchaTester() {
        contextRunner.withBean(CacheService.class, () -> mock(CacheService.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(SimpleCaptchaService.class);
                    assertThat(context.getBean(SimpleCaptchaService.class)).isInstanceOf(KaptchaSimpleCaptchaService.class);
                    assertThat(context).hasSingleBean(SimpleCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasSize(1).hasOnlyElementsOfType(SimpleCaptchaTester.class);
                });
    }

    @Test
    void testAfsCaptchaTester() {
        contextRunner.withBean(CacheService.class, () -> mock(CacheService.class))
                .withBean(IAcsClient.class, () -> mock(IAcsClient.class))
                .withBean(AliyunAfsProperties.class, () -> mock(AliyunAfsProperties.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(AfsCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasAtLeastOneElementOfType(AfsCaptchaTester.class);
                });
    }

    @Test
    void testTccCaptchaTester() {
        contextRunner.withBean(CacheService.class, () -> mock(CacheService.class))
                .withBean(CaptchaClient.class, () -> mock(CaptchaClient.class))
                .withBean(TencentCloudCaptchaProperties.class, () -> mock(TencentCloudCaptchaProperties.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(TccCaptchaTester.class);
                    assertThat(context).hasSingleBean(CaptchaTesterComposite.class);
                    assertThat(context.getBean(CaptchaTesterComposite.class)).extracting(CaptchaTesterComposite::getCaptchaTesters).asList().hasAtLeastOneElementOfType(TccCaptchaTester.class);
                });
    }
}