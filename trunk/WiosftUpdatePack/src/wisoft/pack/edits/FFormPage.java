package wisoft.pack.edits;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.wb.swt.ResourceManager;

import wisoft.pack.models.FileModel;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.PackConfigInfo;
import wisoft.pack.utils.UpdateInfo;

public class FFormPage extends FormPage {
	
	private Table table;
	public Text txtNewText;
	Vector<ProgressBar> bars = new Vector<ProgressBar>(); 
	private int serNo =0;
	private PackInfoModel pm;
	private boolean isstop = false;//�Ƿ��ж�
	private PackConfig_Server selSever;//��ǰ���µķ�����
	
	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public FFormPage(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public FFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		pm =((PackInfoInput)editor.getEditorInput()).getPackinfo();
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("���¹���");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(managedForm.getForm().getBody(), SWT.NONE);
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.BORDER | SWT.SMOOTH);
		sashForm.setSashWidth(2);
		sashForm.setOrientation(SWT.VERTICAL);
		managedForm.getToolkit().adapt(sashForm);
		managedForm.getToolkit().paintBordersFor(sashForm);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		managedForm.getToolkit().adapt(composite_1);
		managedForm.getToolkit().paintBordersFor(composite_1);
		composite_1.setLayout(new GridLayout(4, false));
		
