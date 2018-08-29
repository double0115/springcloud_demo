package com.zxhl.filters.exceptions;


public class ZKException extends BaseException {
    @Override
    public String getMessage() {
        return  super.getMessage();
    }
    public ZKException(){
        super(ExceptionHint.DaoException.getKey(), ExceptionHint.DaoException.getValue());
    }
    public ZKException(int code){
        super(code);
    }
    public ZKException(int code, String message){
        super(code,message);
    }
    public ZKException(int code, String message, Object data){
        super(code,message,data);
    }
    public ZKException(int code, String message, Throwable data){
        super(code,message,data);
    }
}