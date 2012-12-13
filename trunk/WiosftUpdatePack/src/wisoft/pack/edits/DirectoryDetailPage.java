package wisoft.pack.edits;

import java.io.File;
import java.util.Date;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import wisoft.pack.models.FileModel;

public class DirectoryDetailPage implements IDetailsPage {
	public DirectoryDetailPage() {
		
	}

	private IManagedForm mform;
	private File file;
	private Section fileSection;
	private Text fileName;
	private Text filePath;
	private Text lastModify;
//	private Button isRead;
//	private Button isWrite;
	private Button isManalConf;
	private Composite client;
	private Label label;
	private Text text;
	private Label label_1;
	private Text text_1;
	private Button button;
	@SuppressWarnings("deprecation")
	public void createContents(Composite parent) {
	   //设置父类面板的布局
	   TableWrapLayout layout = new TableWrapLayout();
	   layout.topMargin = 5;
	   layout.leftMargin = 5;
	   layout.rightMargin = 2;
	   layout.bottomMargin = 2;
	   parent.setLayout(layout);
	   //获得表单工具对象
	   FormToolkit toolkit = mform.getToolkit();
	   //创建Detail的内容区
	   fileSection = toolkit.createSection(parent, Section.TITLE_BAR);
	   fileSection.setText("\u6587\u4EF6\u5C5E\u6027");
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   td.align = TableWrapData.FILL;
	   fileSection.setLayoutData(td);
	   //创建内容区的所设置的面板
	   client = toolkit.createComposite(fileSection);
	   fileSection.setClient( client );
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 2;
	   gridLayout.marginHeight = 2;
	   gridLayout.numColumns = 4;
	   gridLayout.horizontalSpacing=10;
	   client.setLayout(gridLayout);
	   //创建Detail部分具体的各种控件
	   toolkit.createLabel( client , "文件名称:");
	   fileName = toolkit.createText( client ,"");
	   fileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
	   toolkit.createLabel( client , "路径:");
	   filePath = toolkit.createText( client , "");
	   GridData gd_filePath = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
	   gd_filePath.widthHint = 200;
	   filePath.setLayoutData(gd_filePath);
	  // filePath.setBounds(50,50,300,50);
	   label = new Label(client, SWT.NONE);
	   label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   toolkit.adapt(label, true, true);
	   label.setText("\u6E90\u8DEF\u5F84\uFF1A");
	   
	   text = new Text(client, SWT.BORDER);
	   text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	   toolkit.adapt(text, true, true);
	   toolkit.createLabel( client , "最后修改:");
	   lastModify = toolkit.createText( client , file!=null?new Date(file.lastModified()).toLocaleString():"");
	   lastModify.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	   
	   label_1 = new Label(client, SWT.NONE);
	   toolkit.adapt(label_1, true, true);
	   label_1.setText("\u521B\u5EFA\u65F6\u95F4\uFF1A");
	   new Label(client, SWT.NONE);
	   isManalConf = toolkit.createButton( client , "是否需要编辑" ,SWT.CHECK);
	   isManalConf.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		//if()
	   		text_1.setVisible(isManalConf.getSelection());
	   		button.setVisible(isManalConf.getSelection());
	   		//else
	   	}
	   });
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   
	   text_1 = new Text(client, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

	 //  text_1.setLayoutData(twd_text_1);
	   text_1.setVisible(isManalConf.getSelection());
	   GridData gd_text_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
	   gd_text_1.heightHint = 300;
	   text_1.setLayoutData(gd_text_1);
	   toolkit.adapt(text_1, true, true);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   button = new Button(client, SWT.NONE);
	   button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
	   toolkit.adapt(button, true, true);
	   button.setText("\u5E94\u7528");
	   button.setVisible(isManalConf.getSelection());
	   //isWrite = toolkit.createButton( client , "是否可写" ,SWT.CHECK);
	  
	}

	public void initialize(IManagedForm form) {
	   this.mform = form ;
	   //this.
	}
	
	
	public boolean isDirty() {
	   return false;
	}
	public void commit(boolean onSave) {
	  
	}
	public boolean setFormInput(Object input) {
	  
	   return false;
	}
	public void setFocus() {
	  
	}
	public boolean isStale() {
	   return false;
	}
	public void refresh() {
		update();
	}
	//当Master区域选中事件发生时
	public void selectionChanged(IFormPart part, ISelection selection) {
	   //首先获得选中的对象
	   IStructuredSelection currentSelection = (IStructuredSelection)selection;
	   if (currentSelection.size()==1) 
	    file = ((FileModel)currentSelection.getFirstElement()).getFile();
	   //如果选中的对象不为null,则刷新控件所显示的值
	   if (file != null)
	    update();
	}
	//刷新值
	@SuppressWarnings("deprecation")
	public void update (){
	   //如果选中的为文件夹
	   if ( file.isDirectory()){
	    fileSection.setText("文件夹");
	    fileSection.setDescription("这是一个文件夹");
	   }else{//否则
	    fileSection.setText("文件");
	    fileSection.setDescription("这是一个文件"); 
	   }
	   //设置文件名
	   fileName.setText(file.getName());
	   //设置路径
	   filePath.setText(file.getAbsolutePath());
	   //设置上次修改
	   lastModify.setText(new Date(file.lastModified()).toLocaleString());
	   //设置是否只读
//	   isRead.setSelection( file.canRead());
//	   //设置是否可写
//	   isWrite.setSelection( file.canWrite() );
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	
}
