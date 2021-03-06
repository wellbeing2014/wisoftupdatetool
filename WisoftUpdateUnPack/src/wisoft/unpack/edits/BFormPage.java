package wisoft.unpack.edits;

import java.util.List;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import wisoft.unpack.app.Activator;
import wisoft.unpack.models.PackRelyModel;
import wisoft.unpack.utils.UpdateInfo;
import wisoft.unpack.utils.XmlOperator;

public class BFormPage extends FormPage {
	
	private XmlOperator xmlo;
	
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Button button;
	private Button button_1;
	private Button button_2;
	
	
	private Text txtNewText;
	private Button btnNewButton;
	
	private TableViewer viewer;
	
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
		//PackInfoInput pack = (PackInfoInput)getEditorInput()).getPackinfo();
		xmlo = ((PackInfoInput)editor.getEditorInput()).getPackinfo().getXmlo();
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("���°�����");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		TableWrapLayout layout = new TableWrapLayout();
		TableWrapData td = new TableWrapData();
//		ColumnLayout layout = new ColumnLayout();
//		layout.topMargin = 0;
//		layout.bottomMargin = 5;
//		layout.leftMargin = 10;
//		layout.rightMargin = 10;
//		layout.horizontalSpacing = 10;
//		layout.verticalSpacing = 10;
//		layout.maxNumColumns = 2;
//		layout.minNumColumns = 1;
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		Section section_1 =CreateBaseInfoSection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.heightHint=250;
		section_1.setLayoutData(td);
		Section section_2 =CreateScopeSection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.rowspan = 2;
		td.heightHint=400;
		section_2.setLayoutData(td);
		
		Section section_3 =CreateRelySection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.heightHint=150;
		section_3.setLayoutData(td);
		
		//CreateBaseInfoSection(managedForm);
		
		//section.setClient(composite);
		loadXmlData();
	}
	
	private Section CreateRelySection(final IManagedForm managedForm)
	{
		Section section = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
//		ColumnLayoutData cld_section = new ColumnLayoutData();
//		cld_section.heightHint = 150;
//		//cld_section.heightHint = 237;
//		//cld_section.widthHint = 217;
//		cld_section.widthHint = ColumnLayoutData.FILL;
//		cld_section.horizontalAlignment=ColumnLayoutData.FILL;
//		section.setLayoutData(cld_section);
		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u66F4\u65B0\u4F9D\u8D56");
		section.setExpanded(true);
		
		Composite client = managedForm.getToolkit().createComposite(section, SWT.WRAP);
		section.setClient(client);
		managedForm.getToolkit().paintBordersFor(client);
		client.setLayout(new FormLayout());
		
		Composite composite = new Composite(client, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(100);
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(100, -50);
		composite.setLayoutData(fd_composite);
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		
		Table t = managedForm.getToolkit().createTable(client, SWT.NULL);
		FormData fd_t = new FormData();
		fd_t.bottom = new FormAttachment(100);
		fd_t.right = new FormAttachment(composite);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		managedForm.getToolkit().adapt(button, true, true);
		button.setText("\u65B0\u589E");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
				 List<?> selectionlist = selection.toList();
				 Element relys =xmlo.OnlyElementInRoot(UpdateInfo.PackRelys);
				 for(int i=0;i<selectionlist.size();i++)
				 {
					 if(selectionlist.get(i) instanceof PackRelyModel)
					 {
						 PackRelyModel pm =(PackRelyModel) selectionlist.get(i);
						 xmlo.RemoveElementInElement(relys, UpdateInfo.PackRely, UpdateInfo.PackRely_attr_name, pm.getName());
					 }
				 }
				 xmlo.save();
				 viewer.refresh();
			}
		});
		managedForm.getToolkit().adapt(button_1, true, true);
		button_1.setText("\u5220\u9664");
		fd_t.top = new FormAttachment(0, 2);
		fd_t.left = new FormAttachment(0);
		t.setLayoutData(fd_t);
		
		
		final SectionPart spart = new SectionPart(section);
		managedForm.addPart(spart);
		viewer = new TableViewer(t);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				managedForm.fireSelectionChanged(spart, event.getSelection());
				//viewer.refresh();
			}
		});
		viewer.setContentProvider(new PackRelyContentProvider());
		viewer.setLabelProvider(new PackRelyLabelProvider());
		viewer.setInput(getEditor().getEditorInput());
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				managedForm.getForm().reflow(false);
				//System.out.println("adfasdfasdfasdfas dian kai ");
			}
		});
		return section;
	}
	
	private Section CreateScopeSection( IManagedForm managedForm)
	{
		final ScrolledForm form = managedForm.getForm();
		Section section = managedForm.getToolkit().createSection(form.getBody(), Section.EXPANDED|Section.TWISTIE | Section.TITLE_BAR);

		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u66F4\u65B0\u8303\u56F4\u8BF4\u660E");
		
		Composite composite = new Composite(section, SWT.NONE);
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		section.setClient(composite);
		composite.setLayout(new FormLayout());
		
		txtNewText = managedForm.getToolkit().createText(composite, "New Text", SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
	
		FormData fd_txtNewText = new FormData();
		fd_txtNewText.right = new FormAttachment(100);
		fd_txtNewText.top = new FormAttachment(0);
		fd_txtNewText.left = new FormAttachment(0);
		txtNewText.setLayoutData(fd_txtNewText);
		
		btnNewButton = managedForm.getToolkit().createButton(composite, "\u5E94\u7528", SWT.NONE);
		btnNewButton.setEnabled(false);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 Element relys =xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
				 relys.clearContent(); 
				 relys.addCDATA(txtNewText.getText());
				 xmlo.save();
				 btnNewButton.setEnabled(false);
				 //isRNedit = false;
			}
		});
		fd_txtNewText.bottom = new FormAttachment(btnNewButton);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.bottom = new FormAttachment(100);
		fd_btnNewButton.right = new FormAttachment(100);
		btnNewButton.setLayoutData(fd_btnNewButton);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		
		return section;
	}
	private Section CreateBaseInfoSection(final IManagedForm managedForm)
	{
		final ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		Section section = managedForm.getToolkit().createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR|Section.EXPANDED);
		//section.setBounds(10, 10, 296, 237);
		toolkit.paintBordersFor(section);
		section.setText("\u57FA\u672C\u4FE1\u606F");
		//section.setExpanded(true);
