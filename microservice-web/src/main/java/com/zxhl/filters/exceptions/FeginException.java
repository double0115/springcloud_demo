package com.zxhl.filters.exceptions;


public class FeginException extends BaseException {
    @Override
    public String getMessage() {
        return  super.getMessage();
    }
    public FeginException(){
        super(ExceptionHint.FeginException.getKey(), ExceptionHint.FeginException.getValue());
    }
    public FeginException(int code){
        super(code);
    }
    public FeginException(int code, String message){
        super(code,message);
    }
    public FeginException(int code, String message, Object data){
        super(code,message,data);
    }
    public FeginException(int code, String message, Throwable data){
        super(code,message,data);
    }
}