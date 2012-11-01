package wisoft.pack.edits;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import wisoft.pack.edits.xml.XMLEditor;

public class PackInfoEditor extends FormEditor {

	public static final String ID = "wisoft.pack.edits.PackInfoEditor"; //$NON-NLS-1$
	private XMLEditor editor;

	public PackInfoEditor() {
	}
	
	protected FormToolkit createToolkit(Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(display);
	}

	protected void addPages() {
		//createPage1();
		editor =new XMLEditor();
		try {
			addPage(new AFormPage(this,"AFormPage","版权"),getEditorInput());
			addPage(new BFormPage(this,"BFormPage","基本信息"),getEditorInput());
			int i = addPage(editor, getEditorInput());
			setPageText(i,"updatexml");
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void createPage1() {
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
