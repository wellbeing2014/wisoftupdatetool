package wisoft.pack.data;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

public class WisoftDataSourceFactory implements DataSourceFactory {

	public static WisoftDataSourceFactory instance = new WisoftDataSourceFactory();
	
	public WisoftDataSourceFactory() {
	}
	@Override
	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		
		WisoftDataSource ws = new WisoftDataSource();
		return ws.instance();
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}
	public DataSource getWisoftDataSource()
	{
		return instance.getDataSource();
	}

	
}
