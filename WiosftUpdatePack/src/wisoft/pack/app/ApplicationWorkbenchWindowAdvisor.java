package wisoft.pack.app;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
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
//    	if(Navinfo.selOperate())
//    	{	
//    		NavigationView nv =(NavigationView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(NavigationView.ID);
//    		nv.SaveNavInfo();
//    	}
//    	else
//    	{
//    		UnPackNavigation unv =(UnPackNavigation) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(UnPackNavigation.ID);
//    		unv.SaveNavInfo();
//    	}
    	return super.preWindowShellClose();
    }
}
