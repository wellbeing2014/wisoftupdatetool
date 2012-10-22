package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
		setId(ICommandIds.PACKINFO_ADD);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_ADD);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/add.gif"));
	}

	public void run() {
		SetPackInfoTitle nd = new SetPackInfoTitle(window.getShell());
		
		
		if(nd.open()==Window.OK)
		{
			NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
			nv.addPackInfo(new PackInfoModel(nd.getPackname()));
		}
	}

}
