package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.captcha;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.common.Credential;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class TencentCloudCaptchaConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TencentCloudCaptchaConfiguration.class));

    @Test
    void testDisabled() {
        contextRunner
                .withPropertyValues(TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(CaptchaClient.class);
                });
    }

    @Test
    void testProperties() {
        contextRunner
                .withPropertyValues(
                        TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".client.region=REGION",
                        TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".client.credential.secretId=SECRET-ID",
                        TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".client.credential.secretKey=SECRET-KEY",
                        TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".captcha-app-id=0000000000",
                        TencentCloudCaptchaProperties.PROPERTY_PREFIX + ".app-secret-key=APP-SECRET-KEY"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(TencentCloudCaptchaProperties.class);
                    assertThat(context).hasSingleBean(CaptchaClient.class);
                    CaptchaClient client = context.getBean(CaptchaClient.class);
                    assertThat(client).extracting(CaptchaClient::getRegion).isEqualTo("REGION");
                    Credential credential = client.getCredential();
                    assertThat(credential).extracting(Credential::getSecretId).isEqualTo("SECRET-ID");
                    assertThat(credential).extracting(Credential::getSecretKey).isEqualTo("SECRET-KEY");
                });
    }
}
