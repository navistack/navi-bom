package org.navistack.boot.autoconfigure.locking;

import lombok.Getter;
import lombok.Setter;
import org.navistack.framework.cache.ScopedCacheServiceBuilder;
import org.navistack.framework.expression.DefaultMethodExpressionEvaluatorFactory;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;
import org.navistack.framework.locking.CachePessimisticLockService;
import org.navistack.framework.locking.PessimisticLock;
import org.navistack.framework.locking.PessimisticLockAspect;
import org.navistack.framework.locking.PessimisticLockService;
import org.navistack.framework.utils.ApplicationContexts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(PessimisticLock.class)
public class PessimisticLockConfiguration implements ApplicationContextAware {
    @Getter
    @Setter
    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean
    public PessimisticLockService pessimisticLockService(ScopedCacheServiceBuilder cacheServiceBuilder) {
        return new CachePessimisticLockService(cacheServiceBuilder);
    }

    @Bean
    @ConditionalOnMissingBean(PessimisticLockAspect.class)
    public PessimisticLockAspect pessimisticLockAspect(PessimisticLockService lockService) {
        MethodExpressionEvaluatorFactory evaluatorFactory;
        evaluatorFactory = ApplicationContexts.getBean(applicationContext, MethodExpressionEvaluatorFactory.class);
        if (evaluatorFactory == null) {
            evaluatorFactory = new DefaultMethodExpressionEvaluatorFactory();
        }
        return new PessimisticLockAspect(evaluatorFactory, lockService);
    }
}
