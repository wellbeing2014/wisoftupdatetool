package wisoft.unpack.edits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import wisoft.unpack.app.Activator;
import wisoft.unpack.utils.UpdateInfo;
import wisoft.unpack.views.Console;
import wisoft.unpack.views.Console.ConsoleType;

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
		//����һ��������
		Section section = toolkit.createSection(parent,  Section.TITLE_BAR);
		section.setText("����ļ�");
		section.marginWidth = 10;
		section.marginHeight = 5;
		//���������������
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		//���Ƹ����ı߿�����ķ��һ��
		toolkit.paintBordersFor(client);
		client.setLayout(new FormLayout());
		//����һ������ʹ��toolkit���󴴽�
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
		 IFormPart����������Part��dirty state, saving, commit, focus, selection changes�ȵ��������¼���
		   �����Ǳ��е�ÿһ��-�ռ�վ����Ҫ��Ϊһ��IFormPart����ý�һ��controlͨ��ʵ��IFormPart���һ��Part.
		    һ����˵Section����һ����Ȼ�γɵ��飬����Eclipse Form�ṩ��һ��SectionPart��ʵ�֣�
		   ������һ��Section�Ķ���   
		 */
		final SectionPart spart = new SectionPart(section);
		//ע��ö���IManagedForm����������
		managedForm.addPart(spart);
		//����ͨ������װ��MVC����
		tv = new TreeViewer(tree);
		composite.setLayout(new GridLayout(1, false));
		
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.right = new FormAttachment(100);
		fd_composite.left = new FormAttachment(100, -70);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);
		toolkit.paintBordersFor(composite);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}	
		});
		toolkit.adapt(button, true, true);
		button.setText("\u6DFB\u52A0\u6587\u4EF6");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		toolkit.adapt(button_2, true, true);
		button_2.setText("\u6DFB\u52A0\u914D\u7F6E");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			
			 private  void delFolder(String folderPath) {
				  try {
				        delAllFile(folderPath); //ɾ����������������
				        String filePath = folderPath;
				        filePath = filePath.toString();
				        java.io.File myFilePath = new java.io.File(filePath);
				        myFilePath.delete(); //ɾ�����ļ���
				     } catch (Exception e) {
				       e.printStackTrace(); 
				     }
			 }
			 
			 private  boolean  delAllFile(String path)
			 {
				   boolean flag = false;
			       File file = new File(path);
			       if (!file.exists()) {
			         return flag;
			       }
			       if (!file.isDirectory()) {
			         return flag;
			       }
			       String[] tempList = file.list();
			       File temp = null;
			       for (int i = 0; i < tempList.length; i++) {
			          if (path.endsWith(File.separator)) {
			             temp = new File(path + tempList[i]);
			          } else {
			              temp = new File(path + File.separator + tempList[i]);
			          }
			          if (temp.isFile()) {
			             temp.delete();
			          }
			          if (temp.isDirectory()) {
			             delAllFile(path + "/" + tempList[i]);//��ɾ���ļ���������ļ�
			             delFolder(path + "/" + tempList[i]);//��ɾ�����ļ���
			             flag = true;
			          }
			       }
			       return flag;
			 }
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tv.getTree().getSelectionCount()>0)
				{
					TreeItem[] tis = tv.getTree().getSelection();
					for(TreeItem ti:tis)
					{
						File file = (File)ti.getData();
						if(file.exists())
						{
							if(file.isDirectory())
								delFolder(file.getAbsolutePath());
							else file.delete();
						}
					}
					tv.refresh();
				}
				else
				{
					MessageBox mb = new MessageBox(page.getPartControl().getShell());
					mb.setMessage("������ѡ��һ��Ҫɾ������");
					mb.setText("��ʾ");
					mb.open();
				}
				
			}
		});
		toolkit.adapt(button_1, true, true);
		button_1.setText("\u5220\u9664");
		//ע������ѡ���¼�������
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    //����������ĳһ���ڵ�ʱ
		    public void selectionChanged(SelectionChangedEvent event) {
		     //ͨ��IManagedForm������IFormPart����Ӧ���¼�
		    	managedForm.fireSelectionChanged(spart, event.getSelection());
		    }
		});
		//������������
		tv.setContentProvider(new MasterContentProvider());
		//�������ı�ǩ
		tv.setLabelProvider(new MasterLabelProvider(false));
		//���ó�ʼ���������
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
		//ˮƽ���ֲ���
		Action hAction = new Action("horizon", Action.AS_RADIO_BUTTON) {
		    public void run() {
		    	sashForm.setOrientation(SWT.HORIZONTAL);
		    	form.reflow(true);
		    }
		};
		hAction.setChecked(true);
		hAction.setToolTipText("ˮƽ����");
		hAction.setImageDescriptor(Activator.getImageDescriptor("icons/th_horizontal.gif"));
		//��ֱ���ֲ���
		Action vAction = new Action("vertical", Action.AS_RADIO_BUTTON) {
		    public void run() {
		    	sashForm.setOrientation(SWT.VERTICAL);
		    	form.reflow(true);
		    }
		};
		vAction.setChecked(false);
		vAction.setToolTipText("��ֱ����"); //$NON-NLS-1$
		vAction.setImageDescriptor(Activator.getImageDescriptor("icons/th_vertical.gif"));
		//��������������ӵ����Ĺ�������������
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

