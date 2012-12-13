package wisoft.pack.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class AddConfIntoPackDialog extends Dialog {
	private Text text;
	private Combo combo;
	private String[] packPaths;
	public String packPath="";
	public String filePath = "";
	private String defaultpath;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	
	public AddConfIntoPackDialog(Shell parentShell,String[] dataprovider,String defaultpath)
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
		//getCheckedFiles(tv.getTree().getItems());
		super.okPressed();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		
		super.configureShell(newShell);
		newShell.setText("ÃÌº”≈‰÷√");
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
		combo.setItems(packPaths);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setAlignment(SWT.RIGHT);
		lblNewLabel_1.setText("\u6587\u4EF6\u7C7B\u578B\uFF1A");
		
		Button btnRadioButton = new Button(container, SWT.RADIO);
		btnRadioButton.setText("\u6587\u4EF6");
		
		Button button = new Button(container, SWT.RADIO);
		button.setText("\u6587\u4EF6\u5939");
		
		Label label = new Label(container, SWT.RIGHT);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setAlignment(SWT.RIGHT);
		label.setText("\u540D\u79F0\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

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
		return new Point(441, 205);
	}

}
