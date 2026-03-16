package org.navistack.boot.autoconfigure.sms;

import com.tencentcloudapi.sms.v20210111.SmsClient;
import org.junit.jupiter.api.Test;
import org.navistack.boot.autoconfigure.cloudservice.tencentcloud.sms.TencentCloudSmsProperties;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.tencentcloud.TencentCloudShortMessageServiceProvider;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TencentCloudShortMessageConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TencentCloudShortMessageConfiguration.class));

    @Test
    void testDefault() {
        contextRunner.run(context -> {
            assertThat(context).doesNotHaveBean(TencentCloudShortMessageServiceProvider.class);
        });
    }

    @Test
    void testWithServiceProvider() {
        contextRunner.withBean(SmsClient.class, () -> mock(SmsClient.class))
                .withBean(TencentCloudSmsProperties.class, () -> mock(TencentCloudSmsProperties.class))
                .withBean(ShortMessageServiceProvider.class, () -> mock(ShortMessageServiceProvider.class))
                .run(context -> {
                    assertThat(context).doesNotHaveBean(TencentCloudShortMessageServiceProvider.class);
                });
    }

    @Test
    void testWithTencentCloudShortMessageServiceProvider() {
        TencentCloudShortMessageServiceProvider serviceProvider = mock(TencentCloudShortMessageServiceProvider.class);
        contextRunner.withBean(SmsClient.class, () -> mock(SmsClient.class))
                .withBean(TencentCloudSmsProperties.class, () -> mock(TencentCloudSmsProperties.class))
                .withBean(TencentCloudShortMessageServiceProvider.class, () -> serviceProvider)
                .run(context -> {
                    assertThat(context).hasSingleBean(TencentCloudShortMessageServiceProvider.class);
                    assertThat(context.getBean(TencentCloudShortMessageServiceProvider.class)).isSameAs(serviceProvider);
                });
    }

    @Test
    void testWithSmsClient() {
        contextRunner.withBean(SmsClient.class, () -> mock(SmsClient.class))
                .withBean(TencentCloudSmsProperties.class, () -> mock(TencentCloudSmsProperties.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(TencentCloudShortMessageServiceProvider.class);
                });
    }
}
