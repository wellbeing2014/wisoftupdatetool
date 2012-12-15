package wisoft.pack.edits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
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

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.AddConfIntoPackDialog;
import wisoft.pack.dialogs.AddFileIntoPackDialog;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.FileModel.EditType;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;

public class FileMasterDetailsBlock extends MasterDetailsBlock {

	private FormPage page;
	private TreeViewer tv ;
	private PackInfoInput pi ;

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
		client.setLayout(new FillLayout(SWT.HORIZONTAL));
		//����һ������ʹ��toolkit���󴴽�
		Tree tree = toolkit.createTree(client, SWT.NULL);
		section.setClient(client); // 
		/*
		 IFormPart����������Part��dirty state, saving, commit, focus, selection changes�ȵ��������¼���
		   �����Ǳ��е�ÿһ��-�ռ�վ����Ҫ��Ϊһ��IFormPart����ý�һ��controlͨ��ʵ��IFormPart���һ��Part.
		    һ����˵Section����һ����Ȼ�γɵ��飬����Eclipse Form�ṩ��һ��SectionPart��ʵ�֣�
		   ������һ��Section�Ķ���   
		 */
		final SectionPart spart = new SectionPart(section);
		Section section_1 = spart.getSection();
		//ע��ö���IManagedForm����������
		managedForm.addPart(spart);
		
