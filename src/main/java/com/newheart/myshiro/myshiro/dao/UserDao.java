package com.newheart.myshiro.myshiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newheart.myshiro.myshiro.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author hanjie
 * @date 2019/12/20 10:28
 */
@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    User findUserByName(String username);
}
