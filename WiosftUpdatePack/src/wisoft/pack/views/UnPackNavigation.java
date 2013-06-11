package wisoft.pack.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import wisoft.pack.app.Activator;
import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.edits.PackInfoEditor;
import wisoft.pack.edits.PackInfoInput;
import wisoft.pack.interfaces.IPackNavigation;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackFolder;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoContentProvider;
import wisoft.pack.models.PackInfoLabelProvider;
import wisoft.pack.models.PackInfoModel;

public class UnPackNavigation extends ViewPart implements IPackNavigation {

	public static final String ID = "wisoft.pack.views.UnPackNavigation"; //$NON-NLS-1$
	private TreeViewer viewer; // 树查看器
	private PackFolderModel root;

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
		
		
		viewer.setContentProvider(new PackInfoContentProvider());
		viewer.setLabelProvider(new PackInfoLabelProvider());
		viewer.setInput(createDummyModel());
		hookDoubleClickAction();
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

	
	private Model createDummyModel() {
		root =new PackFolderModel(null,PackFolder.DEFALUT);

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
	
	
	private void hookDoubleClickAction()
	{
		this.viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
				//(PackInfoModel)selection.getFirstElement();
				IWorkbenchPage workbenchPage = getViewSite().getPage();
				IEditorPart editorPart ;
				final PackInfoModel packinfo;
				if(selection.getFirstElement() instanceof PackInfoModel)
				{
					packinfo =((PackInfoModel)selection.getFirstElement());
					if(packinfo.getEditInput()==null)
					{
						packinfo.setEditInput(new PackInfoInput(packinfo));
					}
					packinfo.readFromXML();
//				packinfo.saveIntoXML();
					editorPart = workbenchPage.findEditor(packinfo.getEditInput());
					if(editorPart!=null)
						workbenchPage.bringToTop(editorPart);
					else
					{
						try {
							editorPart = workbenchPage.openEditor(packinfo.getEditInput(), PackInfoEditor.ID);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					editorPart.addPropertyListener(new IPropertyListener() {
						
						@Override
						public void propertyChanged(Object source, int propId) {
							// TODO Auto-generated method stub
							if(propId==ISaveablePart2.PROP_DIRTY)
							{
//							packinfo.isdirty = true;
//							packinfo.setName("*"+packinfo.getName());
//							viewer.refresh();
							}
						}
					});
					Activator.getDefault().setCurrent_pack(packinfo);
				}	
//				else
//				{
//					Model md =((Model) selection.getFirstElement());
//					packinfo = (PackInfoModel)md.getParent();
//				}
			}
		});
	}
	
	public void addUnUpdatePackInfo(PackInfoModel pack)
	{
		this.root.getChildFolder(PackFolder.UNUPDATE, true).addChild(pack);
		this.viewer.refresh();
	}
	
	
	
	private void readNavInfo()
	{
//		this.root =Navinfo.getInstance().readPackNavInfo();
//		this.root.getChildFolder(PackFolder.UNUPDATE, true);
//		this.root.getChildFolder(PackFolder.UPDATED, true);
//		this.root.getChildFolder(PackFolder.ARCHIVED, true);
//		this.viewer.setInput(root);
//		this.viewer.refresh();
		this.root =NavigatorData.getUnPackInput();
		this.viewer.setInput(root);
		this.viewer.refresh();
	}
	public void SaveNavInfo()
	{
//		Navinfo.getInstance().SaveNavInfo(this.root);
	}
	
	public PackInfoModel[] getSelectPackInfo()
	{
		 IStructuredSelection selection = (IStructuredSelection)this.viewer.getSelection();
		 List<PackInfoModel> selPack =new ArrayList<PackInfoModel>();
		 List<?> selectionlist = selection.toList();
		 for(int i=0;i<selectionlist.size();i++)
		 {
			 if(selectionlist.get(i) instanceof PackInfoModel)
				 selPack.add((PackInfoModel)(selectionlist.get(i)));
		 }
		 return selPack.toArray(new PackInfoModel[0]);
	}
	
}
