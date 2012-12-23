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
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
	private ToolBar toolBar;
	private ToolItem toolItem;
	private Button button_2;
	@SuppressWarnings("deprecation")
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
	   	}
	   });
	   toolkit.adapt(button_2, true, true);
	   button_2.setText("\u7279\u6B8A\u64CD\u4F5C");
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   isManalConf = toolkit.createButton( client , "\u9700\u8981\u7F16\u8F91" ,SWT.RADIO);
	   
	   button_1 = new Button(client, SWT.RADIO);
	   toolkit.adapt(button_1, true, true);
	   button_1.setText("\u9700\u8981\u5220\u9664");
	   button_1.setVisible(button_2.getSelection());
	   new Label(client, SWT.NONE);
	   new Label(client, SWT.NONE);
	   
	   
	   text_1 = new Text(client, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

	 //  text_1.setLayoutData(twd_text_1);
	  // text_1.setVisible(isManalConf.getSelection());
	   GridData gd_text_1 = new GridData(SWT.FILL, SWT.FILL, true, false, 4, 1);
	   gd_text_1.heightHint = 250;
	   text_1.setLayoutData(gd_text_1);
	   toolkit.adapt(text_1, true, true);
	   text_1.setVisible(button_2.getSelection());
	   
	   toolBar = new ToolBar(fileSection, SWT.FLAT | SWT.RIGHT);
	   toolkit.adapt(toolBar);
	   toolkit.paintBordersFor(toolBar);
	   fileSection.setTextClient(toolBar);
	   
	   toolItem = new ToolItem(toolBar, SWT.NONE);
	   toolItem.addSelectionListener(new SelectionAdapter() {
	   	@Override
	   	public void widgetSelected(SelectionEvent e) {
	   	}
	   });
	   toolItem.setToolTipText("\u5E94\u7528\u8BBE\u7F6E");
	   toolItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/save.gif"));
	   toolItem.setEnabled(isManalConf.getSelection()||button_1.getSelection());
	   //isWrite = toolkit.createButton( client , "�Ƿ��д" ,SWT.CHECK);
	  
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
		   String filepath =pack.getSavePath()+"/"+UpdateInfo.UpdateDirName+fm.getFile().attributeValue(UpdateInfo.UpdateFile_fullpath);
		   file = new File(filepath);
	   }
	   //���ѡ�еĶ���Ϊnull,��ˢ�¿ؼ�����ʾ��ֵ
	   if (file.exists())
	    update();
	}
	//ˢ��ֵ
	@SuppressWarnings("deprecation")
	public void update (){
	   //�����ļ���
	   fileName.setText(file.getName());
	   //����·��
	   filePath.setText(file.getAbsolutePath().replace(pack.getSavePath()+"/"+UpdateInfo.UpdateDirName, ""));
	   //�����ϴ��޸�
	   lastModify.setText(new Date(file.lastModified()).toLocaleString());
	   //���ý���ʱ��
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	
}
