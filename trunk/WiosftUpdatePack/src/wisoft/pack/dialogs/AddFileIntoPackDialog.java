package wisoft.pack.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import wisoft.pack.app.PackConfigs;
import wisoft.pack.edits.MasterContentProvider;
import wisoft.pack.edits.MasterLabelProvider;
import wisoft.pack.edits.MasterStyleLabelProviderC;

public class AddFileIntoPackDialog extends Dialog {
	private Text text;
	//private List<String> packPaths;
	private String defaultpath;
	CheckboxTreeViewer  tv ;
	Tree tree;
	//public String packPath="";
	public String filePath = "";
	public List<File> filelist = new ArrayList<File>();
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AddFileIntoPackDialog(Shell parentShell,String defaultpath) {
		super(parentShell);
		this.defaultpath =defaultpath;
	}
	private void getCheckedFiles(TreeItem[] tis)
	{
		for(TreeItem ti: tis)
		{
			if(ti.getChecked())
			{
				filelist.add((File)ti.getData());
			}
			getCheckedFiles(ti.getItems());
		}
	}
	@Override
	protected void okPressed() 
	{
		//final PackInfoInput pi = (PackInfoInput)page.getEditorInput();
		//packPath = this.text.getText();
		filePath = this.text.getText();
		getCheckedFiles(tv.getTree().getItems());
		super.okPressed();
	}
	@Override
	protected void configureShell(Shell shell) {  
	    super.configureShell(shell);  
	    shell.setText("添加选项");  
	}  
	
	private void setcombodata()
	{
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
		setcombodata();
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setText(defaultpath);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6E90\u76EE\u5F55\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		//text.setEditable(false)
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(getParentShell());
				dd.setText("选择文件夹");
				dd.setFilterPath("C:\\");
				String path =dd.open();
				if(path!=null)
				{
				text.setText(path);
				tv.setInput(new File(path));
				tv.refresh();
				}
			}
		});
		button_4.setToolTipText("\u8BBE\u7F6E\u4E00\u4E2A\u9ED8\u8BA4\u6253\u5F00\u7684\u76EE\u5F55");
		button_4.setText("\u6D4F\u89C8");
		
		Button button = new Button(container, SWT.NONE);
		button.setText("\u8BBE\u4E3A\u9ED8\u8BA4");
		
		tree = new Tree(container, SWT.BORDER | SWT.CHECK | SWT.MULTI);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		
		tv = new CheckboxTreeViewer(tree);
		tv.setContentProvider(new MasterContentProvider());
		//设置树的标签
		
		tv.setLabelProvider(new MasterLabelProvider(new MasterStyleLabelProviderC()));
		//tv.setInput();
		
		tv.addCheckStateListener(new ICheckStateListener() {
			
			MasterContentProvider provider = (MasterContentProvider)tv.getContentProvider();
			
			
			
			private void setParentGrayed(Object element)
			{
				Object Parent_element =provider.getParent(element);
				if(Parent_element==null||tv.getInput().equals(Parent_element))
					return ;
				tv.setGrayChecked(Parent_element, true);
				setParentGrayed(Parent_element);
				
			}
			
			private void setParentCheck(Object element)
			{
				//获取父对象
				Object Parent_element =provider.getParent(element);
				
				if(Parent_element==null||tv.getInput().equals(Parent_element))
					return ;
				//获取所有兄弟对象
				Object[] elements =provider.getChildren(Parent_element);
				if(elements.length==1)
				{
					tv.setChecked(Parent_element, true);
					setParentCheck(Parent_element);
					return;
				}
				else
				{
					boolean haveHalfChecked = false;
					for(Object zelement:elements)
					{
						if(zelement!=element)
						{
							//兄弟对象 没有设置 checked，父对象一定为 半选 状态
							if(!tv.getChecked(zelement))
							{
								haveHalfChecked = true;
								break;
							}
						}
					}
					if(haveHalfChecked)
						setParentGrayed(element);
					else
					{
						tv.setGrayChecked(Parent_element, false);
						tv.setChecked(Parent_element, true);
					}
					setParentCheck(Parent_element);
				}
			}
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				// TODO Auto-generated method stub
				
				Object element = event.getElement();
				
				boolean checked = event.getChecked();
		        tv.setSubtreeChecked(element, checked);
		        tv.setGrayed(element, false);
				setParentCheck(element);
			}
		});
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
