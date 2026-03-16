package org.navistack.boot.autoconfigure.sms;

import org.junit.jupiter.api.Test;
import org.navistack.framework.sms.ConfigurableShortMessageTemplateRegistration;
import org.navistack.framework.sms.ShortMessageService;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.ShortMessageTemplateRegistration;
import org.navistack.framework.sms.SimpleShortMessageTemplate;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ShortMessageAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ShortMessageAutoConfiguration.class));

    @Test
    void testDefault() {
        contextRunner.run(context -> {
            assertThat(context).doesNotHaveBean(ShortMessageService.class)
                    .hasSingleBean(ShortMessageTemplateRegistration.class)
                    .getBean(ShortMessageTemplateRegistration.class).isInstanceOf(ConfigurableShortMessageTemplateRegistration.class);
        });
    }

    @Test
    void testWithServiceProvider() {
        contextRunner.withBean(ShortMessageServiceProvider.class, () -> mock(ShortMessageServiceProvider.class))
                .run(context -> {
            assertThat(context).hasSingleBean(ShortMessageService.class);
        });
    }

    @Test
    void testWithProperties() {
        contextRunner.withPropertyValues(
                ShortMessageProperties.PROPERTIES_PREFIX+".templates[0].code=CODE-1",
                ShortMessageProperties.PROPERTIES_PREFIX+".templates[0].message=MESSAGE-1",
                ShortMessageProperties.PROPERTIES_PREFIX+".templates[1].code=CODE-2",
                ShortMessageProperties.PROPERTIES_PREFIX+".templates[1].message=MESSAGE-2"
                )
                .run(context -> {
            assertThat(context).hasSingleBean(ShortMessageTemplateRegistration.class)
                    .getBean(ShortMessageTemplateRegistration.class).isInstanceOf(ConfigurableShortMessageTemplateRegistration.class);
                    ConfigurableShortMessageTemplateRegistration registration = context.getBean(ConfigurableShortMessageTemplateRegistration.class);
                    assertThat(registration.getTemplate("CODE-1")).isEqualTo(SimpleShortMessageTemplate.of("CODE-1", "MESSAGE-1"));
                    assertThat(registration.getTemplate("CODE-2")).isEqualTo(SimpleShortMessageTemplate.of("CODE-2", "MESSAGE-2"));
                });
    }

}
