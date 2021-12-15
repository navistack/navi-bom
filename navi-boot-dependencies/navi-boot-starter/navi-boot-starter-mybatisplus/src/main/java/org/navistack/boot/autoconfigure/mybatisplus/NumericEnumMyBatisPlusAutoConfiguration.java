package org.navistack.boot.autoconfigure.mybatisplus;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(MybatisPlusAutoConfiguration.class)
public class NumericEnumMyBatisPlusAutoConfiguration implements ConfigurationCustomizer {
    @Override
    public void customize(MybatisConfiguration configuration) {
        configuration.setDefaultEnumTypeHandler(EnumDelegatingTypeHandler.class);
    }
}
