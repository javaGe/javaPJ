package com.ggf.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  对mysql的数据进行操作
 * @author: ggf
 */
public class Conn2Mysql {

    public static void main(String[] args) {
        // 获取连接
        Connection conn = getConn();

        // 查询数据库数据
        /*List<String> list = select(conn);
        for (String str : list) {
            System.out.println(str);
        }*/

        save(conn);

        // 关闭数据库
        closeConn(conn);
    }


    /**
     * 向mysql数据库插入数据
     * @param conn
     */
    public static void save(Connection conn) {
        // 创建预处理
        PreparedStatement pre =null;

        //插入数据库sql
        String sql = "insert into tb_story values(?,?,?,?,?,?,?)";

        try {
            pre = conn.prepareStatement(sql);

            pre.setString(1, "a");
            pre.setString(2, "a");
            pre.setString(3, "a");
            pre.setString(4, "a");
            pre.setString(5, "a");
            pre.setString(6, "a");
            pre.setString(7, "a");

            int num = pre.executeUpdate();
            if (num > 0) {
                System.out.println("插入成功");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询数据库
     * @param conn
     * @return
     */
    public static List<String> select(Connection conn) {
        // 创建预处理对象
        PreparedStatement pre = null;
        // 创建结果集
        ResultSet rs = null;

        //查询sql语句
        String sql = "select * from tb_story limit 0,100";

        // 存储数据集合
        List<String> dataList = new ArrayList<String>();

        try {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String author = rs.getString("author");
                String str = name +"@"+ author;
                dataList.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接资源
                pre.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }

    /**
     * 获取mysql数据库连接
     * @return
     */
    public static Connection getConn() {
        // 用户名
        String user = "root";
        //密码
        String pwd = "root";
        // 驱动
        String driver = "com.mysql.jdbc.Driver";
        // 连接url
        String url = "jdbc:mysql://localhost:3306/story?useUnicode=true&characterEncoding=UTF8";

        //创建连接对象
        Connection conn = null;

        //加载驱动
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}