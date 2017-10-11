package com.ggf.ssm.mapper;

import com.ggf.ssm.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PanYuanJin on 2017/10/10.
 */
@Repository
public interface UserMapper {
    List<User> findAll();
}
