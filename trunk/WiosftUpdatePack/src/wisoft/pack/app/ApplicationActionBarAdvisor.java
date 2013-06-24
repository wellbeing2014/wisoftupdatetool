package wisoft.pack.app;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

import wisoft.pack.actions.DelPackInfoAction;
import wisoft.pack.actions.DeployPackToServerAction;
import wisoft.pack.actions.ExportPackEditAction;
import wisoft.pack.actions.OpenNewPackDialogAction;
import wisoft.pack.actions.OpenPackAction;
import wisoft.pack.actions.PackConfigAction;
import wisoft.pack.actions.SavePackEditAction;
import wisoft.pack.utils.PackConfigInfo;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of the
 * actions added to a workbench window. Each window will be populated with
 * new actions.
 */
@SuppressWarnings("restriction")
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    // Actions - important to allocate these only in makeActions, and then use them
    // in the fill methods.  This ensures that the actions aren't recreated
    // when fillActionBars is called with FILL_PROXY.
    private IWorkbenchAction exitAction;
    private IWorkbenchAction aboutAction;
    private IWorkbenchAction refreshAction;

    private SavePackEditAction savenAction;
    private OpenNewPackDialogAction openNewPackDialogAction;
    private DelPackInfoAction removePackInfoAction;
    private ExportPackEditAction exportPackAction;
    private PackConfigAction packConfigAction;
    private OpenPackAction openPackAction;
    private DeployPackToServerAction deployPackAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
        removeDuplicateAction();
    }
    
    protected void makeActions(final IWorkbenchWindow window) {

        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);
        aboutAction = ActionFactory.ABOUT.create(window);
        aboutAction.setText("���ڱ�ϵͳ����");
        register(aboutAction);
        refreshAction = ActionFactory.REFRESH.create(window);
        register(refreshAction);
        
        
        savenAction =new  SavePackEditAction();
        register(savenAction);
        
        openNewPackDialogAction = new OpenNewPackDialogAction(window,"�½����°�");
        register(openNewPackDialogAction);
        
        removePackInfoAction = new DelPackInfoAction(window, "ɾ�����°�");
        register(removePackInfoAction);
        
        
        packConfigAction = new PackConfigAction(window, "����ѡ��");
        register(packConfigAction);
        
        exportPackAction = new ExportPackEditAction(window,"�������°�");
        register(exportPackAction);
        
        openPackAction = new OpenPackAction(window, "�򿪸��°�");
        register(openPackAction);
        
        deployPackAction = new DeployPackToServerAction(window, "������°�");
        register(deployPackAction);

    }
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", "wisoft.pack.file");
        MenuManager ediltMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
        MenuManager toolMenu = new MenuManager("&Properties", IWorkbenchActionConstants.PROPERTIES);
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        
        ediltMenu.add(exitAction);
        toolMenu.add(packConfigAction);
        helpMenu.add(aboutAction);
        fileMenu.add(exitAction);
        
        menuBar.add(fileMenu);
        menuBar.add(ediltMenu);
        menuBar.add(toolMenu);
        menuBar.add(helpMenu);
        
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));   
        boolean isPack =PackConfigInfo.getInstance().selOperate();
		if(isPack)
		{
			toolbar.add(exportPackAction);
			toolbar.add(openNewPackDialogAction);
		}
		else
		{
			toolbar.add(openPackAction);
			toolbar.add(deployPackAction);
		}
		toolbar.add(removePackInfoAction);
		toolbar.add(savenAction);
		toolbar.add(packConfigAction);
    }
    
    @SuppressWarnings("restriction")   
    public void removeDuplicateAction()   
    {   
    	ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
        IActionSetDescriptor[] actionSets = reg.getActionSets();
        // removing annoying gotoLastPosition Message.
        String actionSetId = "org.eclipse.ui.edit.text.actionSet.navigation"; //$NON-NLS-1$
        for (int i = 0; i <actionSets.length; i++)
        {
            if (!actionSets[i].getId().equals(actionSetId))    continue;
            IExtension ext = actionSets[i].getConfigurationElement()
                .getDeclaringExtension();
            reg.removeExtension(ext, new Object[] { actionSets[i] });
        }
        // Removing convert line delimiters menu.
        actionSetId = "org.eclipse.ui.edit.text.actionSet.annotationNavigation"; //$NON-NLS-1$
        for (int i = 0; i <actionSets.length; i++)
        {
            if (!actionSets[i].getId().equals(actionSetId))
                continue;
            IExtension ext = actionSets[i].getConfigurationElement()
                .getDeclaringExtension();
            reg.removeExtension(ext, new Object[] { actionSets[i] });
        }
    }  
    
    
    
    
    
    
}
