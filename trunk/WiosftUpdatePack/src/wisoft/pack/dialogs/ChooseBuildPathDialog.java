package wisoft.pack.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import wisoft.pack.models.FileTreeContentProvider;
import wisoft.pack.models.FileTreeLabelProvider;
import wisoft.pack.utils.PackConfigInfo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ChooseBuildPathDialog extends Dialog {

	
	CheckboxTreeViewer checkboxTreeViewer;
	public String buildpath;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ChooseBuildPathDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("构建服务选择");
	}
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
		checkboxTreeViewer.setAutoExpandLevel(2);
		checkboxTreeViewer.setContentProvider(new FileTreeContentProvider());
		checkboxTreeViewer.setLabelProvider(new FileTreeLabelProvider());
		checkboxTreeViewer.setInput(PackConfigInfo.getInstance().getBuildServerPath()); 
		return container;
	}

	@Override
	protected void okPressed() {
		final MessageBox mb =  new MessageBox(this.getParentShell(),SWT.OK|SWT.ICON_WARNING);
		if(checkboxTreeViewer.getCheckedElements().length!=1)
		{
			mb.setMessage("请选择一个构建路径!");
			mb.setText("警告");
			mb.open();
		}
		else
		{
			File build = (File)(checkboxTreeViewer.getCheckedElements())[0];
			buildpath = build.getPath();
			super.okPressed();
		}
		
	};
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(421, 425);
	}

}
