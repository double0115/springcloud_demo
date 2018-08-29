package com.zxhl.utils;

/**
 * Created by Administrator on 2017/2/24.
 */
public class LoggerFactory {
    public LoggerFactory() {
    }

    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }
}
