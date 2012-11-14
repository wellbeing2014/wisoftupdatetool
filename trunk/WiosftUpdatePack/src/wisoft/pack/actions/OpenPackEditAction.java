package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.dialogs.ExportPackWizard;
import wisoft.pack.dialogs.NewPackWizard;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.NavigationView;


public class OpenPackEditAction extends Action {
	private final String ID=ICommandIds.PACKINFO_OPEN;
	private IWorkbenchWindow window;
	public OpenPackEditAction(IWorkbenchWindow window)
	{
		this.window =window;
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKINFO_OPEN);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_OPEN);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/open.gif"));
	}
	
	@Override
	public void run() {
		NavigationView nv = (NavigationView)window.getActivePage().findView(NavigationView.ID);
		PackInfoModel[] nm = nv.getSelectPackInfo();
		MessageBox mb;
		if(nm.length==1)
		{
			WizardDialog wd = new WizardDialog(window.getShell(),new ExportPackWizard(nm[0]));
			wd.open();
		}
		else
		{
			mb= new MessageBox(window.getShell());
			mb.setMessage("��ѡ��һ�����°����̡�");
			mb.setText("��ʾ");
			mb.open();
		}
	}

}
