package wisoft.pack.edits;

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

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.AddFileIntoPackDialog;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;
import org.eclipse.swt.widgets.Label;

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
		fd_composite.left = new FormAttachment(100, -70);
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
					final PackInfoInput pi = (PackInfoInput)page.getEditorInput();
					final String toPath = pi.getPackinfo().getSavePath()+"/"+UpdateInfo.UpdateDirName+ap.packPath;
					final String fromPath = ap.filePath;
					final File[] filelist = ap.filelist.toArray(new File[0]);
					//Console.getInstance().print("从路径中复制文件开始：", pi.getName(), Console.ConsoleType.INFO);	
					Job job = new Job("导出更新包") {
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
									monitor.worked(i);
								}
								else
								{
									try{
										copyFile(filelist[i],file2);
										printlnToConsole("复制文件完成:"+tempfilename,ConsoleType.INFO);
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
					mb.setMessage("请至少选择一个要删除的项");
					mb.setText("提示");
					mb.open();
				}
				
			}
		});
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
		tv.setLabelProvider(new MasterLabelProvider(false));
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

