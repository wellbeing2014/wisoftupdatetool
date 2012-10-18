package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.*;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.NavigationView;

public class OpenNewPackDialogAction extends Action {
	private final IWorkbenchWindow window;
	public OpenNewPackDialogAction(IWorkbenchWindow window,String label) {
		this.window =window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.OPEN_NEWPACK_DLG);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.OPEN_NEWPACK_DLG);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/sample2.gif"));
	}
	
	public void run() {
		NewPackDialog nd = new NewPackDialog(window.getShell(), SWT.APPLICATION_MODAL|SWT.CLOSE);
		nd.open();
		NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
		nv.root.addPackInfo(new PackInfoModel("我是增加的A"));
	}

}
