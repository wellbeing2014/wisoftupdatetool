package wisoft.pack.dialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import wisoft.pack.models.FileModel;
import wisoft.pack.models.Model;
import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.PackRelyModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.PackConfigInfo;
import wisoft.pack.utils.UpdateInfo;

public class UpdateServerDialog extends Dialog {

	private PackInfoModel pm;
	protected Object result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;
	private Text text_1;
	private PackConfig_Server selSever;
	private ComboViewer comboViewer;
	private ProgressBar progressBar;
	//ȫ��״̬ status 0��ʾδ��ʼ��1��ʾ�ѿ�ʼ 2��ʾ��ͣ��3��ʾȡ��
	private int status = 0; 
	Thread  thread;
	Button button;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateServerDialog(Shell parent, int style) {
		super(parent, style);
		
	}
	
	public UpdateServerDialog(Shell parent, PackInfoModel pm) {
		super(parent, SWT.NONE);
		this.pm = pm;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shell.setSize(507, 497);
		shell.setText("\u66F4\u65B0\u5DE5\u4F5C\u53F0");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Rectangle parentBounds = getParent().getBounds();  
		Rectangle shellBounds = shell.getBounds();  
		shell.setLocation(parentBounds.x + (parentBounds.width - shellBounds.width)/2, parentBounds.y + (parentBounds.height - shellBounds.height)/2); 
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 100);
		fd_composite_1.right = new FormAttachment(100);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.left = new FormAttachment(0);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(composite_1);
		fd_composite_2.bottom = new FormAttachment(100);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_2, true, true);
		label_2.setText("\u66F4\u65B0\u5305\u540D\u79F0\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		text.setText(pm.getName());
		formToolkit.adapt(text, true, true);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label, true, true);
		label.setText("\u9009\u62E9\u670D\u52A1\u5668\uFF1A");
		
		
		comboViewer = new ComboViewer(composite_1, SWT.READ_ONLY);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				// TODO Auto-generated method stub
				assert element instanceof PackConfig_Server;  
				PackConfig_Server server = (PackConfig_Server)element;  
			    return server.getServerName();  
			}
		});
		PackConfig_Server[] servers = PackConfigInfo.getInstance().getUnPackProInfos();
		comboViewer.setInput(servers);
		if(servers.length>0)
			comboViewer.setSelection(new StructuredSelection(servers[0]));
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		formToolkit.paintBordersFor(combo);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		formToolkit.adapt(label_1, true, true);
		label_1.setText("\u66F4\u65B0\u8FDB\u5EA6\uFF1A");

		
		progressBar = new ProgressBar(composite_1, SWT.NONE);
		//gd_progressBar.widthHint = 239;
		progressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		button = formToolkit.createButton(composite_1, "\u5F00\u59CB/\u6682\u505C", SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doStart();
			}
		});
		progressBar.setMinimum(0);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		
		Button button_1 = formToolkit.createButton(composite_1, "\u53D6\u6D88", SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		composite_2.setLayout(new FormLayout());
		fd_composite_2.right = new FormAttachment(100);
		fd_composite_2.left = new FormAttachment(0);
		composite_2.setLayoutData(fd_composite_2);
		
		SashForm sashForm = new SashForm(composite_2, SWT.SMOOTH);
		sashForm.setOrientation(SWT.VERTICAL);
		FormData fd_sashForm = new FormData();
		fd_sashForm.top = new FormAttachment(0);
		fd_sashForm.bottom = new FormAttachment(100);
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.left = new FormAttachment(0, 5);
		sashForm.setLayoutData(fd_sashForm);
		formToolkit.adapt(sashForm);
		formToolkit.paintBordersFor(sashForm);
		
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		formToolkit.adapt(table);
		formToolkit.paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setText("\u5E8F\u53F7");
		tableColumn.setWidth(41);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(269);
		tableColumn_1.setText("\u914D\u7F6E\u9879");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(62);
		tableColumn_2.setText("\u914D\u7F6E\u7C7B\u578B");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(59);
		tableColumn_3.setText("\u72B6\u6001");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(40);
		tableColumn_4.setText("\u64CD\u4F5C");
		
		text_1 = new Text(sashForm, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		formToolkit.adapt(text_1, true, true);
		sashForm.setWeights(new int[] {110, 246});
		 thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("aaa"); 
			}
		});
	}
	
	private void addTableData()
	{
		 List<FileModel> tabledata = pm.getConfFiles();
		 table.removeAll();
	     for (int i = 0;i<tabledata.size();i++) {
	    	 final FileModel dataitem = tabledata.get(i);
	    	 TableItem item =new TableItem(table, SWT.NONE);
	      item.setText(0, String.valueOf(i+1));
	      item.setText(1, dataitem.getFullPath());
	      item.setText(2, dataitem.getConftype());
	      item.setText(3, dataitem.getFullPath());
	      TableEditor editor = new TableEditor(table);
	      Button button = new Button(table, SWT.NONE);
	      if(UpdateInfo.FileOpr_Del.equals(dataitem.getConftype()))
	    	  button.setText("ɾ��");
	      if(UpdateInfo.FileOpr_Mod.equals(dataitem.getConftype()))
	    	  button.setText("�޸�");
	      
	      button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(UpdateInfo.FileOpr_Del.equals(dataitem.getConftype()))
				    	  MessageDialog.openConfirm(shell, "��ʾ", "��������Ҫɾ��"+selSever.getWebappPath()+dataitem.getFullPath()+"ȷ��ɾ�����ļ���");
				    if(UpdateInfo.FileOpr_Mod.equals(dataitem.getConftype()))
				    {
				    	MessageDialog.openInformation(shell, "��ʾ", selSever.getWebappPath()+dataitem.getFullPath());
				    	UpdateServerDialog_EditConf1 edit =new UpdateServerDialog_EditConf1();
				    	edit.open();
				    }
				}
	      });
	      button.pack();
	      editor.minimumWidth = button.getSize().x;
	      editor.horizontalAlignment = SWT.LEFT;
	      editor.setEditor(button, item, 4);
	      
	      table.setData(tabledata.get(i));
	    }
	}
	
	private void doStart()
	{
		switch(status)
		{
		case 0: //����һ��������
			status=1;
			button.setText("��ͣ");
			if(thread.isAlive())
				thread.stop();
			thread.start();
		case 1: status=2;
			thread.suspend();
			button.setText("����");
			print("����ͣ��",true );
		case 2: status=1;
			thread.resume();
			button.setText("��ͣ");
			print("��������",true );
		case 3: //����һ��������
			status=1;
			button.setText("��ͣ");
			if(thread.isAlive())
				thread.stop();
			thread.start();
		}
	}
	
	private void doUpdateFiles()
	{
		print("����顿���°�������----"+pm.getName(),true );
		num=0;
		countFile(pm.getUpdateFileRoot());
		progressBar.setMaximum(num);
		print("   ��Ҫ����"+num+"���ļ�",false);
		int confnum = pm.getConfFiles().size();
		print("   ��Ҫ����"+confnum+"���ļ�",false);
		addTableData();
		IStructuredSelection selection = (IStructuredSelection)comboViewer.getSelection();
		PackConfig_Server ps= new PackConfig_Server();
		if(selection!=null&&selection.getFirstElement()!=null)
		{
			ps = (PackConfig_Server)selection.getFirstElement();
			selSever = ps;
		}
		print("����顿�����ļ����Ƿ����----"+selSever.getWebappPath(),true );
		try
		{
			File server = new File(selSever.getWebappPath());
			if(!server.exists()&&!server.isDirectory())
			{
				print("�����󡿣�����ָ��ķ�����WEBAPP�ļ��в����ڣ����Է���"+ps.getServerName(),true );
				return;
			}
		}
		catch(Exception e)
		{
			print("�����󡿣����������������⣡���ԣ�"+e.getStackTrace().toString(),true );
			return;
		}
		print("���ͨ��������WEBAPP��ַ��"+ps.getWebappPath(),false);
		
		print("����顿���°�����----",true );
		List<PackRelyModel> packrelys = pm.getPackRelys();
		if(packrelys.size()>0)
		{
			for(PackRelyModel prm:packrelys)
			{	
				print("[���°����� ]--- "+prm.getName()+" ����ʱ�䣺"+prm.getPublishTime(),false );
				boolean b = MessageDialog.openQuestion(this.shell,"ע���и��°�����","�˸��°�����Ҫ�ȸ���  "+prm.getName()+"\n����ʱ�䣺"+prm.getPublishTime()+"\nȷ���������£�");
				if(b)
					continue;
				else
					print("���жϡ����û���ȡ����",true);
			}
		}
		
		boolean b = MessageDialog.openQuestion(this.shell,"��ʼ���£�","���м����ϣ�ȷ���������³���");
		if(b)
			copyFile();
		else
			print("���жϡ����û���ȡ����",true);
		print("�ļ��������:",true);
	}
	
	private int num;
	private Table table;
	private void countFile(FileModel file)
	{
		num++;
		for(Model zfile:file.getChildren())
		{
			countFile((FileModel)zfile);
		}
	}
	private void copyFile()
	{
		FileModel file =pm.getUpdateFileRoot();
		copyCircle(file);
	}
	
	
	private void copyCircle(FileModel file )
	{
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
		
		
		for(Model zfile:file.getChildren())
		{
			copyCircle((FileModel)zfile);
		}
		Display.getDefault().asyncExec(new Runnable() {   
			//����߳��ǵ���UI�߳̿ؼ�
			public void run() {   
				 if (progressBar.isDisposed())
			         return;
			        progressBar.setSelection(progressBar.getSelection() + 1);
			}
		});
	}
	
	private void doExecuteSql()
	{
		print("��ִ��SQL��:��ʼ",true);
		String sql = "SQLPLUS "+selSever.getDBPath()+" @"+pm.getSavePath()+File.separator+UpdateInfo.SqlFileName;
        try {
        	final Process proc =Runtime.getRuntime().exec(sql);
        	
        	//��ȡ��ӡ������
        	proc.getOutputStream().close();
			final BufferedReader pin = new BufferedReader(
		              new InputStreamReader(proc.getInputStream()));
		    Thread errReadThread = new Thread() {
	            public void run() {
	              try {
	                String line=null;
	                while ( (line=pin.readLine()) != null) {
	                	System.out.println(line);
	                	
	                	print(line,false);
	                }
	                
	                proc.waitFor();
	                pin.close();
	              }
	              catch (Exception ex) {
	                ex.printStackTrace();
	              }
	            }
	          };
	          
	          errReadThread.start();
	          
		    
        } catch (Exception e1) {
            e1.printStackTrace();
        }
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
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(isShowTime)
					text_1.append(sf.format(new Date())+": "+str+"\n");
				else
					text_1.append(str+"\n");
			}   
		});   
	}
}
