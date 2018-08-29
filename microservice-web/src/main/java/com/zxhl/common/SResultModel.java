package com.zxhl.common;

import com.zxhl.utils.RequestID;
import com.zxhl.utils.StringUtil;


public class SResultModel<T> {
    private Integer code;
    private String message;
    private T data;
    private boolean success = false;
    private String taskId;

    public SResultModel() {
        this.taskId = RequestID.getRequestID();
    }

    public Boolean getSuccess() {
        if(!success)
            success= code==200?true:false;
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTaskId() {
        return StringUtil.isNullOrEmpty(taskId)? RequestID.getRequestID():taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getCode() {
        if(code==null  && success)
            code= 200;

        if(code==null && !success)
            code= 500;
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        this.setSuccess(code == 200);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
