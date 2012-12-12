package wisoft.pack.edits;

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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
import org.eclipse.wb.swt.ResourceManager;

import com.sun.xml.internal.ws.util.xml.CDATA;
import com.wisoft.wims.WimsSingleIssueTracking;

import wisoft.pack.app.Activator;
import wisoft.pack.dialogs.PackRelyDialog;
import wisoft.pack.dialogs.TrackingListSelDialog;
import wisoft.pack.models.PackRelyModel;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;

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
	//private Button btnNewButton;
	private ToolItem tltmNewItem;
	
	private TableViewer viewer;
	private Table table;
	
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
		form.setText("¸üÐÂ°ü¸ÅÀÀ");
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
		td.heightHint=TableWrapData.FILL_GRAB;
		section_1.setLayoutData(td);
		Section section_2 =CreateScopeSection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		//td.rowspan = 2;
		td.heightHint=TableWrapData.FILL_GRAB;
		section_2.setLayoutData(td);
		
		Section section_3 =CreateRelySection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.heightHint=TableWrapData.FILL_GRAB;
		section_3.setLayoutData(td);
		
		
		Section section_4 = CreateTrackingSection(managedForm);
		td = new TableWrapData(TableWrapData.FILL_GRAB);
		td.heightHint=TableWrapData.FILL_GRAB;
		section_4.setLayoutData(td);
		//CreateBaseInfoSection(managedForm);
		
		//section.setClient(composite);
		loadXmlData();
	}
	
	private Section CreateRelySection(final IManagedForm managedForm)
	{
		Section section = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u66F4\u65B0\u4F9D\u8D56");
		section.setExpanded(true);
		
		Composite client = managedForm.getToolkit().createComposite(section, SWT.WRAP);
		section.setClient(client);
		managedForm.getToolkit().paintBordersFor(client);
		client.setLayout(new FormLayout());
		
		Table t = managedForm.getToolkit().createTable(client, SWT.NULL);
		FormData fd_t = new FormData();
		fd_t.right = new FormAttachment(100);
		fd_t.left = new FormAttachment(0);
		fd_t.bottom = new FormAttachment(100);
		fd_t.top = new FormAttachment(0, 2);
		t.setLayoutData(fd_t);
		
		
		final SectionPart spart = new SectionPart(section);
		Section section_1 = spart.getSection();
		managedForm.addPart(spart);
		
		ToolBar toolBar = new ToolBar(section_1, SWT.FLAT | SWT.RIGHT);
		managedForm.getToolkit().adapt(toolBar);
		managedForm.getToolkit().paintBordersFor(toolBar);
		section_1.setTextClient(toolBar);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setToolTipText("\u589E\u52A0\u66F4\u65B0\u5305\u4F9D\u8D56");
		toolItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PackRelyDialog pd = new PackRelyDialog(getPartControl().getShell());
				int i=pd.open();
				if(i==IDialogConstants.OK_ID)
				{
					Element relys =xmlo.OnlyElementInRoot(UpdateInfo.PackRelys);
					PackRelyModel prm = new PackRelyModel();
					prm.setName(pd.name);
					prm.setCode(pd.code);
					prm.setVersion(pd.version);
					Element rely =xmlo.addElementInElement(relys, UpdateInfo.PackRely,UpdateInfo.PackRely_attr_name,pd.name);
					rely.addAttribute(UpdateInfo.PackRely_attr_code, pd.code);
					rely.addAttribute(UpdateInfo.PackRely_attr_ver, pd.version);
					xmlo.save();
					viewer.refresh();
				}
			}
			});
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setToolTipText("\u5220\u9664\u4F9D\u8D56");
		toolItem_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/del.png"));
		toolItem_1.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
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
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		txtNewText = managedForm.getToolkit().createText(composite, "New Text", SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		
//		btnNewButton = managedForm.getToolkit().createButton(composite, "\u5E94\u7528", SWT.NONE);
//		btnNewButton.setEnabled(false);
//		btnNewButton.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				 Element relys =xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
//				 relys.clearContent(); 
//				 relys.addCDATA(txtNewText.getText());
//				 xmlo.save();
//				 btnNewButton.setEnabled(false);
//				 //isRNedit = false;
//			}
//		});
		//fd_txtNewText.bottom = new FormAttachment(btnNewButton);
//		FormData fd_btnNewButton = new FormData();
//		fd_btnNewButton.bottom = new FormAttachment(100);
//		fd_btnNewButton.right = new FormAttachment(100);
//		btnNewButton.setLayoutData(fd_btnNewButton);
		
		ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.RIGHT);
		managedForm.getToolkit().adapt(toolBar);
		managedForm.getToolkit().paintBordersFor(toolBar);
		section.setTextClient(toolBar);
		
		tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/save.gif"));
		tltmNewItem.setEnabled(false);
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Element relys =xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
				 relys.clearContent(); 
				 relys.addCDATA(txtNewText.getText());
				 xmlo.save();
				 tltmNewItem.setEnabled(false);
			}
		});
		tltmNewItem.setToolTipText("\u4FDD\u5B58");
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
	
	private Section CreateTrackingSection(final IManagedForm managedForm)
	{
		Section section = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.EXPANDED | Section.TWISTIE | Section.TITLE_BAR);
		managedForm.getToolkit().paintBordersFor(section);
		section.setText("\u4FEE\u8BA2\u7684\u95EE\u9898\u5355");
		
		Composite composite = new Composite(section, SWT.NONE);
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		section.setClient(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		managedForm.getToolkit().adapt(table);
		managedForm.getToolkit().paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(SWT.FILL);
		tableColumn.setText("\u95EE\u9898\u5355\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(SWT.FILL);
		tableColumn_1.setText("\u63CF\u8FF0");
		
		ToolBar toolBar = new ToolBar(section, SWT.FLAT | SWT.RIGHT);
		managedForm.getToolkit().adapt(toolBar);
		managedForm.getToolkit().paintBordersFor(toolBar);
		section.setTextClient(toolBar);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TrackingListSelDialog td = new TrackingListSelDialog(getPartControl().getShell());
				int i=td.open();
				if(i==IDialogConstants.OK_ID)
				{
					for(WimsSingleIssueTracking track :td.wimstracklist)
					{
						Element trackrelys =xmlo.OnlyElementInRoot(UpdateInfo.TackRelys);
						Element rely =xmlo.addElementInElement(trackrelys, UpdateInfo.TackRely,UpdateInfo.TackRely_attr_id,track.getId());
						rely.addAttribute(UpdateInfo.TackRely_attr_personname,track.getSqpersonid());
						rely.addAttribute(UpdateInfo.TackRely_attr_proname,track.getProinfoId());
						if(rely.element(UpdateInfo.TackRely_elem_content)!=null)
							rely.remove(rely.element(UpdateInfo.TackRely_elem_content));
						Element rely_content = rely.addElement(UpdateInfo.TackRely_elem_content);
						rely_content.addCDATA(track.getContent());
					}
					xmlo.save();
					
				}
			}
		});
		toolItem.setToolTipText("\u589E\u52A0\u4FEE\u8BA2\u95EE\u9898\u5355");
		toolItem.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/add.gif"));
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setToolTipText("\u5220\u9664\u4FEE\u8BA2\u95EE\u9898\u5355");
		toolItem_1.setImage(ResourceManager.getPluginImage("WiosftUpdatePack", "icons/del.png"));
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
					tltmNewItem.setEnabled(true);
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
