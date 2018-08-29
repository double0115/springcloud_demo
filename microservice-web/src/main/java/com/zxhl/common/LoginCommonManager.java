package com.zxhl.common;

import com.zxhl.utils.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class LoginCommonManager {
    //设备id
    public static final String DEVICEID = "deviceid";
    //用户代理信息
    public static final String USER_AGENT = "User-Agent";
    //登陆点id，从header获取
    public static final String DEPTID_HEADER_NAME = "departmentId";
    public static final String VERSION = "version";

    public static String getCookie(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie c : cookies)
            {
                if (c.getName().equals(cookieName))
                {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    //设备id
    public static String getDeviceId(HttpServletRequest request) {
        String deviceid= request.getHeader(LoginCommonManager.DEVICEID);
        if (StringUtil.isNullOrEmpty(deviceid)) {
            return getCookie(request, LoginCommonManager.DEVICEID);
        }
        return deviceid;
    }

    //登陆点id，从header获取
    public  static long getDepartmentId(HttpServletRequest request){
        return  StringUtil.getLong(request.getHeader(LoginCommonManager.DEPTID_HEADER_NAME));
    }

    //version
    public  static String getVersion(HttpServletRequest request){
        return  StringUtil.getString(request.getHeader(LoginCommonManager.VERSION));
    }
    //获取请求中用户代理信息(包括用户系统、浏览器、设备型号等，例如获取系统类型iOS、Android)
    public  static String getUserAgent(HttpServletRequest request){
        return  StringUtil.getString(request.getHeader(LoginCommonManager.USER_AGENT));
    }
    //获取taskid
    public  static  String getTaskid(HttpServletRequest request){
        String version =  LoginCommonManager.getVersion(request);
        String deviceId = LoginCommonManager.getDeviceId(request);
        if(StringUtil.isNullOrEmpty(deviceId)){
            deviceId=UUID.randomUUID().toString();
        }
        String tag= StringUtil.isNullOrEmpty(version)?"0tsd1":"0tsd0";
        String  taskId=deviceId+"d"+ LoginCommonManager.getDepartmentId(request)+tag+ UUID.randomUUID().toString()+ StringUtil.getString(version).replace(".","");
        taskId = String.format("%s-%s","zxhl", taskId.toLowerCase());
        return taskId;
    }
}
