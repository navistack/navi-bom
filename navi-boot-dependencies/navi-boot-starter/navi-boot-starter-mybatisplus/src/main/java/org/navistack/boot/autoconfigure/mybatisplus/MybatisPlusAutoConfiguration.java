package org.navistack.boot.autoconfigure.mybatisplus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.navistack.framework.mybatisplus.DefaultSqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration.class)
public class MybatisPlusAutoConfiguration implements ConfigurationCustomizer {
    @Bean
    DefaultSqlInjector defaultSqlInjector() {
        return new DefaultSqlInjector();
    }

    @Override
    public void customize(MybatisConfiguration configuration) {
        configuration.setDefaultEnumTypeHandler(EnumDelegatingTypeHandler.class);
    }
}
