package com.zxhl.common;

import com.zxhl.utils.RequestID;
import com.zxhl.utils.StringUtil;

/**
 * Created by chenggang on 2016/2/19.
 */
public class ResultModel<T> {
    private Integer code;
    private String message;
    private T data;
    private String taskId;

    public ResultModel() {
        this.taskId = RequestID.getRequestID();
    }

    public String getTaskId() {
        return StringUtil.isNullOrEmpty(taskId)? RequestID.getRequestID():taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
