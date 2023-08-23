package org.navistack.framework.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrCodeImplTest {

    @Test
    void value_shouldReturnAsIs() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.value())
                .isEqualTo(value);
    }

    @Test
    void message_shouldReturnAsIs() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.message())
                .isEqualTo("Nonexistent airport name in request");
    }

    @Test
    void category_shouldReturnAsIs() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.category())
                .isEqualTo(category);
    }

    @Test
    void equals_shouldReturnTrueWhenCompareToSelf() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.equals(errCode))
                .isTrue();
    }

    @Test
    void equals_shouldReturnFalseWhenCompareToNull() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.equals(null))
                .isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenCompareToNonErrCode() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory category = new FlightsErrCategory();
        ErrCodeImpl errCode = new ErrCodeImpl(value, category);
        assertThat(errCode.equals("ERROR CODE"))
                .isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenCompareToErrCodeFromDifferentCategory() {
        int lValue = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory lCategory = new FlightsErrCategory();
        ErrCodeImpl lErrCode = new ErrCodeImpl(lValue, lCategory);
        int rValue = SeatsErrCode.INVALID_REQUEST.ordinal();
        ErrCategory rCategory = new SeatsErrCategory();
        ErrCodeImpl rErrCode = new ErrCodeImpl(rValue, rCategory);
        assertThat(lErrCode.equals(rErrCode))
                .isFalse();
    }

    @Test
    void equals_shouldReturnFalseWhenCompareToDifferentValueFromSameCategory() {
        int lValue = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCategory lCategory = new FlightsErrCategory();
        ErrCodeImpl lErrCode = new ErrCodeImpl(lValue, lCategory);
        int rValue = FlightsErrCode.DATES_IN_THE_PAST.ordinal();
        ErrCategory rCategory = new FlightsErrCategory();
        ErrCodeImpl rErrCode = new ErrCodeImpl(rValue, rCategory);
        assertThat(lErrCode.equals(rErrCode))
                .isFalse();
    }

    @Test
    void equals_shouldReturnTrueWhenCompareToSameValueAndSameCategory() {
        ErrCategory category = new FlightsErrCategory();
        int lValue = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCodeImpl lErrCode = new ErrCodeImpl(lValue, category);
        int rValue = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        ErrCodeImpl rErrCode = new ErrCodeImpl(rValue, category);
        assertThat(lErrCode.equals(rErrCode))
                .isTrue();
    }
}
