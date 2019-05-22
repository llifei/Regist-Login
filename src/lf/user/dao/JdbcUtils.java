package lf.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * v1.0
 * author lifei
 */
public class JdbcUtils {
    private static Properties props=null;

    //只在JdbcUtils类被加载时执行一次
    static {
        /**
         * 1.加载配置文件
         * 2.加载驱动类
         * 3.调用DriverManager.getConnection()
         */
        //加载配置文件
        try {
            InputStream in = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties");
            props = new Properties();
            props.load(in);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        //加载驱动类
        try {
            Class.forName(props.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws   SQLException {
        //得到Connetion
        Connection con= DriverManager.getConnection(props.getProperty("url")
                ,props.getProperty("username"),props.getProperty("password"));
        return con;
    }
}