package com.ggf.jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据连接工具
 * Created by geguangfu on 2017/10/10.
 */
public class DBUtils {
    //连接池对象
    private static ComboPooledDataSource bs = null;

    //静态代码块加载配置文件，只加载一次
    static {
        Properties p = new Properties();//配置类
        try {
            //加载配置文件
            p.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            //获取配置文件中的参数
            String driver = p.getProperty("driver");
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String pwd = p.getProperty("password");
            String initSize = p.getProperty("initSize");
            String maxSize = p.getProperty("maxSize");

            //实例化连接池
            bs = new ComboPooledDataSource();

            //配置连接池的参数
            bs.setDriverClass(driver);
            bs.setJdbcUrl(url);
            bs.setUser(user);
            bs.setPassword(pwd);
            bs.setInitialPoolSize(Integer.parseInt(initSize));
            bs.setMaxConnectionAge(Integer.parseInt(maxSize));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }//static end

    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return bs.getConnection();
    }

    /**
     * 关闭连接
     * @param connection 数据库连接
     */
    public static void getClose(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //public static void main(String[] args) throws SQLException {
    //    System.out.println(DBUtils.getConnection());
    //}
}