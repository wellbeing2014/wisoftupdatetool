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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TreeItem;
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

import wisoft.pack.actions.OpenNewPackDialogAction;
import wisoft.pack.app.Activator;
import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.pojo.WisoftPackageClass;
import wisoft.pack.edits.PackInfoEditor;
import wisoft.pack.edits.PackInfoInput;
import wisoft.pack.interfaces.IPackNavigation;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoContentProvider;
import wisoft.pack.models.PackInfoLabelProvider;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;



public class NavigationView extends ViewPart implements IPackNavigation {
	
	
	protected OpenNewPackDialogAction addNewPack;
	public NavigationView() {
	}
	public static final String ID = "WiosftUpdatePack.navigationView";
	private TreeViewer viewer;
	public PackFolderModel root;
	

    /**
     * We will set up a dummy model to initialize tree heararchy. In real
     * code, you will connect to a real model and expose its hierarchy.
     */
    private Model createDummyModel() {
         root =new PackFolderModel(null,new WisoftPackageClass());
        return root;
    }

    
    /**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		
		toolbarManager.add(addNewPack);
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
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
	
	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		addNewPack = new OpenNewPackDialogAction(this.getSite().getWorkbenchWindow(), "新增一个更新包");
	}
	
	/**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new PackInfoContentProvider());
		viewer.setLabelProvider(new PackInfoLabelProvider());
		viewer.setInput(createDummyModel());
		
		setViewToolBar();
		
		createActions();
		initializeToolBar();
		initializeMenu();
		readNavInfo();
		//System.out.println(dir.getAbsolutePath());
		hookDoubleClickAction();
		   
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
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void addPackInfo(PackInfoModel pack)
	{
		this.root.addChild(pack);
		this.viewer.refresh();
	}
	
	public void removePackInfo(PackInfoModel[] packlist)
	{
		boolean isdeldir = false;
		MessageBox mb = new MessageBox(this.getSite().getShell(),SWT.OK|SWT.CANCEL);
		mb.setMessage("是否删除工程文件?");
		mb.setText("提示");
		if(SWT.OK==mb.open())
			isdeldir =true;
		for(int i=0;i<packlist.length;i++)
		{
			this.root.removeChild(packlist[i]);
			if(isdeldir)
				FileUtil.delFolder(packlist[i].getSavePath());
		}
		this.viewer.refresh();
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
					packinfo.readFromXML(packinfo.getSavePath());
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
	
	
	private void readNavInfo()
	{
		this.root =NavigatorData.getPackInput();
		this.viewer.setInput(root);
		this.viewer.refresh();
	}
	public void SaveNavInfo()
	{
		//Navinfo.getInstance().SaveNavInfo(this.root);
	}
	
}