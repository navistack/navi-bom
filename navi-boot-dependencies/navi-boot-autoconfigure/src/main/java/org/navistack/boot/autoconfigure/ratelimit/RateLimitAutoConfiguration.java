package org.navistack.boot.autoconfigure.ratelimit;

import org.navistack.boot.autoconfigure.redis.RedisAutoConfiguration;
import org.navistack.framework.ratelimit.*;
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
            return new RedisOperationFixedWindowRateLimiter(redisOperations);
        }

        @Bean
        public FixedWindowRateLimitAspect fixedWindowRateLimitAspect(FixedWindowRateLimiter rateLimiter) {
            return new FixedWindowRateLimitAspect(rateLimiter);
        }
    }

    @Configuration
    @ConditionalOnClass(SlidingWindowRateLimit.class)
    public static class SlidingWindowRateLimitAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public SlidingWindowRateLimiter slidingWindowRateLimiter(RedisOperations<String, Long> redisOperations) {
            return new RedisOperationSlidingWindowRateLimiter(redisOperations);
        }

        @Bean
        public SlidingWindowRateLimitAspect slidingWindowRateLimitAspect(SlidingWindowRateLimiter rateLimiter) {
            return new SlidingWindowRateLimitAspect(rateLimiter);
        }
    }
}
