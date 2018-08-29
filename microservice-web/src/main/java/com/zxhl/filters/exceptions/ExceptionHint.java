package com.zxhl.filters.exceptions;


public enum ExceptionHint {
    DaoException("操作失败",502),
    ServiceException("操作失败",503),
    RedisException("操作失败",504),
    ZKException("操作失败",505),
    FeginException("操作失败",506);

    // 成员变量
    private String name;
    private Integer index;

    // 构造方法
    private ExceptionHint(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(String index) {
        for (ExceptionHint c : ExceptionHint.values()) {
            if (c.getKey().equals(index)) {
                return c.name;
            }
        }
        return "";
    }
    public String getValue() {
        return name;
    }
    public Integer getKey() {
        return index;
    }
}