package wisoft.pack.app;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import wisoft.pack.pojo.Person;
import wisoft.pack.utils.Navinfo;
import wisoft.pack.views.NavigationView;
import wisoft.pack.views.UnPackNavigation;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "WiosftUpdatePack.perspective";

	public   String   inputStream2String   (InputStream   is)  { 
		        StringBuilder sb = new StringBuilder();
		        String readline = "";
		        try
		        {
		            /**
		             * 若乱码，请改为new InputStreamReader(is, "GBK").
		             */
		            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		            while (br.ready())
		            {
		                readline = br.readLine();
		                System.out.println(readline);
		                sb.append(readline);
		            }
		            br.close();
		        } catch (IOException ie)
		        {
		            System.out.println("converts failed.");
		        }
		        return sb.toString();
}
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		String resource = "/wisoft/pack/data/mybatis-config.xml";
		InputStream inputStream =Perspective.class.getResourceAsStream(resource);
		
		String aa=inputStream2String(inputStream);
		String mdbpath = "";
		try {
			URL url =FileLocator.toFileURL(Perspective.class.getResource("/wisoft/pack/data/wup.mdb"));
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
		aa=aa.replace("${mymdbpath}", mdbpath);
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(new ByteArrayInputStream(aa.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
