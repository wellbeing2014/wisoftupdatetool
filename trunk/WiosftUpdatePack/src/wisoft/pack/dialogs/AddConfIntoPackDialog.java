package wisoft.pack.dialogs;

import java.util.Map;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddConfIntoPackDialog extends Dialog {
	private Text text;
	private Combo combo;
	private Map<String ,Element> packPaths;
	public String packPath="";
	public String filePath = "";
	public String content="";
	public boolean isfile = true;
	public boolean isdel =  false;
	
	private String defaultpath;
	
	private Text text_1;
	private Button ckbutton_file;
	private Button ckbutton_dir;
	private Button ckbutton_modify;
	private Button ckbutton_del;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	
	public AddConfIntoPackDialog(Shell parentShell,Map<String ,Element> dataprovider,String defaultpath)
	{
		super(parentShell);
		this.packPaths = dataprovider;
		this.defaultpath = defaultpath;
	}
	
	@Override
	protected void okPressed() 
	{
		//final PackInfoInput pi = (PackInfoInput)page.getEditorInput();
		packPath = this.combo.getText();
		filePath = this.text.getText();
		content = this.text_1.getText();
		//getCheckedFiles(tv.getTree().getItems());
		isfile = ckbutton_file.getSelection();
		isdel = ckbutton_del.getSelection();
		super.okPressed();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		
		super.configureShell(newShell);
		newShell.setText("ÃÌº”≈‰÷√");
	}
	
	private void setcombodata()
	{
		int i=0;
		boolean ishave =false;
		for(Map.Entry<String, Element> entry :packPaths.entrySet())
		{
			combo.setData(entry.getKey(),entry.getValue());
			if(!ishave)
			{
				if(defaultpath.equals(entry.getKey()))
				{
					ishave = true;
				}
				else
					i++;
			}
		}
		combo.select(0);
	}
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 3;
		
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u6DFB\u52A0\u81F3\uFF1A");
		
		combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		setcombodata();
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setText("\u6587\u4EF6\u7C7B\u578B\uFF1A");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_composite.heightHint = 20;
		composite.setLayoutData(gd_composite);
		
		ckbutton_file = new Button(composite, SWT.RADIO);
		ckbutton_file.setSelection(true);
		ckbutton_file.setText("\u6587\u4EF6");
		
		ckbutton_dir= new Button(composite, SWT.RADIO);
		ckbutton_dir.setText("\u6587\u4EF6\u5939");
		
		Label label = new Label(container, SWT.RIGHT);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setAlignment(SWT.RIGHT);
		label.setText("\u540D\u79F0\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u64CD\u4F5C\u7C7B\u578B\uFF1A");
		
		ckbutton_modify = new Button(container, SWT.RADIO);
		ckbutton_modify.setSelection(true);
		ckbutton_modify.setText("\u4FEE\u6539");
		
		ckbutton_del = new Button(container, SWT.RADIO);
		ckbutton_del.setText("\u5220\u9664");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u914D\u7F6E\u8BF4\u660E\uFF1A");
		
		text_1 = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 5));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

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
		return new Point(464, 354);
	}
}