		ToolBar toolBar = new ToolBar(section_1, SWT.FLAT | SWT.RIGHT);
		toolkit.adapt(toolBar);
		toolkit.paintBordersFor(toolBar);
		section_1.setTextClient(toolBar);
		//����ļ���ť
		ToolItem tltmNew = new ToolItem(toolBar, SWT.NONE);
		tltmNew.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		tltmNew.addSelectionListener(new SelectionAdapter() {
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
					final PackInfoInput pi = (PackInfoInput)page.getEditorInput();
					final String toPath = pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName+ap.packPath;
					final String fromPath = ap.filePath;
					final File[] filelist = ap.filelist.toArray(new File[0]);
					//Console.getInstance().print("��·���и����ļ���ʼ��", pi.getName(), Console.ConsoleType.INFO);	
					Job job = new Job("�������°�") {
						private void printlnToConsole(final String msg,final ConsoleType type)
						{
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() {                                                                                    
				    				Console.getInstance();
				    				Console.print(msg, pi.getName(), type);
				    				//nv.addPackInfo(pack);
				    			}});
						}
						
						private void copyFile(File f1,File f2) throws Exception{   
							int length=2097152;   
							FileInputStream in=new FileInputStream(f1);   
							FileOutputStream out=new FileOutputStream(f2);   
							byte[] buffer=new byte[length];   
							while(true){   
								int ins=in.read(buffer);   
								if(ins==-1){   
									in.close();   
									out.flush();   
									out.close();   
									return;   
								}
								else  
									out.write(buffer,0,ins);   
						   }   
						}  

					
						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							// TODO Auto-generated method stub
							
							monitor.beginTask("��Ҫ����"+filelist.length+"���ļ�",  IProgressMonitor.UNKNOWN);
							printlnToConsole("ԭ·����"+fromPath,ConsoleType.INFO);
							printlnToConsole("���Ƶ���"+toPath,ConsoleType.INFO);
							printlnToConsole("��Ҫ����"+filelist.length+"���ļ�",ConsoleType.INFO);
							for(int i=0;i<filelist.length; i++)
							{
								String tempfilename=filelist[i].getAbsolutePath().replace(fromPath, "");
								File file2 =new File(toPath+tempfilename);
								monitor.setTaskName(tempfilename);
								if(filelist[i].isDirectory())
								{
									file2.mkdirs();
									printlnToConsole("�����ļ���:"+tempfilename,ConsoleType.INFO);
									monitor.worked(i);
								}
								else
								{
									try{
										copyFile(filelist[i],file2);
										printlnToConsole("�����ļ����:"+tempfilename,ConsoleType.INFO);
										monitor.worked(i);
									}
									catch(Exception e)
									{
										printlnToConsole("�����ļ�����:"+e.toString(),ConsoleType.ERROR);
									}
								}
							}
							
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() {      
				    				tv.setInput(new FileModel(new File(pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName)));
				    				tv.refresh();
				    			}});
							return Status.OK_STATUS;
						}
						
					};
					job.setUser(true);
					job.schedule();
				}
			}
		});
		tltmNew.setText("\u6587\u4EF6");
		//������ð�ť
		ToolItem tltmNew_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mylist.removeAll(mylist);
				mylist.add("/");
				TreeItem ti =null;
				if(tv.getTree().getSelectionCount()>0)
					ti=tv.getTree().getSelection()[0];
				getPackPaths(tv.getTree().getItems(),"",ti);
				AddConfIntoPackDialog ap = new AddConfIntoPackDialog(page.getPartControl().getShell(),mylist.toArray(new String[0]),defaultSel);
				if(IDialogConstants.OK_ID==ap.open())
				{
					String packpath = ap.packPath;
					String filepath = ap.filePath;
					boolean isfile = ap.isfile;
					boolean isdel = ap.isdel;
					String content = ap.content;
					PackInfoModel pack =((PackInfoInput)page.getEditorInput()).getPackinfo();
					XmlOperator xmlo =pack.getXmlo();
					
					Element fileconfs =xmlo.OnlyElementInRoot(UpdateInfo.FileConfs);
					Element rely =xmlo.addElementInElement(fileconfs, UpdateInfo.FileConf,UpdateInfo.FileConf_attr_fullpath, packpath+"/"+filepath);
					rely.addAttribute(UpdateInfo.FileConf_attr_opr,isdel?UpdateInfo.Con_FileOpr_Del:UpdateInfo.Con_FileOpr_Mod);
					rely.addAttribute(UpdateInfo.FileConf_attr_type,isfile?UpdateInfo.Con_FileType_File:UpdateInfo.Con_FileType_Dir);
					rely.addAttribute(UpdateInfo.FileConf_attr_path, packpath);
					rely.addAttribute(UpdateInfo.FileConf_attr_name, filepath);
					if(rely.element(UpdateInfo.FileConf_elem_content)!=null)
						rely.remove(rely.element(UpdateInfo.FileConf_elem_content));
					Element rely_content = rely.addElement(UpdateInfo.FileConf_elem_content);
					rely_content.addCDATA(content);
					xmlo.save();
				}
				
				tv.refresh();
			}
		});
		tltmNew_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		tltmNew_1.setText("\u914D\u7F6E");
		//ɾ����ť
		ToolItem tltmNew_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_2.addSelectionListener(new SelectionAdapter() {
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
						FileModel file = (FileModel)ti.getData();
						if(file.getEdittype()!=EditType.NORMAL)
						{
							PackInfoModel pack =((PackInfoInput)page.getEditorInput()).getPackinfo();
							XmlOperator xmlo =pack.getXmlo();
							Element confs = xmlo.getRootElement().element("configFiles");
							String fullpath = file.getFile().getPath().replace("\\" ,"/");
							xmlo.RemoveElementInElement(confs, "configFile", "fullpath",fullpath.replace(pack.getSavePath()+"/"+UpdateInfo.UpdateDirName, "") );
							xmlo.save();
						}
						else if(file.getFile().exists())
						{
							if(file.getFile().isDirectory())
								delFolder(file.getFile().getAbsolutePath());
							else file.getFile().delete();
						}
					}
					tv.setInput(new FileModel(new File(pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName)));
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
		tltmNew_2.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/del.png"));
		tltmNew_2.setText("\u5220\u9664");
		//����ͨ������װ��MVC����
		tv = new TreeViewer(tree);
		//ע������ѡ���¼�������
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    //����������ĳһ���ڵ�ʱ
		    public void selectionChanged(SelectionChangedEvent event) {
		     //ͨ��IManagedForm������IFormPart����Ӧ���¼�
		    	managedForm.fireSelectionChanged(spart, event.getSelection());
		    }
		});
		//������������
		tv.setContentProvider(new MasterContentProvider(true));
		//�������ı�ǩ
		tv.setLabelProvider(new MasterLabelProvider(false));
		//���ó�ʼ���������
		pi = (PackInfoInput)page.getEditorInput();
		tv.setInput(new FileModel(new File(pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName)));
		tv.expandToLevel(3);

	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		// TODO Auto-generated method stub
		detailsPart.registerPage(FileModel.class, new DirectoryDetailPage());
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
			FileModel filemodel = (FileModel)ti[i].getData();
			if(filemodel.getEdittype()!=EditType.NORMAL)
				break;
			File file =filemodel.getFile();
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

