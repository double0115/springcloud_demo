package com.zxhl.filters.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import com.zxhl.utils.IPCommon;
import com.zxhl.utils.StringUtil;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogFilter extends Filter<ILoggingEvent> {

    public  static   String getValue(String str,String key){
        String value = "";
        try {
            String regex2 = "\"" + key + "\":\"(.*?)\",";
            Matcher matcher = Pattern.compile(regex2).matcher(str);
            while (matcher.find()) {
                value = matcher.group(1);
                break;
            }
        }
        catch (Exception ex){
            // LOGGER.error("日志提取错误："+ CommonUtil.getExceptionAllinformation(ex));
        }
        return  value;
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if(StringUtil.isNullOrEmpty(event.getMessage())
                || (event.getMessage().indexOf("\"taskId\"")<0)){
            MDC.put("host",  "");
            MDC.put("clienthost", "");
            return null;
        }
        HttpServletRequest request=getServletContext();
        String host= IPCommon.getClientAddr(request);
        String hostName= null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        MDC.put("host",  hostName);
        MDC.put("clienthost", host);
        return null;
    }
    private HttpServletRequest getServletContext(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            return  ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }
}
