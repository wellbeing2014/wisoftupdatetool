package wisoft.pack.data;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MyDataSource extends DriverManagerDataSource {
	
	public MyDataSource() {
		super();
		
		String mdbpath = "";
		try {
			URL url =FileLocator.toFileURL(MyDataSource.class.getResource("WisoftUpdatePack.db3"));
			File file =new File(url.toURI());
			mdbpath = file.getAbsolutePath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDriverClassName("org.sqlite.JDBC");
		this.setUrl("jdbc:sqlite:///"+mdbpath);
		System.out.println("我是驱动，我被spring实例化了。");
		System.out.println("我的URL是"+"jdbc:sqlite:///"+mdbpath);
	}

}
