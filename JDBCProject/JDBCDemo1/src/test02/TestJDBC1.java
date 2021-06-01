package test02;

import unity.Accout;

import java.sql.*;
import java.util.Scanner;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/22 - 05 - 22 - 10:35
 * @Description: test01
 */
public class TestJDBC1 {
    private static String url="`jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user="root";
    private static String password="root";
    private static String driver ="com.mysql.cj.jdbc.Driver";
    public static Accout getAccout(String username, Double pwd){
        Connection con=null;
        Statement statement=null;
        ResultSet result=null;
        Accout accout=null;
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,user,password);
            String sql="select * from accout where sname ='"+username+"' and balance ='"+pwd+"'";
            statement=con.createStatement();
            result=statement.executeQuery(sql);
            while(result.next()){
                String use=result.getString("sname");
                Double pass=result.getDouble("balance");
                accout=new Accout(use,pass);
                System.out.println(accout);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                result.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return accout;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        String username=sc.next();
        Double pwd=sc.nextDouble();
        Accout accout=getAccout(username,pwd);
        System.out.println(accout==(null)?"登录失败":"登录成功");
    }
}