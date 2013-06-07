package wisoft.pack.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import wisoft.pack.dao.PersonDao;
import wisoft.pack.data.WisoftSessionFactory;
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
		SqlSessionFactory sql =WisoftSessionFactory.instance.sqlSessionFactory;
		SqlSession session = null;
		try {
	        session = sql.openSession();
	        PersonDao persondao = (PersonDao) session.getMapper(PersonDao.class);
	        Person person = persondao.selectPerson(1);
	        if(person == null)
	            System.out.println("null");
	        else
	            System.out.println(person.getName());
	        } 
        finally {
            session.close();
        }		
		String editorArea = layout.getEditorArea();
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
