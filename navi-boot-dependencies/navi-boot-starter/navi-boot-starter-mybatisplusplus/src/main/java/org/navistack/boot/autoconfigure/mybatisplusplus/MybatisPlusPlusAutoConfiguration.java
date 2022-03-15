package org.navistack.boot.autoconfigure.mybatisplusplus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.navistack.framework.mybatisplusplus.ExtendedSqlInjector;
import org.navistack.framework.mybatisplusplus.typehandlers.EnumDelegatingTypeHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(MybatisPlusAutoConfiguration.class)
public class MybatisPlusPlusAutoConfiguration implements ConfigurationCustomizer {
    @Bean
    ExtendedSqlInjector extendedSqlInjector() {
        return new ExtendedSqlInjector();
    }

    @Override
    public void customize(MybatisConfiguration configuration) {
        configuration.setDefaultEnumTypeHandler(EnumDelegatingTypeHandler.class);
    }
}
