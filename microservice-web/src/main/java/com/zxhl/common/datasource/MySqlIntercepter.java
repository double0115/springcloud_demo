package com.zxhl.common.datasource;


import com.zxhl.common.datasource.strategy.DbSplit;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_FACTORY;
import static org.apache.ibatis.reflection.SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY;

@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class MySqlIntercepter implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlIntercepter.class);

    public ShardingDataSourceRouter getShardingDataSourceRouter() {
        return shardingDataSourceRouter;
    }

    public void setShardingDataSourceRouter(ShardingDataSourceRouter shardingDataSourceRouter) {
        this.shardingDataSourceRouter = shardingDataSourceRouter;
    }

    //分库分表策略
    ShardingDataSourceRouter shardingDataSourceRouter;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,  new DefaultReflectorFactory() );

        //分库逻辑
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        //Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        MappedStatement mappedStatement=(MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        //com.zhaopin.ihr.BlogMapper.getBlog  获取方法的命名空间
        String selectId=mappedStatement.getId();
        String className = selectId.substring(0, selectId.lastIndexOf("."));
        Class<?> classObj = Class.forName(className);
        DbSplit dbSplit = classObj.getAnnotation(DbSplit.class);
        //if (dbSplit != null) {
            shardingDataSourceRouter.onDBRoute(
                    dbSplit,
                    MyRoutingDataSource.dataSources,
                    boundSql.getParameterObject());
        //}

        //分表逻辑
        shardingDataSourceRouter.onTableRoute(metaStatementHandler, boundSql.getParameterObject());

        //region 打印sql
        long start = System.currentTimeMillis();
        Object returnValue = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = (end - start);
        Configuration configuration = mappedStatement.getConfiguration();
        LOGGER.info((int)time,selectId, showSql(configuration, boundSql),"","");
        //endregion

        // 传递给下一个拦截器处理
        return returnValue;
    }

    //拼接sql
    public  String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value = "";
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        if(value.indexOf("$")>=0){
            value=value.replace("$","\\$");
        }
        return value;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
