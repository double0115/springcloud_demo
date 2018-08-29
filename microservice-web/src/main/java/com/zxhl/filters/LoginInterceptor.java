package com.zxhl.filters;


import com.zxhl.common.JsonResult;
import com.zxhl.common.LoginManager;
import com.zxhl.common.ViewHint;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本功能拦截（登录）
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private LoginManager loginManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String actionNameUrl = request.getServletPath();
        if(!loginManager.preHandleAction(request,response)){
            new JsonResult().Json(response, ViewHint.HeaderError);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 判断数组是否有值
     *
     * @param urls
     * @return
     */
    private boolean hasUrl(String[] urls) {
        if (urls == null || urls.length == 0) {
            return false;
        }
        return true;
    }

    /**
     * 对url的过滤判断  增加正则和equals
     *
     * @param urls
     * @param actionNameUrl
     * @return
     */
    private boolean urlJudy(String[] urls, String actionNameUrl) {
        if (hasUrl(urls)) {
            for (String url : urls) {
                if (actionNameUrl.equals(url)) {
                    return true;
                }
                Matcher matcher = Pattern.compile(url).matcher(actionNameUrl);
                if (matcher.matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}
