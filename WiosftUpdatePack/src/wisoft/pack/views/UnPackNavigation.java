package wisoft.pack.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.RootModel;
import wisoft.pack.utils.Navinfo;

public class UnPackNavigation extends ViewPart {

	public static final String ID = "wisoft.pack.views.UnPackNavigation"; //$NON-NLS-1$
	private TreeViewer viewer; // 树查看器
	public RootModel root;
	public UnPackNavigation() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		//Composite container = new Composite(parent, SWT.NONE);
		
		// 创建树查看器
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	    // 设置内容提供其和标签提供器
	   // treeViewer.setContentProvider(new NavigatorTreeViewerContentProvider());
	   // treeViewer.setLabelProvider(new NavigatorTreeViewerLabelProvider());
	    // 读入数据
	   // treeViewer.setInput(NavigatorEntityFactory.setNavigatorEntity());
	    // 自定义的方法
	    // 设置视图的工具栏
	    setViewToolBar();
		
		createActions();
		initializeToolBar();
		initializeMenu();
		readNavInfo();
		
		//去掉console 工具栏上的 多余按钮		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
		IViewPart viewpart = page.findView(IConsoleConstants.ID_CONSOLE_VIEW);  
		IActionBars actionBar = viewpart.getViewSite().getActionBars();  
		IToolBarManager toolbarMgr = actionBar.getToolBarManager();  
		IContributionItem[] items = toolbarMgr.getItems();  
		for  (IContributionItem item : items) {  
			if  (item instanceof ActionContributionItem) {  
				IAction action = ((ActionContributionItem) item).getAction();  
				String text = action.getText();  
				if  (text.equals( "Open Console" )) {  
					toolbarMgr.remove(item);  
				}  
			}  
		}  
		actionBar.updateActionBars(); 
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	private void setViewToolBar() {
	    // IActionBars:Used by a part to access its menu, toolbar, and
	    // status line managers.
	    IActionBars bars = getViewSite().getActionBars();
	    // 定义工具栏
	    IToolBarManager toolBarManager = bars.getToolBarManager();
	    // 定义下拉菜单栏
	    IMenuManager menuManager = bars.getMenuManager();
	    // DrillDownAdapter:Implements a simple web style navigation
	    // metaphor for a TreeViewer. Home, back,
	    // and "drill into" functions are supported for the viewer,
	    DrillDownAdapter drillDownAdapter = new DrillDownAdapter(viewer);
	    // 为工具栏添加“goHome”，“goBack”，“goInto”操作
	    drillDownAdapter.addNavigationActions(menuManager);
	    // 为菜单栏添加“goHome”，“goBack”，“goInto”操作
	    drillDownAdapter.addNavigationActions(toolBarManager);
	}
	
	public PackInfoModel[] getAllPackInfo()
	{
		// IStructuredSelection selection = (IStructuredSelection)this.viewer.getTree().gegetSelection();
		 List<PackInfoModel> selPack =new ArrayList<PackInfoModel>();
		 TreeItem[] treeItems =this.viewer.getTree().getItems();
		 for(int i=0;i<treeItems.length;i++)
		 {
			 if(treeItems[i].getData() instanceof PackInfoModel)
				 selPack.add((PackInfoModel)treeItems[i].getData());
		 }
		 
		 return selPack.toArray(new PackInfoModel[0]);
	}
	
	public void addPackInfo(PackInfoModel pack)
	{
		this.root.addPackInfo(pack);
		this.viewer.refresh();
	}
	private void readNavInfo()
	{
		List<PackInfoModel> packs =Navinfo.getInstance().readPackNavInfo();
		for(PackInfoModel pack :packs)
		{
			addPackInfo(pack);
		}
	}
	public void SaveNavInfo()
	{
		PackInfoModel[] pack = getAllPackInfo();
		Navinfo.getInstance().SaveNavInfo(pack);
	}
	
}
