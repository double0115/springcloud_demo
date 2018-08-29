package com.zxhl.common.datasource.strategy;

import com.zxhl.common.datasource.db.BbEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public  @interface  DbSplit {
    //库名  userdb--》friend库   ，  ihrdb--》ihrdb库
    BbEnum db() default BbEnum.UserDb;
    //分库用的表字段
    //String cloumn() default "orgid";
    //获取分库策略
    //String strategy();
}
