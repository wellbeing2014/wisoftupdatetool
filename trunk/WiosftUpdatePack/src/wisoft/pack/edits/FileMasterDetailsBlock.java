package wisoft.pack.edits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.dom4j.Element;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.ResourceManager;

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.AddConfIntoPackDialog;
import wisoft.pack.dialogs.AddFileIntoPackDialog;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;

public class FileMasterDetailsBlock extends MasterDetailsBlock {

	private FormPage page;
	private TreeViewer tv ;
	private PackInfoModel pi ;
	private XmlOperator xmlo;
	private FileModel parent_fm;
	private FileModel root_fm;
	public FileMasterDetailsBlock(FormPage page) {
	    this.page = page;
	}
	
	@Override
	protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
		 pi = ((PackInfoInput)page.getEditorInput()).getPackinfo();
		 xmlo = pi.getXmlo();
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
		//Tree tree = toolkit.createTree(client, SWT.NULL);
		Composite c = new Composite(client, SWT.NONE);
		c.setLayout(new FillLayout());
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
		//����ͨ������װ��MVC����
		tv = new TreeViewer(c);
		//ע������ѡ���¼�������

		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    //����������ĳһ���ڵ�ʱ
		    public void selectionChanged(SelectionChangedEvent event) {
		     //ͨ��IManagedForm������IFormPart����Ӧ���¼�
		    	managedForm.fireSelectionChanged(spart, event.getSelection());
		    }
		});
		//������������
		tv.setContentProvider(new FileModelContentProvider1() );
		//�������ı�ǩ
		tv.setLabelProvider(new FileModelLabelProvider());
		//���ó�ʼ���������
		Element input =xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
		//List<Model> inputfile = (new FileModel(input)).getChildren();
		root_fm = new FileModel(input);
		tv.setInput(root_fm);
		tv.setAutoExpandLevel(3);
		//tv.expandToLevel(AbstractTreeViewer.ALL_LEVELS);
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
				
				final String defaultPath =getSelectionItem(true);
				final AddFileIntoPackDialog ap = new AddFileIntoPackDialog(page.getPartControl().getShell(),defaultPath);
				//����ȫ�ֱ����� ����ѡ�к���ӵĸ�Ԫ��
				
				if(IDialogConstants.OK_ID==ap.open())
				{
					final String rootPath = pi.getSavePath()+"/"+UpdateInfo.UpdateDirName;
					final String toPath = rootPath+defaultPath;
					final String fromPath = ap.filePath;
					final File[] filelist = ap.filelist.toArray(new File[0]);
					Job job = new Job("�������°�") {
						//��־д������̨�ķ���
						private void printlnToConsole(final String msg,final ConsoleType type)
						{
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() {                                                                                    
				    				Console.getInstance();
				    				Console.print(msg, pi.getName(), type);
				    			}});
						}
						private void addTreeItem(final FileModel parent,final FileModel child)
						{
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() {                                                                                    
				    				if(!tv.getExpandedState(parent)) {
			                            tv.expandToLevel(parent, 1);
				    				}
				    				tv.add(parent, child);
				    			}});
						}
						
						//д���ļ��ṹ��XML�У��Ա�չʾ�ļ���
						private void recordFileToXml(File file)
						{
							//�Ѵ��ڵĸ�Ŀ¼
							String parentElementPath = defaultPath;
							//���ļ��ľ���·��
							String fileabpath = file.getAbsolutePath().replace("\\", "/");
							//���ļ������·��
							String childElementPath =fileabpath.replace(pi.getSavePath()+"/"+UpdateInfo.UpdateDirName+parentElementPath, "");
							System.out.println(pi.getSavePath()+"/"+UpdateInfo.UpdateDirName);
							System.out.println(rootPath);
							//���ļ�Ŀ¼�ṹ����
							String[] children = childElementPath.split("/");
							FileModel relative_parent =  parent_fm;
							for(int i= 0;i<children.length;i++)
							{
								if(children[i].isEmpty())
									continue;
								if(relative_parent.isContain(children[i])==null)
								{
									Element element =relative_parent.getFile().addElement(UpdateInfo.UpdateFile);
									FileModel child =new FileModel(element); 
									child.setName(children[i]);
									String parentfullpath = "";
									if(null!=(relative_parent.getFullPath())&&!relative_parent.getFullPath().isEmpty())
										parentfullpath = relative_parent.getFullPath();
									child.setFullPath(parentfullpath+"/"+children[i]);
									if(file.isDirectory())
										child.setFileType(UpdateInfo.FileType_Dir);
									else if(file.isFile())
										child.setFileType(UpdateInfo.FileType_File);
									relative_parent.addChild(child);
									addTreeItem(relative_parent,child);
									relative_parent = child;
								}
								else
									relative_parent = relative_parent.isContain(children[i]);
							}
							
							xmlo.save();
						}
						
						@Override
						protected IStatus run(final IProgressMonitor monitor) {
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
									recordFileToXml(file2);
									monitor.worked(i);
								}
								else
								{
									try{
										FileUtil.copyFile(filelist[i],file2);
										printlnToConsole("�����ļ����:"+tempfilename,ConsoleType.INFO);
										recordFileToXml(file2);
										monitor.worked(i);
									}
									catch(Exception e)
									{
										printlnToConsole("�����ļ�����:"+e.toString(),ConsoleType.ERROR);
									}
								}
							}
							
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
				final String defaultPath =getSelectionItem(false);
				AddConfIntoPackDialog ap = new AddConfIntoPackDialog(page.getPartControl().getShell(),defaultPath,parent_fm);
				if(IDialogConstants.OK_ID==ap.open())
				{
					String filepath = ap.filePath;
					boolean isfile = ap.isfile;
					boolean isdel = ap.isdel;
					String content = ap.content;
					boolean isdirect =ap.isdirect;
					if(isdirect)
					{
						parent_fm.setIsConf(true);
						parent_fm.setConftype(isdel?UpdateInfo.FileOpr_Del:UpdateInfo.FileOpr_Mod);
						parent_fm.setVirtual(false);
						parent_fm.setContent(content);
						tv.refresh(parent_fm,true);
					}
					else
					{
						String[] children = filepath.split("/");
						FileModel relative_parent =  parent_fm;
						for(int i= 0;i<children.length;i++)
						{
							
							boolean isFile =(i==children.length-1)&&isfile;
							if(children[i].isEmpty())
								continue;
							if(relative_parent.isContain(children[i])==null)
							{
								Element element =relative_parent.getFile().addElement(UpdateInfo.UpdateFile);
								FileModel child =new FileModel(element); 
								child.setName(children[i]);
								child.setVirtual(true);
								child.setIsConf(true);
								String parentfullpath = "";
								if(null!=(relative_parent.getFullPath())&&!relative_parent.getFullPath().isEmpty())
									parentfullpath = relative_parent.getFullPath();
								child.setFullPath(parentfullpath+"/"+children[i]);
								child.setFileType(isFile?UpdateInfo.FileType_File:UpdateInfo.FileType_Dir);
								child.setContent(isFile?content:"�뿴���ļ�����˵��");
								if(isFile)
									child.setConftype(isdel?UpdateInfo.FileOpr_Del:UpdateInfo.FileOpr_Mod);
								relative_parent.addChild(child);
								if(!tv.getExpandedState(relative_parent)) {
		                            tv.expandToLevel(relative_parent, 1);
			    				}
			    				tv.add(relative_parent, child);
			    				relative_parent = child;
							}
							else
								relative_parent = relative_parent.isContain(children[i]);
							tv.refresh(parent_fm,true);
						}
					}
					xmlo.save();
				}
			}
		});
		tltmNew_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		tltmNew_1.setText("\u914D\u7F6E");
		//ɾ����ť
		ToolItem tltmNew_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_2.addSelectionListener(new SelectionAdapter() {
			
			//��־д������̨�ķ���
			private void printlnToConsole(final String msg,final ConsoleType type)
			{
				Display.getDefault().asyncExec(new Runnable() {                        
	    			public void run() {                                                                                    
	    				Console.getInstance();
	    				Console.print(msg, pi.getName(), type);
	    			}});
			}
			
		
			 
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
				if(selection!=null&&selection.getFirstElement()!=null)
				{
					FileModel fm = (FileModel)selection.getFirstElement();
					try
					{
						fm.remove();
						tv.remove(fm);
						if(!fm.isVirtual())
						{
							String filepath = pi.getSavePath()+"/"+UpdateInfo.UpdateDirName+fm.getFullPath();
							if(fm.isDir())
								FileUtil.delFolder(filepath);
							else
								FileUtil.delAllFile(filepath);
						}
						xmlo.save();
						printlnToConsole("ɾ���ļ����:"+fm.getName(),ConsoleType.INFO);
					}
					catch(Exception e1)
					{
						printlnToConsole("ɾ���ļ�����:"+fm.getName(),ConsoleType.INFO);
					}
					fm = null;
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
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		// TODO Auto-generated method stub
		detailsPart.registerPage(FileModel.class, new DirectoryDetailPage(pi,tv));
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
	
	/**
	 * ����ѡ�е�·������������ѡ����浽ȫ�ֱ�����
	 * @param needdir true�Ƿ����ļ��У������ļ����򷵻��ϲ㣬false��û���ơ�
	 * @return
	 */
	private String getSelectionItem(boolean needdir)
	{
		IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
		FileModel ti=root_fm;
		String defaultp = "/";
		//��ʼ�� ��ȡ �ļ�����ѡ���� ���� ����
		if(selection!=null)
		{
			ti=(FileModel)selection.getFirstElement();
			if (ti ==null)
			{	
				ti= root_fm;
				parent_fm =root_fm;
				return defaultp;
			}
			if(needdir)
			{
				if(ti.isDir())
					defaultp = ti.getFullPath();
				else if(ti!=null&&(ti.getParent()!=null))
				{
					ti = (FileModel)ti.getParent();
					defaultp = ti.getFullPath();
				}
			}
			else
				defaultp = ti.getFullPath();
			if(defaultp==null||defaultp.equals("")||defaultp.equals("null"))
				defaultp ="/";
		}
		parent_fm = ti;
		return defaultp;
	}
}

