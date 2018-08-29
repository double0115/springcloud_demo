package com.zxhl.filters;


import com.zxhl.common.JsonResult;
import com.zxhl.common.LoginManager;
import com.zxhl.common.ViewHint;
import com.zxhl.utils.CommonUtil;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 基本功能拦截（未登录）
 */
public class UnLoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnLoginInterceptor.class);
    @Autowired
    private LoginManager loginManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUrl= request.getServletPath();
        if(!loginManager.preHandleAction(request,response)){
            new JsonResult().Json(response, ViewHint.HeaderError);
            return false;
        }
        return UnLoginedAPI(request, response, handler);
    }

    /**
     * 未登录验证
     */
    private boolean UnLoginedAPI(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            return true;
        } catch (Exception ex) {
            LOGGER.error("UnLoginedAPI:" + CommonUtil.getExceptionAllinformation(ex));
            new JsonResult().Json(response, ViewHint.Exception);
            return false;
        }
    }
}
