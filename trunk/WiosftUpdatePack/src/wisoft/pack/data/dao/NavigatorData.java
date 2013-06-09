package wisoft.pack.data.dao;

import wisoft.pack.app.Activator;
import wisoft.pack.data.mapper.PackPackagesMapper;
import wisoft.pack.data.mapper.UnpackPackagesMapper;
import wisoft.pack.data.mapper.WisoftPackageClassMapper;
import wisoft.pack.models.PackFolder;
import wisoft.pack.models.PackFolderModel;

public class NavigatorData {
	private static WisoftPackageClassMapper packageclass = (WisoftPackageClassMapper)Activator.getDefault().getSpringBean("wisoftPackageClassMapper");
	private static UnpackPackagesMapper unpackages = (UnpackPackagesMapper)Activator.getDefault().getSpringBean("unpackPackagesMapper");
	private static PackPackagesMapper packages = (PackPackagesMapper)Activator.getDefault().getSpringBean("packPackagesMapper");
	
	public static PackFolderModel getUnPackInput()
	{
		PackFolderModel pfm = new PackFolderModel(null,PackFolder.DEFALUT);
		
		return pfm;
	}

}
