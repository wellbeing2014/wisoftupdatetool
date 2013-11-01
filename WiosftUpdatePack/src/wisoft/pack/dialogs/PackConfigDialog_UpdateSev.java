package wisoft.pack.dialogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import wisoft.pack.models.PackConfig_Server;

import com.wisoft.wims.ResultReturnByArray;
import com.wisoft.wims.TrankingManager;
import com.wisoft.wims.WimsProInfo;

public class PackConfigDialog_UpdateSev extends Dialog {
	
	public PackConfig_Server server ;
	private boolean isEdit =false;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Combo combo;
	private Button btnCheckButton;

	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public PackConfigDialog_UpdateSev(Shell parentShell) {
		super(parentShell);
		server = new PackConfig_Server();
	}
	
	public PackConfigDialog_UpdateSev(Shell parentShell,PackConfig_Server server) {
		super(parentShell);
		this.server = server;
		this.isEdit = true;
	}
	
	private void updateinit()
	{
		this.text.setText(this.server.getServerName());
		this.text_1.setText(this.server.getWebappPath());
		this.text_2.setText(this.server.getDBPath());
		this.text_3.setText(this.server.getWSMPath());
		this.text_4.setText(this.server.getWebPort());
		this.text_5.setText(this.server.getServerUser());
		this.text_6.setText(this.server.getServerPwd());
		this.combo.setText(this.server.getProinfo().getProname());
		btnCheckButton.setSelection(this.server.isNewStruct());
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		if(isEdit)
			newShell.setText("编辑服务器");
		else
			newShell.setText("增加服务器");
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		MessageBox mb = new MessageBox(this.getParentShell());
		mb.setText("提示");
		if(this.text.getText().isEmpty())
		{
			mb.setMessage("服务名称不能为空");
			mb.open();
			return;
		}
		
		if(this.text_2.getText().isEmpty())
		{
			mb.setMessage("数据库路径不能为空");
			mb.open();
			return;
		}
		
		if(this.text_1.getText().isEmpty())
		{
			mb.setMessage("服务部署路径不能为空");
			mb.open();
			return;
		}
		
		server.setServerName(this.text.getText());
		server.setDBPath(this.text_2.getText());
		server.setServerPwd(this.text_6.getText());
		server.setServerUser(this.text_5.getText());
		server.setWebappPath(this.text_1.getText());
		server.setWebPort(this.text_4.getText());
		server.setWSMPath(this.text_3.getText());
		server.setNewStruct(this.btnCheckButton.getSelection());
		String proname= this.combo.getText();
		if(proname.isEmpty())
		{
			mb.setMessage("所属项目不能为空");
			mb.open();
			return;
		}
		WimsProInfo pro = (WimsProInfo)this.combo.getData(proname);
		if(null==pro)
		{
			pro = new WimsProInfo();
			pro.setProname(proname);
		}
		server.setProinfo(pro);
		mb=null;
		super.okPressed();
		
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(6, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u670D\u52A1\u5668\u540D\u79F0");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(container, SWT.NONE);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u5E94\u7528\u8BBF\u95EE\u5730\u5740");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(container, SWT.NONE);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u6570\u636E\u8BBF\u95EE\u5730\u5740");
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dbconStr =text_2.getText().trim();
				if(dbconStr.isEmpty())
					MessageDialog.openError(getParentShell(), "错误", "请输入数据库连接字符串，如user/pwd@ip/orcl");
				else
				{
			        String sql = "SQLPLUS "+dbconStr;
			        try {
			        	final Process proc =Runtime.getRuntime().exec(sql);
			        	
			        	//读取打印数据流
			        	proc.getOutputStream().close();
            			final BufferedReader pin = new BufferedReader(
            		              new InputStreamReader(proc.getInputStream()));
            		    Thread errReadThread = new Thread() {
            	            public void run() {
            	              try {
            	            	   String errorstr1="";
            	                String line=null;
            	                while ( (line=pin.readLine()) != null) {
            	                	System.out.println(line);
            	                	errorstr1+=line;
            	                }
            	                
            	                int i =errorstr1.indexOf("ERROR:");
            	                if(i!=-1)
            	                {
            	                	errorstr1=errorstr1.substring(i);
            	                }
            	                
            	                int j =errorstr1.indexOf(";");
            	                if(j!=-1)
            	                {
            	                	errorstr1=errorstr1.substring(0,j+1);
            	                }
            	                
            	                final String errorstr=errorstr1;
            	                Display.getDefault().asyncExec(new Runnable() {   
            	        			//这个线程是调用UI线程控件
            	        			public void run() {   
            	        				if(errorstr.contains("ERROR:"))
            	        					MessageDialog.openError(getParentShell(), "错误", errorstr);
            	        				if(errorstr.contains("连接到:")||errorstr.contains("SQL>"))
            	        					MessageDialog.openInformation(getParentShell(), "成功", "测试成功！");
            	        			}   
            	        		});
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
			}
		});
		button.setText("\u6D4B\u8BD5");
		new Label(container, SWT.NONE);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u6240\u5C5E\u9879\u76EE");
		
		combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		TrankingManager tm = new TrankingManager();
		ResultReturnByArray rr = tm.getTrackingManager().findAllWimsProInfo();
		WimsProInfo[] pros = rr.getArrayobj().toArray(new WimsProInfo[0]);
		
		int key =0;
		for(WimsProInfo pro: pros)
		{
			combo.add(pro.getProname());
			combo.setData(pro.getProname(), pro);
			key++;
		}
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblWsm = new Label(container, SWT.NONE);
		lblWsm.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblWsm.setText("WSM\u5730\u5740");
		
		text_3 = new Text(container, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 4, 1));
		new Label(container, SWT.NONE);
		
		btnCheckButton = new Button(container, SWT.CHECK);
		btnCheckButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnCheckButton.setText("\u662F\u5426\u4E3A\u65B0\u6846\u67B6\u7ED3\u6784");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u670D\u52A1\u5668\u7AEF\u53E3");
		
		text_4 = new Text(container, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u670D\u52A1\u5668\u7528\u6237\u540D");
		
		text_5 = new Text(container, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setText("\u670D\u52A1\u5668\u5BC6\u7801");
		
		text_6 = new Text(container, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		if(isEdit)
			updateinit();
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(505, 346);
	}

}
