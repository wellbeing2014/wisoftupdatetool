package wisoft.pack.data;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

import javax.sql.DataSource;

public class WisoftDataSource implements DataSource {

    private static final String dirverClassName = "com.hxtt.sql.access.AccessDriver"; 
    private static final String url = "jdbc:access:///"+WisoftDataSource.class.getResource("/")+"test.mdb"; 
    private static final String user = ""; 
    private static final String pswd = ""; 
	
  //连接池 
    private static LinkedList<Connection> pool = 
    		(LinkedList<Connection>)Collections.synchronizedList(new LinkedList<Connection>()); 
    private static WisoftDataSource instance = new WisoftDataSource(); 
    static { 
            try { 
                    Class.forName(dirverClassName); 
            } catch (ClassNotFoundException e) { 
            	System.out.println(e.toString());
            } 
    } 
    
    
    /** 
     * 获取数据源单例 
     * 
     * @return 数据源单例 
     */ 
    public WisoftDataSource instance() { 
            if (instance == null) instance = new WisoftDataSource(); 
            return instance; 
    }
    
    /** 
     * 获取一个数据库连接 
     * 
     * @return 一个数据库连接 
     * @throws SQLException 
     */ 
    public Connection getConnection() throws SQLException { 
            synchronized (pool) { 
                    if (pool.size() > 0) return pool.removeFirst(); 
                    else return makeConnection(); 
            } 
    } 
    
    /** 
     * 连接归池 
     * 
     * @param conn 
     */ 
    public static void freeConnection(Connection conn) { 
            pool.addLast(conn); 
    } 
    
    private Connection makeConnection() throws SQLException { 
        return DriverManager.getConnection(url, user, pswd); 
    } 
    
    @Override
    public Connection getConnection(String username, String password) throws SQLException { 
        return DriverManager.getConnection(url, username, password); 
    } 
    
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
