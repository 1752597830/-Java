package test01;

import java.sql.*;

public class TestJDBC2 {
	private static String url="jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
	private static String user="root";
	private static String password="root";
	public static void main(String[] args) throws SQLException {
		//Class forName("com.mysql.cj.jdbc.Driver");
		//
		Driver driver=new com.mysql.cj.jdbc.Driver();
		//注册机
		DriverManager.registerDriver(driver);
		Connection con=DriverManager.getConnection(url,user,password);
		Statement statement=con.createStatement();
		String sql="select *from dept";
		ResultSet result= statement.executeQuery(sql);
		while(result.next()){
			int deptno=result.getInt("deptno");
			String dname=result.getString("dname");
			String loc=result.getString("loc");
			System.out.println(deptno+" "+dname+" "+loc);
		}
		result.close();
		statement.close();
		con.close();
	}
}
