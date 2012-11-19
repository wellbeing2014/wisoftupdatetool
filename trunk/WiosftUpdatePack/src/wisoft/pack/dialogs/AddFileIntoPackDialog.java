package wisoft.pack.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import wisoft.pack.edits.MasterContentProvider;
import wisoft.pack.edits.MasterLabelProvider;

public class AddFileIntoPackDialog extends Dialog {
	private Text text;
	private String[] packPaths;
	private Combo combo;
	private String defaultpath;
	TreeViewer tv ;
	public int filetype = 0;
	public boolean isMutiFile = false;
	public String packPath="";
	public String[] filePath = {""};

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AddFileIntoPackDialog(Shell parentShell,String[] dataprovider,String defaultpath) {
		super(parentShell);
		this.packPaths = dataprovider;
		this.defaultpath = defaultpath;
	}
	
	@Override
	protected void okPressed() 
	{
		
	}
	@Override
	protected void configureShell(Shell shell) {  
	    super.configureShell(shell);  
	    shell.setText("添加选项");  
	}  
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 4;
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u6DFB\u52A0\u81F3\uFF1A");
		
		combo = new Combo(container, SWT.NONE);
		combo.setToolTipText("\u9009\u62E9\u66F4\u65B0\u5305\u5185\u5DF2\u6709\u7684\u76EE\u5F55");
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.setItems(packPaths);
		combo.setText(defaultpath.isEmpty()?packPaths[0]:defaultpath);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6E90\u76EE\u5F55\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(getParentShell());
				dd.setText("选择文件夹");
				//dd.setFilterPath()
				String path =dd.open();
				text.setText(path);
				tv.setInput(new File(path));
				tv.refresh();
			}
		});
		button_4.setToolTipText("\u8BBE\u7F6E\u4E00\u4E2A\u9ED8\u8BA4\u6253\u5F00\u7684\u76EE\u5F55");
		button_4.setText("\u6D4F\u89C8");
		
		Button button = new Button(container, SWT.NONE);
		button.setText("\u8BBE\u4E3A\u9ED8\u8BA4");
		
		Tree tree = new Tree(container, SWT.BORDER | SWT.CHECK | SWT.MULTI);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		tv = new TreeViewer(tree);
		tv.setContentProvider(new MasterContentProvider());
		//设置树的标签
		tv.setLabelProvider(new MasterLabelProvider());
		//tv.setInput();
		tv.expandToLevel(3);
		
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
		return new Point(539, 522);
	}

}
