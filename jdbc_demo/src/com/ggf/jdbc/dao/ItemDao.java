package com.ggf.jdbc.dao;

import com.ggf.jdbc.entity.Item;
import com.ggf.jdbc.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装对数据库的操作
 * Created by geguangfu on 2017/10/10.
 */
public class ItemDao {
    /**
     *
     * 查询所有的分类信息
     * @return list集合
     */
    public static List<Item> findAll() {
        Connection conn = null; // 创建数据库连接
        PreparedStatement ps = null; // 预处理对象
        ResultSet rs = null; // 结果集

        try {
            // 获取连接
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM item";
            ps = conn.prepareStatement(sql); //执行批处理
            rs = ps.executeQuery(); // 获取结果集

            List<Item> items = new ArrayList<Item>();
            // 遍历结果集
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getString("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                items.add(item);
            }

            return items;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(conn);
        }
        return null;
    }
}
