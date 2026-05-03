package org.navistack.boot.locking.autoconfigure;

import org.navistack.framework.cache.HierarchicalCacheStoreBuilder;
import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.locking.CachePessimisticLockService;
import org.navistack.framework.locking.PessimisticLock;
import org.navistack.framework.locking.PessimisticLockAspect;
import org.navistack.framework.locking.PessimisticLockService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(PessimisticLock.class)
public class PessimisticLockConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PessimisticLockService pessimisticLockService(HierarchicalCacheStoreBuilder cacheStoreBuilder) {
        return new CachePessimisticLockService(cacheStoreBuilder);
    }

    @Bean
    @ConditionalOnMissingBean(PessimisticLockAspect.class)
    public PessimisticLockAspect pessimisticLockAspect(MethodExpressionBinder expressionBinder,
                                                       PessimisticLockService lockService) {
        return new PessimisticLockAspect(expressionBinder, lockService);
    }
}
