package wisoft.pack.edits.sql;

import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditorExtension3;

import wisoft.pack.edits.sql.ColorManager;

public class SQLEditor extends AbstractTextEditor {
	private ColorManager colorManager;
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
}
