package com.newheart.myshiro.myshiro.service.impl;

import com.newheart.myshiro.myshiro.dao.UserDao;
import com.newheart.myshiro.myshiro.entity.User;
import com.newheart.myshiro.myshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hanjie
 * @date 2019/12/20 10:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }
}
