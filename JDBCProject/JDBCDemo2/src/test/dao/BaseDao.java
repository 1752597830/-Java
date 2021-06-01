package test.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 17:48
 * @Description: test.dao
 */
public abstract class BaseDao {
    public  static int baseUpdate(String sql,Object ... args){
        int rows=0;
        Connection con=null;
        PreparedStatement preparedStatement=null;
        try {
            con= MyConnectionPool.getConnection();
            preparedStatement=con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            rows = preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            MyConnectionPool.returnConnection(con);
        }
        return rows;
    }
    public static List baseQuery(Class clazz, String sql, Object ... args){
        List list=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= MyConnectionPool.getConnection();
            preparedStatement=connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            list=new ArrayList();
            //获取字节码信息
            Field[] fields = clazz.getDeclaredFields();
            for (Field filed:fields) {
                filed.setAccessible(true);//设置属性可以访问
            }
            while(resultSet.next()){
                //通过反射创建对象
                Object obj=clazz.newInstance();
                for (Field field:fields) {
                    String fieldName=field.getName();
                    Object data=resultSet.getObject(fieldName);
                    field.set(obj,data);
                }
                list.add(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(resultSet!=null){
                try {
                    resultSet.close();
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
            MyConnectionPool.returnConnection(connection);
        }
        return list;
    }
}