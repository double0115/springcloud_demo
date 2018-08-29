package com.zxhl.filters.exceptions;


public class ServiceException extends  BaseException{
    @Override
    public String getMessage() {
        return super.getMessage();
    }
    public ServiceException(){
        super(ExceptionHint.ServiceException.getKey(),ExceptionHint.ServiceException.getValue());
    }
    public ServiceException(int code){
        super(code);
    }
    public ServiceException(int code,String message){
        super(code,message);
    }
    public ServiceException(int code,String message,Object data){
        super(code,message,data);
    }
    public ServiceException(int code,String message,Throwable data){
        super(code,message,data);
    }
}