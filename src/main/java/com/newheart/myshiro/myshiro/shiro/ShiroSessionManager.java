package com.newheart.myshiro.myshiro.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * shiroSession管理器
 * @author hanjie
 * @date 2019/12/21 9:22
 */
public class ShiroSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "token";

    public ShiroSessionManager() {
        super();
    }

    /**
     * 重写方法实现从请求头获取Token,便于接口统一
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        /**
         *  1. 获取token
         *  2. 如果token不为null,给Requset设置属性
         * */
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (StringUtils.isNotEmpty(sessionId)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,true);
            return sessionId;
        }else {
            return  super.getSessionId(request,response);
        }
    }
}
