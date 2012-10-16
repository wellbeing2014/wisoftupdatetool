package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.*;
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
		NewPackInitDlg nd = new NewPackInitDlg(window);
		if(nd.open()==IDialogConstants.OK_ID)
		{
			//System.out.println("aa");
			NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
			nv.add();
		}
		
	}

}
