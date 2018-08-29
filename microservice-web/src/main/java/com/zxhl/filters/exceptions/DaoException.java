package com.zxhl.filters.exceptions;


public class DaoException extends BaseException {
    @Override
    public String getMessage() {
        return  super.getMessage();
    }
    public DaoException(){
        super(ExceptionHint.DaoException.getKey(), ExceptionHint.DaoException.getValue());
    }
    public DaoException(int code){
        super(code);
    }
    public DaoException(int code,String message){
        super(code,message);
    }
    public DaoException(int code,String message,Object data){
        super(code,message,data);
    }
    public DaoException(int code,String message,Throwable data){
        super(code,message,data);
    }
}