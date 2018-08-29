package com.zxhl.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashSet;
import java.util.Set;

public class MyRoutingDataSource extends AbstractRoutingDataSource {
    //所有数据源
    public static  Set<String> dataSources;

    //执行sql前选择db
    @Override
    protected Object determineCurrentLookupKey() {
        return ShardingContextHolder.getDBKey();
    }

    //spring bean 初始化时保存所有数据源
    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        if(dataSources==null){
            dataSources = new HashSet<String>();
        }
        dataSources.add(String.valueOf(lookupKey));
        return lookupKey;
    }
}
