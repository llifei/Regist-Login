package lf.user.dao;

import lf.user.domain.User;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    private static Properties props=null;
    static{
        //加载配置文件内容到props对象中
        try {
            InputStream in=DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            props=new Properties();
            props.load(in);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回一个具体UseDao的实现类对象
     * @return
     */
    public static UserDao getUserDao() {
        /**
         * 给出一个配置文件，文件中给出UserDao接口的实现类名称
         * 这个方法获取实现类的类名，通过反射完成创建对象
         */
        String daoClassName=props.getProperty("lf.user.dao.UserDao");

        /**
         * 通过反射来创建实现类的对象
         */
        try {
            Class clazz = Class.forName(daoClassName);
            return (UserDao) clazz.newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
