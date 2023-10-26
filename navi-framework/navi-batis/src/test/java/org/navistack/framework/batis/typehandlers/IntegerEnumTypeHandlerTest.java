package org.navistack.framework.batis.typehandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import org.junit.jupiter.api.Test;
import org.navistack.framework.core.Numeric;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntegerEnumTypeHandlerTest extends BaseTypeHandlerTest {
    private final IntegerEnumTypeHandler<State> typeHandler = new IntegerEnumTypeHandler<>(State.class);

    @Override
    @Test
    void shouldSetParameter() throws Exception {
        typeHandler.setParameter(ps, 1, State.R, null);
        verify(ps).setInt(1, 1001);
    }

    @Test
    void shouldSetNullParameter() throws Exception {
        typeHandler.setParameter(ps, 1, null, JdbcType.NUMERIC);
        verify(ps).setNull(1, JdbcType.NUMERIC.TYPE_CODE);
    }

    @Override
    @Test
    void shouldGetResultFromResultSetByName() throws Exception {
        when(rs.getInt("state")).thenReturn(1001);
        assertThat(typeHandler.getResult(rs, "state")).isEqualTo(State.R);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromResultSetByName() throws Exception {
        when(rs.getInt("state")).thenReturn(0);
        when(rs.wasNull()).thenReturn(true);
        assertThat(typeHandler.getResult(rs, "state")).isNull();
    }

    @Override
    @Test
    void shouldGetResultFromResultSetByPosition() throws Exception {
        when(rs.getInt(1)).thenReturn(1001);
        assertThat(typeHandler.getResult(rs, 1)).isEqualTo(State.R);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromResultSetByPosition() throws Exception {
        when(rs.getInt(1)).thenReturn(0);
        when(rs.wasNull()).thenReturn(true);
        assertThat(typeHandler.getResult(rs, 1)).isNull();
    }

    @Override
    @Test
    void shouldGetResultFromCallableStatement() throws Exception {
        when(cs.getInt(1)).thenReturn(1001);
        assertThat(typeHandler.getResult(cs, 1)).isEqualTo(State.R);
        verify(rs, never()).wasNull();
    }

    @Override
    @Test
    void shouldGetResultNullFromCallableStatement() throws Exception {
        when(cs.getInt(1)).thenReturn(0);
        when(cs.wasNull()).thenReturn(true);
        assertThat(typeHandler.getResult(cs, 1)).isNull();
    }

    @AllArgsConstructor
    @Getter
    @Accessors(fluent = true)
    public enum State implements Numeric<Integer> {
        R(1001),
        D(1002),
        S(1003),
        T(1004),
        Z(1005);

        private final Integer numeral;
    }
}
