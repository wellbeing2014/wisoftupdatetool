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
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

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
				
				Element ti =null;
				String defaultp = "/";
				if(tv.getTree().getSelectionCount()>0)
				{
					
					ti=((FileModel)(tv.getTree().getSelection()[0].getData())).getFile();
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
				}
				packpaths.clear();
				packElements.clear();
				packpaths.add("/");
				

				//packElements.add(xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList));
				getPackPaths(xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList).elements(UpdateInfo.UpdateFile).toArray(new Element[0]),"");
				final AddFileIntoPackDialog ap = new AddFileIntoPackDialog(page.getPartControl().getShell(),packpaths,defaultp);
				if(IDialogConstants.OK_ID==ap.open())
				{
					//final PackInfoInput pi = (PackInfoInput)page.getEditorInput();
					final String rootPath = pi.getSavePath()+"/"+UpdateInfo.UpdateDirName;
					final String toPath = rootPath+ap.packPath;
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

						private void recordFileToXml(File f1)
						{
							String parentElementPath = ap.packPath;
							//System.out.println(rootPath);
							String f1abpath = f1.getAbsolutePath().replace("\\", "/");
							String childElementPath =f1abpath.replace(rootPath+parentElementPath, "");
							Element rootelement =packElements.get(parentElementPath);
							if(rootelement==null)
								rootelement = xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
							
							String[] children = childElementPath.split("/");
							Element has = rootelement;
							Element lasthas = rootelement;
							int curChild = 0;
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
								curChild++;
							}	
							xmlo.save();
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
									recordFileToXml(file2);
									monitor.worked(i);
								}
								else
								{
									try{
										copyFile(filelist[i],file2);
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
							Display.getDefault().asyncExec(new Runnable() {                        
				    			public void run() { 
				    				tv.refresh();
				    				tv.expandToLevel(3);
				    				
				    				
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
				getPackPaths(xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList).elements(UpdateInfo.UpdateFile).toArray(new Element[0]),"");
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
					//XmlOperator xmlOperator = pi.getPackinfo().getXmlo();
					//Element updatefiles = xmlOperator.getRootElement().element("UpdateFileList");
					for(TreeItem ti:tis)
					{
						FileModel file = (FileModel)ti.getData();
						
						System.out.println("��ɾ��"+file.getFile().attributeValue("filename"));
					}
					tv.setInput(xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList));
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
		tv.setContentProvider(new FileModelContentProvider() );
		//�������ı�ǩ
		tv.setLabelProvider(new FileModelLabelProvider(new IStyledLabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addListener(ILabelProviderListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public StyledString getStyledText(Object element) {
				// TODO Auto-generated method stub
				Element file = ((FileModel)element).getFile();
				String str =file.attributeValue(UpdateInfo.UpdateFile_filename);
				boolean isconf =Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isconf));
				boolean isVirtual =Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isVirtual));
				String conftype = file.attributeValue(UpdateInfo.UpdateFile_conftype);
				
				StyledString str1= new StyledString(str, null);
				if(isconf)
				{
					if(isVirtual)
					{
						StyledString str2= new StyledString(str, StyledString.COUNTER_STYLER);
						if(conftype.equals(UpdateInfo.FileOpr_Mod))
						{
						str2.append("(���ļ�������)",new Styler(){
							@Override
							public void applyStyles(TextStyle textStyle) {
								textStyle.foreground=new Color(null,128,128,128);
								textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
							}});
						}
						if(conftype.equals(UpdateInfo.FileOpr_Del))
						{
							Styler red =new Styler(){
								@Override
								public void applyStyles(TextStyle textStyle) {
									textStyle.foreground=new Color(null,128,0,0);
									textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
								}};
							str2= new StyledString(str,red);
							str2.append("(���ļ���Ҫɾ��)");
						}
						return str2;
					}
					else
					{
						str1.append("(��Ҫ����)",new Styler(){
							@Override
							public void applyStyles(TextStyle textStyle) {
								textStyle.foreground=new Color(null,128,128,128);
								textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
							}});
					}
				}
						
				
				return str1;
			}
			
			@Override
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				FileModel filemodel = (FileModel)element;
				Element file = filemodel.getFile();
				if(file.attributeValue(UpdateInfo.UpdateFile_filetype).equals(UpdateInfo.FileType_Dir))
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
				else
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			}
		}));
		//���ó�ʼ���������
		//pi = (PackInfoInput)page.getEditorInput();
		Element input =xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
		tv.setInput(new FileModel(input));
		tv.expandToLevel(3);
		

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
	
	
	//private String defaultSel ;
	private List<String> packpaths= new ArrayList<String>();
	private Map<String,Element> packElements = new HashMap<String,Element>();
	private void getPackPaths(Element[] ti,String parentname)
	{
		for(int i=0;i<ti.length;i++)
		{	
			//System.out.println("�Ҵ����"+parentname);
			String name = ti[i].attributeValue(UpdateInfo.UpdateFile_filename);
			boolean isdir =  ti[i].attributeValue(UpdateInfo.UpdateFile_filetype).equals(UpdateInfo.FileType_Dir);
			String fullname = parentname+"/"+name;			
			if(isdir)
			{
				packpaths.add(fullname);
				packElements.put(fullname,ti[i]);
				getPackPaths(ti[i].elements(UpdateInfo.UpdateFile).toArray(new Element[0]),parentname+"/"+name);
			}
		}
		
	}
	

}

