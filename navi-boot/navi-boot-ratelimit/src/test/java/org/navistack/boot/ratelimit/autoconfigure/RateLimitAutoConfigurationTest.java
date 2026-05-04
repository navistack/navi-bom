package org.navistack.boot.ratelimit.autoconfigure;

import org.junit.jupiter.api.Test;
import org.navistack.framework.expression.MethodExpressionBinder;
import org.navistack.framework.ratelimit.PeriodicRateLimitAspect;
import org.navistack.framework.ratelimit.PeriodicRateLimitHandler;
import org.navistack.framework.ratelimit.PeriodicRateLimiter;
import org.navistack.framework.ratelimit.RollingRateLimitAspect;
import org.navistack.framework.ratelimit.RollingRateLimitHandler;
import org.navistack.framework.ratelimit.RollingRateLimiter;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.data.redis.core.RedisOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RateLimitAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withBean(MethodExpressionBinder.class, () -> mock(MethodExpressionBinder.class))
            .withBean("redisOperations", RedisOperations.class, () -> mock(RedisOperations.class))
            .withConfiguration(AutoConfigurations.of(RateLimitAutoConfiguration.class));

    @Test
    void shouldRegisterDefaultBeans() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(PeriodicRateLimiter.class);
            assertThat(context).hasSingleBean(RollingRateLimiter.class);
            assertThat(context).hasSingleBean(PeriodicRateLimitHandler.class);
            assertThat(context).hasSingleBean(RollingRateLimitHandler.class);
            assertThat(context).hasSingleBean(PeriodicRateLimitAspect.class);
            assertThat(context).hasSingleBean(RollingRateLimitAspect.class);
        });
    }

    @Test
    void shouldNotOverrideUserProvidedPeriodicRateLimiter() {
        PeriodicRateLimiter userLimiter = mock(PeriodicRateLimiter.class);
        contextRunner
                .withBean(PeriodicRateLimiter.class, () -> userLimiter)
                .run(context -> {
                    assertThat(context).hasSingleBean(PeriodicRateLimiter.class);
                    assertThat(context.getBean(PeriodicRateLimiter.class)).isSameAs(userLimiter);
                });
    }

    @Test
    void shouldNotOverrideUserProvidedRollingRateLimiter() {
        RollingRateLimiter userLimiter = mock(RollingRateLimiter.class);
        contextRunner
                .withBean(RollingRateLimiter.class, () -> userLimiter)
                .run(context -> {
                    assertThat(context).hasSingleBean(RollingRateLimiter.class);
                    assertThat(context.getBean(RollingRateLimiter.class)).isSameAs(userLimiter);
                });
    }

    @Test
    void shouldNotOverrideUserProvidedPeriodicRateLimitHandler() {
        PeriodicRateLimitHandler userHandler = mock(PeriodicRateLimitHandler.class);
        contextRunner
                .withBean(PeriodicRateLimitHandler.class, () -> userHandler)
                .run(context -> {
                    assertThat(context).hasSingleBean(PeriodicRateLimitHandler.class);
                    assertThat(context.getBean(PeriodicRateLimitHandler.class)).isSameAs(userHandler);
                });
    }

    @Test
    void shouldNotOverrideUserProvidedRollingRateLimitHandler() {
        RollingRateLimitHandler userHandler = mock(RollingRateLimitHandler.class);
        contextRunner
                .withBean(RollingRateLimitHandler.class, () -> userHandler)
                .run(context -> {
                    assertThat(context).hasSingleBean(RollingRateLimitHandler.class);
                    assertThat(context.getBean(RollingRateLimitHandler.class)).isSameAs(userHandler);
                });
    }
}
