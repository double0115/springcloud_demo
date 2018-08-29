package com.zxhl.filters.fegin;


import com.zxhl.utils.CommonUtil;
import com.zxhl.utils.Logger;
import com.zxhl.utils.RequestID;
import com.zxhl.utils.StringUtil;
import feign.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static feign.Util.*;

/**
 * 请求拦截（RequestInterceptor)，  fegin日志输出（继承Logger）
 */
@Component
public class FeginInterceptor extends feign.Logger implements RequestInterceptor {
    private static final Logger LOGGER = com.zxhl.utils.LoggerFactory.getLogger(FeginInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        MDC.put("taskId", RequestID.getRequestID());
        //分布式taskid传给provider
        template.header("task-id", RequestID.getRequestID());
    }


    @Override
    protected void log(String configKey, String format, Object... args) {

    }

    /**
     * fegin 日志请求格式重写
     * @param configKey  fegin接口类+方法名称
     * @param logLevel 日志级别
     * @param request
     */
    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        String bodyText=null;
        StringBuilder sb=new StringBuilder();
        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
            for (String field : request.headers().keySet()) {
                for (String value : valuesOrEmpty(request.headers(), field)) {
                    sb.append(String.format("[%s: %s]", field, value));
                }
            }
            if (request.body() != null) {
                if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                     bodyText =
                            request.charset() != null ? new String(request.body(), request.charset()) : null;
                }
            }
        }

       String data= configKey+",$,"+request.url()+ ",$,"+bodyText;
        RequestID.setRequestData(data);
    }

    @Override
    protected void logRetry(String configKey, Level logLevel) {}
    /**
     * fegin 日志请求格式重写
     * @param configKey  fegin接口类+方法名称
     * @param logLevel 日志级别
     * @param response
     */
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        try {
            int status = response.status();
            int timeStr = StringUtil.getInt(elapsedTime);
            String data = null;
            String action = null;
            String url = null;
            String param = null;
            String requestData = RequestID.getRequestData();
            if (!StringUtil.isNullOrEmpty(requestData)) {
                String[] arr = requestData.split(",\\$,");
                if (arr.length == 3) {
                    action = requestData.split(",\\$,")[0].replace(",","");
                    url = requestData.split(",\\$,")[1];
                    param = requestData.split(",\\$,")[2];
                }
            }
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                if (response.body() != null && !(status == 204 || status == 205)) {
                    byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                    data = decodeOrDefault(bodyData, UTF_8, "Binary data");
                    if(CommonUtil.isSuccess(data,url) || url.indexOf("MICROSERVICE-IHRAPI")>=0){
                        LOGGER.info(timeStr, action, url, param, data);
                    }else{
                        LOGGER.error(timeStr,action,url,param,data);
                    }
                    return response.toBuilder().body(bodyData).build();
                } else {
                    if(CommonUtil.isSuccess(data,url)  || StringUtil.getString(url).indexOf("MICROSERVICE-IHRAPI")>=0){
                        LOGGER.info(timeStr, action, url, param, data);
                    }else{
                        LOGGER.error(timeStr,action,url,param,data);
                    }
                }
            }

            return response;
        }
        catch (Exception ex){
            throw  ex;//交给统一异常处理
        }
        finally {
            RequestID.removeRequestData();
        }
    }

    //logIOException  此方法只拦截IOException异常
    @Override
    protected IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
        RequestID.removeRequestData();
        StringWriter sw = new StringWriter();
        ioe.printStackTrace(new PrintWriter(sw));
        LOGGER.error((int)elapsedTime,"fegin#logIOException,"+ioe.getClass().getSimpleName(),configKey,"",sw.toString());
        return ioe;
    }
}
