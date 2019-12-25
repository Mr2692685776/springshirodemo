package com.newheart.myshiro.myshiro.shiro;


import com.alibaba.druid.util.Utils;
import com.newheart.myshiro.myshiro.entity.User;
import com.newheart.myshiro.myshiro.service.UserService;
import com.newheart.myshiro.myshiro.util.ShiroUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanjie
 * @date 2019/12/20 10:33
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("order");
        authorizationInfo.addRole("role");
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//                token 由账号，密码组成
        String principal = (String) token.getPrincipal();
        User user = userService.findUserByName(principal);
        if (!StringUtils.isEmpty(user.getPassword())){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
            //验证成功开始踢人(清除缓存和Session)
            ShiroUtils.deleteCache(principal,true);
            return info;
        }

        return null;
    }
}
