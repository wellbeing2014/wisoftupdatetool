package wisoft.pack.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.utils.PackConfigInfo;

public class PackConfigDialog extends TitleAreaDialog {
	
	private List<PackConfig_Server> servers ;

	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Table table;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PackConfigDialog(Shell parentShell) {
		super(parentShell);
		servers = new ArrayList<PackConfig_Server>();
	}
	
	@Override
	protected void configureShell(Shell shell) {  
	    super.configureShell(shell);  
	    shell.setText("СЎПо");  
	}  
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitleImage(ResourceManager.getPluginImage("WiosftUpdatePack", "product_lg.bmp"));
		setMessage("\u914D\u7F6E\u5DE5\u5177\u7684\u4E00\u7CFB\u5217\u73AF\u5883");
		setTitle("\u5DE5\u5177\u9009\u9879");
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(null);
		
		CTabFolder tabFolder = new CTabFolder(area, SWT.BORDER);
		tabFolder.setBounds(0, 0, 619, 369);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u6253\u5305\u914D\u7F6E");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		composite.setLayout(new GridLayout(4, false));
		new Label(composite, SWT.NONE);
		
		Button button = new Button(composite, SWT.CHECK);
		button.setText("\u81EA\u5B9A\u4E49\u6784\u5EFA\u8DEF\u5F84");
		
		text = new Text(composite, SWT.BORDER);
		text.setEditable(false);
		text.setText(PackConfigInfo.getInstance().getBuildServerPath());
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite, SWT.NONE);
		
		Label lblWims = new Label(composite, SWT.NONE);
		lblWims.setText("WIMS\u63A5\u53E3\u5730\u5740");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setText(PackConfigInfo.getInstance().getWimsTrackManagerPath());
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite, SWT.NONE);
		
		Label lblTms = new Label(composite, SWT.NONE);
		lblTms.setText("TMS\u63A5\u53E3\u5730\u5740");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite, SWT.NONE);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("\u66F4\u65B0\u5305\u53D1\u5E03\u5730\u5740");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		new Label(composite, SWT.NONE);
		
		Button btnwims = new Button(composite, SWT.CHECK);
		btnwims.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnwims.setText("\u5411WIMS\u540C\u6B65\u66F4\u65B0\u5305\u7248\u672C\u4FE1\u606F");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u9ED8\u8BA4\u5BFC\u51FA\u5730\u5740");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setText(PackConfigInfo.getInstance().getDefaultExportPath());
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(e.display.getActiveShell());
				String path =dd.open();
				if(path!=null)
					text_3.setText(path);
			}
		});
		btnNewButton.setText("\u6D4F\u89C8");
		new Label(composite, SWT.NONE);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u9ED8\u8BA4\u540E\u7F00\u540D");
		
		Composite composite1 = new Composite(composite, SWT.NONE);
		composite1.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button btnRadioButton = new Button(composite1, SWT.RADIO);
		btnRadioButton.setText("wi");
		btnRadioButton.setSelection(true);
		
		Button btnRadioButton_1 = new Button(composite1, SWT.RADIO);
		btnRadioButton_1.setText("rar");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setText("\u7BA1\u7406\u6784\u5EFA\u9879\u76EE");
		
		Label lblhudson = new Label(composite, SWT.NONE);
		lblhudson.setText("\u5B9E\u73B0hudson\u81EA\u52A8\u96C6\u6210\u6784\u5EFA\u7684\u63A5\u53E3,\u8FDB\u884C\u81EA\u52A8\u6784\u5EFA");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setText("\u7BA1\u7406\u6211\u7684\u6A21\u677F");
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("\u5BF9\u65B0\u5EFA\u66F4\u65B0\u5305\u7684\u5FEB\u6377\u64CD\u4F5C\u7684\u6A21\u677F\u8FDB\u884C\u7EF4\u62A4");
		new Label(composite, SWT.NONE);
		
		
		
		
		CTabItem tabItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u89E3\u5305\u914D\u7F6E");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		composite_1.setLayout(new FormLayout());
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new FormLayout());
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(0);
		fd_composite_2.left = new FormAttachment(0);
		fd_composite_2.bottom = new FormAttachment(100, -199);
		fd_composite_2.right = new FormAttachment(100, 0);
		composite_2.setLayoutData(fd_composite_2);
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100);
		fd_table.bottom = new FormAttachment(100);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		ToolBar toolBar = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		fd_table.top = new FormAttachment(toolBar, 0);
		fd_table.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(104);
		tableColumn.setText("\u670D\u52A1\u5668\u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(171);
		tableColumn_1.setText("\u5E94\u7528\u8BBF\u95EE\u5730\u5740");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(131);
		tableColumn_2.setText("\u6570\u636E\u5E93\u8BBF\u95EE");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(167);
		tableColumn_3.setText("\u6240\u5C5E\u9879\u76EE");
		FormData fd_toolBar = new FormData();
		fd_toolBar.left = new FormAttachment(0);
		fd_toolBar.top = new FormAttachment(0);
		toolBar.setLayoutData(fd_toolBar);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PackConfigDialog_UpdateSev pu = new PackConfigDialog_UpdateSev(getParentShell());
				if(IDialogConstants.OK_ID == pu.open())
				{
					servers.add(pu.server);
					getTrackTableData();
				}
			}
		});
		tltmNewItem.setToolTipText("\u65B0\u589E");
		tltmNewItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setToolTipText("\u4FEE\u6539");
		tltmNewItem_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/edit.png"));
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_2.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/del.png"));
		
		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(3, false));
		FormData fd_composite_3 = new FormData();
		fd_composite_3.bottom = new FormAttachment(100);
		fd_composite_3.right = new FormAttachment(100);
		fd_composite_3.top = new FormAttachment(composite_2, 3);
		fd_composite_3.left = new FormAttachment(0);
		composite_3.setLayoutData(fd_composite_3);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("FTP\u5730\u5740\uFF1A");
		
		text_5 = new Text(composite_3, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Button button_4 = new Button(composite_3, SWT.CHECK);
		button_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		button_4.setText("\u81EA\u52A8\u5907\u4EFD");
		new Label(composite_3, SWT.NONE);
		
		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u5907\u4EFD\u5730\u5740\uFF1A");
		
		text_6 = new Text(composite_3, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_1.setText("\u6D4F\u89C8");
		
		Label label_5 = new Label(composite_3, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("\u6570\u636E\u5E93\u5907\u4EFD\uFF1A");
		
		text_7 = new Text(composite_3, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_3 = new Button(composite_3, SWT.NONE);
		button_3.setText("\u5F00\u59CB");
		


		return area;
	}

	private void getTrackTableData()
	{
		table.removeAll();
		for(PackConfig_Server server :servers)
		{
			TableItem item  = new TableItem(table, SWT.NONE);
			item.setText(new String[]{server.getServerName(),server.getWebappPath(),server.getDBPath(),"їХ"});
		}
	}
	
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		//PackConfigInfo.getInstance().setBuildServerPath(text.getText());
		//PackConfigInfo.getInstance().setWimsTrackManagerPath(text_1.getText());
		PackConfigInfo.getInstance().setDefaultExportPath(text_3.getText());
		
		super.okPressed();
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
		return new Point(625, 538);
	}
}
