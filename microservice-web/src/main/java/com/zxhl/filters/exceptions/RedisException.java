package com.zxhl.filters.exceptions;


public class RedisException extends BaseException
{
    @Override
    public String getMessage() {
        return  super.getMessage();
    }
    public RedisException(){
        super(ExceptionHint.RedisException.getKey(), ExceptionHint.RedisException.getValue());
    }
    public RedisException(int code){
        super(code);
    }
    public RedisException(int code, String message){
        super(code,message);
    }
    public RedisException(int code, String message, Object data){
        super(code,message,data);
    }
    public RedisException(int code, String message, Throwable data){
        super(code,message,data);
    }
}
