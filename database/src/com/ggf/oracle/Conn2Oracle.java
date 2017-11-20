package com.ggf.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by PanYuanJin on 2017/11/16.
 */
public class Conn2Oracle {
    public static void main(String[] args) {
        System.out.println(getConn());
    }

    /**
     * 获取oracle连接
     * @return
     */
    public static Connection getConn() {
        // 用户名
        String user = "gsxx";
        // 密码
        String pwd = "gsxx";
        // oracle驱动
        String driver = "oracle.jdbc.OracleDriver";
        // url
        String url = "jdbc:oracle:thin:@192.168.0.18:1521:oradb01";

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭连接
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
