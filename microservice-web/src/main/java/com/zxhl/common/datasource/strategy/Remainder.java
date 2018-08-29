package com.zxhl.common.datasource.strategy;

/**
 * 取余
 */
public class Remainder {
    /**
     * 获取取余数
     * @param key 原数
     * @param count 被余数
     * @return
     */
    public long get(long key,int count) {
      return key%count;
    }
}
