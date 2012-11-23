package wisoft.pack.app;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import wisoft.pack.views.NavigationView;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
    }
//    @SuppressWarnings("restriction")
//    @Override
//    public void postWindowClose() {
//    	NavigationView nv =(NavigationView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(NavigationView.ID);
//    	nv.SaveNavInfo();
//    } 
    
    @Override
    public boolean preWindowShellClose() {
    	// TODO Auto-generated method stub
    	NavigationView nv =(NavigationView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(NavigationView.ID);
    	nv.SaveNavInfo();
    	return super.preWindowShellClose();
    }
}
