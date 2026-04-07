package org.navistack.boot.alibaba.cloud.autoconfigure.sms;

import com.aliyun.dysmsapi20170525.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class AlibabaCloudSmsConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AlibabaCloudSmsConfiguration.class));

    @Test
    void shouldNotRegisterSmsClientWhenEnabledPropertyIsFalse() {
        contextRunner
                .withPropertyValues(AlibabaCloudSmsProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(Client.class);
                });
    }

    @Test
    void shouldRegisterSmsClientWithBoundCredentialsWhenAccessKeyPropertiesAreSet() {
        contextRunner
                .withPropertyValues(
                        AlibabaCloudSmsProperties.PROPERTY_PREFIX + ".accessKeyId=ACCESS-KEY-ID",
                        AlibabaCloudSmsProperties.PROPERTY_PREFIX + ".accessKeySecret=ACCESS-KEY-SECRET",
                        AlibabaCloudSmsProperties.PROPERTY_PREFIX + ".endpoint=ENDPOINT"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(AlibabaCloudSmsProperties.class);
                    assertThat(context).hasSingleBean(Client.class);
                    Client client = context.getBean(Client.class);
                    String accessKeyId = client.getAccessKeyId();
                    assertThat(accessKeyId).isEqualTo("ACCESS-KEY-ID");
                    String accessKeySecret = client.getAccessKeySecret();
                    assertThat(accessKeySecret).isEqualTo("ACCESS-KEY-SECRET");
                });
    }
}
