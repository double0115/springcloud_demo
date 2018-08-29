package com.zxhl.common.datasource;

import com.zxhl.common.datasource.strategy.DbSplit;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public interface ShardingDataSourceRouter {
    //分库逻辑
    void onDBRoute(DbSplit dbSplit, Set<String> dataSources, Object parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //分表逻辑
    void onTableRoute(MetaObject metaStatementHandler, Object parameter) throws ClassNotFoundException ;


}
