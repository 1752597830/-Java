package test05;

import java.sql.*;
import java.util.LinkedList;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 13:00
 * @Description: test05
 */
public class TestTransaction1 {
    private static String URL="jdbc:mysql://localhost:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String User="root";
    private static String pwd="root";
    private static String driver="com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        testTransaction();
    }
    public static void testTransaction(){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        LinkedList<Savepoint>list=null;
        list=new LinkedList<>();
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(URL,User,pwd);
            con.setAutoCommit(false);//设置手动提交
            String sql="insert into accout values(DEFAULT,?,?)";
            preparedStatement=con.prepareStatement(sql);
            for (int i = 1; i <= 10000; i++) {
                preparedStatement.setString(1,"aaa");
                preparedStatement.setDouble(2,3000.0);
                preparedStatement.addBatch();
                if(i%1000==0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    //设置保存点，也叫回滚点
                    Savepoint sp=con.setSavepoint();
                    list.addLast(sp);
                }
                if(i%10001==0){
                    int k=1/0;
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
        }catch(Exception e){
            if(con!=null){
                try {
                    Savepoint Sp=list.get(4);
                    con.rollback(Sp);
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            if(con!=null){
                try {
                    con.commit();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
            if(con!=null){
                try {
                    con.close();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        }
    }
}
