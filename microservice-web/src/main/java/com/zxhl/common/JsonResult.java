package com.zxhl.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zxhl.utils.RequestID;
import com.zxhl.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JsonResult {
    //region-------------------------------web数据交互公共model

    /**
     * response输出json
     */
    public void Json(HttpServletResponse response, int code, String message, String data) throws IOException {
        SResultModel result = new SResultModel();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setTaskId(RequestID.getRequestID());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getOutputStream().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue).getBytes("utf-8"));
    }

    public void Json(HttpServletResponse response, ViewHint viewHint) throws IOException {
        SResultModel result = new SResultModel();
        result.setCode(viewHint.getKey());
        result.setMessage(viewHint.getValue());
        result.setTaskId(RequestID.getRequestID());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getOutputStream().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue).getBytes("utf-8"));
    }

    public String Json(int code, String message, Object data) {
        SResultModel result = new SResultModel();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setTaskId(RequestID.getRequestID());
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    public String Json(int code, String message, Object data, String taskId) {
        SResultModel result = new SResultModel();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setTaskId(taskId);
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    public String Json(int code, String message) {
        return Json(code, message, null);
    }

    public String Json(ViewHint viewHint) {
        return Json(viewHint.getKey(), viewHint.getValue());
    }

    public String Json(ViewHint viewHint, Object object) {
        return Json(viewHint.getKey(), viewHint.getValue(), object);
    }

    public String JsonSuccess(Object data) {
        return Json(ViewHint.Success.getKey(), "", data);
    }

    public String JsonSuccessWx() {
        return Json(1, "", null);
    }

    public String JsonSuccessWx(Object data) {
        return Json(1, "", data);
    }

    public String JsonSuccess() {
        return Json(ViewHint.Success.getKey(), "", null);
    }

    public String JsonFail() {
        return Json(ViewHint.Fail.getKey(), ViewHint.Fail.getValue(), null);
    }

    public String JsonFail(String message) {
        return Json(ViewHint.Fail.getKey(), message, null);
    }

    public String JsonParameterError() {
        return Json(ViewHint.ParameterError.getKey(), ViewHint.ParameterError.getValue(), null);
    }

    public String Json(boolean success, String message) {
        return Json(success, message, null);
    }

    public String Json(boolean success) {
        return Json(success, "", null);
    }

    public String Jsonp(int code, String message, Object data, HttpServletRequest request) {
        String callback = request.getParameter("callback");
        SResultModel result = new SResultModel();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setTaskId(RequestID.getRequestID());
        String jsonresult = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        if (!StringUtil.isNullOrEmpty(callback)) {
            jsonresult = callback + "(" + jsonresult + ")";
        }
        return jsonresult;
    }

    public String Jsonp(Object data, HttpServletRequest request) {
        String callback = request.getParameter("callback");
        String jsonresult = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
        if (!StringUtil.isNullOrEmpty(callback)) {
            jsonresult = callback + "(" + jsonresult + ")";
        }
        return jsonresult;
    }

    public String Jsonp(int code, String message, HttpServletRequest request) {
        return Jsonp(code, message, null, request);
    }

    public String Jsonp(int code, HttpServletRequest request) {
        return Jsonp(code, "", null, request);
    }

    public String JsonpSuccess(Object data, HttpServletRequest request) {
        return Jsonp(ViewHint.Success.getKey(), ViewHint.Success.getValue(), data, request);
    }

    public String JsonpFail(HttpServletRequest request) {
        return Jsonp(ViewHint.Fail.getKey(), ViewHint.Fail.getValue(), null, request);
    }

    public String JsonpParameterError(HttpServletRequest request) {
        return Jsonp(ViewHint.ParameterError.getKey(), ViewHint.ParameterError.getValue(), request);
    }
    public String Json(int code, String message, Object data,boolean isNull) {
        ResultModel result = new ResultModel();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        if(isNull)
            return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        else
            return JSON.toJSONString(result);
    }

    //此方法只做兼容用，以后废除
    public String Json(boolean success, String message, Object data) {
        SResultModel result = new SResultModel();
        result.setSuccess(success);
        result.setCode(success ? ViewHint.Success.getKey() : ViewHint.Fail.getKey());
        result.setMessage(message);
        result.setTaskId(RequestID.getRequestID());
        result.setData(data);
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
    //endregion
}
