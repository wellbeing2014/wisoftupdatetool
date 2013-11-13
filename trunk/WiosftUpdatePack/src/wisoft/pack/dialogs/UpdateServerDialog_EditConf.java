package wisoft.pack.dialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import wisoft.pack.app.Activator;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackConfig_Server;
import wisoft.pack.utils.XmlOperator;

public class UpdateServerDialog_EditConf extends Dialog {

	private java.util.Map<FileModel,Boolean> confiles ;
	private FileModel ConFile;
	private PackConfig_Server server;
	
	
	public PackConfig_Server getServer() {
		return server;
	}

	public void setServer(PackConfig_Server server) {
		this.server = server;
	}

	StyledText styledText_1;
	/**
	 * Create the dialog.
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public UpdateServerDialog_EditConf(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MAX);
	}
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UpdateServerDialog_EditConf(Shell parentShell,java.util.List<FileModel> conf) {
		super(parentShell);
		setShellStyle(SWT.MAX);
		this.confiles = new HashMap<FileModel, Boolean>();
		for(FileModel file:conf)
		{
			confiles.put(file, false);
		}
	}
	@Override
	protected void okPressed() {
		
	}
	
	private boolean saveConf()
	{
		try { 
			File conffile = new File(server.getWebappPath()+ConFile.getFullPath());
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(conffile.getAbsolutePath()),"UTF-8");
			out.write(styledText_1.getText()); 
			out.close(); 
			return true; 
		} catch (IOException e) { 
			MessageDialog.openError(getShell(), "保存出错", "保存出错了。如果不能解决请手动更新");
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
		container.setLayout(new FormLayout());
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		FormData fd_sashForm = new FormData();
		fd_sashForm.left = new FormAttachment(0, 169);
		fd_sashForm.right = new FormAttachment(100, -3);
		fd_sashForm.bottom = new FormAttachment(100, -3);
		fd_sashForm.top = new FormAttachment(0);
		sashForm.setLayoutData(fd_sashForm);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label label = new Label(composite, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.right = new FormAttachment(100);
		fd_label.top = new FormAttachment(0, 3);
		fd_label.left = new FormAttachment(0, 3);
		label.setLayoutData(fd_label);
		label.setText("\u4FEE\u6539\u8BF4\u660E");
		
		final StyledText styledText = new StyledText(composite, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		styledText.setEditable(false);
		FormData fd_styledText = new FormData();
		fd_styledText.bottom = new FormAttachment(100);
		fd_styledText.right = new FormAttachment(100);
		fd_styledText.top = new FormAttachment(label, 6);
		fd_styledText.left = new FormAttachment(label, 0, SWT.LEFT);
		styledText.setLayoutData(fd_styledText);

		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		
		styledText_1 = new StyledText(composite_1, SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		FormData fd_styledText_1 = new FormData();
		fd_styledText_1.bottom = new FormAttachment(100);
		fd_styledText_1.right = new FormAttachment(100);
		styledText_1.setLayoutData(fd_styledText_1);
		styledText_1.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				getOKButton().setEnabled(true);
			}
		});
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		fd_styledText_1.top = new FormAttachment(label_1, 6);
		fd_styledText_1.left = new FormAttachment(label_1, 0, SWT.LEFT);
		label_1.setText("\u670D\u52A1\u5668\u6587\u4EF6\u4FEE\u6539:");
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(100);
		fd_label_1.top = new FormAttachment(0, 3);
		fd_label_1.left = new FormAttachment(0, 3);
		label_1.setLayoutData(fd_label_1);
		sashForm.setWeights(new int[] {1, 2});
		
		ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(sashForm, 0, SWT.BOTTOM);
		fd_list.right = new FormAttachment(sashForm, -6);
		fd_list.top = new FormAttachment(sashForm, 0, SWT.TOP);
		fd_list.left = new FormAttachment(0);
		list.setLayoutData(fd_list);
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub
				
				StructuredSelection selection = (StructuredSelection) event.getSelection();
				if(selection.getFirstElement()==null)
					return;
				ConFile = (FileModel)selection.getFirstElement();
				styledText.setText(ConFile.getContent());
				try { 
					File conffile = new File(server.getWebappPath()+ConFile.getFullPath());
					InputStreamReader isr = new InputStreamReader(new FileInputStream(conffile.getAbsoluteFile()), "UTF-8");  
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
						e.printStackTrace();
					}
				if(confiles.get(ConFile))
				{
					getOKButton().setEnabled(false);
				}
			}
		});
		
		listViewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
		          Set v = (Set)inputElement;
		          return v.toArray();
		    }
	        public void dispose() {
	        }

	        public void inputChanged(
	          Viewer viewer,
	          Object oldInput,
	          Object newInput) {
	        }
		});
		listViewer.setLabelProvider(new LabelProvider() {
	      public Image getImage(Object element) {
	    	  FileModel file = (FileModel)element;
	    	  if(confiles.get(file))
	    		  return Activator.getImageDescriptor("/icons/check_alt.png").createImage();
	    	  else
	    		  return Activator.getImageDescriptor("/icons/x_alt.png").createImage();
	        }
	
	        public String getText(Object element) {
	        	FileModel file = (FileModel)element;
	        	return file.getName()+"【"+file.getConftype()+"】";
	        }
	      });
		
		listViewer.setInput(confiles.keySet());
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
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filename =new File(server.getWebappPath()+ConFile.getFullPath()).getName();
				if(filename.indexOf("xml")>0||filename.indexOf("XML")>0)
				{
					if(!XmlOperator.validateXMLByXSD(styledText_1.getText()))
							return ;
				}
				if(saveConf())
					return ;
				//修改map内容设置 值为 true;表示已经处理过。
				confiles.put(ConFile, true);
			}
		});
		button.setText("\u4FDD\u5B58");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("\u5173\u95ED");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(627, 482);
	}
}
