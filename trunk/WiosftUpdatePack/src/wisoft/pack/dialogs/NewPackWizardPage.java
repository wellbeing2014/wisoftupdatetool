package wisoft.pack.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import wisoft.pack.app.Activator;

public class NewPackWizardPage extends WizardPage implements ModifyListener {
	public Text text;
	public Text text_1;
	public Text text_2;
	public Text text_3;
	public Combo combo ;
	private String path;

	/**
	 * Create the wizard.
	 */
	public NewPackWizardPage() {
		super("wizardPage");
		setTitle("\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684\u66F4\u65B0\u5305\u5DE5\u7A0B");
		setDescription("\u6B22\u8FCE\u4F7F\u7528\u5411\u5BFC");
		path = Activator.getDefault().getWorkSpacePath();
		path=path.substring(path.indexOf("/")+1,path.length());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u6A21\u5757\u540D\u79F0\uFF1A");
		
		combo = new Combo(container, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		combo.addModifyListener(this);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6A21\u5757\u4EE3\u7801\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.addModifyListener(this);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u7248\u672C\u53F7\uFF1A");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_1.addModifyListener(this);
		
		Button button = new Button(container, SWT.CHECK);
		button.setText("\u81EA\u52A8\u751F\u6210");
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u521B\u5EFA\u4EBA\uFF1A");
		
		text_3 = new Text(container, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_2 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		
		
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
		
		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_2.setText(path);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setText("\u6D4F\u89C8");
	}

	@Override
	public void modifyText(ModifyEvent e) {
		// TODO Auto-generated method stub
		if (combo.getText().trim().length() == 0) {
	      setMessage("更新包名字不能为空", IMessageProvider.WARNING);
	      setPageComplete(false);
	      return;
	    }
		else
		{
			text_2.setText(path+combo.getText().trim());
		}
		if (text.getText().trim().length() == 0) {
		      setMessage("更新包代码不能为空", IMessageProvider.WARNING);
		      setPageComplete(false);
		      return;
		    }
		else
		{
			text_2.setText(path+combo.getText().trim()+"("+text.getText().trim()+")");
		}
		if (text_1.getText().trim().length() == 0) {
		      setMessage("更新包版本号不能为空", IMessageProvider.WARNING);
		      setPageComplete(false);
		      return;
		    }
		else
			text_2.setText(path+combo.getText().trim()+"("+text.getText().trim()+")"+text_1.getText().trim());
		setMessage(null);
		setPageComplete(true);
	}
	 
}
