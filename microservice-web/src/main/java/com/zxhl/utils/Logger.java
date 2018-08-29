package com.zxhl.utils;

import org.slf4j.Marker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mafei on 2017/2/24.
 */
public class Logger implements org.slf4j.Logger{
    private org.slf4j.Logger log = null;
    public Logger(Class<?> clazz){
        log=org.slf4j.LoggerFactory.getLogger(clazz.getName());
    }

    private String getFormat(){
        return String.format("{\"taskId\":\"%s\",\"date\":\"%s\",\"msg\":\"{}\"}",RequestID.getRequestID(), getNowDate());
    }
    private String getFormat(String format){
        return String.format("{\"taskId\":\"%s\",\"date\":\"%s\",\"msg\":\"%s\"}",RequestID.getRequestID(), getNowDate(), format);
    }
    private String getEFormat(int time,String action,String url,String param,String msg){
        return String.format("{\"action\":\"%s\",\"url\":\"%s\",\"param\":\"%s\",\"taskId\":\"%s\",\"date\":\"%s\",\"elapsedTime\":%s,\"msg\":%s}",action,url,param,RequestID.getRequestID(), getNowDate(),time, msg);
    }
    private String getMsg(String msg){
        return String.format("{\"taskId\":\"%s\",\"date\":\"%s\",\"msg\":\"%s\"}",RequestID.getRequestID(), getNowDate(), msg);
    }
    private String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public void error(String url,String params,Throwable ex){
        log.error(getMsg("[url:"+url+",params:"+params+",exception:"+ CommonUtil.getExceptionAllinformation(ex)+"]"));
    }
    public void info(String url,String params,String requestStr) {
        log.info(getMsg("[url:"+url+",params:"+params+",requestStr:"+ requestStr+"]"));
    }
    /**
     * 统计接口运行时间
     * @param time  接口运行时间
     * @param action  方法
     * @param msg  msg
     * @param params  参数
     * @param url  请求地址
     */
    public void info(int time,String action,String url,String params,String msg) {
        log.info(getEFormat(time,action,url,params,msg));
    }

    public void error(int time,String action,String url,String params,Throwable ex) {
        log.error(getEFormat(time,action,url,params,CommonUtil.getExceptionAllinformation(ex)));
    }

    public void error(int time,String action,String url,String params,String exmsg) {
        log.error(getEFormat(time,action,url,params,exmsg));
    }

    public void error(String msg){
        log.error(getFormat(),msg);
    }
    public void info(String msg){
        log.info(getFormat(),msg);
    }
    public void error(String format, Object arg){
        log.error(getFormat(format),arg);
    }
    public void info(String format, Object arg){
        log.info(getFormat(format),arg);
    }

    public String getName() {
        return log.getName();
    }

    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    public void trace(String msg) {
        log.trace(getFormat(),msg);

    }

    public void trace(String format, Object arg) {
        log.trace(getFormat(format),arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        log.trace(getFormat(format),arg1,arg2);
    }

    public void trace(String format, Object... arguments) {
        log.trace(getFormat(format),arguments);
    }

    public void trace(String msg, Throwable t) {
        log.trace(getMsg(msg),t);
    }

    public boolean isTraceEnabled(Marker marker) {
        return log.isTraceEnabled(marker);
    }

    public void trace(Marker marker, String msg) {
        log.trace(marker, getMsg(msg));
    }

    public void trace(Marker marker, String format, Object arg) {
        log.trace(marker, getFormat(format),arg);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        log.trace(marker, getFormat(format),arg1,arg2);
    }

    public void trace(Marker marker, String format, Object... argArray) {
        log.trace(marker, getFormat(format),argArray);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        log.trace(marker, getMsg(msg),t);
    }

    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    public void debug(String msg) {
        log.debug(getMsg(msg));
    }

    public void debug(String format, Object arg) {
        log.debug(getFormat(format),arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        log.debug(getFormat(format),arg1,arg2);
    }

    public void debug(String format, Object... arguments) {
        log.debug(getFormat(format),arguments);
    }

    public void debug(String msg, Throwable t) {
        log.debug(getMsg(msg),t);
    }

    public boolean isDebugEnabled(Marker marker) {
        return log.isDebugEnabled(marker);
    }

    public void debug(Marker marker, String msg) {
        log.debug(marker, getMsg(msg));
    }

    public void debug(Marker marker, String format, Object arg) {
        log.debug(marker, getFormat(format), arg);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        log.debug(marker, getFormat(format), arg1, arg2);
    }

    public void debug(Marker marker, String format, Object... arguments) {
        log.debug(marker, getFormat(format), arguments);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        log.debug(marker, getMsg(msg), t);
    }

    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    public void info(String format, Object arg1, Object arg2) {
        log.info(getFormat(format), arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        log.info(getFormat(format), arguments);
    }

    public void info(String msg, Throwable t) {
        log.info(getMsg(msg), t);
    }

    public boolean isInfoEnabled(Marker marker) {
        return log.isInfoEnabled(marker);
    }

    public void info(Marker marker, String msg) {
        log.info(marker, getMsg(msg));
    }

    public void info(Marker marker, String format, Object arg) {
        log.info(marker, getFormat(format), arg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        log.info(marker, getFormat(format), arg1,arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        log.info(marker, getFormat(format), arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        log.info(marker, getMsg(msg), t);
    }

    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    public void warn(String msg) {
        log.warn(getMsg(msg));
    }

    public void warn(String format, Object arg) {
        log.warn(getFormat(format), arg);
    }

    public void warn(String format, Object... arguments) {
        log.warn(getFormat(format), arguments);
    }

    public void warn(String format, Object arg1, Object arg2) {
        log.warn(getFormat(format),  arg1,  arg2);
    }

    public void warn(String msg, Throwable t) {
        log.warn(getMsg(msg), t);
    }

    public boolean isWarnEnabled(Marker marker) {
        return log.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
        log.warn(marker, getMsg(msg));
    }

    public void warn(Marker marker, String format, Object arg) {
        log.warn(marker, getFormat(format), arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        log.warn(marker, getFormat(format), arg1,arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
        log.warn(marker, getFormat(format), arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        log.warn(marker, getMsg(msg), t);
    }

    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    public void error(String format, Object arg1, Object arg2) {
        log.error(getFormat(format), arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        log.error(getFormat(format), arguments);
    }

    public void error(String msg, Throwable t) {
        log.error(getMsg(msg), t);
    }

    public boolean isErrorEnabled(Marker marker) {
        return log.isErrorEnabled(marker);
    }

    public void error(Marker marker, String msg) {
        log.error(marker, getMsg(msg));
    }

    public void error(Marker marker, String format, Object arg) {
        log.error(marker, getFormat(format), arg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        log.error(marker, getFormat(format), arg1,arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        log.error(marker, getFormat(format), arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        log.error(marker,getMsg(msg),t);
    }
}

