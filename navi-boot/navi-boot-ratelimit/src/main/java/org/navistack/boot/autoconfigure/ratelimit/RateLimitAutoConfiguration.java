package org.navistack.boot.autoconfigure.ratelimit;

import lombok.Getter;
import lombok.Setter;
import org.navistack.boot.autoconfigure.redis.RedisAutoConfiguration;
import org.navistack.framework.expression.DefaultMethodExpressionEvaluatorFactory;
import org.navistack.framework.expression.MethodExpressionEvaluatorFactory;
import org.navistack.framework.ratelimit.FixedWindowRateLimit;
import org.navistack.framework.ratelimit.FixedWindowRateLimitAspect;
import org.navistack.framework.ratelimit.FixedWindowRateLimiter;
import org.navistack.framework.ratelimit.RedisFixedWindowRateLimiter;
import org.navistack.framework.ratelimit.RedisSlidingWindowRateLimiter;
import org.navistack.framework.ratelimit.SlidingWindowRateLimit;
import org.navistack.framework.ratelimit.SlidingWindowRateLimitAspect;
import org.navistack.framework.ratelimit.SlidingWindowRateLimiter;
import org.navistack.framework.utils.ApplicationContexts;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RateLimitAutoConfiguration {
    @Configuration
    @ConditionalOnClass(FixedWindowRateLimit.class)
    public static class FixedWindowRateLimitAutoConfiguration implements ApplicationContextAware {
        @Getter
        @Setter
        private ApplicationContext applicationContext;

        @Bean
        @ConditionalOnMissingBean
        public FixedWindowRateLimiter fixedWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
            return new RedisFixedWindowRateLimiter(redisOperations);
        }

        @Bean
        public FixedWindowRateLimitAspect fixedWindowRateLimitAspect(FixedWindowRateLimiter rateLimiter) {
            MethodExpressionEvaluatorFactory evaluatorFactory;
            evaluatorFactory = ApplicationContexts.getBean(applicationContext, MethodExpressionEvaluatorFactory.class);
            if (evaluatorFactory == null) {
                evaluatorFactory = new DefaultMethodExpressionEvaluatorFactory();
            }
            return new FixedWindowRateLimitAspect(evaluatorFactory, rateLimiter);
        }
    }

    @Configuration
    @ConditionalOnClass(SlidingWindowRateLimit.class)
    public static class SlidingWindowRateLimitAutoConfiguration implements ApplicationContextAware {
        @Getter
        @Setter
        private ApplicationContext applicationContext;

        @Bean
        @ConditionalOnMissingBean
        public SlidingWindowRateLimiter slidingWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
            return new RedisSlidingWindowRateLimiter(redisOperations);
        }

        @Bean
        public SlidingWindowRateLimitAspect slidingWindowRateLimitAspect(SlidingWindowRateLimiter rateLimiter) {
            MethodExpressionEvaluatorFactory evaluatorFactory;
            evaluatorFactory = ApplicationContexts.getBean(applicationContext, MethodExpressionEvaluatorFactory.class);
            if (evaluatorFactory == null) {
                evaluatorFactory = new DefaultMethodExpressionEvaluatorFactory();
            }
            return new SlidingWindowRateLimitAspect(evaluatorFactory, rateLimiter);
        }
    }
}
