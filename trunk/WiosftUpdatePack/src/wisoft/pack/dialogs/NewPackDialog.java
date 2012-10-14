package wisoft.pack.dialogs;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class NewPackDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	
	Rectangle shellBounds;
	Rectangle parentBounds;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NewPackDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		parentBounds = parent.getBounds(); 
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(414, 226);
		shell.setText("\u65B0\u5EFA\u66F4\u65B0\u5305");
		shell.setLayout(null);
		shellBounds = shell.getBounds(); 
		shell.setLocation(parentBounds.x + (parentBounds.width - shellBounds.width)/2, parentBounds.y + (parentBounds.height - shellBounds.height)/2);
		Label label =new Label(shell, SWT.NONE);
		label.setBounds(45, 26, 60, 17);
		label.setText("\u9009\u62E9\u6A21\u5757\uFF1A");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(110, 22, 217, 24);
		
		
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(45, 55, 60, 17);
		label_1.setText("\u53D1\u5E03\u7248\u672C\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(110, 52, 217, 20);
	
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(45, 83, 60, 17);
		label_2.setText("\u6D4B\u8BD5\u7248\u672C\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(110, 80, 217, 20);
		
		//确定按钮
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("1231231");
			}
		});
		btnNewButton.setBounds(321, 161, 36, 27);
		btnNewButton.setText("\u786E\u5B9A");
		
		//取消按钮
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MessageBox mb = new MessageBox(shell, SWT.OK|SWT.CANCEL);
				if(mb.open()==SWT.CANCEL)
				{
				shell.dispose();
				}
			}
		});
		btnNewButton_1.setBounds(362, 161, 36, 27);
		btnNewButton_1.setText("\u53D6\u6D88");

	}

}
