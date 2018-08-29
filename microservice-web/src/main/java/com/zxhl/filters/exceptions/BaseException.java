package com.zxhl.filters.exceptions;



public class BaseException extends Exception{
    private int code;
    private String message;
    private Object data;
    Throwable cause;
    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }

    public BaseException(int code){
        this.code = code;
    }
    public BaseException(int code,String message){
        this.code = code;
        this.message = message;
    }
    public BaseException(int code,String message,Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public BaseException(int code,String message,Throwable cause){
        this.code = code;
        this.message = message;
        this.data = data;
        this.cause = cause;
    }
    public int getCode() {
        return code;
    }
}

