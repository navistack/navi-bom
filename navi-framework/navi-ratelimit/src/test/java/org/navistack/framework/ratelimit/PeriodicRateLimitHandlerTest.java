package org.navistack.framework.ratelimit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PeriodicRateLimitHandlerTest {
    @Mock
    private PeriodicRateLimiter rateLimiter;

    @InjectMocks
    private PeriodicRateLimitHandler handler;

    static class TestTarget {
        @PeriodicRateLimit(key = "'key'", maxRequests = 100, temporalUnit = ChronoUnit.MINUTES)
        void method() {}
    }

    @Test
    void tryAcquire_delegatesToLimiterWithAnnotationParams() throws NoSuchMethodException {
        PeriodicRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(PeriodicRateLimit.class);
        when(rateLimiter.tryAcquire("key", 100, ChronoUnit.MINUTES)).thenReturn(true);

        assertThat(handler.tryAcquire(annotation, "key")).isTrue();
        verify(rateLimiter).tryAcquire("key", 100, ChronoUnit.MINUTES);
    }

    @Test
    void tryAcquire_returnsFalseWhenLimiterDenies() throws NoSuchMethodException {
        PeriodicRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(PeriodicRateLimit.class);
        when(rateLimiter.tryAcquire("key", 100, ChronoUnit.MINUTES)).thenReturn(false);

        assertThat(handler.tryAcquire(annotation, "key")).isFalse();
    }
}
