package wisoft.pack.views;

import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.actions.DelPackInfoAction;
import wisoft.pack.actions.OpenNewPackDialogAction;
import wisoft.pack.actions.SavePackEditAction;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Tree;

public class RootEdit extends Composite {
	private Text text;
	private Text text_1;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RootEdit(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ViewForm viewForm = new ViewForm(this, SWT.NONE);
		
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT | SWT.RIGHT);
		toolBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		viewForm.setTopRight(toolBar);
		ToolBarManager tbm = new ToolBarManager(toolBar);
		tbm.add(new SavePackEditAction());
		tbm.update(true);
		
		Label lblNewLabel = new Label(viewForm, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		viewForm.setTopLeft(lblNewLabel);
		lblNewLabel.setText("\u57FA\u672C\u4FE1\u606F");
		
		Composite composite = new Composite(viewForm, SWT.NONE);
		viewForm.setContent(composite);
		composite.setLayout(new FormLayout());
		
		Group group = new Group(composite, SWT.NONE);
		FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(0, 138);
		fd_group.right = new FormAttachment(50);
		fd_group.top = new FormAttachment(0);
		fd_group.left = new FormAttachment(0);
		group.setLayoutData(fd_group);
		group.setText("\u66F4\u65B0\u5305");
		group.setLayout(new GridLayout(2, false));
		
		Label label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u66F4\u65B0\u5305\u540D\u79F0\uFF1A");
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(group, SWT.RIGHT);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u6A21\u5757\u4EE3\u7801\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(group, SWT.RIGHT);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u7248\u672C\u53F7\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group group_1 = new Group(composite, SWT.NONE);
		FormData fd_group_1 = new FormData();
		fd_group_1.top = new FormAttachment(group, 0, SWT.DEFAULT);
		fd_group_1.bottom = new FormAttachment(100);
		fd_group_1.right = new FormAttachment(100);
		fd_group_1.left = new FormAttachment(0);
		group_1.setLayoutData(fd_group_1);
		group_1.setText("\u6587\u4EF6\u4FE1\u606F");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(group_1, SWT.BORDER);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
