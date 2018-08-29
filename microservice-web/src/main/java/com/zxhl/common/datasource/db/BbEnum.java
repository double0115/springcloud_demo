package com.zxhl.common.datasource.db;

public enum  BbEnum {
    //userdb
    UserDb(10),
    //ihrdb
    IhrDb(20);
    private int value;
    private BbEnum(int value) {
        this.value=value;
    }
    public  int getValue(){
        return  value;
    }

    public static BbEnum getName(int index) {
        for (BbEnum c : BbEnum.values()) {
            if (c.getValue() == index) {
                return c;
            }
        }
        return null;
    }
}
