package org.navistack.framework.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrCodesTest {

    @Test
    void of_shouldReturnErrCode() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        FlightsErrCategory category = new FlightsErrCategory();
        ErrCode errCode = ErrCodes.of(value, category);
        assertThat(errCode.value())
                .isEqualTo(value);
        assertThat(errCode.message())
                .isEqualTo("Nonexistent airport name in request");
        assertThat(errCode.category())
                .isEqualTo(category);
    }

    @Test
    void of_shouldReturnErrCodeWhenEnumIsPassed() {
        FlightsErrCode value = FlightsErrCode.NONEXISTENT_LOCATIONS;
        FlightsErrCategory category = new FlightsErrCategory();
        ErrCode errCode = ErrCodes.of(value, category);
        assertThat(errCode.value())
                .isEqualTo(FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal());
        assertThat(errCode.message())
                .isEqualTo("Nonexistent airport name in request");
        assertThat(errCode.category())
                .isEqualTo(category);
    }

    @Test
    void of_shouldConstructErrCodeInNeed() {
        int value = FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal();
        Class<FlightsErrCategory> category = FlightsErrCategory.class;
        ErrCode errCode = ErrCodes.of(value, category);
        assertThat(errCode.value())
                .isEqualTo(value);
        assertThat(errCode.message())
                .isEqualTo("Nonexistent airport name in request");
        assertThat(errCode.category())
                .isEqualTo(ErrCodes.getErrCategory(category));
    }

    @Test
    void of_shouldConstructErrCodeInNeedWhenEnumIsPassed() {
        FlightsErrCode value = FlightsErrCode.NONEXISTENT_LOCATIONS;
        Class<FlightsErrCategory> category = FlightsErrCategory.class;
        ErrCode errCode = ErrCodes.of(value, category);
        assertThat(errCode.value())
                .isEqualTo(FlightsErrCode.NONEXISTENT_LOCATIONS.ordinal());
        assertThat(errCode.message())
                .isEqualTo("Nonexistent airport name in request");
        assertThat(errCode.category())
                .isEqualTo(ErrCodes.getErrCategory(category));
    }

    @Test
    void getErrCategory_shouldReturnSameInstance() {
        FlightsErrCategory lCategory = ErrCodes.getErrCategory(FlightsErrCategory.class);
        FlightsErrCategory rCategory = ErrCodes.getErrCategory(FlightsErrCategory.class);
        assertThat(lCategory).isSameAs(rCategory);
    }
}
