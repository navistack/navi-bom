package org.navistack.boot.ratelimit.autoconfigure;

import org.navistack.boot.redis.autoconfigure.RedisAutoConfiguration;
import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.ratelimit.FixedWindowRateLimit;
import org.navistack.framework.ratelimit.FixedWindowRateLimitAspect;
import org.navistack.framework.ratelimit.FixedWindowRateLimiter;
import org.navistack.framework.ratelimit.RedisFixedWindowRateLimiter;
import org.navistack.framework.ratelimit.RedisSlidingWindowRateLimiter;
import org.navistack.framework.ratelimit.SlidingWindowRateLimit;
import org.navistack.framework.ratelimit.SlidingWindowRateLimitAspect;
import org.navistack.framework.ratelimit.SlidingWindowRateLimiter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

@AutoConfiguration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RateLimitAutoConfiguration {
    @Configuration
    @ConditionalOnClass(FixedWindowRateLimit.class)
    public static class FixedWindowRateLimitAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public FixedWindowRateLimiter fixedWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
            return new RedisFixedWindowRateLimiter(redisOperations);
        }

        @Bean
        public FixedWindowRateLimitAspect fixedWindowRateLimitAspect(MethodExpressionBinder expressionBinder,
                                                                     FixedWindowRateLimiter rateLimiter) {
            return new FixedWindowRateLimitAspect(expressionBinder, rateLimiter);
        }
    }

    @Configuration
    @ConditionalOnClass(SlidingWindowRateLimit.class)
    public static class SlidingWindowRateLimitAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public SlidingWindowRateLimiter slidingWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
            return new RedisSlidingWindowRateLimiter(redisOperations);
        }

        @Bean
        public SlidingWindowRateLimitAspect slidingWindowRateLimitAspect(MethodExpressionBinder expressionBinder,
                                                                         SlidingWindowRateLimiter rateLimiter) {
            return new SlidingWindowRateLimitAspect(expressionBinder, rateLimiter);
        }
    }
}
