package wisoft.pack.edits;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import wisoft.pack.edits.xml.XMLEditor;

public class PackInfoEditor extends MultiPageEditorPart {

	public static final String ID = "wisoft.pack.edits.PackInfoEditor"; //$NON-NLS-1$
	private XMLEditor editor;

	public PackInfoEditor() {
	}

	@Override
	protected void createPages() {
		try {
			editor =new XMLEditor();
			int i =addPage(editor, getEditorInput());
			setPageText(i,"updatexml");
		} catch (PartInitException e) {
		}
		createPage1();
	}
	
	void createPage1() {

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;
		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");
		
		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				//setFont();
			}
		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		//System.out.println(input.toString());
        this.setInput(input);
        this.setSite(site);
        this.setPartName(input.getName());
       // System.out.println("编辑器初始化完成！");
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		System.out.println("妈呀，我开始保存了。");
		editor.doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		editor.doSaveAs();
	}
	
	@Override
	public boolean isDirty()
	{
		return editor.isDirty();
		
	}

}
