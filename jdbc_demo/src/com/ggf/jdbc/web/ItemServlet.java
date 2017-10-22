package com.ggf.jdbc.web;

import com.ggf.jdbc.entity.Item;
import com.ggf.jdbc.service.ItemService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by geguangfu on 2017/10/11.
 */
public class ItemServlet {
    public static Logger logger = Logger.getLogger(ItemServlet.class);
    public static void main(String[] args) {
        ItemService itemService = new ItemService();
        logger.info("初始化itemservlet.....");
        List<Item> list = itemService.findAll();
        for (Item item : list) {
            System.out.println(item.getName());
            logger.info("分类id"+item.getId());
        }
    }
}
