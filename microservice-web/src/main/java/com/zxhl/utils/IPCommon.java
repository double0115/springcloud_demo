package com.zxhl.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class IPCommon {
    private static final Logger LOGGER = LoggerFactory.getLogger(IPCommon.class);
    private static HttpServletRequest request;

    public static String getCurrentIP() {
        String ip = "";
        try {

            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                    .getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();

                Enumeration<?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ia = (InetAddress) e2.nextElement();
                    //_logger.info("IP是否为Inet4Address:" +(ia instanceof Inet4Address) );
                    LOGGER.info("获取到的客户端IP有 :" + ia.getHostAddress() + "是否为Inet4Address" + (ia instanceof Inet4Address));
//					getLocalAddress();
                    if (ia instanceof Inet6Address)
                        continue;

                    if (ia instanceof Inet4Address)
                        ip = ia.getHostAddress();

                    if (ip != null && !ip.trim().equals("")
                            && !ip.equals("127.0.0.1")) {
                        break;
                    }
                }

                if (ip != null && !ip.trim().equals("")
                        && !ip.equals("127.0.0.1")) {
                    break;
                }

            }
        } catch (Exception e) {
            LOGGER.error("获取客户端IP异常" + e);
        }
        return ip;
    }

    public static String getClientAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 0 && ip.contains(":")) {
            ip = "127.0.0.1";
        }
        ip = ip.split(",")[0];
        return ip;
    }
}
