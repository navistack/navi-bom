package org.navistack.framework.mybatisplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;

public class DefaultSqlInjector extends com.baomidou.mybatisplus.core.injector.DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        methodList.add(new ExistsMethod());

        return methodList;
    }
}
