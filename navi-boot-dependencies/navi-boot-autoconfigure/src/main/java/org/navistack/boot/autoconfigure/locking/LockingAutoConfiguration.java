package org.navistack.boot.autoconfigure.locking;

import org.navistack.framework.cache.CacheService;
import org.navistack.framework.locking.CachePessimisticLockService;
import org.navistack.framework.locking.PessimisticLock;
import org.navistack.framework.locking.PessimisticLockAspect;
import org.navistack.framework.locking.PessimisticLockService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockingAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PessimisticLockService pessimisticLockService(CacheService cacheService) {
        return new CachePessimisticLockService(cacheService);
    }

    @Configuration
    @ConditionalOnClass(PessimisticLock.class)
    public static class PessimisticLockConfiguration {
        @Bean
        @ConditionalOnMissingBean(PessimisticLockAspect.class)
        public PessimisticLockAspect pessimisticLockAspect(PessimisticLockService lockService) {
            return new PessimisticLockAspect(lockService);
        }
    }
}
