package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.NewPackWizard;

public class OpenNewPackDialogAction extends Action {
	private final IWorkbenchWindow window;

	public OpenNewPackDialogAction(IWorkbenchWindow window,String label) {
		this.window =window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKINFO_ADD);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_ADD);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/add.gif"));
	}

	public void run() {
		WizardDialog wd = new WizardDialog(window.getShell(),new NewPackWizard());
		wd.open();
	}

}
