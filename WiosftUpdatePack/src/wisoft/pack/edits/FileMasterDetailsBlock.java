package wisoft.pack.edits;

import java.io.File;

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

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.AddConfIntoPackDialog;
import wisoft.pack.dialogs.AddFileIntoPackDialog;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.sourceprovider.ResourceManager;
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
		//创建一个内容区
		Section section = toolkit.createSection(parent,  Section.TITLE_BAR);
		section.setText("浏览文件");
		section.marginWidth = 10;
		section.marginHeight = 5;
		//创建内容区的面板
		Composite client = toolkit.createComposite(section, SWT.WRAP);
		//绘制该面板的边框，与表单的风格一致
		toolkit.paintBordersFor(client);
		client.setLayout(new FillLayout(SWT.HORIZONTAL));
		//创建一个树，使用toolkit对象创建
		//Tree tree = toolkit.createTree(client, SWT.NULL);
		Composite c = new Composite(client, SWT.NONE);
		c.setLayout(new FillLayout());
		section.setClient(client); // 
		/*
		 IFormPart管理了整个Part的dirty state, saving, commit, focus, selection changes等等这样的事件。
		   并不是表单中的每一个-空间站都需要成为一个IFormPart，最好将一组control通过实现IFormPart变成一个Part.
		    一般来说Section就是一个自然形成的组，所以Eclipse Form提供了一个SectionPart的实现，
		   它包含一个Section的对象。   
		 */
		final SectionPart spart = new SectionPart(section);
		Section section_1 = spart.getSection();
		//注册该对象到IManagedForm表单管理器中
		managedForm.addPart(spart);
		//将普通的树包装成MVC的树
		tv = new TreeViewer(c);
		//注册树的选择事件监听器

		tv.addSelectionChangedListener(new ISelectionChangedListener() {
		    //当单击树中某一个节点时
		    public void selectionChanged(SelectionChangedEvent event) {
		     //通过IManagedForm来发布IFormPart所对应的事件
		    	managedForm.fireSelectionChanged(spart, event.getSelection());
		    }
		});
		//设置树的内容
		tv.setContentProvider(new FileModelContentProvider1() );
		//设置树的标签
		tv.setLabelProvider(new FileModelLabelProvider());
		//设置初始化输入的类
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
		//添加文件按钮
		ToolItem tltmNew = new ToolItem(toolBar, SWT.NONE);
		tltmNew.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		tltmNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				final String defaultPath =getSelectionItem(true);
				final AddFileIntoPackDialog ap = new AddFileIntoPackDialog(page.getPartControl().getShell(),defaultPath);
				//设置全局变量的 关于选中后添加的父元素
				
				if(IDialogConstants.OK_ID==ap.open())
				{
					
					File file = new File(pi.getSavePath()+"/"+UpdateInfo.UpdateDirName);
					final String rootPath = file.getAbsolutePath().replace("\\", "/");
					final String toPath = rootPath+defaultPath;
					final String fromPath = ap.filePath;
					final File[] filelist = ap.filelist.toArray(new File[0]);
					Job job = new Job("导出更新包") {
						//日志写往控制台的方法
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
						
						//写入文件结构到XML中，以便展示文件树
						private void recordFileToXml(File file)
						{
							//已存在的父目录
							String parentElementPath = defaultPath;
							//子文件的绝对路径
							String fileabpath = file.getAbsolutePath().replace("\\", "/");
							//子文件的相对路径
							String childElementPath =fileabpath.replace(rootPath+parentElementPath, "");
							//子文件目录结构数组
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
							monitor.beginTask("需要复制"+filelist.length+"个文件",  IProgressMonitor.UNKNOWN);
							printlnToConsole("原路径："+fromPath,ConsoleType.INFO);
							printlnToConsole("复制到："+toPath,ConsoleType.INFO);
							printlnToConsole("需要复制"+filelist.length+"个文件",ConsoleType.INFO);
							
							for(int i=0;i<filelist.length; i++)
							{
								String tempfilename=filelist[i].getAbsolutePath().replace(fromPath, "");
								File file2 =new File(toPath+tempfilename);
								monitor.setTaskName(tempfilename);
								if(filelist[i].isDirectory())
								{
									file2.mkdirs();
									printlnToConsole("创建文件夹:"+tempfilename,ConsoleType.INFO);
									recordFileToXml(file2);
									monitor.worked(i);
								}
								else
								{
									try{
										FileUtil.copyFile(filelist[i],file2);
										printlnToConsole("复制文件完成:"+tempfilename,ConsoleType.INFO);
										recordFileToXml(file2);
										monitor.worked(i);
									}
									catch(Exception e)
									{
										printlnToConsole("复制文件出错:"+e.toString(),ConsoleType.ERROR);
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
		//添加配置按钮
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
								child.setContent(isFile?content:"请看子文件配置说明");
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
		//删除按钮
		ToolItem tltmNew_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_2.addSelectionListener(new SelectionAdapter() {
			
			//日志写往控制台的方法
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
							FileUtil.delete(filepath);
						}
						xmlo.save();
						printlnToConsole("删除文件完成:"+fm.getName(),ConsoleType.INFO);
					}
					catch(Exception e1)
					{
						printlnToConsole("删除文件出错:"+fm.getName(),ConsoleType.INFO);
					}
					fm = null;
				}
				else
				{
					MessageBox mb = new MessageBox(page.getPartControl().getShell());
					mb.setMessage("请至少选择一个要删除的项");
					mb.setText("提示");
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
	
	/**
	 * 返回选中的路径，并把树的选中项保存到全局变量中
	 * @param needdir true是返回文件夹，不是文件夹则返回上层，false则没限制。
	 * @return
	 */
	private String getSelectionItem(boolean needdir)
	{
		IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
		FileModel ti=root_fm;
		String defaultp = "/";
		//初始化 获取 文件树的选中项 设置 参数
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

