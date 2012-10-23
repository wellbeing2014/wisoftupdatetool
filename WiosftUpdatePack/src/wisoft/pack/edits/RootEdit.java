package wisoft.pack.edits;

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
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

public class RootEdit extends Composite {
	private Text text;
	private Text text_1;
	private Label label;
	protected String titleName;

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
		this.label.setText(titleName);
	}

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RootEdit(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		Composite comp = new Composite(this, SWT.NONE);
		comp.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		comp.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_comp = new FormData();
		fd_comp.top = new FormAttachment(0, 1);
		fd_comp.bottom = new FormAttachment(0, 32);
		fd_comp.left = new FormAttachment(0);
		fd_comp.right = new FormAttachment(100);
		comp.setLayoutData(fd_comp);
		
		label = new Label(comp, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 13, SWT.BOLD | SWT.ITALIC));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setText("\u57FA\u672C\u4FE1\u606F");
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0, 3);
		fd_toolBar.left = new FormAttachment(80);
		
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
