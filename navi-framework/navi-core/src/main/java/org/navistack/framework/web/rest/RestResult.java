package org.navistack.framework.web.rest;

import lombok.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public interface RestResult<T, E> {
    boolean isSucceeded();

    T getResult();

    E getError();

    static Ok<Void> ok() {
        return Ok.EMPTY;
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

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(staticName = "of")
    class Ok<T> implements RestResult<T, Void> {
        private static final Ok<Void> EMPTY = new Ok<>();

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
    class ParameterizedError extends SimpleError {
        private final Map<String, Object> parameters = new HashMap<>();

        public ParameterizedError(int error, String message) {
            super(error, message);
        }

        public ParameterizedError(int error, String message, Map<String, ? super Object> parameters) {
            super(error, message);

            this.parameters.putAll(parameters);
        }

        public static ParameterizedError of(int error, String message) {
            return new ParameterizedError(error, message);
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
}
