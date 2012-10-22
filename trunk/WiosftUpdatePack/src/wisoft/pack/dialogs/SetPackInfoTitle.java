package wisoft.pack.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class SetPackInfoTitle extends Dialog {
	private Text text;
	private Text text_1;
	private Combo combo;
	private String packname;

	public String getPackname() {
		return packname;
	}

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SetPackInfoTitle(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		Label lblNewLabel = new Label(container, SWT.SHADOW_NONE | SWT.CENTER);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(37, 10, 100, 23);
		lblNewLabel.setText("\u6A21\u5757\u540D\u79F0");
		
		Label label = new Label(container, SWT.SHADOW_NONE | SWT.CENTER);
		label.setText("\u6A21\u5757\u4EE3\u7801");
		label.setAlignment(SWT.CENTER);
		label.setBounds(37, 47, 100, 23);
		
		Label label_1 = new Label(container, SWT.SHADOW_NONE | SWT.CENTER);
		label_1.setText("\u6A21\u5757\u7248\u672C");
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(37, 88, 100, 23);
		
		combo = new Combo(container, SWT.NONE);
		combo.setBounds(143, 10, 162, 23);
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(143, 47, 73, 23);
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setBounds(143, 88, 73, 23);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}
	
	
	
	@Override
	protected void okPressed() {
		if(text.getText().trim().isEmpty()||text_1.getText().trim().isEmpty()||combo.getText().trim().isEmpty())
		{
			MessageBox mb = new MessageBox(this.getShell());
			mb.setText("¥ÌŒÛ");
			mb.setMessage("«Î ‰»ÎÕÍ’˚£°");
			mb.open();
		}
		else
		{
			packname=combo.getText().trim()+"("+text.getText().trim()+")"+text_1.getText().trim();
			super.okPressed();
		}
	}
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(378, 239);
	}
	
}
