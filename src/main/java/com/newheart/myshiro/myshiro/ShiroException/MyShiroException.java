package com.newheart.myshiro.myshiro.ShiroException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hanjie
 * @date 2019/12/21 8:42
 */
@ControllerAdvice
public class MyShiroException {

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String,Object> defaultErrorHandler(){
        Map<String, Object> map = new HashMap<>(4);
        map.put("403","权限不足");
        return null;
    }
}
