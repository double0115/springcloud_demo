package com.zxhl.filters.fegin;

import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mafei
 */
@Configuration
public class FeignConfiguration {
    //fegin日志级别设置
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    //重试机制  默认重试5次
//    @Bean
//    public Retryer feginRetryer(){
//        //第一个参数period是请求重试的间隔算法参数，第二个参数maxPeriod 是请求间隔最大时间，第三个参数是重试的次数
//        Retryer retryer = new Retryer.Default(100, TimeUnit.SECONDS.toMillis(10), 3);
//        return retryer;
//    }
    // //重试机制  禁用
    @Bean
    public Retryer feginRetryer() {
       // FeignClientsConfiguration
        return Retryer.NEVER_RETRY;
    }

    //fegin超时时间   如果配置文件同时设置，这里生效
    @Bean
    public Request.Options feginOption(){
        int max_connection_timeout = 2000;
        int max_soket_timeout = 5000;
        Request.Options option = new Request.Options(max_connection_timeout,max_soket_timeout);
        return option;
    }



    //region springmvc基础上自定义契约（禁止url编码）
    @Autowired(required = false)
    private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Autowired(required = false)
    private List<FeignFormatterRegistrar> feignFormatterRegistrars = new ArrayList<>();

    @Bean
    @ConditionalOnMissingBean
    public Contract feignContract(ConversionService feignConversionService) {
        return new MySpringMvcContract(this.parameterProcessors, feignConversionService);
    }

    @Bean
    public FormattingConversionService feignConversionService() {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (FeignFormatterRegistrar feignFormatterRegistrar : feignFormatterRegistrars) {
            feignFormatterRegistrar.registerFormatters(conversionService);
        }
        return conversionService;
    }
    //endregion

}
