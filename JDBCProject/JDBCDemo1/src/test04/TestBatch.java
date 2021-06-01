package test04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 10:47
 * @Description: test04
 * 批处理
 */
public class TestBatch {
    //jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&cachePrepStmts=true&&rewriteBatchedStatements=true";
    private static String URL="jdbc:mysql://localhost:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&cachePrepStmts=true&rewriteBatchedStatements=true";
    private static String User="root";
    private static String pwd="root";
    private static String driver="com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        testAdd();
    }
    //deptno dname loc
    //批量增加
    public static void testAdd(){
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            Class.forName(driver);
            con= DriverManager.getConnection(URL,User,pwd);
            String sql="insert into dept values(DEFAULT,?,?)";
            preparedStatement=con.prepareStatement(sql);
            for (int i = 1; i <= 10000; i++) {
                preparedStatement.setString(1,"ll");
                preparedStatement.setString(2,"aa");
                preparedStatement.addBatch();
                if(i%1000==0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            System.out.println(".....");
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
}
