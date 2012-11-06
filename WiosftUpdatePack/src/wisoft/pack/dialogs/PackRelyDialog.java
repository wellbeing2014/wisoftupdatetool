package wisoft.pack.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import wisoft.pack.app.Activator;

public class PackRelyDialog extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PackRelyDialog(Shell parentShell) {
		super(parentShell);
		//parentShell.setText("新建关联更新包");
	}

	@Override
	protected void configureShell(Shell shell) {  
	    super.configureShell(shell);  
	    shell.setText("新建关联更新包");  
	    shell.setImage(new Image(null,Activator.getImageDescriptor("icons/wi_updatetool.ico").getImageData()));
	}  
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

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
		return new Point(450, 300);
	}

}
