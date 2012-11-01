package wisoft.pack.edits;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.forms.widgets.ColumnLayoutData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.RowLayout;

import wisoft.pack.models.PackInfoModel;

public class BFormPage extends FormPage {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public BFormPage(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public BFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("¸üÐÂ°ü¸ÅÀÀ");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		ColumnLayout layout = new ColumnLayout();
		layout.topMargin = 0;
		layout.bottomMargin = 5;
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		layout.horizontalSpacing = 10;
		layout.verticalSpacing = 10;
		layout.maxNumColumns = 4;
		layout.minNumColumns = 1;
		form.getBody().setLayout(layout);
		
		CreateBaseInfoSection(managedForm);
		
		//CreateBaseInfoSection(managedForm);
		
		//section.setClient(composite);
		CreateScopeSection(managedForm);
	}
	private void CreateScopeSection(IManagedForm managedForm)
	{
		Section section = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
		ColumnLayoutData cld_section = new ColumnLayoutData();
		//cld_section.heightHint = 237;
		//cld_section.widthHint = 217;
		cld_section.horizontalAlignment=ColumnLayoutData.FILL;
		section.setLayoutData(cld_section);
		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u66F4\u65B0\u8303\u56F4\u8BF4\u660E");
		section.setExpanded(true);
		
		Composite composite = new Composite(section, SWT.NONE);
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		section.setClient(composite);
		composite.setLayout(new FormLayout());
		
		List list = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		FormData fd_list = new FormData();
		fd_list.top = new FormAttachment(0);
		fd_list.left = new FormAttachment(0);
		fd_list.bottom = new FormAttachment(0, 215);
		list.setLayoutData(fd_list);
		managedForm.getToolkit().adapt(list, true, true);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		fd_list.right = new FormAttachment(composite_1);
		composite_1.setLayout(new RowLayout(SWT.HORIZONTAL));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.left = new FormAttachment(100, -50);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.right = new FormAttachment(100);
		fd_composite_1.bottom = new FormAttachment(100);
		composite_1.setLayoutData(fd_composite_1);
		managedForm.getToolkit().adapt(composite_1);
		managedForm.getToolkit().paintBordersFor(composite_1);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setText("\u589E\u52A0");
		managedForm.getToolkit().adapt(button, true, true);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		managedForm.getToolkit().adapt(btnNewButton, true, true);
		btnNewButton.setText("\u5220\u9664");
	}
	private void CreateBaseInfoSection(IManagedForm managedForm)
	{
		Section section = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
		//section.setBounds(10, 10, 296, 237);
		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u57FA\u672C\u4FE1\u606F");
		section.setExpanded(true);
		ColumnLayoutData cld_section = new ColumnLayoutData();
		//cld_section.heightHint = 232;
		//cld_section.widthHint = 217;
		cld_section.horizontalAlignment=ColumnLayoutData.FILL;
		section.setLayoutData(cld_section);
		
		Composite composite = managedForm.getToolkit().createComposite(section, SWT.NONE);
		managedForm.getToolkit().paintBordersFor(composite);
		section.setClient(composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(lblNewLabel, true, true);
		lblNewLabel.setText("\u6A21\u5757\u540D\u79F0\uFF1A");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text, true, true);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("\u6A21\u5757\u4EE3\u7801\uFF1A");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_2, true, true);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(label, true, true);
		label.setText("\u7248\u672C\u53F7\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_1, true, true);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(label_1, true, true);
		label_1.setText("\u521B\u5EFA\u4EBA\uFF1A");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_3, true, true);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		managedForm.getToolkit().adapt(label_2, true, true);
		label_2.setText("\u521B\u5EFA\u65F6\u95F4\uFF1A");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		managedForm.getToolkit().adapt(text_4, true, true);
		
		Label label_3 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(label_3, true, true);
		label_3.setText("\u66F4\u65B0\u8303\u56F4\uFF1A");
		
		Button button = new Button(composite, SWT.CHECK);
		managedForm.getToolkit().adapt(button, true, true);
		button.setText("\u524D\u53F0");
		new Label(composite, SWT.NONE);
		
		Button button_1 = new Button(composite, SWT.CHECK);
		managedForm.getToolkit().adapt(button_1, true, true);
		button_1.setText("\u540E\u53F0");
		new Label(composite, SWT.NONE);
		
		Button button_2 = new Button(composite, SWT.CHECK);
		managedForm.getToolkit().adapt(button_2, true, true);
		button_2.setText("\u6570\u636E\u5E93");
		
		PackInfoModel pack =((PackInfoInput)getEditorInput()).getPackinfo();
		this.text.setText( pack.getModuleName());
		this.text_1.setText( pack.getModuleCode());
		this.text_2.setText( pack.getVersion());
		this.text_3.setText( pack.getCreateMan());
//		this.text.setText( pack.getModuleName());
//		this.text.setText( pack.getModuleName());
	}
}
