package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.app.Activator;
import wisoft.pack.edits.PackEdit;
import wisoft.pack.models.PackInfoModel;

public class RefreshPackAction extends Action {
	public RefreshPackAction()
	{
		setText("刷新更新包");
		setId(ICommandIds.PACKINFO_REFRESH);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_REFRESH);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/refresh.gif"));
	}
	
	@Override
	public void run()
	{
		System.out.println("aaa");
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		PackInfoModel pack = Activator.getDefault().getCurrent_pack();
		if(pack!=null&&pack.getEditInput()!=null)		
		{
			PackEdit editorPart  =(PackEdit) workbenchPage.findEditor(pack.getEditInput());
			editorPart.
		}
	}

}
