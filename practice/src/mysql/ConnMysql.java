package mysql;

import java.sql.*;

/**
 * @Deacription： 连接数据库的简单练习
 * @author: geguangfu
 * @Date: 2017/10/29
 * @Time: 14:25
 * @Version: 1.0
 */
public class ConnMysql {
    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver"); // 注册驱动，需要导包
        String user = "root"; // 用户名
        String pwd = "root"; // 密码
        String url = "jdbc:mysql://localhost:3306/scott"; // 数据库
        Connection conn = DriverManager.getConnection(url, user, pwd); // 获取连接
        System.out.println(conn);
        String sql = "SELECT * FROM user";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }

        conn.close();
    }
}
