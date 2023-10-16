package org.navistack.boot.autoconfigure.cloudservice.aliyun.sms;

import com.aliyun.dysmsapi20170525.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class AliyunSmsConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AliyunSmsConfiguration.class));

    @Test
    void testDisabled() {
        contextRunner
                .withPropertyValues(AliyunSmsProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(Client.class);
                });
    }

    @Test
    void testProperties() {
        contextRunner
                .withPropertyValues(
                        AliyunSmsProperties.PROPERTY_PREFIX + ".accessKeyId=ACCESS-KEY-ID",
                        AliyunSmsProperties.PROPERTY_PREFIX + ".accessKeySecret=ACCESS-KEY-SECRET",
                        AliyunSmsProperties.PROPERTY_PREFIX + ".endpoint=ENDPOINT"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunSmsProperties.class);
                    assertThat(context).hasSingleBean(Client.class);
                    Client client = context.getBean(Client.class);
                    String accessKeyId = client.getAccessKeyId();
                    assertThat(accessKeyId).isEqualTo("ACCESS-KEY-ID");
                    String accessKeySecret = client.getAccessKeySecret();
                    assertThat(accessKeySecret).isEqualTo("ACCESS-KEY-SECRET");
                });
    }
}
