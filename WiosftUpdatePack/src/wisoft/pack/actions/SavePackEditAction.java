package wisoft.pack.actions;

import org.eclipse.jface.action.Action;

public class SavePackEditAction extends Action {
	public SavePackEditAction()
	{
		setText("±£´æ");
		setId(ICommandIds.PACKINFO_SAVE);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_SAVE);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/save.gif"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("save");
		super.run();
	}
}
