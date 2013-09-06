package wisoft.pack.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import wisoft.pack.app.Activator;
import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.dao.UUIDGenerator;
import wisoft.pack.data.pojo.PackProperties;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.ZipUtil;
import wisoft.pack.views.PackNavigation;

public class OpenPackHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		Shell shell = HandlerUtil.getActiveShell(event);
		MessageBox mb = new MessageBox(shell,SWT.ERROR);
		mb.setText("出错");
		FileDialog dialog = new FileDialog(shell,SWT.OPEN);
		dialog.setFilterExtensions(new String[]{"*.rar","*.zip","*.wi","*.*"});
		String fileSelected = dialog.open();
		if(fileSelected==null)
			return null;
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
			return null;
		}
		File updateinfoxml = new File(destFolder+File.separator+UpdateInfo.FileName);
		if(!updateinfoxml.exists())
		{
			try {
				FileUtil.delete(destFolder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mb.setMessage("亲，这个更新包是不标准的，不能更新，\n劳驾您手动更新吧。");
			mb.open();
		}
		PackInfoModel pim = new PackInfoModel();
		pim.readFromXML(destFolder);
		pim.setName(pim.getModuleName()+"("+pim.getModuleCode()+")"+pim.getVersion());
		PackageInfo pi = new PackageInfo();
		pi.setModuleCode(pim.getModuleCode());
		pi.setModuleName(pim.getModuleName());
		pi.setModuleVer(pim.getVersion());
		pi.setPackageClassId(NavigatorData.getDefaultClass(PackProperties.TYPE_UNPACK).getId());
		pi.setId(UUIDGenerator.getUUID());
		pi.setPackageFileId(UUIDGenerator.getUUID());//备用
		pi.setPackageName(pim.getName());
		pi.setPackagePublishdate(null);
		pi.setSavePath(destFolder);
		pi.setTestNumber(1);//备用
		pi.setWimsProjectId(null);//备用
		//写入数据库  准备
		pim.setPackageinfo(pi);
		UnpackPackages upg = new UnpackPackages();
		try {
			pim.getPackageinfo().fatherToChild(pi, upg);
			upg.setUnpackPerson("朱新培");
			upg.setState(PackProperties.UNPACK_STATE_01DOWNLOAD);
			NavigatorData.insertUnPack(upg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PackNavigation packnav =(PackNavigation)Activator.findView(PackNavigation.ID);
		packnav.refreshInput(pi);
		return null;
	}


}
