package wisoft.pack.edits;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Tree;

public class OverViewEditView extends RootEdit {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OverViewEditView(Composite parent, int style) {
		super(parent, style);
		super.setTitleName("浏览基本信息");
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(100);
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0, 32);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
		Group group = new Group(composite, SWT.NONE);
		group.setText("\u9879\u76EE\u4FE1\u606F");
		group.setLayout(new GridLayout(3, false));
		FormData fd_group = new FormData();
		fd_group.top = new FormAttachment(0);
		fd_group.left = new FormAttachment(0, 10);
		fd_group.bottom = new FormAttachment(0, 143);
		fd_group.right = new FormAttachment(70, -3);
		group.setLayoutData(fd_group);
		
		Label label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6A21\u5757\u540D\u79F0\uFF1A");
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(group, SWT.NONE);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u6A21\u5757\u4EE3\u7801\uFF1A");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(group, SWT.NONE);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("\u6A21\u5757\u7248\u672C\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(group, SWT.NONE);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(group, SWT.NONE);
		button.setText("\u6D4F\u89C8");
		
		Group group_1 = new Group(composite, SWT.NONE);
		group_1.setText("\u6587\u4EF6\u6D4F\u89C8");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_group_1 = new FormData();
		fd_group_1.right = new FormAttachment(100, -6);
		fd_group_1.bottom = new FormAttachment(100, -6);
		fd_group_1.top = new FormAttachment(group, 6);
		fd_group_1.left = new FormAttachment(group, 0, SWT.LEFT);
		group_1.setLayoutData(fd_group_1);
		
		Tree tree = new Tree(group_1, SWT.BORDER);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
