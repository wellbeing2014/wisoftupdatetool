package wisoft.pack.edits.xml;

import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;


public class XMLEditor extends AbstractTextEditor {

	private ColorManager colorManager;

	public XMLEditor()
	{
		super();
		// make sure we inherit all the text editing commands (delete line etc).
		//setKeyBindingScopes(new String[] { "org.eclipse.ui.textEditorScope" });  //$NON-NLS-1$
		internal_init();
	}
	///
    protected void internal_init() {
		configureInsertMode(ITextEditorExtension3.SMART_INSERT, false);
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	@Override
    public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
	
}
