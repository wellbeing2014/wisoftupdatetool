package wisoft.pack.edits.sql;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;

import wisoft.pack.utils.PackConfigInfo;

public class SQLEditor extends AbstractTextEditor {
	private ColorManager colorManager;
	private static boolean isOpr = PackConfigInfo.getInstance().selOperate();
	public SQLEditor()
	{
		super();
		// make sure we inherit all the text editing commands (delete line etc).
		setKeyBindingScopes(new String[] { "org.eclipse.ui.textEditorScope" });  //$NON-NLS-1$
		internal_init();
	}
	protected void internal_init() {
		configureInsertMode(ITextEditorExtension3.SMART_INSERT, false);
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new SQLConfiguration(colorManager));
		setDocumentProvider(new SQLDocumentProvider());
	}
	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		updateState(getEditorInput());
		validateState(getEditorInput());
		super.performSave(true,progressMonitor);
	}
}
