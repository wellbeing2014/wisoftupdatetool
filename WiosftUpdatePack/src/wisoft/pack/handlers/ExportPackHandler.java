package wisoft.pack.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.handlers.HandlerUtil;

import wisoft.pack.dialogs.ExportPackWizard;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.PackNavigation;

public class ExportPackHandler  extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		PackNavigation packnav =(PackNavigation)HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().findView(PackNavigation.ID);
		IStructuredSelection selection = (IStructuredSelection) packnav.getCommonViewer().getSelection();
		MessageBox mb;
		if(selection!=null&&selection.getFirstElement()!=null)
		{
			if(selection.getFirstElement() instanceof PackInfoModel)
			{
				WizardDialog wd = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(),new ExportPackWizard((PackInfoModel)selection.getFirstElement()));
				wd.open();
			}
		}
		else
		{
			mb= new MessageBox(HandlerUtil.getActiveWorkbenchWindow(event).getShell());
			mb.setMessage("请选择一个更新包工程。");
			mb.setText("提示");
			mb.open();
		}
		return null;
	}

	public ExportPackHandler() {
		// TODO Auto-generated constructor stub
	
	}
}
