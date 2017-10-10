package com.ggf.jdbc.web;

import com.ggf.jdbc.entity.Item;
import com.ggf.jdbc.service.ItemService;

import java.util.List;

/**
 * Created by geguangfu on 2017/10/11.
 */
public class ItemServlet {
    public static void main(String[] args) {
        ItemService itemService = new ItemService();
        List<Item> list = itemService.findAll();
        for (Item item : list) {
            System.out.println(item.getName());
        }
    }
}
