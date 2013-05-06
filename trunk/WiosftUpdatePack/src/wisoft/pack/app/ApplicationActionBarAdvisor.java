package wisoft.pack.app;

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

import wisoft.pack.actions.DelPackInfoAction;
import wisoft.pack.actions.DeployPackToServerAction;
import wisoft.pack.actions.ExportPackEditAction;
import wisoft.pack.actions.OpenNewPackDialogAction;
import wisoft.pack.actions.OpenPackAction;
import wisoft.pack.actions.PackConfigAction;
import wisoft.pack.actions.SavePackEditAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of the
 * actions added to a workbench window. Each window will be populated with
 * new actions.
 */
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
    }
    
    protected void makeActions(final IWorkbenchWindow window) {

        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);
        aboutAction = ActionFactory.ABOUT.create(window);
        aboutAction.setText("关于本系统……");
        register(aboutAction);
        refreshAction = ActionFactory.REFRESH.create(window);
        register(refreshAction);
        
        
        savenAction =new  SavePackEditAction();
        register(savenAction);
        
        openNewPackDialogAction = new OpenNewPackDialogAction(window,"新建更新包");
        register(openNewPackDialogAction);
        
        removePackInfoAction = new DelPackInfoAction(window, "删除更新包");
        register(removePackInfoAction);
        
        
        packConfigAction = new PackConfigAction(window, "配置选项");
        register(packConfigAction);
        
        exportPackAction = new ExportPackEditAction(window,"导出更新包");
        register(exportPackAction);
        
        openPackAction = new OpenPackAction(window, "打开更新包");
        register(openPackAction);
        
        deployPackAction = new DeployPackToServerAction(window, "部署更新包");
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
        toolbar.add(openPackAction);
        toolbar.add(openNewPackDialogAction);
        toolbar.add(removePackInfoAction);
        toolbar.add(exportPackAction);
        toolbar.add(savenAction);
        toolbar.add(packConfigAction);
        toolbar.add(deployPackAction);
    }
    
}
