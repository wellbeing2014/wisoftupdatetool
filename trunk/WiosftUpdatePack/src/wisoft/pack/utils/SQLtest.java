package wisoft.pack.utils;

import java.io.IOException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.springframework.core.io.ClassPathResource;

public class SQLtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLExec sqlExec = new SQLExec();
		sqlExec.setDriver("oracle.jdbc.driver.OracleDriver");
		String url;
		url = "jdbc:oracle:thin:@localhost:1521:orcl";
		sqlExec.setUrl(url);
		sqlExec.setUserid("frame");
		sqlExec.setPassword("wisoft");
		sqlExec.setKeepformat(false);

		ClassPathResource res = new ClassPathResource("wisoft/pack/utils/test.sql");
		try {
			sqlExec.setSrc(res.getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute
		.getInstance(SQLExec.OnError.class, "abort")));
		sqlExec.setPrint(true);
		sqlExec.setProject(new Project());
		sqlExec.execute();
	}

}
