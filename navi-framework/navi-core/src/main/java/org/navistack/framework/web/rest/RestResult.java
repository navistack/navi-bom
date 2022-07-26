package org.navistack.framework.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.navistack.framework.core.problem.Problem;

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

    static Err<SimpleError> err(Problem problem) {
        return Err.of(
                SimpleError.of(
                        problem.getError(),
                        problem.getMessage())
        );
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
    class ExceptionalError extends SimpleError {
        private ExceptionalEntity exception;

        public ExceptionalError(int error, String message, ExceptionalEntity exception) {
            super(error, message);
            this.exception = exception;
        }

        public static ExceptionalError of(int error, String message, ExceptionalEntity exception) {
            return new ExceptionalError(error, message, exception);
        }

        public static ExceptionalError of(int error, String message, Throwable throwable) {
            ExceptionalEntity exception = ExceptionalEntityBuilder.of(throwable).build();
            return new ExceptionalError(error, message, exception);
        }
    }
}
