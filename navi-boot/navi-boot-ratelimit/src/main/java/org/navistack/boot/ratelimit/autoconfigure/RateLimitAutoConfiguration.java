package org.navistack.boot.ratelimit.autoconfigure;

import org.navistack.boot.redis.autoconfigure.RedisAutoConfiguration;
import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.ratelimit.PeriodicRateLimitAspect;
import org.navistack.framework.ratelimit.PeriodicRateLimitHandler;
import org.navistack.framework.ratelimit.PeriodicRateLimiter;
import org.navistack.framework.ratelimit.RedisPeriodicRateLimiter;
import org.navistack.framework.ratelimit.RedisRollingRateLimiter;
import org.navistack.framework.ratelimit.RollingRateLimitAspect;
import org.navistack.framework.ratelimit.RollingRateLimitHandler;
import org.navistack.framework.ratelimit.RollingRateLimiter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisOperations;

@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RateLimitAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PeriodicRateLimiter periodicRateLimiter(RedisOperations<String, Long> redisOperations) {
        return new RedisPeriodicRateLimiter(redisOperations);
    }

    @Bean
    @ConditionalOnMissingBean
    public RollingRateLimiter rollingRateLimiter(RedisOperations<String, Long> redisOperations) {
        return new RedisRollingRateLimiter(redisOperations);
    }

    @Bean
    @ConditionalOnMissingBean
    public PeriodicRateLimitHandler periodicRateLimitHandler(PeriodicRateLimiter rateLimiter) {
        return new PeriodicRateLimitHandler(rateLimiter);
    }

    @Bean
    @ConditionalOnMissingBean
    public RollingRateLimitHandler rollingRateLimitHandler(RollingRateLimiter rateLimiter) {
        return new RollingRateLimitHandler(rateLimiter);
    }

    @Bean
    public PeriodicRateLimitAspect periodicRateLimitAspect(MethodExpressionBinder expressionBinder,
                                                           PeriodicRateLimitHandler handler) {
        return new PeriodicRateLimitAspect(expressionBinder, handler);
    }

    @Bean
    public RollingRateLimitAspect rollingRateLimitAspect(MethodExpressionBinder expressionBinder,
                                                         RollingRateLimitHandler handler) {
        return new RollingRateLimitAspect(expressionBinder, handler);
    }
}
