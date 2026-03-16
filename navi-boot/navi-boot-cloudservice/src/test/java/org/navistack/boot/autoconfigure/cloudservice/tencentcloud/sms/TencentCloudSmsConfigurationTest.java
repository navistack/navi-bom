package org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class TencentCloudSmsConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TencentCloudSmsConfiguration.class));

    @Test
    void testDisabled() {
        contextRunner
                .withPropertyValues(TencentCloudSmsProperties.PROPERTY_PREFIX + ".enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(SmsClient.class);
                });
    }

    @Test
    void testProperties() {
        contextRunner
                .withPropertyValues(
                        TencentCloudSmsProperties.PROPERTY_PREFIX + ".client.region=REGION",
                        TencentCloudSmsProperties.PROPERTY_PREFIX + ".client.credential.secretId=SECRET-ID",
                        TencentCloudSmsProperties.PROPERTY_PREFIX + ".client.credential.secretKey=SECRET-KEY",
                        TencentCloudSmsProperties.PROPERTY_PREFIX + ".sdk-app-id=SDK-APP-ID",
                        TencentCloudSmsProperties.PROPERTY_PREFIX + ".app-key=APP-KEY"
                )
                .run(context -> {
                    assertThat(context).hasSingleBean(TencentCloudSmsProperties.class);
                    assertThat(context).hasSingleBean(SmsClient.class);
                    SmsClient client = context.getBean(SmsClient.class);
                    assertThat(client).extracting(SmsClient::getRegion).isEqualTo("REGION");
                    Credential credential = client.getCredential();
                    assertThat(credential).extracting(Credential::getSecretId).isEqualTo("SECRET-ID");
                    assertThat(credential).extracting(Credential::getSecretKey).isEqualTo("SECRET-KEY");
                });
    }
}
