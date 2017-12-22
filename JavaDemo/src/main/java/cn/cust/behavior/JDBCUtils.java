package cn.cust.behavior;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by upnoob on 2017/12/22.
 */
public class JDBCUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://106.15.194.13:3306/behavior";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "cust";
    static final String PASS = "cust1105";

    public static Connection connMySQL(){
        Connection conn = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
        return null;
    }
}