//		ColumnLayoutData cld_section = new ColumnLayoutData();
//		cld_section.heightHint = 245;
//		//cld_section.heightHint = 232;
//		//cld_section.widthHint = 217;
//		cld_section.widthHint = ColumnLayoutData.FILL;
//		cld_section.horizontalAlignment=ColumnLayoutData.FILL;
//		section.setLayoutData(cld_section);
		
		Composite composite = managedForm.getToolkit().createComposite(section, SWT.NONE);
		toolkit.paintBordersFor(composite);
		section.setClient(composite);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("\u6A21\u5757\u540D\u79F0\uFF1A");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(text, true, true);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("\u6A21\u5757\u4EE3\u7801\uFF1A");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(text_2, true, true);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(label, true, true);
		label.setText("\u7248\u672C\u53F7\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(text_1, true, true);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(label_1, true, true);
		label_1.setText("\u521B\u5EFA\u4EBA\uFF1A");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(text_3, true, true);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		toolkit.adapt(label_2, true, true);
		label_2.setText("\u521B\u5EFA\u65F6\u95F4\uFF1A");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(text_4, true, true);
		
		Label label_3 = new Label(composite, SWT.NONE);
		toolkit.adapt(label_3, true, true);
		label_3.setText("\u66F4\u65B0\u8303\u56F4\uFF1A");
		
		button = new Button(composite, SWT.CHECK);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Element scope =xmlo.getRootElement().element(UpdateInfo.Scope);
				if(button.getSelection())
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_Front).setText("true");
				else
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_Front).setText("false");
				save();
			}
		});
		toolkit.adapt(button, true, true);
		button.setText("\u524D\u53F0");
		new Label(composite, SWT.NONE);
		
		button_1 = new Button(composite, SWT.CHECK);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Element scope =xmlo.getRootElement().element(UpdateInfo.Scope);
				if(button_1.getSelection())
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_Back).setText("true");
				else
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_Back).setText("false");
				save();
			}
		});
		toolkit.adapt(button_1, true, true);
		button_1.setText("\u540E\u53F0");
		new Label(composite, SWT.NONE);
		
		button_2 = new Button(composite, SWT.CHECK);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Element scope =xmlo.getRootElement().element(UpdateInfo.Scope);
				if(button_2.getSelection())
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_DB).setText("true");
				else
					xmlo.OnlyElementInElemnt(scope,UpdateInfo.Scope_DB).setText("false");
				save();
			}
		});
		toolkit.adapt(button_2, true, true);
		button_2.setText("\u6570\u636E\u5E93");
		
		
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});
		return section;
	}
	
	public synchronized void save()
	{
		xmlo.save();
	}
	
	public void loadXmlData()
	{
		this.text.setText( xmlo.getRootElement().elementText(UpdateInfo.ModuleName));
		this.text_1.setText( xmlo.getRootElement().elementText(UpdateInfo.ModuleCode));
		this.text_2.setText( xmlo.getRootElement().elementText(UpdateInfo.Version));
		this.text_3.setText( xmlo.getRootElement().elementText(UpdateInfo.CreateMan));
		this.text_4.setText(xmlo.getRootElement().elementText(UpdateInfo.CreateTime));
		Element scope = xmlo.getRootElement().element(UpdateInfo.Scope);
		this.button.setSelection(Boolean.parseBoolean(scope.elementText(UpdateInfo.Scope_Front)));
		this.button_1.setSelection(Boolean.parseBoolean(scope.elementText(UpdateInfo.Scope_Back)));
		this.button_2.setSelection(Boolean.parseBoolean(scope.elementText(UpdateInfo.Scope_DB)));
		this.txtNewText.setText(xmlo.getRootElement().elementText(UpdateInfo.ReleaseNote));
		this.txtNewText.addModifyListener(new ModifyListener() {
				
				@Override
				public void modifyText(ModifyEvent e) {
					btnNewButton.setEnabled(true);
				}
			});
	}
	class PackRelyContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof PackInfoInput) {
				PackInfoInput input = (PackInfoInput)getEditor().getEditorInput();
				return input.getPackRelyData();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	class PackRelyLabelProvider extends LabelProvider
			implements
				ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return obj.toString();
		}
		public Image getColumnImage(Object obj, int index) {
			return new Image(null, Activator.getImageDescriptor("icons/wi_updatetool.ico").getImageData());
		}
	}
}
