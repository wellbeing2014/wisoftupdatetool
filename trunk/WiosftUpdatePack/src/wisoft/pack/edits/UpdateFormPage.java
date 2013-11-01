package wisoft.pack.edits;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import wisoft.pack.app.Activator;
import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.utils.PackConfigInfo;

public class UpdateFormPage extends FormPage {
	private Table table;
	PackConfig_Server[] servers ;
	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public UpdateFormPage(String id, String title) {
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
	public UpdateFormPage(FormEditor editor, String id, String title) {
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
		form.setText("Empty FormPage");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(1, false));
		
		ToolBar toolBar = new ToolBar(managedForm.getForm().getBody(), SWT.FLAT | SWT.RIGHT);
		managedForm.getToolkit().adapt(toolBar);
		managedForm.getToolkit().paintBordersFor(toolBar);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setText("\u6267\u884C");
		
		table = new Table(managedForm.getForm().getBody(), SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().adapt(table);
		managedForm.getToolkit().paintBordersFor(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(140);
		tableColumn.setText("\u670D\u52A1\u540D\u79F0");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setText("\u68C0\u67E5\u5B8C\u6574\u6027");
		tableColumn_4.setWidth(80);
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(107);
		tableColumn_2.setText("\u6587\u4EF6\u66F4\u65B0\u8FDB\u5EA6");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(66);
		tableColumn_3.setText("\u624B\u52A8\u914D\u7F6E");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(61);
		tblclmnNewColumn.setText("SQL\u6267\u884C");
		
		
		BindingServer();
	}
	
	private void BindingServer()
	{
		servers = PackConfigInfo.getInstance().getUnPackProInfos();
		this.table.clearAll();
		for(PackConfig_Server server:servers)
		{
			TableItem item = new TableItem(table, SWT.CENTER);
			item.setText(0,server.getServerName());
			item.setText(1,"¸üÐÂÖÐ");
			item.setImage(1,Activator.getImageDescriptor("/icons/loading.gif").createImage());
			TableEditor editor = new TableEditor(table);
			editor.grabHorizontal = editor.grabVertical = true;
			ProgressBar bar = new ProgressBar(table, SWT.NONE);
			bar.setMaximum(100);
			editor.setEditor(bar, item, 2);
	        item.setData(server);
		}
		
	}
}
