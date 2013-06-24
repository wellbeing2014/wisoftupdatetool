package wisoft.pack.data.dao;

import java.util.List;

import wisoft.pack.app.Activator;
import wisoft.pack.data.mapper.PackPackagesMapper;
import wisoft.pack.data.mapper.UnpackPackagesMapper;
import wisoft.pack.data.mapper.WisoftPackageClassMapper;
import wisoft.pack.data.pojo.PackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;
import wisoft.pack.models.PackFolderModel;

public class NavigatorData {
	private static WisoftPackageClassMapper packageclass = (WisoftPackageClassMapper)Activator.getDefault().getSpringBean("wisoftPackageClassMapper");
	private static UnpackPackagesMapper unpackages = (UnpackPackagesMapper)Activator.getDefault().getSpringBean("unpackPackagesMapper");
	private static PackPackagesMapper packages = (PackPackagesMapper)Activator.getDefault().getSpringBean("packPackagesMapper");
	
	public static PackFolderModel getUnPackInput()
	{
		PackFolderModel pfm = new PackFolderModel(null,null);
		List<PackFolderModel> unpacklist= packageclass.selectUnPackClassWithPack();
		for(PackFolderModel zpfm:unpacklist)
		{
			pfm.addChild(zpfm);
		}
		return pfm;
	}
	
	/**获取打包导航树的列表
	 * @return
	 */
	public static PackFolderModel getPackInput()
	{
		PackFolderModel pfm = new PackFolderModel(null,null);
		List<PackFolderModel> unpacklist= packageclass.selectPackClassWithPack();
		for(PackFolderModel zpfm:unpacklist)
		{
			pfm.addChild(zpfm);
		}
		return pfm;
	}
	
	public static boolean insertpackageclass(WisoftPackageClass wpc)
	{
		return packageclass.insert(wpc)==1;
	}
	
	public static boolean deleteClassById(String id)
	{
		 packageclass.deleteClassById(id);
		 unpackages.deleteUpackageByClassId(id);
		 packages.deletepackageByClassId(id);
		 return true;
	}
	
	public static void insertPackPackage(PackPackages pack)
	{
		packages.insert(pack);
	}
	
	public static void UpdatePackPackage(PackPackages pack)
	{
		packages.update(pack);
	}
	
	public static PackPackages getPackPackageBy(String id)
	{
		return packages.selectPakageById(id);
	}
}
