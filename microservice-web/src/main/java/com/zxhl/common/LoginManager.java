package com.zxhl.common;

import com.alibaba.fastjson.JSON;
import com.zxhl.utils.Logger;
import com.zxhl.utils.LoggerFactory;
import com.zxhl.utils.RequestID;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service("loginManager")
public class LoginManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);

    public boolean preHandleAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestID.setRequestID(LoginCommonManager.getTaskid(request));
        String actionNameUrl = request.getServletPath();
        String deviceId = LoginCommonManager.getDeviceId(request);
        String version = LoginCommonManager.getVersion(request);
        String useragent= LoginCommonManager.getUserAgent(request);
        LOGGER.info("BaseFilter-request(请求入口)，url:{},method:{},ip:{},参数：[{}]", request.getRequestURL(),
                request.getMethod(), request.getRemoteAddr(), JSON.toJSONString(request.getParameterMap()));
        return true;
    }

}

