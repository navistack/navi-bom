package org.navistack.framework.mybatisplusplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class ExistsMethod extends AbstractMethod {
    private final static String SQL_TEMPLATE = "<script>%s SELECT EXISTS(SELECT %s FROM %s %s) %s\n</script>";
    private final static String METHOD_NAME = "exists";

    public ExistsMethod() {
        super(METHOD_NAME);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(
                SQL_TEMPLATE,
                sqlFirst(),
                sqlSelectColumns(tableInfo, true),
                tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlComment()
        );
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, METHOD_NAME, sqlSource, Boolean.class);
    }
}
