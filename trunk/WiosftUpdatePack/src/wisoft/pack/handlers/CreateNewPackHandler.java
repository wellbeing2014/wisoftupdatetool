package wisoft.pack.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import wisoft.pack.dialogs.NewPackWizard;

public class CreateNewPackHandler extends AbstractHandler implements IHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		
		NewPackWizard newPackWizard = new NewPackWizard();
		WizardDialog wd = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),newPackWizard);
		
		//System.out.println(newPackWizard.packinfo);
		wd.open();
	
		return null;
	}

}
