package wisoft.pack.app;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import wisoft.pack.utils.PackConfigInfo;
import wisoft.pack.views.NavigationView;
import wisoft.pack.views.PackNavigation;
import wisoft.pack.views.UnPackNavigation;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "WiosftUpdatePack.perspective";


	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		
		IFolderLayout navFolder = layout.createFolder("navigate",
				IPageLayout.LEFT, 0.25f, editorArea);
		
//		boolean isPack =PackConfigInfo.getInstance().selOperate();
//		if(isPack)
//			layout.addStandaloneView(NavigationView.ID,  true, IPageLayout.LEFT, 0.25f, editorArea);
//		else
//			layout.addStandaloneView(UnPackNavigation.ID, true,0, 0, editorArea);
		
		navFolder.addView(NavigationView.ID);
		navFolder.addView(UnPackNavigation.ID);
		navFolder.addView(PackNavigation.ID);
		
		layout.getViewLayout(NavigationView.ID).setCloseable(false);
		layout.getViewLayout(UnPackNavigation.ID).setCloseable(false);
		layout.addView(IConsoleConstants.ID_CONSOLE_VIEW, IPageLayout.BOTTOM,0.70f, editorArea);
		
	}
	
	
}
