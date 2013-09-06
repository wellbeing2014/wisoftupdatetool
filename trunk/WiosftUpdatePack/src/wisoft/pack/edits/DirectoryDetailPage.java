package wisoft.pack.edits;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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

import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.sourceprovider.ResourceManager;
import wisoft.pack.utils.UpdateInfo;

public class DirectoryDetailPage implements IDetailsPage {
	private PackInfoModel pack;
	private FileModel fm;
	private TreeViewer tv;
	public DirectoryDetailPage(PackInfoModel pack,TreeViewer tv) {
		this.pack = pack;
		this.tv = tv;
	}

	private IManagedForm mform;
	private File file;
	private Section fileSection;
	private Text fileName;
	private Text filePath;
	private Text lastModify;
	private Button isManalConf;
	private Composite client;
	private Text text_1;
	private Button isDelConf;
	private Button button_isconf;
	private ToolBar toolBar_1;
	private ToolItem Item_save;
	private ToolItem Item_del;
	
	private ModifyListener listener;
	public void createContents(Composite parent) {
	   //���ø������Ĳ���
	   TableWrapLayout layout = new TableWrapLayout();
	   layout.topMargin = 5;
	   layout.leftMargin = 5;
	   layout.rightMargin = 2;
	   layout.bottomMargin = 2;
	   parent.setLayout(layout);
	   //��ñ����߶���
	   FormToolkit toolkit = mform.getToolkit();
	   //����Detail��������
	   fileSection = toolkit.createSection(parent, Section.TITLE_BAR);
	   fileSection.setText("\u6587\u4EF6\u8BE6\u7EC6");
	   TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
	   td.grabHorizontal = true;
	   td.align = TableWrapData.FILL;
	   fileSection.setLayoutData(td);
	   //�����������������õ����
	   client = toolkit.createComposite(fileSection);
	   fileSection.setClient( client );
	   GridLayout gridLayout = new GridLayout();
	   gridLayout.marginWidth = 2;
	   gridLayout.marginHeight = 2;
	   gridLayout.numColumns = 4;
	   gridLayout.horizontalSpacing=10;
	   client.setLayout(gridLayout);
	   //����Detail���־���ĸ��ֿؼ�
	   toolkit.createLabel( client , "�ļ�����:");
	   fileName = toolkit.createText( client ,"");
	   fileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
	   toolkit.createLabel( client , "·��:");
	   filePath = toolkit.createText( client , "");
	   GridData gd_filePath = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
	   gd_filePath.widthHint = 200;
	   filePath.setLayoutData(gd_filePath);
	   toolkit.createLabel( client , "����޸�:");
	   lastModify = toolkit.createText( client , file!=null?new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())):"");
	   lastModify.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
	   new Label(client, SWT.NONE);
	   
	   button_isconf = new Button(client, SWT.CHECK);
	   button_isconf.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		text_1.setText("");
	   		isDelConf.setSelection(false);
	   		isManalConf.setSelection(false);
	   		text_1.setVisible(button_isconf.getSelection());
	   		isDelConf.setVisible(button_isconf.getSelection());
	   		isManalConf.setVisible(button_isconf.getSelection());
	   		Item_save.setEnabled(button_isconf.getSelection());
	   	}
	   });
	   toolkit.adapt(button_isconf, true, true);
	   button_isconf.setText("\u914D\u7F6E\u64CD\u4F5C");
	   
	   toolBar_1 = new ToolBar(client, SWT.FLAT | SWT.RIGHT);
	   toolkit.adapt(toolBar_1);
	   toolkit.paintBordersFor(toolBar_1);
	   
	   //�������ð�ť
	   Item_save = new ToolItem(toolBar_1, SWT.NONE);
	   Item_save.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		if(isDelConf.getSelection()||isManalConf.getSelection())
	   		{
	   			if(isManalConf.getSelection()&&text_1.getText().isEmpty())
	   			{
		   			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
		   					"��ʾ", "�޸Ĳ���������д����˵��");
		   			return;
	   			}
	   			Element element = fm.getFile().addAttribute(UpdateInfo.UpdateFile_isconf, "true");
	   			if(isManalConf.getSelection())
	   				element.addAttribute(UpdateInfo.UpdateFile_conftype, UpdateInfo.FileOpr_Mod);
	   			if(isDelConf.getSelection())
	   				element.addAttribute(UpdateInfo.UpdateFile_conftype, UpdateInfo.FileOpr_Del);
	   			Element element_content =element.element(UpdateInfo.UpdateFile_conftent);
	   			if(element_content!=null)
	   				element.remove(element_content);
	   			element_content = element.addElement(UpdateInfo.UpdateFile_conftent);
	   			element_content.addCDATA(text_1.getText());
	   			pack.getXmlo().save();
	   			Item_save.setEnabled(false);
	   			Item_del.setEnabled(true);
	   		}
	   		else
	   		{
	   			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
	   					"��ʾ", "ѡ��һ�����õĲ�������");
	   			return;
	   		}
	   	}
	   });
	   Item_save.setText("\u4FDD\u5B58\u914D\u7F6E");
	   Item_save.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/save.gif"));
	   Item_save.setEnabled(button_isconf.getSelection());
	   
	   Item_del = new ToolItem(toolBar_1, SWT.NONE);
	   Item_del.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		
	   		boolean isconfim =MessageDialog.openConfirm(
	   				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
	   				"���ü���ɾ��", "��ȷ��Ҫɾ���ô�������");
	   		if(isconfim)
		   	{
		   		Attribute attr = fm.getFile().attribute(UpdateInfo.UpdateFile_isconf);
		   		if(attr!=null)
		   			fm.getFile().remove(attr);
		   		Attribute attr_opr =fm.getFile().attribute(UpdateInfo.UpdateFile_conftype);
	   			if(attr_opr!=null)
	   				fm.getFile().remove(attr_opr);
	   			Element element_content =fm.getFile().element(UpdateInfo.UpdateFile_conftent);
	   			if(element_content!=null)
	   				fm.getFile().remove(element_content);
	   			pack.getXmlo().save();
		   		button_isconf.setSelection(false);
		   		isManalConf.setVisible(false);
		   		isDelConf.setVisible(false);
		   		text_1.setVisible(false);
		   		Item_save.setEnabled(false);
		   		Item_del.setEnabled(false);
		   	}
	   	}
	   });
	   Item_del.setText("\u5220\u9664\u914D\u7F6E");
	   Item_del.setEnabled(button_isconf.getSelection());
	   
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   isManalConf = toolkit.createButton( client , "\u9700\u8981\u7F16\u8F91" ,SWT.RADIO);
	   isManalConf.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   		Item_save.setEnabled(true);
	   	}
	   });
	   toolkit.adapt(isManalConf, true, true);
	   isManalConf.setVisible(button_isconf.getSelection());
	   
	   isDelConf = new Button(client, SWT.RADIO);
	   toolkit.adapt(isDelConf, true, true);
	   isDelConf.setText("\u9700\u8981\u5220\u9664");
	   isDelConf.setVisible(button_isconf.getSelection());
	   isDelConf.addSelectionListener(new SelectionAdapter() {
		   	@Override
		   	public void widgetSelected(SelectionEvent e) {
		   		Item_save.setEnabled(true);
		   	}
		   });
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   
	   text_1 = new Text(client, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
	   GridData gd_text_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
	   gd_text_1.heightHint = 220;
	   text_1.setLayoutData(gd_text_1);
	   toolkit.adapt(text_1, true, true);
	   text_1.setVisible(button_isconf.getSelection());
	   listener = new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Item_save.setEnabled(true);
			}
	   };
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
	//��Master����ѡ���¼�����ʱ
	public void selectionChanged(IFormPart part, ISelection selection) {
	   //���Ȼ��ѡ�еĶ���
	   IStructuredSelection currentSelection = (IStructuredSelection)selection;
	   if (currentSelection.size()==1) 
	   {
		   FileModel fm =(FileModel)currentSelection.getFirstElement();
		   this.fm = fm;
		   String filepath =pack.getSavePath()+"/"+UpdateInfo.UpdateDirName+fm.getFile().attributeValue(UpdateInfo.UpdateFile_fullpath);
		   file = new File(filepath);
	   }
	   //���ѡ�еĶ���Ϊnull,��ˢ�¿ؼ�����ʾ��ֵ
	    update();
	
	}
	//ˢ��ֵ
	public void update (){
		if (file.exists())
		{
		   //�����ļ���
		   fileName.setText(file.getName());
		   //����·��
		   String filefullpath = file.getAbsolutePath().replace("\\", "/");
		   filePath.setText(filefullpath.replace(pack.getSavePath()+"/"+UpdateInfo.UpdateDirName, ""));
		   //�����ϴ��޸�
		   lastModify.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));
		}
		else
		{
			//�����ļ���
		   fileName.setText("����ȡ");
		   //����·��
		   filePath.setText("����ȡ");
		   //�����ϴ��޸�
		   lastModify.setText("����ȡ");
		}
		boolean isconf =Boolean.valueOf(fm.getFile().attributeValue(UpdateInfo.UpdateFile_isconf));
		if(isconf)
		{
			button_isconf.setSelection(Boolean.TRUE);
			isManalConf.setVisible(Boolean.TRUE);
			isDelConf.setVisible(Boolean.TRUE);
			text_1.setVisible(Boolean.TRUE);
			Item_del.setEnabled(true);
			Item_save.setEnabled(false);
			String conftype = fm.getFile().attributeValue(UpdateInfo.UpdateFile_conftype);
			if(conftype!=null)
			{	if(conftype.equals(UpdateInfo.FileOpr_Del))
				{
					isManalConf.setSelection(false);
					isDelConf.setSelection(true);
				}
				if(conftype.equals(UpdateInfo.FileOpr_Mod))
				{
					isManalConf.setSelection(true);
					isDelConf.setSelection(false);
				}
			}
			else
			{
				
				isManalConf.setSelection(false);
				isDelConf.setSelection(false);
			}
			String content =fm.getFile().elementText(UpdateInfo.UpdateFile_conftent);
			
			text_1.removeModifyListener(listener);
			text_1.setText(content==null?"":content);
			text_1.addModifyListener(listener);
		}
		else
		{
			button_isconf.setSelection(Boolean.FALSE);
			text_1.setVisible(button_isconf.getSelection());
	   		isDelConf.setVisible(button_isconf.getSelection());
	   		isManalConf.setVisible(button_isconf.getSelection());
	   		Item_save.setEnabled(button_isconf.getSelection());
	   		Item_del.setEnabled(button_isconf.getSelection());
		}
	   tv.refresh(fm,true);
	 //  tv.remove(fm);
	  //tv.setSelection(new StructuredSelection(fm), true);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	
}
