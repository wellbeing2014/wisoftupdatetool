package wisoft.pack.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class PackConfigDialog extends TitleAreaDialog {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public PackConfigDialog(Shell parentShell) {
		super(parentShell);
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
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		new Label(container, SWT.NONE);
		
		Button button = new Button(container, SWT.CHECK);
		button.setText("\u81EA\u5B9A\u4E49\u6784\u5EFA\u8DEF\u5F84");
		
		text = new Text(container, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setText("\u81EA\u5B9A\u4E49\u6784\u5EFA\u5217\u8868");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.setText("\u7BA1\u7406\u6211\u7684\u6A21\u677F");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblWims = new Label(container, SWT.NONE);
		lblWims.setText("WIMS\u63A5\u53E3\u5730\u5740");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblTms = new Label(container, SWT.NONE);
		lblTms.setText("TMS\u63A5\u53E3\u5730\u5740");
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(596, 418);
	}

}
