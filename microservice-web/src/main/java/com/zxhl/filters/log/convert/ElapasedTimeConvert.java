package com.zxhl.filters.log.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zxhl.utils.StringUtil;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ElapasedTimeConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        if(StringUtil.isNullOrEmpty(event.getMessage())
                || (event.getMessage().indexOf("\"taskId\"")<0)){
            return "0";
        }
        String elapsedTime="0";
        if(event.getMessage().indexOf("\"action\"")>=0
                && event.getMessage().indexOf("\"elapsedTime\"")>=0) {
            elapsedTime=getValue(event.getMessage(),"elapsedTime");
        }
        DecimalFormat df=new DecimalFormat("0.0000");
        elapsedTime= df.format((float) StringUtil.getInt(elapsedTime)/10000);
        return elapsedTime.replace(".","");
    }

    public static void main(String[] args) {
        DecimalFormat df=new DecimalFormat("0.0000");
        System.out.println(df.format((float) StringUtil.getInt(8)/10000));
    }
    private  String getValue(String str,String key){
        String value = "";
        try {
            String regex2 = "\"" + key + "\":(\\d+),";
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
}
