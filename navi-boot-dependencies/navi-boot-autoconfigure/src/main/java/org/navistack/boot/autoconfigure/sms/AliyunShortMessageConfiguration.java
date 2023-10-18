package org.navistack.boot.autoconfigure.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.navistack.framework.sms.ShortMessageServiceProvider;
import org.navistack.framework.sms.aliyun.AliyunShortMessageServiceProvider;
import org.navistack.framework.utils.ApplicationContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Client.class)
public class AliyunShortMessageConfiguration implements ApplicationContextAware {
    @Setter
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnBean(Client.class)
    @ConditionalOnMissingBean({ShortMessageServiceProvider.class, AliyunShortMessageServiceProvider.class})
    public AliyunShortMessageServiceProvider aliyunShortMessageServiceProvider(Client client) {
        ObjectMapper objectMapper = ApplicationContexts.getBean(applicationContext, ObjectMapper.class);
        return objectMapper == null
                ? new AliyunShortMessageServiceProvider(client)
                : new AliyunShortMessageServiceProvider(client, objectMapper);
    }
}
