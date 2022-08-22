package org.navistack.framework.utils;

import org.junit.jupiter.api.Test;
import org.navistack.framework.core.domain.DomainException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DomainAssertsTest {
    @Test
    void constructor() throws NoSuchMethodException {
        Constructor<DomainAsserts> constructor = DomainAsserts.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isEqualTo(true);
        constructor.setAccessible(true);
        assertThatThrownBy(constructor::newInstance).getCause().isInstanceOf(UnsupportedOperationException.class);
        constructor.setAccessible(false);
    }

    @Test
    void state() {
        DomainAsserts.state(true, () -> new DomainException(-1));
        assertThatThrownBy(() -> DomainAsserts.state(false, () -> new DomainException(-1))).isInstanceOf(DomainException.class);
    }

    @Test
    void stateIllegalArgument() {
        DomainAsserts.state(true, -1);
        assertThatThrownBy(() -> DomainAsserts.state(false, -1)).isInstanceOf(DomainException.class);
    }

    @Test
    void stateBooleanSupplier() {
        DomainAsserts.state(() -> true, () -> new DomainException(-1));
        assertThatThrownBy(() -> DomainAsserts.state(() -> false, () -> new DomainException(-1))).isInstanceOf(DomainException.class);
    }

    @Test
    void stateBooleanSupplierIllegalArgument() {
        DomainAsserts.state(() -> true, -1);
        assertThatThrownBy(() -> DomainAsserts.state(() -> false, -1)).isInstanceOf(DomainException.class);
    }

    @Test
    void statePredicate() {
        DomainAsserts.state(new Object(), object -> true, () -> new DomainException(-1));
        assertThatThrownBy(() -> DomainAsserts.state(new Object(), object -> false, () -> new DomainException(-1))).isInstanceOf(DomainException.class);
    }

    @Test
    void statePredicateIllegalArgument() {
        DomainAsserts.state(new Object(), object -> true, -1);
        assertThatThrownBy(() -> DomainAsserts.state(new Object(), object -> false, -1)).isInstanceOf(DomainException.class);
    }

    @Test
    void stateBiPredicate() {
        DomainAsserts.state(new Object(), new Object(), (left, right) -> true, () -> new DomainException(-1));
        assertThatThrownBy(() -> DomainAsserts.state(new Object(), new Object(), (left, right) -> false, () -> new DomainException(-1))).isInstanceOf(DomainException.class);
    }

    @Test
    void stateBiPredicateIllegalArgument() {
        DomainAsserts.state(new Object(), new Object(), (left, right) -> true, -1);
        assertThatThrownBy(() -> DomainAsserts.state(new Object(), new Object(), (left, right) -> false, -1)).isInstanceOf(DomainException.class);
    }
}
