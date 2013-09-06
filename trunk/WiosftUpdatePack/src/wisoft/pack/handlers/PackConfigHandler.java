package wisoft.pack.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.handlers.HandlerUtil;

import wisoft.pack.dialogs.PackConfigDialog;

public class PackConfigHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		PackConfigDialog packconfig = new PackConfigDialog(HandlerUtil.getActiveShell(event));
		packconfig.open();
		return null;
	}

}
