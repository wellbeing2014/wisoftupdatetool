package wisoft.pack.utils;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

public class OracleDbAccess {
	private Connection con;//连接
	private PreparedStatement  pstm;
	private String dbIp="127.0.0.1";
	private String dbPort="1521";
	private String dbName="ICSS";
	private String dbUser="jsp";
	private String dbPwd="jsp";
	private String conString="jdbc:oracle:thin:@"+dbIp+":"+dbPort+":"+dbName;

	public OracleDbAccess(){}

	


	//用户自己指定连接字符串 frame/wisoft@192.10.110.205@orcl
	public  void setValue(String connstr){
		
		this.dbIp=connstr.substring(connstr.indexOf("@")+1,connstr.lastIndexOf("/"));
		if(connstr.indexOf(":")>0)
			this.dbPort=connstr.substring(connstr.indexOf(":"),connstr.lastIndexOf("/"));
		else
			this.dbPort = "1521";
		this.dbName=connstr.substring(connstr.lastIndexOf("/")+1);
		this.dbUser=connstr.substring(0, connstr.indexOf("/"));;
		this.dbPwd=connstr.substring(connstr.indexOf("/")+1,connstr.indexOf("@"));
		this.conString="jdbc:oracle:thin:@"+dbIp+":"+dbPort+":"+dbName;
	
	}


	//获取数据库连接
	private void getCon(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//加载驱动包
			try{
				con=DriverManager.getConnection(conString,dbUser, dbPwd);//初始化数据库连接
			}catch(Exception e){
				System.out.println("初始化数据库连接失败！");
			}
		}catch(Exception e){
			System.out.println("驱动包加载失败！");
		}
	}


	private void excute(String sql){
		if(con==null){//如果是第一次连接，则初始化连接
			getCon();
		}
		try {
			pstm=con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ResultSet getResultSet(String sql){//根据sql语句返回一个ResultSet结果集
		excute(sql);
		try {
			return pstm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getFirstUnit(String sql){//根据sql语句返回结果集的第一行第一列单元格数据
		excute(sql);
		try {
			String value;
			ResultSet rs=pstm.executeQuery();
			rs.next();
			value=rs.getString(1);
			rs.close();
			return value;//返回第一个单元格数据
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList getFirstRow(String sql){//根据sql返回结果集的第一行所有数据。
		excute(sql);
		try {
			ArrayList aList=new ArrayList();//新建一个arrayList对象。
			ResultSet rs=pstm.executeQuery();
			rs.next();//将指针指向结果集的第一行。
			int columns=rs.getMetaData().getColumnCount();//获取该结果集的列数。
			for(int i=1;i<columns+1;i++){//因为用索引取值是从1开始的。
				aList.add(rs.getString(i));//将第一行的所有列都以String形式封装到Arraylist。
			}
			return aList;//返回封装好的arraylist.
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public int getInt(String sql){//返回受影响的行数
		excute(sql);
		try {
			return	pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public void close(){//当最后退出程序时执行就行了，因为是单例模式，所以不用每次都关
		try {
			pstm.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
		con.close();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}