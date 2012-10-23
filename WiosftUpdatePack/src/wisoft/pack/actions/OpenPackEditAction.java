package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.edits.PackEdit;
import wisoft.pack.edits.PackInfoInput;


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
		//MyInput input=new MyInput(path);
		//MessageDialog.openInformation(window.getShell(), "info", path);
//		IWorkbenchPage page=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		try{
//			page.openEditor(new PackInfoInput()  ,PackEdit.ID  );
//		}catch(PartInitException e){
//			System.out.println(e.getMessage());
//		}
	}

}
