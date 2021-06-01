package test03;

import unity.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 10:45
 * @Description: test03
 */
public class TestPreparedStatement {
    private static String url="jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String user="root";
    private static String password="root";
    private static String driver="com.mysql.cj.jdbc.Driver";
    public static void main(String[] args){
        //testAdd();
        //testDelete();
        //testUpdate();
        //testQuery();
    }

    public static void testAdd(){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            String sql="insert into Emp values(DEFAULT,?,?,?,?,?,?,?)";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,"lala");
            preparedStatement.setString(2,"Manager");
            preparedStatement.setInt(3,7902);
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
            preparedStatement.setDouble(5,300.00);
            preparedStatement.setDouble(6,0.0);
            preparedStatement.setInt(7,30);
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(con!=null){
                try {
                    con.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void testDelete(){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,password);
            String sql="delete from Emp where ename = ?";
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,"lala");
            int row =preparedStatement.executeUpdate();
            System.out.println(row);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(null!=preparedStatement){
                try {
                    preparedStatement.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(null!=con){
                try {
                    con.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void testUpdate(){
        Connection con=null;
        PreparedStatement preparedstatement=null;
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,password);
            String sql="Update emp set job=? where ename=?";
            preparedstatement=con.prepareStatement(sql);
            preparedstatement.setString(1,"CLERK");
            preparedstatement.setString(2,"lala");
            int row = preparedstatement.executeUpdate();
            System.out.println(row);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedstatement!=null){
                try {
                    preparedstatement.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(con!=null){
                try {
                    con.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void testQuery(){
        List<Emp> list=null;
        Connection con=null;
        PreparedStatement preparedstatement=null;
        ResultSet result=null;
        list=new ArrayList<>();
        try {
            Class.forName(driver);
            con=DriverManager.getConnection(url,user,password);
            String sql="select *from emp where deptno =?";
            preparedstatement=con.prepareStatement(sql);
            preparedstatement.setInt(1,30);
            result=preparedstatement.executeQuery();
            while(result.next()){
                Integer empno=result.getInt("empno");
                String ename=result.getString("ename");
                String job=result.getString("job");
                Integer mgr=result.getInt("mgr");
                Date date=result.getDate("hiredate");
                Double sal=result.getDouble("sal");
                Double comm=result.getDouble("comm");
                Integer deptno=result.getInt("deptno");
                Emp emp=new Emp(empno,ename,job,mgr,date,sal,comm,deptno);
                list.add(emp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(result!=null){
                try {
                    result.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(preparedstatement!=null){
                try {
                    preparedstatement.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(con!=null){
                try {
                    con.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        for (Emp e:list) {
            System.out.println(e);
        }
    }
}
