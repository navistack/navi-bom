package org.navistack.boot.autoconfigure.sms;

import org.navistack.framework.sms.ConfigurableShortMessageTemplateRegistration;
import org.navistack.framework.sms.DefaultShortMessageService;
import org.navistack.framework.sms.ShortMessageService;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.ShortMessageTemplateRegistration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnClass(ShortMessageService.class)
@EnableConfigurationProperties(ShortMessageProperties.class)
@Import({
        AliyunShortMessageConfiguration.class,
        TencentCloudShortMessageConfiguration.class
})
public class ShortMessageAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ShortMessageTemplateRegistration shortMessageTemplateRegistration(ShortMessageProperties properties) {
        ConfigurableShortMessageTemplateRegistration registration = new ConfigurableShortMessageTemplateRegistration();
        registration.addTemplates(properties.getTemplates());
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean({ShortMessageTemplateRegistration.class, ShortMessageServiceProvider.class})
    public ShortMessageService shortMessageService(
            ShortMessageTemplateRegistration templateRegistration,
            ShortMessageServiceProvider serviceProvider
    ) {
        return new DefaultShortMessageService(templateRegistration, serviceProvider);
    }

}
