package wisoft.pack.edits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.AddFileIntoPackDialog;
import wisoft.pack.utils.UpdateInfo;

public class FileMasterDetailsBlock extends MasterDetailsBlock {

	private FormPage page;
	private TreeViewer tv ;

	public FileMasterDetailsBlock(FormPage page) {
	    this.page = page;
	}

	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		// TODO Auto-generated method stub
		FormToolkit toolkit = managedForm.getToolkit();
		//创建一个内容区
		Section section = toolkit.createSection(parent,  Section.TITLE_BAR);
		section.setText("浏览文件");
		section.marginWidth = 10;
		section.marginHeight = 5;
		//创建内容区的面板
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		//绘制该面板的边框，与表单的风格一致
		toolkit.paintBordersFor(client);
		client.setLayout(new FormLayout());
		//创建一个树，使用toolkit对象创建
		Tree tree = toolkit.createTree(client, SWT.NULL);
		Composite composite = new Composite(client, SWT.NONE);
		FormData fd_tree = new FormData();
		fd_tree.right = new FormAttachment(composite);
		fd_tree.bottom = new FormAttachment(100);
		//fd_tree.right = new FormAttachment(composite.bottom = new FormAttachment(100);
		fd_tree.top = new FormAttachment(0);
		fd_tree.left = new FormAttachment(0, 2);
		tree.setLayoutData(fd_tree);
		section.setClient(client); // 
		/*
		 IFormPart管理了整个Part的dirty state, saving, commit, focus, selection changes等等这样的事件。
		   并不是表单中的每一个-空间站都需要成为一个IFormPart，最好将一组control通过实现IFormPart变成一个Part.
		    一般来说Section就是一个自然形成的组，所以Eclipse Form提供了一个SectionPart的实现，
		   它包含一个Section的对象。   
		 */
		final SectionPart spart = new SectionPart(section);
		//注册该对象到IManagedForm表单管理器中
		managedForm.addPart(spart);
		//将普通的树包装成MVC的树
		tv = new TreeViewer(tree);
		composite.setLayout(new GridLayout(1, false));
		
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.right = new FormAttachment(100);
		fd_composite.left = new FormAttachment(100, -50);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mylist.removeAll(mylist);
				mylist.add("/");
				TreeItem ti =null;
				if(tv.getTree().getSelectionCount()>0)
					ti=tv.getTree().getSelection()[0];
				getPackPaths(tv.getTree().getItems(),"",ti);
				AddFileIntoPackDialog ap = new AddFileIntoPackDialog(page.getPartControl().getShell(),mylist.toArray(new String[0]),defaultSel);
				if(IDialogConstants.OK_ID==ap.open())
				{
					
				}
			}
		});
		toolkit.adapt(button, true, true);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(composite, SWT.NONE);
		toolkit.adapt(button_1, true, true);
		button_1.setText("\u5220\u9664");
		//注册树的选择事件监听器
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    //当单击树中某一个节点时
		    public void selectionChanged(SelectionChangedEvent event) {
		     //通过IManagedForm来发布IFormPart所对应的事件
		    	managedForm.fireSelectionChanged(spart, event.getSelection());
		    }
		});
		//设置树的内容
		tv.setContentProvider(new MasterContentProvider());
		//设置树的标签
		tv.setLabelProvider(new MasterLabelProvider());
		//设置初始化输入的类
		PackInfoInput pi = (PackInfoInput)page.getEditorInput();
		tv.setInput(new File(pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName));
		tv.expandToLevel(3);

	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		// TODO Auto-generated method stub
		detailsPart.registerPage(File.class, new DirectoryDetailPage());
	}

	@Override
	protected void createToolBarActions(IManagedForm managedForm) {
		// TODO Auto-generated method stub
		final ScrolledForm form = managedForm.getForm();
		//水平布局操作
		Action hAction = new Action("horizon", Action.AS_RADIO_BUTTON) {
		    public void run() {
		    	sashForm.setOrientation(SWT.HORIZONTAL);
		    	form.reflow(true);
		    }
		};
		hAction.setChecked(true);
		hAction.setToolTipText("水平布局");
		hAction.setImageDescriptor(Activator.getImageDescriptor("icons/th_horizontal.gif"));
		//垂直布局操作
		Action vAction = new Action("vertical", Action.AS_RADIO_BUTTON) {
		    public void run() {
		    	sashForm.setOrientation(SWT.VERTICAL);
		    	form.reflow(true);
		    }
		};
		vAction.setChecked(false);
		vAction.setToolTipText("垂直布局"); //$NON-NLS-1$
		vAction.setImageDescriptor(Activator.getImageDescriptor("icons/th_vertical.gif"));
		//将这两个操作添加到表单的工具栏管理器中
		form.getToolBarManager().add(hAction);
		form.getToolBarManager().add(vAction);
	}
	
	private List<String> mylist=new ArrayList<String>();
	private String defaultSel ="";
	
	private List<String> getPackPaths(TreeItem[] ti,String parent,TreeItem selti)
	{
		for(int i=0;i<ti.length;i++)
		{
			File file =(File)ti[i].getData();
			if(file.isDirectory())
			{
				if(ti[i]==selti)
					defaultSel = parent+"/"+file.getName();
				mylist.add(parent+"/"+file.getName());
			}
			else
			{
				if(ti[i]==selti)
					defaultSel = parent;
			}
			if(ti[i].getItemCount()>0)
			{
				getPackPaths(ti[i].getItems(),parent+"/"+file.getName(),selti);
			}
		}
		return mylist;
	}
	
}

