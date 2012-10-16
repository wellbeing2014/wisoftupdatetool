package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.views.NavigationView;

public class DelPackAction extends Action {
	private IWorkbenchWindow window;
	public DelPackAction(IWorkbenchWindow window,String label)
	{
		this.window =window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.DEL_PACKINFO);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.DEL_PACKINFO);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/del.png"));
	}
	@Override
	public void run() {
//		// TODO Auto-generated method stub
//		NavigationView nv = (NavigationView)window.getActivePage().findView("WiosftUpdatePack.navigationView");
//		nv.removeSelected();
	}

}
