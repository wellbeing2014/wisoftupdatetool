package wisoft.pack.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import wisoft.pack.views.PackNavigation;

public class DeletePackHandler extends AbstractHandler implements IHandler {

	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		PackNavigation packnav =(PackNavigation)HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(PackNavigation.ID);
		
		packnav.deleteSelectPack();
		return null;
	}

	

}
