package org.navistack.boot.autoconfigure.context;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import static org.assertj.core.api.Assertions.assertThat;

class MessageSourceAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MessageSourceAutoConfiguration.class));

    @Test
    void testDefault() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(MessageSource.class);
            assertThat(context.getBean(MessageSource.class)).isInstanceOf(ReloadableResourceBundleMessageSource.class);
            ReloadableResourceBundleMessageSource messageSource = context.getBean(ReloadableResourceBundleMessageSource.class);
            assertThat(messageSource.getBasenameSet()).contains("classpath:navi/i18n/messages", "classpath:i18n/messages");
        });
    }
}
