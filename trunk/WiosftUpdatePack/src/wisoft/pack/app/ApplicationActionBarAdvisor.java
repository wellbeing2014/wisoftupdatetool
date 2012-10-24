package wisoft.pack.app;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
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
import wisoft.pack.actions.OpenNewPackDialogAction;
import wisoft.pack.actions.OpenPackEditAction;

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
    private IWorkbenchAction savenAction;
    //private IWorkbenchAction newWindowAction;
   // private OpenViewAction openViewAction;
    //private Action messagePopupAction;
    
    private OpenNewPackDialogAction openNewPackDialogAction;
    private DelPackInfoAction removePackInfoAction;
    private OpenPackEditAction openPackAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
    
    protected void makeActions(final IWorkbenchWindow window) {
        // Creates the actions and registers them.
        // Registering is needed to ensure that key bindings work.
        // The corresponding commands keybindings are defined in the plugin.xml file.
        // Registering also provides automatic disposal of the actions when
        // the window is closed.

        exitAction = ActionFactory.QUIT.create(window);
        register(exitAction);
        
        aboutAction = ActionFactory.ABOUT.create(window);
        register(aboutAction);
        
        savenAction = ActionFactory.SAVE.create(window);
        
//        newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
//        register(newWindowAction);
//        
//        openViewAction = new OpenViewAction(window, "Open Another Message View", View.ID);
//        register(openViewAction);
//        
//        messagePopupAction = new MessagePopupAction("Open Message", window);
//        register(messagePopupAction);
        
        openNewPackDialogAction = new OpenNewPackDialogAction(window,"新建更新包");
        register(openNewPackDialogAction);
        removePackInfoAction = new DelPackInfoAction(window, "删除更新包");
        register(removePackInfoAction);
        
        openPackAction = new OpenPackEditAction(window);
        register(openPackAction);
    }
    
    protected void fillMenuBar(IMenuManager menuBar) {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        
        menuBar.add(fileMenu);
        // Add a group marker indicating where action set menus will appear.
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(helpMenu);
        
        // File
//        fileMenu.add(newWindowAction);
//        fileMenu.add(new Separator());
//        fileMenu.add(messagePopupAction);
//        fileMenu.add(openViewAction);
        fileMenu.add(new Separator());
        fileMenu.add(exitAction);
        
        // Help
        helpMenu.add(aboutAction);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(toolbar, "main"));   
//        toolbar.add(openViewAction);
//        toolbar.add(messagePopupAction);
        toolbar.add(openNewPackDialogAction);
        toolbar.add(removePackInfoAction);
        toolbar.add(openPackAction);
        toolbar.add(savenAction);
    }
}
