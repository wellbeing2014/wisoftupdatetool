package wisoft.pack.edits;


import net.sourceforge.easysql.editors.SQLEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import wisoft.pack.edits.xml.XMLEditor;
import wisoft.pack.models.PackInfoModel;

public class PackInfoEditor extends FormEditor {

	public static final String ID = "wisoft.pack.edits.PackInfoEditor"; //$NON-NLS-1$
	private SQLEditor sqleditor;
	private XMLEditor xmleditor;

	public PackInfoEditor() {
	}
	
	protected FormToolkit createToolkit(Display display) {
		// Create a toolkit that shares colors between editors.
		return new FormToolkit(display);
	}

	protected void addPages() {
		//createPage1();
		sqleditor =new SQLEditor();
		xmleditor =new XMLEditor();
	
		PackInfoModel pack = ((PackInfoInput)getEditorInput()).getPackinfo();
		try {
			addPage(new BFormPage(this,"BFormPage","基本信息"),getEditorInput());
			addPage(new CFormPage(this,"CFormPage","文件列表"));
			int i = addPage(sqleditor, new XmlSqlEditorInput(XmlSqlEditorInput.TYPE_SQL,pack));
			setPageText(i,"SQL语句编写");
			
			int j = addPage(xmleditor, new XmlSqlEditorInput(XmlSqlEditorInput.TYPE_XML,pack));
			setPageText(j,"updateinfo.xml");
			addPage(new AFormPage(this,"AFormPage","版权"),getEditorInput());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CTabFolder tab =(CTabFolder)getContainer();
		tab.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.item.getData() instanceof XMLEditor)
				{
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
		sqleditor.doSave(monitor);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		sqleditor.doSaveAs();
	}
	
	@Override
	public boolean isDirty()
	{
		return sqleditor.isDirty();
		
	}
	
	
}
