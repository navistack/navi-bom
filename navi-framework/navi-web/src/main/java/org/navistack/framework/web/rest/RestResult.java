package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public interface RestResult<T, E> {
    boolean isSucceeded();

    T getResult();

    E getError();

    static <U> Ok<U> ok() {
        return new Ok<>(null);
    }

    static <U> Ok<U> ok(U result) {
        return new Ok<>(result);
    }

    static Err<SimpleError> err(String error, String message) {
        return Err.of(SimpleError.of(error, message));
    }

    static Err<ParameterizedError> err(String error, String message, Map<String, ? super Object> parameters) {
        return Err.of(ParameterizedError.of(error, message, parameters));
    }

    static <F> Err<F> err(F error) {
        return Err.of(error);
    }

    @SafeVarargs
    static Err<ParameterizedError> err(String error, String message, Map.Entry<String, ? super Object>... parameters) {
        return Err.of(ParameterizedError.of(error, message, parameters));
    }

    static Err<SimpleError> err(Throwable throwable) {
        return Err.of(
                SimpleError.of(
                        throwable.getClass().getSimpleName(),
                        throwable.getMessage())
        );
    }

    @Data
    @AllArgsConstructor
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

        public static <U> Ok<U> of(U result) {
            return new Ok<>(result);
        }
    }

    @Data
    @AllArgsConstructor
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

        public static <F> Err<F> of(F err) {
            return new Err<>(err);
        }
    }

    @Data
    @AllArgsConstructor
    class SimpleError {
        private String error;
        private String message;

        public static SimpleError of(String error, String message) {
            return new SimpleError(error, message);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class ParameterizedError {
        private String error;
        private String message;
        private Map<String, ? super Object> parameters;

        public static ParameterizedError of(String error, String message, Map<String, ? super Object> parameters) {
            return new ParameterizedError(error, message, parameters);
        }

        @SafeVarargs
        public static ParameterizedError of(String error, String message, Map.Entry<String, ? super Object>... parameters) {
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
