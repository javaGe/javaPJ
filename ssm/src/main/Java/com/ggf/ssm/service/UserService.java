package com.ggf.ssm.service;

import com.ggf.ssm.mapper.UserMapper;
import com.ggf.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PanYuanJin on 2017/10/10.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }
}
