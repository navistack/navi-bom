package org.navistack.framework.core;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ResultsTest {
    enum Error {
        EMPTY_STRING_ERROR,
        MALFORMAT_STRING_ERROR,
        UNKNOWN_ERROR
    }

    static final Map<Error, String> ERROR_MESSAGES = new HashMap<>();

    static {
        ERROR_MESSAGES.put(Error.EMPTY_STRING_ERROR, "str is empty");
        ERROR_MESSAGES.put(Error.MALFORMAT_STRING_ERROR, "str contains illegal character(s)");
        ERROR_MESSAGES.put(Error.UNKNOWN_ERROR, "Failed parsing due to unexpected error");
    }

    static Result<Integer, Error> parseInteger(String str) {
        if (str == null || str.isEmpty()) {
            return Results.err(Error.EMPTY_STRING_ERROR);
        }
        try {
            return Results.ok(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            return Results.err(Error.MALFORMAT_STRING_ERROR);
        } catch (Exception e) {
            return Results.err(Error.UNKNOWN_ERROR);
        }
    }

    @Test
    void unwrap_shouldReturnContainedValue() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrap())
                .isEqualTo(1011);
    }

    @Test
    void unwrap_shouldThrowIllegalStateExceptionOnErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(result::unwrap)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void unwrap_shouldReturnSuppliedValueOnOk() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrap(0))
                .isEqualTo(1011);
    }

    @Test
    void unwrap_shouldReturnSuppliedValueOnErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrap(0))
                .isEqualTo(0);
    }

    @Test
    void unwrap_shouldCallSupplierOnOk() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrap(() -> 0))
                .isEqualTo(1011);
    }

    @Test
    void unwrap_shouldCallSupplierOnErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrap(() -> 0))
                .isEqualTo(0);
    }

    @Test
    void unwrapErr_shouldReturnContainedErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.unwrapErr())
                .isEqualTo(Error.MALFORMAT_STRING_ERROR);
    }

    @Test
    void unwrapErr_shouldThrowIllegalStateExceptionOnErr() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(result::unwrapErr)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void throwErr_shouldThrowIllegalStateExceptionOnOk() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(result::throwErr)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void throwErr_shouldThrowRuntimeExceptionBasedOnErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(result::throwErr)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(Error.MALFORMAT_STRING_ERROR.toString());
    }

    @Test
    void throwErr_shouldThrowGivenException() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(() ->
                result.throwErr(e ->
                        new IllegalArgumentException(ERROR_MESSAGES.get(e))
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGES.get(Error.MALFORMAT_STRING_ERROR));
    }

    @Test
    void shouldThrowIllegalStateExceptionOnOkInDespiteOfConverter() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThatThrownBy(() ->
                result.throwErr(e ->
                        new IllegalArgumentException(ERROR_MESSAGES.get(e))
                ))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isOk_shouldReturnTrue() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk())
                .isTrue();
    }

    @Test
    void isOk_shouldReturnFalse() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk())
                .isFalse();
    }

    @Test
    void isOk_shouldReturnTrueWhenEqualsToGivenValue() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(1011))
                .isTrue();
    }

    @Test
    void isOk_shouldReturnTrueWhenNotEqualsToGivenValue() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(1010))
                .isFalse();
    }

    @Test
    void isOk_shouldReturnTrueWhenIsInstanceOfGivenClass() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(Integer.class))
                .isTrue();
        assertThat(result.isOk(Number.class))
                .isTrue();
        assertThat(result.isOk(Object.class))
                .isTrue();
    }

    @Test
    void isOk_shouldReturnFalseWhenIsErrorInDespiteOfGivenClass() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(Integer.class))
                .isFalse();
        assertThat(result.isOk(Number.class))
                .isFalse();
        assertThat(result.isOk(Object.class))
                .isFalse();
    }

    @Test
    void isOk_shouldReturnTrueWhenPredicateReturnTrue() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(i -> i > 0))
                .isTrue();
    }

    @Test
    void isOk_shouldReturnFalseWhenPredicateReturnFalse() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(i -> i < 0))
                .isFalse();
    }

    @Test
    void isOk_shouldReturnFalseWhenIsErrorInDespiteOfPredicate() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk(i -> i > 0))
                .isFalse();
        assertThat(result.isOk(i -> i < 0))
                .isFalse();
    }

    @Test
    void isErr_shouldReturnTrue() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr())
                .isTrue();
    }

    @Test
    void isErr_shouldReturnFalse() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr())
                .isFalse();
    }

    @Test
    void isErr_shouldReturnTrueWhenPredicateReturnTrue() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.MALFORMAT_STRING_ERROR::equals))
                .isTrue();
    }

    @Test
    void isErr_shouldReturnFalseWhenPredicateReturnFalse() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.EMPTY_STRING_ERROR::equals))
                .isFalse();
    }

    @Test
    void isErr_shouldReturnFalseWhenIsOkInDespiteOfPredicate() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.MALFORMAT_STRING_ERROR::equals))
                .isFalse();
        assertThat(result.isErr(Error.EMPTY_STRING_ERROR::equals))
                .isFalse();
    }

    @Test
    void isErr_shouldReturnTrueWhenEqualsToGivenErr() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.MALFORMAT_STRING_ERROR))
                .isTrue();
    }

    @Test
    void isErr_shouldReturnTrueWhenNotEqualsToGivenValue() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.EMPTY_STRING_ERROR))
                .isFalse();
    }

    @Test
    void isErr_shouldReturnTrueWhenIsInstanceOfGivenClass() {
        Result<Integer, Error> result = parseInteger("101,1");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.class))
                .isTrue();
        assertThat(result.isErr(Enum.class))
                .isTrue();
        assertThat(result.isErr(Object.class))
                .isTrue();
    }

    @Test
    void isErr_shouldReturnFalseWhenIsOkInDespiteOfGivenClass() {
        Result<Integer, Error> result = parseInteger("1011");
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(Error.class))
                .isFalse();
        assertThat(result.isErr(Enum.class))
                .isFalse();
        assertThat(result.isErr(Object.class))
                .isFalse();
    }

    @Test
    void map_shouldReturnMappedValueOnOk() {
        Result<String, Error> result = parseInteger("1011")
                .map(i -> Integer.toString(i, 2));
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk("1111110011"))
                .isTrue();
    }

    @Test
    void map_shouldReturnMappedValueOnErr() {
        Result<String, Error> result = parseInteger("101,1")
                .map(i -> Integer.toString(i, 2));
        assertThat(result)
                .isNotNull();
        assertThat(result.isOk("1111110011"))
                .isFalse();
        assertThat(result.isErr(Error.MALFORMAT_STRING_ERROR))
                .isTrue();
    }

    @Test
    void map_shouldReturnMappedErrOnErr() {
        Result<Integer, String> result = parseInteger("101,1")
                .mapErr(ERROR_MESSAGES::get);
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(ERROR_MESSAGES.get(Error.MALFORMAT_STRING_ERROR)))
                .isTrue();
    }

    @Test
    void map_shouldReturnMappedErrOnOk() {
        Result<Integer, String> result = parseInteger("1011")
                .mapErr(ERROR_MESSAGES::get);
        assertThat(result)
                .isNotNull();
        assertThat(result.isErr(ERROR_MESSAGES.get(Error.MALFORMAT_STRING_ERROR)))
                .isFalse();
        assertThat(result.isOk(1011))
                .isTrue();
    }

    @Test
    void inspect_shouldCallInspectOnOk() {
        @SuppressWarnings("unchecked")
        Consumer<Integer> inspector = mock(Consumer.class);
        Result<Integer, Error> result = parseInteger("1011")
                .inspect(inspector);
        assertThat(result)
                .isNotNull();
        verify(inspector)
                .accept(anyInt());
    }

    @Test
    void inspect_shouldNotCallInspectOnErr() {
        @SuppressWarnings("unchecked")
        Consumer<Integer> inspector = mock(Consumer.class);
        Result<Integer, Error> result = parseInteger("101,1")
                .inspect(inspector);
        assertThat(result)
                .isNotNull();
        verify(inspector, never())
                .accept(anyInt());
    }
}
