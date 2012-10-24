package wisoft.pack.edits;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import wisoft.pack.actions.SavePackEditAction;
import wisoft.pack.events.PackEditEvent;
import wisoft.pack.events.PackEditEventListener;

public class PackEdit extends EditorPart {

	public static final String ID = "wisoft.pack.edits.PackEdit"; //$NON-NLS-1$
	private OverViewEditView overviewEv;
	private EditConfsEditView editconfsEv;
	private boolean dirty;

	public PackEdit() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(container, SWT.BOTTOM);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("基本信息");
		overviewEv = new OverViewEditView(tabFolder, SWT.NONE);
		overviewEv.addPackEditListener(new PackEditEventListener(){
			@Override
			public void PackEditIsDirty(PackEditEvent event)
			{
				dirty = true;
				firePropertyChange(ISaveablePart2.PROP_DIRTY);
			}
		});
		tabItem.setControl(overviewEv);
	
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("文件浏览");
		editconfsEv = new EditConfsEditView(tabFolder, SWT.NONE);
		tabItem_1.setControl(editconfsEv);
		
		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("配置编辑");
		
		TabItem tabItem_3 = new TabItem(tabFolder, SWT.NONE);
		tabItem_3.setText("SQL编辑");
		//tabItem_1.setControl(text);
		
		TabItem tabItem_4 = new TabItem(tabFolder, SWT.NONE);
		tabItem_4.setText("updateinfo.xml");
		
		//dirty = true;
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Initialize the editor part
		//System.out.println(input.toString());
        this.setInput(input);
        this.setSite(site);
        this.setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

}
