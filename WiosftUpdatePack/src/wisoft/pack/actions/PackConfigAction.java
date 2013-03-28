package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.PackConfigDialog;

public class PackConfigAction extends Action {
	private final String ID=ICommandIds.PACKCONFIG_OPEN;
	private IWorkbenchWindow window;
	public PackConfigAction(IWorkbenchWindow window,String label)
	{
		this.window =window;
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKCONFIG_OPEN);
		setText(label);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKCONFIG_OPEN);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/properties.png"));
	}
	
	@Override
	public void run() {
		PackConfigDialog packconfig = new PackConfigDialog(window.getShell());
		packconfig.open();
	}

}
