package wisoft.pack.dialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class UpdateServerDialog_EditConf extends Dialog {

	public String UpdateNote;
	public File ConFile;
	StyledText styledText_1;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UpdateServerDialog_EditConf(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MAX);
	}
	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		if(saveConf())
			super.okPressed();
		else
			MessageDialog.openError(getShell(), "提示", "保存出现问题");
	}
	
	private boolean saveConf()
	{
		try { 
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(ConFile.getAbsolutePath()),"UTF-8");
			out.write(styledText_1.getText()); 
			out.close(); 
			
			return true; 
			} catch (IOException e) { 
			return false;
			} 
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("手动修改配置文件");
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label label = new Label(composite, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.right = new FormAttachment(100);
		fd_label.top = new FormAttachment(0, 3);
		fd_label.left = new FormAttachment(0, 3);
		label.setLayoutData(fd_label);
		label.setText("\u4FEE\u6539\u8BF4\u660E");
		
		StyledText styledText = new StyledText(composite, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		styledText.setEditable(false);
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100);
		fd_styledText.right = new FormAttachment(100);
		fd_styledText.top = new FormAttachment(label, 6);
		fd_styledText.left = new FormAttachment(label, 0, SWT.LEFT);
		styledText.setLayoutData(fd_styledText);
		styledText.setText(UpdateNote);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		styledText_1 = new StyledText(composite_1, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		FormData fd_styledText_1 = new FormData();
		fd_styledText_1.bottom = new FormAttachment(100);
		fd_styledText_1.right = new FormAttachment(100);
		styledText_1.setLayoutData(fd_styledText_1);
		
		try { 
		InputStreamReader isr = new InputStreamReader(new FileInputStream(ConFile), "UTF-8");  
		BufferedReader reader = new BufferedReader(isr); 
		StringBuffer sb = new StringBuffer(); 
		String line = null; 
		while ((line = reader.readLine()) != null) { 
		sb.append(line); 
		sb.append("\r\n"); 
		} 
		styledText_1.setText(sb.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		Label label_1 = new Label(composite_1, SWT.NONE);
		fd_styledText_1.top = new FormAttachment(label_1, 6);
		fd_styledText_1.left = new FormAttachment(label_1, 0, SWT.LEFT);
		label_1.setText("\u670D\u52A1\u5668\u6587\u4EF6:");
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(100);
		fd_label_1.top = new FormAttachment(0, 3);
		fd_label_1.left = new FormAttachment(0, 3);
		label_1.setLayoutData(fd_label_1);
		sashForm.setWeights(new int[] {1, 2});

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		button.setText("\u4FDD\u5B58");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("\u53D6\u6D88");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(627, 482);
	}
}
