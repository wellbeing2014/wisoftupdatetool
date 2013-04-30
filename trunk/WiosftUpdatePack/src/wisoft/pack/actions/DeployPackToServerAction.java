package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.UpdateServerDialog;

public class DeployPackToServerAction extends Action {
	private final IWorkbenchWindow window;
	public DeployPackToServerAction(IWorkbenchWindow window,String label)
	{
		this.window =window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKINFO_DEPLOY);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_DEPLOY);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/deploy.png"));
	}
	@Override
	public void run() {
		
		UpdateServerDialog usd = new UpdateServerDialog(window.getShell(), 0);
		usd.open();
		
	}

}
