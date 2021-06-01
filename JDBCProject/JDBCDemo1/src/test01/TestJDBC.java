package test01;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestJDBC {
	public static void main(String[] args)throws Exception {
//		// 1.加载驱动Driver
//		Driver driver=new com.mysql.cj.jdbc.Driver();
//		//2.注册驱动DriverManager
//		DriverManager.registerDriver(driver);

		// 也可以通过反射加载驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//3.获得连接Connection
		/*
		user:用户名
		passwo：密码
		url：统一资源定位符
		1协议
		2IP
		3端口号3306
		4数据库名称
		5参数
		协议://ip:端口/数据库名称（资源路径）?参数名=参数值$参数名=参数值....
		jdbc:mysql://localhost:3306/mytestsql?useSSL=false$useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
		url="jdbc:mysql://localhost:3306/mytestsql?useSSL=false$useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai"
		 */
		String url="jdbc:mysql://127.0.0.1:3306/mytestsql?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
		String user="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,user,password);
		//4.获得语句对象Statement
		Statement statement=con.createStatement();
		//5.执行语句，返回结果
		String sql="insert into dept values(12,'SALES','DALLAS');";
		int rows=statement.executeUpdate(sql);
		System.out.println("影响行数"+rows);
		//6.关闭服务  释放资源
		statement.close();
		con.close();
	}
}
