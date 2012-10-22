package wisoft.pack.actions;


import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.models.Model;
import wisoft.pack.views.NavigationView;

public class DelPackInfoAction extends Action {
	private final IWorkbenchWindow window;
	public DelPackInfoAction(IWorkbenchWindow window,String label)
	{
		this.window =window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKINFO_REMOVE);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_REMOVE);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/del.png"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
//		Model[] nm = nv.getSelectPackInfo();
//		for (int i=0;i<nm.length;i++)
//		{
//			if()
//		}
		//nv.removePackInfo(nv.getSelectPackInfo());
	}
}
