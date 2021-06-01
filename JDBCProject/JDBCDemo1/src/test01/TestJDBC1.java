package test01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC1 {
	private static String driver ="com.mysql.cj.jdbc.Driver";
	private static String url="jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
	private static String user="root";
	private static String password="root";
	public static void main(String[] args)  {
		//
		Connection con=null;
		Statement statement=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
			statement= (Statement) con.createStatement();
			String sql="insert into dept values(1,'aaa','cccc');";
			int rows = statement.executeUpdate(sql);
			System.out.println(rows);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			if(null!=con){
				try {
					con.close();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}

	}

}
