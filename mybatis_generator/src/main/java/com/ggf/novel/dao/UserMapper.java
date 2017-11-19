package com.ggf.novel.dao;

import com.ggf.novel.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}