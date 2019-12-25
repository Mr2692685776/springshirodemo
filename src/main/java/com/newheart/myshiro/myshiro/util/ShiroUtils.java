package com.newheart.myshiro.myshiro.util;

import com.newheart.myshiro.myshiro.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * @author hanjie
 * @date 2019/12/21 10:24
 */
public class ShiroUtils {

    private static RedisSessionDAO redisSessionDAO = SpringUtils.getbean(RedisSessionDAO.class);

    /**
     * 获取当前session
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }


    public static void deleteCache(String username,boolean isRemoveSession) {
//  从缓存中获取session
        Session session = null;
        Collection<Session> activeSessions = redisSessionDAO.getActiveSessions();
        Object attribute = null;
        for (Session sessionInfo : activeSessions) {
            //遍历Session,找到该用户名称对应的Session
            attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute != null) {
                String principal = (String) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
                if (StringUtils.isNotEmpty(principal)) {
                    if (principal.equals(username)) {
                        session = sessionInfo;
                        break;
                    }
                }
            }
        }

        if (session==null || attribute==null){
            return;
        }
//        删除session
        if (isRemoveSession){
            redisSessionDAO.delete(session);
        }
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authenticator = securityManager.getAuthenticator();
        ((LogoutAware)authenticator).onLogout((SimplePrincipalCollection) attribute);
    }

}
