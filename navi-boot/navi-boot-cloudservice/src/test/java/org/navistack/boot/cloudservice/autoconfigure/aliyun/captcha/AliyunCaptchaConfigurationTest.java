package org.navistack.boot.cloudservice.autoconfigure.aliyun.captcha;

import com.aliyun.captcha20230305.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class AliyunCaptchaConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AliyunCaptchaConfiguration.class));

    @Test
    void shouldNotRegisterAliyunCaptchaClientWhenEnabledPropertyIsFalse() {
        contextRunner
                .withPropertyValues(AliyunCaptchaProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(Client.class);
                });
    }

    @Test
    void shouldRegisterAliyunCaptchaClientWithBoundCredentialsWhenAccessKeyPropertiesAreSet() {
        contextRunner
                .withPropertyValues(
                        AliyunCaptchaProperties.PROPERTY_PREFIX + ".accessKeyId=ACCESS-KEY-ID",
                        AliyunCaptchaProperties.PROPERTY_PREFIX + ".accessKeySecret=ACCESS-KEY-SECRET",
                        AliyunCaptchaProperties.PROPERTY_PREFIX + ".endpoint=ENDPOINT"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunCaptchaProperties.class);
                    assertThat(context).hasSingleBean(Client.class);
                    Client client = context.getBean(Client.class);
                    String accessKeyId = client.getAccessKeyId();
                    assertThat(accessKeyId).isEqualTo("ACCESS-KEY-ID");
                    String accessKeySecret = client.getAccessKeySecret();
                    assertThat(accessKeySecret).isEqualTo("ACCESS-KEY-SECRET");
                });
    }
}
