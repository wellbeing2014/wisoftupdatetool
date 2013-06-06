package wisoft.pack.app;

import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import wisoft.pack.data.WisoftDataSourceFactory;
import wisoft.pack.pojo.Person;
import wisoft.pack.utils.Navinfo;
import wisoft.pack.views.NavigationView;
import wisoft.pack.views.UnPackNavigation;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "WiosftUpdatePack.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		String resource = "/wisoft/pack/data/mybatis-config.xml";
		InputStream inputStream =Perspective.class.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Person blog = (Person) session.selectOne("wisoft.pack.pojo.selectPerson", 1);
			System.out.println("查出来的名字为："+blog.getName());
		} finally {
		  session.close();
		}
		Navinfo.getInstance();
		boolean isPack =Navinfo.selOperate();
		if(isPack)
			layout.addStandaloneView(NavigationView.ID,  true, IPageLayout.LEFT, 0.25f, editorArea);
		else
			layout.addStandaloneView(UnPackNavigation.ID, true,0, 0, editorArea);
		layout.getViewLayout(NavigationView.ID).setCloseable(false);
		layout.getViewLayout(UnPackNavigation.ID).setCloseable(false);
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM,0.70f, editorArea);
		
	}
	
	
}
