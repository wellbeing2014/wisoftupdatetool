package wisoft.pack.edits;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.swt.widgets.TreeItem;
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
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;

public class FileMasterDetailsBlock extends MasterDetailsBlock {

	private FormPage page;
	private TreeViewer tv ;
	private PackInfoModel pi ;
	private XmlOperator xmlo;
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
		final List<FileModel> inputfile = new ArrayList<FileModel>();
		for(Element element :input.elements(UpdateInfo.UpdateFile))
		{
			inputfile.add(new FileModel(element));
		}
		tv.setInput(inputfile);
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
				final IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
				FileModel ti;
				String defaultp = "/";
				//初始化 获取 文件树的选中项 设置 参数
				if(selection!=null)
				{
					ti=(FileModel)selection.getFirstElement();
					if(UpdateInfo.FileType_Dir.equals(ti.getFile().attributeValue(UpdateInfo.UpdateFile_filetype)))
						defaultp = ti.getFile().attributeValue(UpdateInfo.UpdateFile_fullpath);
					else
					{
						if(ti.getParent()!=null)
						{
							defaultp = ti.getFile().getParent().attributeValue(UpdateInfo.UpdateFile_fullpath);
							if(defaultp==null||defaultp.equals("")||defaultp.equals("null"))
								defaultp ="/";
						}
					}
				}
				packpaths.clear();
				packElements.clear();
				if(!packpaths.contains("/"))
					packpaths.add("/");
				//获取文件结构给弹出窗口
				getPackPaths(inputfile.toArray(new FileModel[0]),"");
				final AddFileIntoPackDialog ap = new AddFileIntoPackDialog(page.getPartControl().getShell(),packpaths,defaultp);
				if(IDialogConstants.OK_ID==ap.open())
				{
					final String rootPath = pi.getSavePath()+"/"+UpdateInfo.UpdateDirName;
					final String toPath = rootPath+ap.packPath;
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
				    				tv.refresh();
				    			}});
						}
						
						//复制文件方法
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
						//写入文件结构到XML中，以便展示文件树
						private void recordFileToXml(File f1)
						{
							String parentElementPath = ap.packPath;
							//System.out.println(rootPath);
							String f1abpath = f1.getAbsolutePath().replace("\\", "/");
							String childElementPath =f1abpath.replace(rootPath+parentElementPath, "");
							FileModel root = packElements.get(parentElementPath);
							Element rootelement =root.getFile();
							if(rootelement==null)
								rootelement = xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
							
							String[] children = childElementPath.split("/");
							Element has = rootelement;
							Element lasthas = rootelement;
							//int curChild = 0;
							String hasRecordString = "";
							for(int i= 0;i<children.length;i++)
							{
								if(children[i].isEmpty())
									continue;
								hasRecordString = hasRecordString+"/"+children[i];
								has = xmlo.getElementInElement(has, UpdateInfo.UpdateFile, UpdateInfo.UpdateFile_filename, children[i]);
								if(has==null)
								{
									has =xmlo.addElementInElement(lasthas, UpdateInfo.UpdateFile, UpdateInfo.UpdateFile_filename,  children[i]);
									has.addAttribute(UpdateInfo.UpdateFile_fullpath, (parentElementPath.equals("/")?"":parentElementPath)+hasRecordString);
									if(f1.isDirectory())
										has.addAttribute(UpdateInfo.UpdateFile_filetype, UpdateInfo.FileType_Dir);
									else if(f1.isFile())
										has.addAttribute(UpdateInfo.UpdateFile_filetype, UpdateInfo.FileType_File);
								}
								lasthas = has;
								//curChild++;
							}	
							addTreeItem(root,new FileModel(has));
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
										copyFile(filelist[i],file2);
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
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() { 
				    				
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
		//添加配置按钮
		ToolItem tltmNew_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Element ti =null;
				String defaultp = "/";
				if(tv.getTree().getSelectionCount()>0)
					ti=((FileModel)(tv.getTree().getSelection()[0].getData())).getFile();
				packpaths.clear();
				packElements.clear();
				packpaths.add("/");
				if(UpdateInfo.FileType_Dir.equals(ti.attributeValue(UpdateInfo.UpdateFile_filetype)))
					defaultp = ti.attributeValue(UpdateInfo.UpdateFile_fullpath);
				else
				{
					if(ti.getParent()!=null)
					{
						defaultp = ti.getParent().attributeValue(UpdateInfo.UpdateFile_fullpath);
						if(defaultp==null||defaultp.equals("")||defaultp.equals("null"))
							defaultp ="/";
					}
				}
				getPackPaths(new FileModel[]{(FileModel)tv.getInput()},"");
				AddConfIntoPackDialog ap = new AddConfIntoPackDialog(page.getPartControl().getShell(),packpaths,defaultp);
				if(IDialogConstants.OK_ID==ap.open())
				{
					String packpath = ap.packPath;
					String filepath = ap.filePath;
					boolean isfile = ap.isfile;
					boolean isdel = ap.isdel;
					String content = ap.content;
					PackInfoModel pack =((PackInfoInput)page.getEditorInput()).getPackinfo();
					XmlOperator xmlo =pack.getXmlo();
					
//					Element fileconfs =xmlo.OnlyElementInRoot(UpdateInfo.FileConfs);
//					Element rely =xmlo.addElementInElement(fileconfs, UpdateInfo.FileConf,UpdateInfo.FileConf_attr_fullpath, "/".equals(packpath)?packpath+filepath:packpath+"/"+filepath);
//					rely.addAttribute(UpdateInfo.FileConf_attr_opr,isdel?UpdateInfo.Con_FileOpr_Del:UpdateInfo.Con_FileOpr_Mod);
//					rely.addAttribute(UpdateInfo.FileConf_attr_type,isfile?UpdateInfo.Con_FileType_File:UpdateInfo.Con_FileType_Dir);
//					rely.addAttribute(UpdateInfo.FileConf_attr_path, packpath);
//					rely.addAttribute(UpdateInfo.FileConf_attr_name, filepath);
//					if(rely.element(UpdateInfo.FileConf_elem_content)!=null)
//						rely.remove(rely.element(UpdateInfo.FileConf_elem_content));
//					Element rely_content = rely.addElement(UpdateInfo.FileConf_elem_content);
//					rely_content.addCDATA(content);
//					fileconfs.addAttribute(UpdateInfo.FileConfs_attr_needRead,"true" );
					xmlo.save();
				}
				
				tv.refresh();
			}
		});
		tltmNew_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		tltmNew_1.setText("\u914D\u7F6E");
		//删除按钮
		ToolItem tltmNew_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNew_2.addSelectionListener(new SelectionAdapter() {
			 private  void delFolder(String folderPath) {
				  try {
				        delAllFile(folderPath); //删除完里面所有内容
				        String filePath = folderPath;
				        filePath = filePath.toString();
				        java.io.File myFilePath = new java.io.File(filePath);
				        myFilePath.delete(); //删除空文件夹
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
			             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
			             delFolder(path + "/" + tempList[i]);//再删除空文件夹
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
					//XmlOperator xmlOperator = pi.getPackinfo().getXmlo();
					//Element updatefiles = xmlOperator.getRootElement().element("UpdateFileList");
					for(TreeItem ti:tis)
					{
						FileModel file = (FileModel)ti.getData();
						
						Element del_element = file.getFile();
						del_element.getParent().remove(del_element);
						
					}
					xmlo.save();
					tv.setInput(new FileModel(xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList)));
					tv.refresh();
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
		detailsPart.registerPage(FileModel.class, new DirectoryDetailPage(pi));
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
	
	
	//private String defaultSel ;
	private List<String> packpaths= new ArrayList<String>();
	private Map<String,FileModel> packElements = new HashMap<String,FileModel>();
//	private void getPackPaths(Element[] ti,String parentname)
//	{
//		for(int i=0;i<ti.length;i++)
//		{	
//			//System.out.println("我打出了"+parentname);
//			String name = ti[i].attributeValue(UpdateInfo.UpdateFile_filename);
//			boolean isdir =  ti[i].attributeValue(UpdateInfo.UpdateFile_filetype).equals(UpdateInfo.FileType_Dir);
//			String fullname = parentname+"/"+name;			
//			if(isdir)
//			{
//				packpaths.add(fullname);
//				packElements.put(fullname,ti[i]);
//				getPackPaths(ti[i].elements(UpdateInfo.UpdateFile).toArray(new Element[0]),parentname+"/"+name);
//			}
//		}
//	}
	
	private void getPackPaths(FileModel[] root,String parentname)
	{
		for(int i=0;i<root.length;i++)
		{	
			//System.out.println("我打出了"+parentname);
			String name = root[i].getFile().attributeValue(UpdateInfo.UpdateFile_filename);
			boolean isdir =  root[i].getFile().attributeValue(UpdateInfo.UpdateFile_filetype).equals(UpdateInfo.FileType_Dir);
			String fullname = parentname+"/"+name;			
			if(isdir)
			{
				packpaths.add(fullname);
				packElements.put(fullname,root[i]);
				getPackPaths(root[i].getChildren().toArray(new FileModel[0]),parentname+"/"+name);
			}
		}
	}

}

