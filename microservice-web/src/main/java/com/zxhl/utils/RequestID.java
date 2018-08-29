package com.zxhl.utils;

import java.util.UUID;

/**
 * 共享
 */
public class RequestID {
    private static ThreadLocal<String> requestID = new ThreadLocal<String>();
    private static ThreadLocal<Long> requestTime = new ThreadLocal<Long>();
    private static ThreadLocal<String> requestData = new ThreadLocal<String>();
    public static void setRequestID() {
        requestID.set(UUID.randomUUID().toString());
        requestTime.set(System.currentTimeMillis());
    }
    public static void setRequestID(String taskId) {
        requestID.set(taskId);
        requestTime.set(System.currentTimeMillis());
    }
    public static String getRequestID() {
        return StringUtil.getString(requestID.get()).replace("-","");
    }
    public static Long getRequestTime() {
        return requestTime.get();
    }
    public static void setRequestData(String data) {
        requestData.set(data);
    }
    public static String getRequestData() {
        return requestData.get();
    }
    public static void removeRequestData() {
         requestData.remove();
    }
}
