package wisoft.pack.edits;

import java.io.File;
import java.util.Date;

import org.dom4j.Element;
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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.wb.swt.ResourceManager;

import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.UpdateInfo;

public class DirectoryDetailPage implements IDetailsPage {
	private PackInfoModel pack;
	private FileModel fm;
	public DirectoryDetailPage(PackInfoModel pack) {
		this.pack = pack;
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
	private Text text_1;
	private Button button_1;
	private Button button_2;
	private ToolBar toolBar_1;
	private ToolItem tltmNewItem;
	private ToolItem toolItem;
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
	   fileSection.setText("\u6587\u4EF6\u8BE6\u7EC6");
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
	   GridData gd_filePath = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
	   gd_filePath.widthHint = 200;
	   filePath.setLayoutData(gd_filePath);
	   toolkit.createLabel( client , "最后修改:");
	   lastModify = toolkit.createText( client , file!=null?new Date(file.lastModified()).toLocaleString():"");
	   lastModify.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
	   new Label(client, SWT.NONE);
	   
	   button_2 = new Button(client, SWT.CHECK);
	   button_2.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		text_1.setVisible(button_2.getSelection());
	   		button_1.setVisible(button_2.getSelection());
	   		isManalConf.setVisible(button_2.getSelection());
	   		tltmNewItem.setEnabled(button_2.getSelection());
	   	}
	   });
	   toolkit.adapt(button_2, true, true);
	   button_2.setText("\u914D\u7F6E\u64CD\u4F5C");
	   
	   toolBar_1 = new ToolBar(client, SWT.FLAT | SWT.RIGHT);
	   toolkit.adapt(toolBar_1);
	   toolkit.paintBordersFor(toolBar_1);
	   
	   //保存配置按钮
	   tltmNewItem = new ToolItem(toolBar_1, SWT.NONE);
	   tltmNewItem.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		MessageBox mb = new MessageBox(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
   				mb.setText("提示");
	   		if(button_1.getSelection()||isManalConf.getSelection())
	   		{
	   			if(isManalConf.getSelection()&&text_1.getText().isEmpty())
	   			{
	   				mb.setMessage("修改操作必须书写操作说明");
		   			mb.open();
		   			return;
	   			}
	   			Element element = fm.getFile().addAttribute(UpdateInfo.UpdateFile_isconf, "true");
	   			if(isManalConf.getSelection())
	   				element.addAttribute(UpdateInfo.UpdateFile_conftype, UpdateInfo.FileOpr_Mod);
	   			if(isManalConf.getSelection())
	   				element.addAttribute(UpdateInfo.UpdateFile_conftype, UpdateInfo.FileOpr_Mod);
	   			//fm.getFile().addElement(UpdateInfo.UpdateFile_conftent).;
	   			Element element_content =element.element(UpdateInfo.UpdateFile_conftent);
	   			if(element_content!=null)
	   				element.remove(element_content);
	   			element_content = element.addElement(UpdateInfo.UpdateFile_conftent);
	   			element_content.addCDATA(text_1.getText());
	   			pack.getXmlo().save();
	   		}
	   		else
	   		{
	   			
	   			mb.setMessage("请选择一个配置操作");
	   			mb.open();
	   			return;
	   		}
	   	}
	   });
	   tltmNewItem.setText("\u4FDD\u5B58\u914D\u7F6E");
	   tltmNewItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/save.gif"));
	   tltmNewItem.setEnabled(button_2.getSelection());
	   
	   toolItem = new ToolItem(toolBar_1, SWT.NONE);
	   toolItem.setText("\u5220\u9664\u914D\u7F6E");
	   
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   isManalConf = toolkit.createButton( client , "\u9700\u8981\u7F16\u8F91" ,SWT.RADIO);
	   toolkit.adapt(isManalConf, true, true);
	   isManalConf.setVisible(button_2.getSelection());
	   
	   button_1 = new Button(client, SWT.RADIO);
	   toolkit.adapt(button_1, true, true);
	   button_1.setText("\u9700\u8981\u5220\u9664");
	   button_1.setVisible(button_2.getSelection());
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   
	   text_1 = new Text(client, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
	   GridData gd_text_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
	   gd_text_1.heightHint = 220;
	   text_1.setLayoutData(gd_text_1);
	   toolkit.adapt(text_1, true, true);
	   text_1.setVisible(button_2.getSelection());
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
	   {
		   FileModel fm =(FileModel)currentSelection.getFirstElement();
		   this.fm = fm;
		   String filepath =pack.getSavePath()+"/"+UpdateInfo.UpdateDirName+fm.getFile().attributeValue(UpdateInfo.UpdateFile_fullpath);
		   file = new File(filepath);
	   }
	   //如果选中的对象不为null,则刷新控件所显示的值
	  
	    update();
	}
	//刷新值
	@SuppressWarnings("deprecation")
	public void update (){
		if (file.exists())
		{
		   //设置文件名
		   fileName.setText(file.getName());
		   //设置路径
		   String filefullpath = file.getAbsolutePath().replace("\\", "/");
		   filePath.setText(filefullpath.replace(pack.getSavePath()+"/"+UpdateInfo.UpdateDirName, ""));
		   //设置上次修改
		   lastModify.setText(new Date(file.lastModified()).toLocaleString());
		}
		else
		{
			//设置文件名
		   fileName.setText("不可取");
		   //设置路径
		   filePath.setText("不可取");
		   //设置上次修改
		   lastModify.setText("不可取");
		}
		boolean isconf =Boolean.valueOf(fm.getFile().attributeValue(UpdateInfo.UpdateFile_isconf));
		if(isconf)
		{
			button_2.setSelection(Boolean.TRUE);
			isManalConf.setVisible(Boolean.TRUE);
			button_1.setVisible(Boolean.TRUE);
			text_1.setVisible(Boolean.TRUE);
			String conftype = fm.getFile().attributeValue(UpdateInfo.UpdateFile_conftype);
			if(conftype.equals(UpdateInfo.FileOpr_Del))
			{
				isManalConf.setSelection(false);
				button_1.setSelection(true);
			}
			if(conftype.equals(UpdateInfo.FileOpr_Mod))
			{
				isManalConf.setSelection(true);
				button_1.setSelection(false);
			}
			text_1.setText(fm.getFile().elementText(UpdateInfo.UpdateFile_conftent));
		}
	   
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	
}
