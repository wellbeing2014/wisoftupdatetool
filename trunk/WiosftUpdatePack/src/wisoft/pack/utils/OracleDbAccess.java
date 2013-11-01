package wisoft.pack.utils;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

public class OracleDbAccess {
	private Connection con;//����
	private PreparedStatement  pstm;
	private String dbIp="127.0.0.1";
	private String dbPort="1521";
	private String dbName="ICSS";
	private String dbUser="jsp";
	private String dbPwd="jsp";
	private String conString="jdbc:oracle:thin:@"+dbIp+":"+dbPort+":"+dbName;

	public OracleDbAccess(){}

	


	//�û��Լ�ָ�������ַ��� frame/wisoft@192.10.110.205@orcl
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


	//��ȡ���ݿ�����
	private void getCon(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//����������
			try{
				con=DriverManager.getConnection(conString,dbUser, dbPwd);//��ʼ�����ݿ�����
			}catch(Exception e){
				System.out.println("��ʼ�����ݿ�����ʧ�ܣ�");
			}
		}catch(Exception e){
			System.out.println("����������ʧ�ܣ�");
		}
	}


	private void excute(String sql){
		if(con==null){//����ǵ�һ�����ӣ����ʼ������
			getCon();
		}
		try {
			pstm=con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ResultSet getResultSet(String sql){//����sql��䷵��һ��ResultSet�����
		excute(sql);
		try {
			return pstm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public String getFirstUnit(String sql){//����sql��䷵�ؽ�����ĵ�һ�е�һ�е�Ԫ������
		excute(sql);
		try {
			String value;
			ResultSet rs=pstm.executeQuery();
			rs.next();
			value=rs.getString(1);
			rs.close();
			return value;//���ص�һ����Ԫ������
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList getFirstRow(String sql){//����sql���ؽ�����ĵ�һ���������ݡ�
		excute(sql);
		try {
			ArrayList aList=new ArrayList();//�½�һ��arrayList����
			ResultSet rs=pstm.executeQuery();
			rs.next();//��ָ��ָ�������ĵ�һ�С�
			int columns=rs.getMetaData().getColumnCount();//��ȡ�ý������������
			for(int i=1;i<columns+1;i++){//��Ϊ������ȡֵ�Ǵ�1��ʼ�ġ�
				aList.add(rs.getString(i));//����һ�е������ж���String��ʽ��װ��Arraylist��
			}
			return aList;//���ط�װ�õ�arraylist.
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public int getInt(String sql){//������Ӱ�������
		excute(sql);
		try {
			return	pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	public void close(){//������˳�����ʱִ�о����ˣ���Ϊ�ǵ���ģʽ�����Բ���ÿ�ζ���
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