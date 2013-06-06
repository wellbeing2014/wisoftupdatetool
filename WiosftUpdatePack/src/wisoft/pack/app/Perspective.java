package wisoft.pack.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));   

        StringBuilder sb = new StringBuilder();   

    

        String line = null;   

        try {   

            while ((line = reader.readLine()) != null) {   

                sb.append(line + "/n");   

            }   

        } catch (IOException e) {   

            e.printStackTrace();   

        } finally {   

            try {   

                is.close();   

            } catch (IOException e) {   

                e.printStackTrace();   

            }   

        }   

    

        return sb.toString();   
}
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		String resource = "/wisoft/pack/data/mybatis-config.xml";
		InputStream inputStream =Perspective.class.getResourceAsStream(resource);
		
		//System.out.println(inputStream2String(inputStream));
		
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
