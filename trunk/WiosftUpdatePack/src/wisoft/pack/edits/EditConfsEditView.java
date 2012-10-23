package wisoft.pack.edits;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class EditConfsEditView extends RootEdit {
	private Text text;
	private Text text_1;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	
	public EditConfsEditView(Composite parent, int style) {
		super(parent, style);
		super.setTitleName("选择打包文件");
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(100);
		fd_composite.bottom = new FormAttachment(100);
		fd_composite.top = new FormAttachment(0, 32);
		fd_composite.left = new FormAttachment(0);
		composite.setLayoutData(fd_composite);
		
		ViewForm viewForm = new ViewForm(composite, SWT.NONE);
		
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT | SWT.RIGHT);
		viewForm.setTopLeft(toolBar);
		
		ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
		toolItem.setText("\u65B0\u589E");
		
		ToolItem toolItem_1 = new ToolItem(toolBar, SWT.NONE);
		toolItem_1.setText("\u5220\u9664");
		
		toolBar.update();
		
		Composite composite_1 = new Composite(viewForm, SWT.NONE);
		viewForm.setContent(composite_1);
		composite_1.setLayout(new FormLayout());
		
		Tree tree = new Tree(composite_1, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.top = new FormAttachment(0, 2);
		fd_tree.bottom = new FormAttachment(100, -2);
		fd_tree.right = new FormAttachment(70);
		fd_tree.left = new FormAttachment(0, 2);
		tree.setLayoutData(fd_tree);
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new GridLayout(2, false));
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(100, -2);
		fd_composite_2.right = new FormAttachment(100, -2);
		fd_composite_2.top = new FormAttachment(0, 2);
		fd_composite_2.left = new FormAttachment(70, 4);
		composite_2.setLayoutData(fd_composite_2);
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("\u6587\u4EF6\u540D\uFF1A");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("\u4FEE\u6539\u65F6\u95F4\uFF1A");
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("\u521B\u5EFA\u65F6\u95F4\uFF1A");
		
		text_3 = new Text(composite_2, SWT.BORDER);
		text_3.setEditable(false);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setText("\u6587\u4EF6\u6765\u6E90\uFF1A");
		new Label(composite_2, SWT.NONE);
		
		text_4 = new Text(composite_2, SWT.BORDER);
		text_4.setEditable(false);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("\u66F4\u65B0\u8DEF\u5F84\uFF1A");
		new Label(composite_2, SWT.NONE);
		
		text_5 = new Text(composite_2, SWT.BORDER);
		text_5.setEditable(false);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
