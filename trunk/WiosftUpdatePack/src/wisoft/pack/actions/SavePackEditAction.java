package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.edits.PackInfoEditor;

public class SavePackEditAction extends Action {
	public SavePackEditAction()
	{
		setText("保存");
		setId(ICommandIds.PACKINFO_SAVE);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_SAVE);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/save.gif"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("save");
		PackInfoEditor ep =(PackInfoEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		ep.setPartName1("我日你吗比");
		ep.doSaveAs();
		super.run();
	}
}
