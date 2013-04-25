package wisoft.pack.actions;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.app.Activator;
import wisoft.pack.utils.ZipUtil;

public class OpenPackAction extends Action {
	
	private final String ID=ICommandIds.PACKINFO_OPEN;
	private IWorkbenchWindow window;
	public OpenPackAction(IWorkbenchWindow window,String label)
	{
		this.window =window;
        // The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.PACKINFO_OPEN);
		setText(label);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(ICommandIds.PACKINFO_OPEN);
		setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/open.png"));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("ÂùOPEN");
		FileDialog dialog = new FileDialog(window.getShell(),SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.rar","*.zip","*.wi","*.*"});
		String fileSelected = dialog.open();
		System.out.println(fileSelected);
		File packfile = new File(fileSelected);
		ZipUtil zip = new ZipUtil();
		try {
			zip.unZip(fileSelected, Activator.getDefault().getWorkSpacePath()+File.separator+packfile.getName(), false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