		table = new Table(composite_1, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
		managedForm.getToolkit().adapt(table);
		managedForm.getToolkit().paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("\u670D\u52A1\u5668\u540D");
		tableColumn.setWidth(140);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(120);
		tableColumn_2.setText("\u4E0A\u6B21\u66F4\u65B0\u65F6\u95F4");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u8FDB\u5EA6");
		
		Button btnCheckButton = new Button(composite_1, SWT.CHECK);
		managedForm.getToolkit().adapt(btnCheckButton, true, true);
		btnCheckButton.setText("\u91CD\u65B0\u66F4\u65B0");
		
		ImageHyperlink mghprlnkNewImagehyperlink = managedForm.getToolkit().createImageHyperlink(composite_1, SWT.NONE);
		mghprlnkNewImagehyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				updateSchedule();
			}
		});
		mghprlnkNewImagehyperlink.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/btn_deploy_normal.png"));
		mghprlnkNewImagehyperlink.setHoverImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/btn_deploy_over.png"));
		managedForm.getToolkit().paintBordersFor(mghprlnkNewImagehyperlink);
		mghprlnkNewImagehyperlink.setText("");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		managedForm.getToolkit().adapt(composite_2);
		managedForm.getToolkit().paintBordersFor(composite_2);
		composite_2.setLayout(new FormLayout());
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		FormData fd_composite_3 = new FormData();
		fd_composite_3.bottom = new FormAttachment(0, 30);
		fd_composite_3.right = new FormAttachment(100);
		fd_composite_3.top = new FormAttachment(0);
		fd_composite_3.left = new FormAttachment(0);
		composite_3.setLayoutData(fd_composite_3);
		managedForm.getToolkit().adapt(composite_3);
		managedForm.getToolkit().paintBordersFor(composite_3);
		composite_3.setLayout(new GridLayout(2, false));
		
		Label label = new Label(composite_3, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(label, true, true);
		label.setText("\u66F4\u65B0\u65E5\u5FD7\uFF1A");
		
		
		Button button = new Button(composite_3, SWT.NONE);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(button, true, true);
		button.setText("\u4FDD\u5B58\u2026\u2026");
		
		txtNewText = managedForm.getToolkit().createText(composite_2, "New Text", SWT.MULTI);
		FormData fd_txtNewText = new FormData();
		fd_txtNewText.top = new FormAttachment(composite_3, 6);
		fd_txtNewText.bottom = new FormAttachment(100);
		fd_txtNewText.right = new FormAttachment(composite_3, 0, SWT.RIGHT);
		fd_txtNewText.left = new FormAttachment(composite_3, 0, SWT.LEFT);
		txtNewText.setLayoutData(fd_txtNewText);
		sashForm.setWeights(new int[] {15, 30});
		PackConfig_Server[] servers = PackConfigInfo.getInstance().getUnPackProInfos();
		for(PackConfig_Server server:servers)
		{
			TableItem item = new TableItem(table, SWT.NULL);
			
			item.setText(0,server.getServerName());
			item.setText(1,server.getWebappPath());
			
			TableEditor editor = new TableEditor(table);
			 ProgressBar bar = new ProgressBar(table, SWT.NONE);
			 bar.setMaximum(pm.getUpdateFileRoot().countFiles());
	        editor.grabHorizontal = editor.grabVertical = true;
	        editor.setEditor(bar, item, 2);
	        bars.add(bar);
	        item.setData(server);
		}
	}
	
	
	private void updateSchedule()
	{
		checkPack();
		boolean b = MessageDialog.openQuestion(this.getSite().getShell(),"��ʼ���£�","���м����ϣ�ȷ���������³���");
		if(!b)
		{
			print("���жϡ����û���ȡ����",true);
			return;
		}
		for(final TableItem item:this.table.getItems())
		{
			//��ȡҪ���µķ�����
			selSever = (PackConfig_Server)item.getData();
			
			print("����顿�����ļ����Ƿ����----"+selSever.getWebappPath(),true );
			try
			{
				File server = new File(selSever.getWebappPath());
				if(!server.exists()&&!server.isDirectory())
				{
					print("�����󡿣�����ָ��ķ�����WEBAPP�ļ��в����ڣ����Է���"+selSever.getServerName(),true );
					break;
				}
			}
			catch(Exception e)
			{
				print("�����󡿣����������������⣡���ԣ�"+e.getStackTrace().toString(),true );
				break;
			}
			print("���ͨ��������WEBAPP��ַ��"+selSever.getWebappPath(),false);
			copyCircle(pm.getUpdateFileRoot());
		}
	}
	
	/**�ļ�����ѭ����
	 * @param file
	 */
	private void copyCircle(FileModel file )
	{
		if(isstop)
		{
			print("���жϡ����û���ȡ����",true);
			return;
		}
		File serverfile = new File(selSever.getWebappPath()+file.getFullPath());
		if(file.isDir())
		{
			if(!file.isVirtual())
			{
				if(serverfile.exists())
					print("���ϲ��ļ��С���"+serverfile.getAbsolutePath(),false);
				else
					print("�������ļ��С���"+serverfile.getAbsolutePath(),false);
				serverfile.mkdirs();
			}
			if(file.isConf()&&file.getConftype()!=null)
			{
				print("���ļ������á���"+serverfile.getAbsolutePath()+"\n[��������]"+file.getConftype()+"\n[����˵��]"+file.getContent(),false);
			}
		}
		else
		{
			try
			{
				File packfile = new File(pm.getSavePath()+File.separator+UpdateInfo.UpdateDirName+file.getFullPath());
				if(packfile.exists())
				{
					FileUtil.copyFile(packfile, serverfile);
					print("�������ļ�����"+file.getFullPath(),false);
				}
				if(file.isConf())
				{
					print("���ֶ����á���"+serverfile.getAbsolutePath()+"\n[����˵��]"+file.getContent()+"\n[��������]"+file.getConftype(),false);
				}
			}
			catch(Exception e)
			{
				print("�����󡿣������ļ�"+file.getFullPath()+"�������ԣ�"+e.getStackTrace().toString(),true );
			}
		}
		updateprogressbar();
		for(Model zfile:file.getChildren())
		{
			copyCircle((FileModel)zfile);
		}
	}
	/**
	 * ����ǰ�������°�
	 */
	public void checkPack()
	{
		print("����顿���°�������----"+pm.getName(),true );
		print("   ��Ҫ����"+pm.getUpdateFileRoot().countFiles()+"���ļ�",false);
		int confnum = pm.getConfFiles().size();
		print("   ��Ҫ����"+confnum+"���ļ�",false);
	}
	
	
	
	/** ���½�����
	 */
	private void updateprogressbar()
	{
		Display.getDefault().asyncExec(new Runnable() {   
			//����߳��ǵ���UI�߳̿ؼ�
			public void run() {   						
					try {
					Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ProgressBar bar =bars.get(serNo);
					bar.setSelection(bar.getSelection()+1);
				}
		});
	}
	
	/**
	 * ��ʾ���ݵ�text�ؼ���Ϊ�˷�ֹ������������������̣߳��첽����ִ���߳�
	 * */
	private void print(final String str,final boolean isShowTime)
	{
		final SimpleDateFormat sf =  new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Display.getDefault().asyncExec(new Runnable() {   
			//����߳��ǵ���UI�߳̿ؼ�
			public void run() {   
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isShowTime)
					txtNewText.append(sf.format(new Date())+": "+str+"\n");
				else
					txtNewText.append(str+"\n");
			}   
		});   
	}
	
}
