package com.ggf.ssm.controller;

import com.ggf.ssm.pojo.User;
import com.ggf.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by PanYuanJin on 2017/10/10.
 */
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("find")
    public List<User> findAll() {
        return userService.findAll();
    }
}
