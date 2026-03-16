package org.navistack.boot.autoconfigure.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.aliyun.AliyunShortMessageServiceProvider;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AliyunShortMessageConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(AliyunShortMessageConfiguration.class));

    @Test
    void testDefault() {
        contextRunner.run(context -> {
            assertThat(context).doesNotHaveBean(AliyunShortMessageServiceProvider.class);
        });
    }

    @Test
    void testWithServiceProvider() {
        contextRunner.withBean(Client.class, () -> mock(Client.class))
                .withBean(ShortMessageServiceProvider.class, () -> mock(ShortMessageServiceProvider.class))
                .run(context -> {
                    assertThat(context).doesNotHaveBean(AliyunShortMessageServiceProvider.class);
                });
    }

    @Test
    void testWithAliyunShortMessageServiceProvider() {
        AliyunShortMessageServiceProvider serviceProvider = mock(AliyunShortMessageServiceProvider.class);
        contextRunner.withBean(Client.class, () -> mock(Client.class))
                .withBean(AliyunShortMessageServiceProvider.class, () -> serviceProvider)
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunShortMessageServiceProvider.class);
                    assertThat(context.getBean(AliyunShortMessageServiceProvider.class)).isSameAs(serviceProvider);
                });
    }

    @Test
    void testWithClient() {
        contextRunner.withBean(Client.class, () -> mock(Client.class))
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunShortMessageServiceProvider.class);
                    AliyunShortMessageServiceProvider serviceProvider = context.getBean(AliyunShortMessageServiceProvider.class);
                    assertThat(serviceProvider.getObjectMapper()).isNotNull();
                });
    }

    @Test
    void testWithObjectMapper() {
        ObjectMapper objectMapper = mock(ObjectMapper.class);
        contextRunner.withBean(Client.class, () -> mock(Client.class))
                .withBean(ObjectMapper.class, () -> objectMapper)
                .run(context -> {
                    assertThat(context).hasSingleBean(AliyunShortMessageServiceProvider.class);
                    AliyunShortMessageServiceProvider serviceProvider = context.getBean(AliyunShortMessageServiceProvider.class);
                    assertThat(serviceProvider.getObjectMapper()).isSameAs(objectMapper);
                });
    }
}
