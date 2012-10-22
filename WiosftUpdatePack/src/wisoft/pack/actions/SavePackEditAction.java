package wisoft.pack.actions;

import org.eclipse.jface.action.Action;

public class SavePackEditAction extends Action {
	public SavePackEditAction()
	{
		setId(ICommandIds.PACKINFO_REMOVE);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_REMOVE);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/save.gif"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
