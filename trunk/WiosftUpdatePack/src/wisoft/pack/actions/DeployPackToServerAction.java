package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.UpdateServerDialog;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.NavigationView;

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
		
		NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
		PackInfoModel[] nm = nv.getSelectPackInfo();
		MessageBox mb;
		if(nm.length!=1)
		{
			mb= new MessageBox(window.getShell());
			mb.setMessage("请选择一个更新包工程。");
			mb.setText("提示");
			mb.open();
			return ;
		}
		
		UpdateServerDialog usd = new UpdateServerDialog(window.getShell(), nm[0]);
		usd.open();
		
	}

}
