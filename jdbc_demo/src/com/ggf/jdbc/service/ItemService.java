package com.ggf.jdbc.service;

import com.ggf.jdbc.dao.ItemDao;
import com.ggf.jdbc.entity.Item;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by geguangfu on 2017/10/11.
 */
public class ItemService {

    /**
     * 获取所有的分类
     * @return
     */
    public List<Item> findAll() {
        Logger logger = Logger.getLogger(ItemService.class);
        logger.info("调用service层。。。");
        return ItemDao.findAll();

    }
}
