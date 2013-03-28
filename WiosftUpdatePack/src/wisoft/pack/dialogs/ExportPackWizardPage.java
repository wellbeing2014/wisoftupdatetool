package wisoft.pack.dialogs;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.PackConfigInfo;
import wisoft.pack.utils.UpdateInfo;

public class ExportPackWizardPage extends WizardPage {
	public Text text;
	private PackInfoModel pack;
	public Text text_1;
	private Label label_1;
	private Button button_1;
	/**
	 * Create the wizard.
	 */
	public ExportPackWizardPage(PackInfoModel pack) {
		super("wizardPage");
		setTitle("导出一个更新包");
		setDescription("您即将拥有一个制作好的更新包");
		this.pack = pack;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.CENTER);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		lblNewLabel.setText(this.pack.getName());
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u4FDD\u5B58\u8DEF\u5F84:");
		
		text = new Text(container, SWT.BORDER);
		String path = PackConfigInfo.getInstance().getDefaultExportPath();
		text.setText(path);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(container, SWT.FLAT);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg=new DirectoryDialog(getShell(),SWT.OPEN|SWT.CANCEL);
				text.setText(dlg.open());
			}
		});
		button.setText("\u6D4F\u89C8");
		
		button_1 = new Button(container, SWT.CHECK);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					label_1.setEnabled(button_1.getSelection());
					text_1.setEnabled(button_1.getSelection());
			}
		});
		button_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		button_1.setText("\u81EA\u5B9A\u4E49\u6587\u4EF6\u540D");
		new Label(container, SWT.NONE);

		label_1 = new Label(container, SWT.NONE);
		label_1.setEnabled(false);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u6587\u4EF6\u540D:");
		
		text_1 = new Text(container, SWT.BORDER);
		text_1.setText(pack.getName()+UpdateInfo.PackExtension);
		text_1.setEnabled(false);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.minimumWidth = 200;
		text_1.setLayoutData(gd_text_1);
		new Label(container, SWT.NONE);
	}
}
