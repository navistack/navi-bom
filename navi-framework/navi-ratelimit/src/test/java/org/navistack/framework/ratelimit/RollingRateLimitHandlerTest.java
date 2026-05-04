package org.navistack.framework.ratelimit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RollingRateLimitHandlerTest {
    @Mock
    private RollingRateLimiter rateLimiter;

    @InjectMocks
    private RollingRateLimitHandler handler;

    static class TestTarget {
        @RollingRateLimit(key = "'key'", maxRequests = 50, windowSize = 30, windowSizeUnit = ChronoUnit.SECONDS)
        void method() {}
    }

    @Test
    void tryAcquire_delegatesToLimiterWithAnnotationParams() throws NoSuchMethodException {
        RollingRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(RollingRateLimit.class);
        Duration expectedWindow = Duration.of(30, ChronoUnit.SECONDS);
        when(rateLimiter.tryAcquire("key", 50, expectedWindow)).thenReturn(true);

        assertThat(handler.tryAcquire(annotation, "key")).isTrue();
        verify(rateLimiter).tryAcquire("key", 50, expectedWindow);
    }

    @Test
    void tryAcquire_returnsFalseWhenLimiterDenies() throws NoSuchMethodException {
        RollingRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(RollingRateLimit.class);
        Duration expectedWindow = Duration.of(30, ChronoUnit.SECONDS);
        when(rateLimiter.tryAcquire("key", 50, expectedWindow)).thenReturn(false);

        assertThat(handler.tryAcquire(annotation, "key")).isFalse();
    }
}
