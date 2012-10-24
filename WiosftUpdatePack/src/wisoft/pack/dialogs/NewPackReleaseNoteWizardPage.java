package wisoft.pack.dialogs;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class NewPackReleaseNoteWizardPage extends WizardPage {
	public Text text;
	public Text text_1;

	/**
	 * Create the wizard.
	 */
	public NewPackReleaseNoteWizardPage() {
		super("wizardPage");
		setTitle("\u53D1\u5E03\u8BF4\u660E");
		setDescription("\u8BF7\u63CF\u8FF0\u4E00\u6BB5\u53D1\u5E03\u8BF4\u660E");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());
		
		text = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 10);
		fd_text.left = new FormAttachment(0, 10);
		fd_text.bottom = new FormAttachment(100, -50);
		fd_text.right = new FormAttachment(100, -10);
		text.setLayoutData(fd_text);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(text, 5);
		fd_composite.bottom = new FormAttachment(100, -10);
		fd_composite.right = new FormAttachment(100, -10);
		fd_composite.left = new FormAttachment(0, 10);
		composite.setLayoutData(fd_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("\u5173\u952E\u5B57\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}
}
