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

import wisoft.pack.interfaces.IPackNavigation;
import wisoft.pack.models.FolderArchivedModel;
import wisoft.pack.models.FolderUnUpdateModel;
import wisoft.pack.models.FolderUpdatedModel;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackFolder;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoContentProvider;
import wisoft.pack.models.PackInfoLabelProvider;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.RootModel;
import wisoft.pack.utils.Navinfo;

public class UnPackNavigation extends ViewPart implements IPackNavigation {

	public static final String ID = "wisoft.pack.views.UnPackNavigation"; //$NON-NLS-1$
	private TreeViewer viewer; // ���鿴��
	public PackFolderModel root;
	public PackFolderModel unUpdateFolder;
	public PackFolderModel updatedFolder;
	public PackFolderModel archiveFolder;
	public UnPackNavigation() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		//Composite container = new Composite(parent, SWT.NONE);
		
		// �������鿴��
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	    // ���������ṩ��ͱ�ǩ�ṩ��
	   // treeViewer.setContentProvider(new NavigatorTreeViewerContentProvider());
	   // treeViewer.setLabelProvider(new NavigatorTreeViewerLabelProvider());
	    // ��������
	   // treeViewer.setInput(NavigatorEntityFactory.setNavigatorEntity());
	    // �Զ���ķ���
	    // ������ͼ�Ĺ�����
		
		
		viewer.setContentProvider(new PackInfoContentProvider());
		viewer.setLabelProvider(new PackInfoLabelProvider());
		viewer.setInput(createDummyModel());
		
	    setViewToolBar();
		
		createActions();
		initializeToolBar();
		initializeMenu();
		readNavInfo();
		
		//ȥ��console �������ϵ� ���ఴť		
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

	
	private Model createDummyModel() {
		root =new PackFolderModel(null,null);
	    unUpdateFolder = new PackFolderModel(root,PackFolder.UNUPDATE);
	    updatedFolder = new PackFolderModel(root,PackFolder.UPDATED);
	    archiveFolder = new PackFolderModel(root,PackFolder.ARCHIVED);
	    return root;
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
	    // ���幤����
	    IToolBarManager toolBarManager = bars.getToolBarManager();
	    // ���������˵���
	    IMenuManager menuManager = bars.getMenuManager();
	    // DrillDownAdapter:Implements a simple web style navigation
	    // metaphor for a TreeViewer. Home, back,
	    // and "drill into" functions are supported for the viewer,
	    DrillDownAdapter drillDownAdapter = new DrillDownAdapter(viewer);
	    // Ϊ��������ӡ�goHome������goBack������goInto������
	    drillDownAdapter.addNavigationActions(menuManager);
	    // Ϊ�˵�����ӡ�goHome������goBack������goInto������
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
	
	public void addUnUpdatePackInfo(PackInfoModel pack)
	{
		this.unUpdateFolder.addChild(pack);
		this.viewer.refresh();
	}
	
	
	
	private void readNavInfo()
	{
		PackFolderModel packs =Navinfo.getInstance().readPackNavInfo();
		this.viewer.setInput(packs);
		this.viewer.refresh();
	}
	public void SaveNavInfo()
	{
		PackInfoModel[] pack = getAllPackInfo();
		Navinfo.getInstance().SaveNavInfo(pack);
	}
	
}
