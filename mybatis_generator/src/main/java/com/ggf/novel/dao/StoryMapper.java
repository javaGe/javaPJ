package com.ggf.novel.dao;

import com.ggf.novel.entity.Story;

public interface StoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Story record);

    int insertSelective(Story record);

    Story selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Story record);

    int updateByPrimaryKey(Story record);
}