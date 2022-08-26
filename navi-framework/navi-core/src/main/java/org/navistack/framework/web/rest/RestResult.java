package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.navistack.framework.core.error.CodedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public interface RestResult<T, E> {
    boolean isSucceeded();

    T getResult();

    E getError();

    static None ok() {
        return None.instance();
    }

    static <U> Ok<U> ok(U result) {
        return new Ok<>(result);
    }

    static Err<SimpleError> err(int error, String message) {
        return Err.of(SimpleError.of(error, message));
    }

    static Err<ParameterizedError> err(int error, String message, Map<String, ? super Object> parameters) {
        return Err.of(ParameterizedError.of(error, message, parameters));
    }

    static <F> Err<F> err(F error) {
        return Err.of(error);
    }

    @SafeVarargs
    static Err<ParameterizedError> err(int error, String message, Map.Entry<String, ? super Object>... parameters) {
        return Err.of(ParameterizedError.of(error, message, parameters));
    }

    static Err<ExceptionalError> err(int error, String message, Throwable throwable) {
        return Err.of(ExceptionalError.of(error, message, throwable));
    }

    static Err<ExceptionalError> err(int error, String message, ExceptionalEntity exception) {
        return Err.of(ExceptionalError.of(error, message, exception));
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    class Ok<T> implements RestResult<T, Void> {
        private T result;

        @Override
        public boolean isSucceeded() {
            return true;
        }

        @Override
        public Void getError() {
            return null;
        }
    }

    final class None extends RestResult.Ok<Void> {
        private static final None INSTANCE = new None();

        private None() {
            super(null);
        }

        public static None instance() {
            return INSTANCE;
        }
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    class Err<E> implements RestResult<Void, E> {
        private E error;

        @Override
        public boolean isSucceeded() {
            return false;
        }

        @Override
        public Void getResult() {
            return null;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    class SimpleError {
        private int error;
        private String message;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    class ParameterizedError extends SimpleError {
        private Map<String, ? super Object> parameters;

        public ParameterizedError(int error, String message, Map<String, ? super Object> parameters) {
            super(error, message);

            this.parameters = parameters;
        }

        public static ParameterizedError of(int error, String message) {
            return new ParameterizedError(error, message, Collections.emptyMap());
        }

        public static ParameterizedError of(int error, String message, Map<String, ? super Object> parameters) {
            return new ParameterizedError(error, message, parameters);
        }

        @SafeVarargs
        public static ParameterizedError of(int error, String message, Map.Entry<String, ? super Object>... parameters) {
            Map<String, ? super Object> parametersMap = Arrays.stream(parameters)
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue
                            )
                    );

            return of(error, message, parametersMap);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    class ThrowableError extends ParameterizedError {
        private Throwable throwable;

        public ThrowableError(int error, String message, Map<String, ? super Object> parameters, Throwable throwable) {
            super(error, message, parameters);
            this.throwable = throwable;
        }

        public static ThrowableError of(int error, String message, Map<String, ? super Object> parameters, Throwable throwable) {
            return new ThrowableError(error, message, parameters, throwable);
        }

        public static ThrowableError of(int error, String message, Throwable throwable) {
            return new ThrowableError(error, message, Collections.emptyMap(), throwable);
        }

        public static ThrowableError of(CodedException exception) {
            return new ThrowableError(exception.getErrorCode(), exception.getMessage(), Collections.emptyMap(), exception);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    class ExceptionalError extends ParameterizedError {
        private ExceptionalEntity exception;

        public ExceptionalError(int error, String message, Map<String, ? super Object> parameters, ExceptionalEntity exception) {
            super(error, message, parameters);
            this.exception = exception;
        }

        public static ExceptionalError of(int error, String message, Map<String, ? super Object> parameters, ExceptionalEntity exception) {
            return new ExceptionalError(error, message, parameters, exception);
        }

        public static ExceptionalError of(int error, String message, ExceptionalEntity exception) {
            return new ExceptionalError(error, message, Collections.emptyMap(), exception);
        }

        public static ExceptionalError of(int error, String message, Map<String, ? super Object> parameters, Throwable throwable) {
            ExceptionalEntity exception = ExceptionalEntityBuilder.of(throwable).build();
            return new ExceptionalError(error, message, parameters, exception);
        }

        public static ExceptionalError of(int error, String message, Throwable throwable) {
            ExceptionalEntity exception = ExceptionalEntityBuilder.of(throwable).build();
            return new ExceptionalError(error, message, Collections.emptyMap(), exception);
        }
    }
}
