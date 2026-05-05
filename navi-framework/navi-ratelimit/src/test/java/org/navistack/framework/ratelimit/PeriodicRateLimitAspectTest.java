package org.navistack.framework.ratelimit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.navistack.framework.expression.BoundExpression;
import org.navistack.framework.expression.MethodExpressionBinder;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PeriodicRateLimitAspectTest {
    @Mock
    private MethodExpressionBinder expressionBinder;

    @Mock
    private BoundExpression boundExpression;

    @Mock
    private RateLimitHandler<PeriodicRateLimit> handler;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    private PeriodicRateLimitAspect aspect;

    static class TestTarget {
        @PeriodicRateLimit(key = "'key'", maxRequests = 10)
        void method() {}
    }

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        Method method = TestTarget.class.getDeclaredMethod("method");
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(method);
        when(joinPoint.getArgs()).thenReturn(new Object[0]);
        when(expressionBinder.bind(eq("'key'"), eq(method))).thenReturn(boundExpression);
        when(boundExpression.evaluate(any())).thenReturn("resolvedKey");
        aspect = new PeriodicRateLimitAspect(expressionBinder, handler);
    }

    @Test
    void around_proceedsWhenHandlerApproves() throws Throwable {
        PeriodicRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(PeriodicRateLimit.class);
        when(handler.tryAcquire(eq(annotation), eq("resolvedKey"))).thenReturn(true);
        when(joinPoint.proceed()).thenReturn("result");

        assertThat(aspect.around(joinPoint, annotation)).isEqualTo("result");
        verify(joinPoint).proceed();
    }

    @Test
    void around_throwsExceptionWhenHandlerDenies() throws Throwable {
        PeriodicRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(PeriodicRateLimit.class);
        when(handler.tryAcquire(eq(annotation), eq("resolvedKey"))).thenReturn(false);

        assertThatThrownBy(() -> aspect.around(joinPoint, annotation))
                .isInstanceOf(RateLimitExceededException.class);
        verify(joinPoint, times(0)).proceed();
    }

    @Test
    void around_cachesExpressionBindingAcrossInvocations() throws Throwable {
        PeriodicRateLimit annotation = TestTarget.class.getDeclaredMethod("method")
                .getAnnotation(PeriodicRateLimit.class);
        when(handler.tryAcquire(any(), anyString())).thenReturn(true);
        when(joinPoint.proceed()).thenReturn(null);

        aspect.around(joinPoint, annotation);
        aspect.around(joinPoint, annotation);

        verify(expressionBinder, times(1)).bind(anyString(), any(Method.class));
    }
}
