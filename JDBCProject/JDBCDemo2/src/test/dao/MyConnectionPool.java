package test.dao;

import util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 19:45
 * @Description: test.dao
 * 连接池
 */
public class MyConnectionPool {
    private static String URL;
    private static String User;
    private static String pwd;
    private static String driver;
    private static LinkedList<Connection>pool;
    private static int initSize;
    private static int maxSize;

    static{
        PropertiesUtil propertiesUtil=new PropertiesUtil("/jdbc.properties");
        URL = propertiesUtil.getProperties("URL");
        User=propertiesUtil.getProperties("User");
        pwd = propertiesUtil.getProperties("pwd");
        driver = propertiesUtil.getProperties("driver");
        initSize = Integer.parseInt(propertiesUtil.getProperties("initSize"));
        maxSize = Integer.parseInt(propertiesUtil.getProperties("maxSize"));
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pool=new LinkedList<Connection>();
        for (int i = 0; i < initSize; i++) {
            Connection con=initConnection();
            if(con!=null){
                pool.add(con);
                System.out.println("创建"+con.hashCode()+"成功");
            }
        }
    }
    private static Connection initConnection(){
        try {
            return DriverManager.getConnection(URL,User,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static Connection getConnection(){
        Connection con=null;
        if(pool.size()>0){
            con=pool.removeFirst();
            System.out.println("连接池中还有连接"+con.hashCode());
        }else{
            con=initConnection();
            System.out.println("连接池为空，创建连接"+con.hashCode());
        }
        return con;
    }
    //归还连接
    public static void returnConnection(Connection con){
        if(con!=null){
            try {
                if(!con.isClosed()){
                    if(pool.size()<maxSize){
                        try {
                            con.setAutoCommit(true);
                            System.out.println("设置连接"+con.hashCode()+"自动提交");
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        pool.addLast(con);
                        System.out.println("连接池未满，归还连接"+con.hashCode());
                    }else{
                        try {
                            con.close();
                            System.out.println("连接池满了，关闭连接"+con.hashCode());
                        }catch (SQLException e1){
                            e1.printStackTrace();
                        }
                    }
                }else{
                    System.out.println("连接:"+con.hashCode()+"已经关闭,无需归还");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            System.out.println("传入为null，不可归还");
        }
    }
}
