package test05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 11:30
 * @Description: test05
 * 事物处理
 */
public class TestTransaction {
    //jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String URL="jdbc:mysql://localhost:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static String User="root";
    private static String pwd="root";
    private static String driver="com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        testTransaction();
    }

    /*
    * 事物处理，
    * 利用connection.setAutoCommit设置事物处理 默认是true 自动提交
    * 修改为false 手动提交
    * rollback回滚数据
    * 防止异常数据没有完全提交
    * 在finally中进行手动提交commit
    * 在catch中进行回滚 rollback
    * */
    public static void testTransaction(){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(URL,User,pwd);
            con.setAutoCommit(false);
            String sql="update accout set balance=balance+? where id=?";
            preparedStatement=con.prepareStatement(sql);
            //转出
            preparedStatement.setDouble(1,-100);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();
            //转入
            //int i=1/0;
            preparedStatement.setDouble(1,100);
            preparedStatement.setInt(2,2);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            if(con!=null){
                try {
                    con.rollback();
                }catch(SQLException m){
                    m.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            if(con!=null){
                try {
                    con.commit();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(con!=null){
                try {
                    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
