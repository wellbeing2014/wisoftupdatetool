package wisoft.unpack.views;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.part.ViewPart;

import wisoft.unpack.app.Activator;
import wisoft.unpack.edits.PackInfoEditor;
import wisoft.unpack.edits.PackInfoInput;
import wisoft.unpack.models.Model;
import wisoft.unpack.models.PackInfoContentProvider;
import wisoft.unpack.models.PackInfoLabelProvider;
import wisoft.unpack.models.PackInfoModel;
import wisoft.unpack.models.RootModel;
import wisoft.unpack.utils.Navinfo;
import wisoft.unpack.utils.XmlOperator;



public class NavigationView extends ViewPart {
	public NavigationView() {
	}
	public static final String ID = "WiosftUpdateUnPack.navigationView";
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
		readNavInfo();
		//System.out.println(dir.getAbsolutePath());
		hookDoubleClickAction();
		
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
		});
	}
	
	
	private void readNavInfo()
	{
		try 
		{ //读取保存的更新包列表
		File file = new File(Navinfo.getFileName());
		if(!file.exists())
		{
			SaveNavInfo();
			return;
		}
			
		SAXReader reader = new SAXReader(); 
		reader.setEncoding("UTF-8");
		Document doc = reader.read(file); 
		// 读取XML文件
        Element root = doc.getRootElement();// 得到根节点
        for (Iterator i = root.elementIterator(Navinfo.getPackName()); i.hasNext();) {
            Element packinfo = (Element) i.next();
            String name = packinfo.attributeValue(Navinfo.getAttriPackName());
            String path = packinfo.attributeValue(Navinfo.getAttriPackPath());
            PackInfoModel  pack = new PackInfoModel(name,path);
            addPackInfo(pack);
        }
		}
		catch (Exception e) { 
			e.printStackTrace();
			return;
		} 
		
	}
	public void SaveNavInfo()
	{
		PackInfoModel[] pack = getAllPackInfo();
		XmlOperator xmlo = new XmlOperator(Navinfo.getFileName());
		xmlo.initXml(Navinfo.getRootName());
		Element root =xmlo.getRootElement();
		List<Element> haveele = root.elements();
		for(int j=0;j<haveele.size();j++)
		{			
			xmlo.getRootElement().remove(haveele.get(j));
		}
		for(int i=0;i<pack.length;i++)
		{
			Element packxml =xmlo.addElementInRoot(Navinfo.getPackName(), Navinfo.getAttriPackName(), pack[i].getName());
			if(!xmlo.isEqualByAttribute(packxml, Navinfo.getAttriPackPath(), pack[i].getSavePath()))
			packxml.addAttribute(Navinfo.getAttriPackPath(), pack[i].getSavePath());
			//packxml.addAttribute("name", pack[i].getName());
		}
		xmlo.save();
		xmlo.close();
	}
	
}