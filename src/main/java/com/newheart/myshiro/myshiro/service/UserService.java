package com.newheart.myshiro.myshiro.service;

import com.newheart.myshiro.myshiro.entity.User;

/**
 * @author hanjie
 * @date 2019/12/20 10:28
 */

public interface UserService {

    User findUserByName(String username);
}
