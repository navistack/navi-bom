package org.navistack.boot.autoconfigure.locking;

import org.navistack.framework.cache.KvCacheService;
import org.navistack.framework.locking.PessimisticLock;
import org.navistack.framework.locking.PessimisticLockAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LockingAutoConfiguration {
    @Configuration
    @ConditionalOnClass(PessimisticLock.class)
    public static class PessimisticLockConfiguration {
        @Bean
        @ConditionalOnMissingBean(PessimisticLockAspect.class)
        public PessimisticLockAspect pessimisticLockAspect(KvCacheService cacheService) {
            return new PessimisticLockAspect(cacheService);
        }
    }
}
