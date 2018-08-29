package com.zxhl.filters.log.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zxhl.filters.log.LogFilter;
import com.zxhl.utils.StringUtil;


public class ModelnameConvert extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        if(StringUtil.isNullOrEmpty(event.getMessage())
                || (event.getMessage().indexOf("\"taskId\"")<0)){
            return null;
        }
        String action="";
        if(event.getMessage().indexOf("\"action\"")>=0
                && event.getMessage().indexOf("\"elapsedTime\"")>=0) {
            action= LogFilter.getValue(event.getMessage(),"modulename");
        }
        return action;
    }
}
