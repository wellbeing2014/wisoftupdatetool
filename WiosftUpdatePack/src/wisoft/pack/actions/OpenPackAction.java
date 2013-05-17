package wisoft.pack.actions;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.app.Activator;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.ZipUtil;
import wisoft.pack.views.NavigationView;
import wisoft.pack.views.UnPackNavigation;

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
		
		MessageBox mb = new MessageBox(window.getShell(),SWT.ERROR);
		mb.setText("出错");
		FileDialog dialog = new FileDialog(window.getShell(),SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.rar","*.zip","*.wi","*.*"});
		String fileSelected = dialog.open();
		if(fileSelected==null)
			return;
		System.out.println(fileSelected);
		File packfile = new File(fileSelected);
		String destFolder = Activator.getDefault().getWorkSpacePath()+File.separator+packfile.getName();
		destFolder=destFolder.substring(destFolder.indexOf("/")+1,destFolder.length());
		int dot = destFolder.lastIndexOf('.'); 
		if ((dot >-1) && (dot < (destFolder.length()))) 
			destFolder = destFolder.substring(0, dot);
		ZipUtil zip = new ZipUtil();
		try {
			zip.unZip(fileSelected,destFolder , false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(-1!=e.toString().indexOf("not a ZIP"))
				mb.setMessage("亲，这个包不适合用来更新哦。");
			else
				mb.setMessage(e.toString());
			mb.open();
			return;
		}
		File updateinfoxml = new File(destFolder+File.separator+UpdateInfo.FileName);
		if(!updateinfoxml.exists())
		{
			FileUtil.delFolder(destFolder);
			mb.setMessage("亲，这个更新包是不标准的，不能更新，\n劳驾您手动更新吧。");
			mb.open();
		}
		UnPackNavigation nv = (UnPackNavigation)window.getActivePage().findView(UnPackNavigation.ID);
		PackInfoModel pim = new PackInfoModel();
		pim.setSavePath(destFolder);
		pim.readFromXML();
		pim.setName(pim.getModuleName()+"("+pim.getModuleCode()+")"+pim.getVersion());
		nv.addPackInfo(pim);
	}

}
