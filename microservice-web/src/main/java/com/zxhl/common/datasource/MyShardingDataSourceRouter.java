package com.zxhl.common.datasource;

import com.zxhl.common.datasource.db.BbEnum;
import com.zxhl.common.datasource.strategy.ConsistentHash;
import com.zxhl.common.datasource.strategy.DbSplit;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class MyShardingDataSourceRouter implements ShardingDataSourceRouter {
    //分库逻辑ShardingDataSourceRouter
    @Override
    public void onDBRoute(DbSplit dbSplit, Set<String> dataSources, Object parameter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(dbSplit==null){
            ShardingContextHolder.setDBKey("test");
            return;
        }
        if(dbSplit.db().equals(BbEnum.UserDb)){
            if (parameter instanceof java.util.Map) {//map情况
                java.util.Map<Object, Object> m = (java.util.Map<Object, Object>) parameter;
                //直接指定分库host
                if (m.containsKey("_$DataSource$_")) {
                    ShardingContextHolder.setDBKey("_$DataSource$_");
                }
                else if (m.containsKey("orgid")) {
                    long orgId = (long) (m).get("orgid");
                    if(dataSources!=null) {
                        dataSources = dataSources.stream().filter(source -> !source.equals("test")).collect(Collectors.toSet());
                        ConsistentHash<String> consistentHash = new ConsistentHash<String>(new ConsistentHash.HashFunction(), 100, dataSources);
                        ShardingContextHolder.setDBKey(consistentHash.get(orgId));
                    }
                }
            }
            else{//module情况
                Method m = (Method) parameter.getClass().getMethod("getOrgid");
                Long orgId = (Long) m.invoke(parameter);
                if(dataSources!=null) {
                    dataSources = dataSources.stream().filter(source -> !source.equals("test")).collect(Collectors.toSet());
                    ConsistentHash<String> consistentHash = new ConsistentHash<String>(new ConsistentHash.HashFunction(), 100, dataSources);
                    ShardingContextHolder.setDBKey(consistentHash.get(orgId));
                }
            }
        }else if(dbSplit.db().equals(BbEnum.IhrDb)){
            ShardingContextHolder.setDBKey("test");
        }
    }

    //分表逻辑
    @Override
    public void onTableRoute(MetaObject metaStatementHandler, Object parameter) throws ClassNotFoundException {}

}
