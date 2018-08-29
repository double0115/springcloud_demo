package com.zxhl.common.datasource;

import java.util.Set;

/**
 * 保存当前选择的库名
 */
public class ShardingContextHolder {
    //当前数据库
    private static final ThreadLocal<String> tlDbKey = new ThreadLocal<String>();
    //所有数据库key类别
    private static final ThreadLocal<Set<String>> tlDb = new ThreadLocal<Set<String>>();

    public static String getDBKey() {
        return tlDbKey.get();
    }

    public static void setDBKey(String dbKey) {
        tlDbKey.set(dbKey);
    }


    public static Set<String> getDB() {
        return tlDb.get();
    }

    public static void setDB(Set<String> dbKey) {
        tlDb.set(dbKey);
    }
}
