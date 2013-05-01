package wisoft.pack.dialogs;

import java.util.Arrays;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.utils.PackConfigInfo;
import org.eclipse.swt.widgets.Text;

public class UpdateServerDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateServerDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MAX | SWT.RESIZE);
		shell.setSize(454, 485);
		shell.setText("\u66F4\u65B0\u5DE5\u4F5C\u53F0");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(4, false));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 90);
		fd_composite_1.right = new FormAttachment(100);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.left = new FormAttachment(0);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.top = new FormAttachment(composite_1);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label_2, true, true);
		label_2.setText("\u66F4\u65B0\u5305\u540D\u79F0\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		formToolkit.adapt(text, true, true);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		formToolkit.adapt(label, true, true);
		label.setText("\u9009\u62E9\u670D\u52A1\u5668\uFF1A");
		
		
		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.NONE);
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				// TODO Auto-generated method stub
				assert element instanceof PackConfig_Server;  
				PackConfig_Server server = (PackConfig_Server)element;  
			    return server.getServerName();  
			}
		});
		comboViewer.setInput(PackConfigInfo.getInstance().getUnPackProInfos());
		Combo combo = comboViewer.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		formToolkit.paintBordersFor(combo);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		formToolkit.adapt(label_1, true, true);
		label_1.setText("\u66F4\u65B0\u8FDB\u5EA6\uFF1A");

		
		ProgressBar progressBar = new ProgressBar(composite_1, SWT.NONE);
		GridData gd_progressBar = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_progressBar.widthHint = 239;
		progressBar.setLayoutData(gd_progressBar);
		Button button = formToolkit.createButton(composite_1, "\u5F00\u59CB/\u6682\u505C", SWT.NONE);
		
		Button button_1 = formToolkit.createButton(composite_1, "\u53D6\u6D88", SWT.NONE);
		fd_composite_2.bottom = new FormAttachment(100);
		fd_composite_2.right = new FormAttachment(100);
		fd_composite_2.left = new FormAttachment(0);
		composite_2.setLayoutData(fd_composite_2);
		
		

	}
}
