package wisoft.pack.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
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
import org.eclipse.ui.part.ViewPart;

import wisoft.pack.app.Activator;
import wisoft.pack.edits.PackInfoEditor;
import wisoft.pack.edits.PackInfoInput;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackInfoContentProvider;
import wisoft.pack.models.PackInfoLabelProvider;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.RootModel;



public class NavigationView extends ViewPart {
	public NavigationView() {
	}
	public static final String ID = "WiosftUpdatePack.navigationView";
	private TreeViewer viewer;
	public RootModel root;
	

    /**
     * We will set up a dummy model to initialize tree heararchy. In real
     * code, you will connect to a real model and expose its hierarchy.
     */
    private Model createDummyModel() {
         root =new RootModel();
         //root.addPackInfo(new PackInfoModel("cefsifhias"));
        return root;
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
		hookDoubleClickAction();
		//去掉console 工具栏上的 多余按钮		
		IWorkbenchPage page = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0];  
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
		this.root.addPackInfo(pack);
		this.viewer.refresh();
	}
	
	public void removePackInfo(PackInfoModel[] packlist)
	{
		for(int i=0;i<packlist.length;i++)
		{
			this.root.removePackInfo(packlist[i]);
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
			 else
				 selPack.add((PackInfoModel)((Model)(selectionlist.get(i))).getParent());
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
				}	
				else
				{
					Model md =((Model) selection.getFirstElement());
					packinfo = (PackInfoModel)md.getParent();
				}
				if(packinfo.getEditInput()==null)
				{
					packinfo.setEditInput(new PackInfoInput(packinfo));
				}
				
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
						if(propId==ISaveablePart2.PROP_DIRTY&&!packinfo.isdirty)
						{
							packinfo.isdirty = true;
							packinfo.setName("*"+packinfo.getName());
							viewer.refresh();
						}
					}
				});
				Activator.getDefault().setCurrent_pack(packinfo);
			}
		});
	}

	
}