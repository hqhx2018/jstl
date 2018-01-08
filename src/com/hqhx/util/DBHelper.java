package com.hqhx.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelper {

	private static String className;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try {
			//先加载配置文件，获取连接数据库的信息
			Properties p=new Properties();
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
			//通过key获取value
			className=p.getProperty("jdbc.className");
			url=p.getProperty("jdbc.url");
			username=p.getProperty("jdbc.username");
			password=p.getProperty("jdbc.password");
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取连接的方法
	public Connection getConn(){
		// 3.和数据库建立连接，通过驱动管理器获取和数据库的连接对象
		Connection conn=null;
		try {
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("连接成功.....");
			} catch (SQLException e) {
				System.out.println("连接失败");
			e.printStackTrace();
		}
		return conn;
	}
	
	//insert into dept values (?,?,?)
	//delete from dept where deptno=?
	//
	public int CUD(String sql,Object...params) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		// 1.添加驱动包
		// 2.加载驱动类
		try {
			// 4.通过Connection连接对象获取一个预编译语句对象
			ps = conn.prepareStatement(sql);
			//5.动态给预编译语句对象设置参数
			for(int i=0;i<params.length;i++){
			ps.setObject(i+1, params[i]);
			}
			// 5.通过预编译语句对象执行sql语句
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7.释放资源
			close(ps,conn);
		}
		return 0;
	}



	//Select * from dept;Dept.Class
	public List query(String sql,Class c,Object...params) {
		for (Object object : params) {
			System.out.println("-----------------");
			System.out.println(object);
		}
		//定义一个List<Dept> depts;
		List entitys=new ArrayList();
		Connection conn = getConn();
		PreparedStatement ps= null;
		ResultSet rs = null;
		// 1.添加驱动包
		// 2.加载驱动类
		try {
			// 4.通过Connection连接对象获取一个语句对象
			ps = conn.prepareStatement(sql);
			//5.设置参数
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			// 5.通过语句对象向数据库发送静态sql语句
			rs = ps.executeQuery();
			//获取ResultSetMetaData对象，该对象存储了查询的结果集中的信息，例如结果集的列数，结果集的字段名
			ResultSetMetaData rsmd=rs.getMetaData();
			int colCount=rsmd.getColumnCount();
			
			//取出数据
			while(rs.next()){
				//动态创建一个对象
				Object object=null;
				try {
					object = c.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i=1;i<=colCount;i++){
					//获取第i列的值
					Object value=rs.getObject(i);
					//获取第i列的字段名
					String colName=rsmd.getColumnName(i);
					//构建方法名
					String methodName="set"+colName.substring(0,1).toUpperCase()+colName.substring(1);
					//动态获取方法
					if(value!=null){
					Method m=c.getMethod(methodName, value.getClass());
					//使用反射把取出的数据动态设置到object对象中
					m.invoke(object, value);
					}
				}
				
				//添加到集合中
				entitys.add(object);
			}
			return entitys;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7.释放资源
			close(rs,ps,conn);
		}
		return null;
	}

	public void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(stmt, conn);
	}


	public void close(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
}
