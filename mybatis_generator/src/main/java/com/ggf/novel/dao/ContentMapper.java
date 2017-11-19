package com.ggf.novel.dao;

import com.ggf.novel.entity.Content;

public interface ContentMapper {
    int insert(Content record);

    int insertSelective(Content record);
}