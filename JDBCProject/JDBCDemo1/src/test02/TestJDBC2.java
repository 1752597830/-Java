package test02;

import unity.Accout;

import java.sql.*;
import java.util.Scanner;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/22 - 05 - 22 - 21:08
 * @Description: test01
 */
public class TestJDBC2 {
    private static String url="jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user="root";
    private static String password="root";
    private static String driver ="com.mysql.cj.jdbc.Driver";
    public static Accout getAccout(String username,Double pwd){
        Accout accout=null;
        Connection con=null;
        ResultSet resultSet=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,user,password);
            String sql="select *from accout where sname=? and balance=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setDouble(2,pwd);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name=resultSet.getString("sname");
                Double bal=resultSet.getDouble("balance");
                accout=new Accout(name,bal);
                System.out.println(accout);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return accout;
    }
    public static void main(String[] args) {
        String sname;
        Double balance;
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入账户：");
        sname=sc.next();
        System.out.println("请输入密码");
        balance=sc.nextDouble();
        Accout accout=getAccout(sname,balance);
        System.out.println(null!=accout?"登录成功":"登录失败");
        sc.close();
    }

}
