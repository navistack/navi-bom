package org.navistack.boot.alibaba.cloud.autoconfigure.captcha;

import com.aliyun.captcha20230305.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class AlibabaCloudCaptchaConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AlibabaCloudCaptchaConfiguration.class));

    @Test
    void shouldNotRegisterCaptchaClientWhenEnabledPropertyIsFalse() {
        contextRunner
                .withPropertyValues(AlibabaCloudCaptchaProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(Client.class);
                });
    }

    @Test
    void shouldRegisterCaptchaClientWithBoundCredentialsWhenAccessKeyPropertiesAreSet() {
        contextRunner
                .withPropertyValues(
                        AlibabaCloudCaptchaProperties.PROPERTY_PREFIX + ".accessKeyId=ACCESS-KEY-ID",
                        AlibabaCloudCaptchaProperties.PROPERTY_PREFIX + ".accessKeySecret=ACCESS-KEY-SECRET",
                        AlibabaCloudCaptchaProperties.PROPERTY_PREFIX + ".endpoint=ENDPOINT"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(AlibabaCloudCaptchaProperties.class);
                    assertThat(context).hasSingleBean(Client.class);
                    Client client = context.getBean(Client.class);
                    String accessKeyId = client.getAccessKeyId();
                    assertThat(accessKeyId).isEqualTo("ACCESS-KEY-ID");
                    String accessKeySecret = client.getAccessKeySecret();
                    assertThat(accessKeySecret).isEqualTo("ACCESS-KEY-SECRET");
                });
    }
}
