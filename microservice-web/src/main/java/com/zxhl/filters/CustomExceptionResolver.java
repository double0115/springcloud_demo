package com.zxhl.filters;


import com.zxhl.common.JsonResult;
import com.zxhl.common.ViewHint;
import com.zxhl.filters.exceptions.BaseException;
import com.zxhl.filters.exceptions.DaoException;
import com.zxhl.filters.exceptions.ServiceException;
import com.zxhl.utils.CommonUtil;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class CustomExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    public int getOrder() {
        //网页code码比如404会在tomcat跳出，如果想在程序捕获提高程序权限
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionResolver.class);
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        BaseException customException = null;
        try {
            // NoHandlerFoundException 未找到页面
            //TypeMismatchException 参数匹配错误
            if(ex instanceof NoHandlerFoundException){
                customException = new BaseException(ViewHint.NotFound.getKey(),ViewHint.NotFound.getValue());
            }
            else if (ex instanceof HttpRequestMethodNotSupportedException) {
                customException = new BaseException(ViewHint.Fail.getKey(),"不支持此HttpMethod");
            }
            else if(ex instanceof TypeMismatchException){
                customException = new BaseException(ViewHint.ParameterError.getKey(),ViewHint.ParameterError.getValue());
            }
            else if(ex instanceof BaseException){
                customException = (BaseException) ex;
            }
            else if(ex instanceof DaoException){
                customException = (DaoException) ex;
            }
            else if(ex instanceof ServiceException){
                customException = (ServiceException) ex;
            }
            else{
                //未定义异常返回特定异常
                customException = new BaseException(ViewHint.Exception.getKey(),"服务器异常，请稍后再试。");
            }
            LOGGER.error(1,"CustomExceptionResolver#doResolveException","",customException.getCode() + "," + customException.getMessage(), CommonUtil.getExceptionAllinformation(ex));
            new JsonResult().Json(response, customException.getCode(), customException.getMessage(), null);
        } catch (IOException e) {
            LOGGER.error(1,"CustomExceptionResolver#doResolveException","",customException.getCode() + "," + customException.getMessage(),CommonUtil.getExceptionAllinformation(ex));
        }
        return mv;
    }

    /**
     * @param request
     * @return
     */
    private   String getHttpHeader(HttpServletRequest request) {
        String strRes = null;
        if (request != null) {
            StringBuilder sb = new StringBuilder();
            Enumeration enumerations = request.getHeaderNames();
            sb.append("url=" + request.getRequestURL().toString());
            sb.append(" ； ");
            while (enumerations.hasMoreElements()) {
                String name = (String) enumerations.nextElement();
                String val = request.getHeader(name);
                sb.append(name + "=" + val);
                sb.append(" ； ");
            }
            strRes = sb.toString();
        }
        return strRes;
    }
}
