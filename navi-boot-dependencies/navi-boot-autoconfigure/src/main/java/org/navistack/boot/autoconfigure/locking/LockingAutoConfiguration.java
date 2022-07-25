package org.navistack.boot.autoconfigure.locking;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.locking.PessimisticLock;
import org.navistack.framework.locking.PessimisticLockAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LockingProperties.class)
@ConditionalOnProperty(value = LockingProperties.PROPERTIES_PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class LockingAutoConfiguration {
    @Configuration
    @ConditionalOnClass(PessimisticLock.class)
    public static class PessimisticLockConfiguration {
        @Bean
        @ConditionalOnMissingBean(PessimisticLockAspect.class)
        public PessimisticLockAspect pessimisticLockAspect(CacheService cacheService) {
            return new PessimisticLockAspect(cacheService);
        }
    }
}
