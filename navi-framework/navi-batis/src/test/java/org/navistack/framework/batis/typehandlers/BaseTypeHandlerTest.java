package org.navistack.framework.batis.typehandlers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@ExtendWith(MockitoExtension.class)
abstract class BaseTypeHandlerTest {
    @Mock
    protected ResultSet rs;

    @Mock
    protected PreparedStatement ps;

    @Mock
    protected CallableStatement cs;

    @Mock
    protected ResultSetMetaData rsmd;

    abstract void shouldSetParameter() throws Exception;

    abstract void shouldGetResultFromResultSetByName() throws Exception;

    abstract void shouldGetResultNullFromResultSetByName() throws Exception;

    abstract void shouldGetResultFromResultSetByPosition() throws Exception;

    abstract void shouldGetResultNullFromResultSetByPosition() throws Exception;

    abstract void shouldGetResultFromCallableStatement() throws Exception;

    abstract void shouldGetResultNullFromCallableStatement() throws Exception;
}
