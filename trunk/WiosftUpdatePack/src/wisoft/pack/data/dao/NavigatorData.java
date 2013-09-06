package wisoft.pack.data.dao;

import java.util.List;

import wisoft.pack.app.Activator;
import wisoft.pack.data.mapper.PackPackagesMapper;
import wisoft.pack.data.mapper.UnpackPackagesMapper;
import wisoft.pack.data.mapper.WisoftPackageClassMapper;
import wisoft.pack.data.pojo.PackPackages;
import wisoft.pack.data.pojo.PackProperties;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;
import wisoft.pack.models.PackFolderModel;

/**
 * @author wellbeing
 *
 */
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
	
	/**��ȡ������������б�
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
	
	/**
	 * ����һ�� ���� ���ҷ���
	 * @param pack
	 */
	public static void insertPackPackage(PackPackages pack)
	{
		packages.insert(pack);
	}
	
	public static void UpdatePackPackage(PackPackages pack)
	{
		packages.update(pack);
	}
	
	
	/** ���ݸ���ɾ�� pack ��unpack�ļ�¼
	 * @param pack
	 */
	public static void deletePackPackage(PackageInfo pack)
	{
		packages.delete(pack.getId());
	}
	
	
	public static PackPackages getPackPackageById(String id)
	{
		return packages.selectPakageById(id);
	}
	
	public static UnpackPackages getUPakageById(String id)
	{
		return unpackages.selectUPakageById(id);
	}
	
	public static void UpdateUnpackPackage(UnpackPackages pack)
	{
		unpackages.update(pack);
	}
	
	/** ����һ�����°���Ĭ�ϵķ���
	 * @param packageinfo
	 */
	public static  void insertUnPack(UnpackPackages packageinfo)
	{
		unpackages.insert(packageinfo);
	}
	
	/** ���� ���ͻ�ȡ Ĭ�ϵķ���
	 * @param type  PackProperties.TYPE_UNPACK|PackProperties.TYPE_PACK
	 * @return
	 */
	public static WisoftPackageClass getDefaultClass(int type)
	{
		return
		 packageclass.selectDefaultClassInfo(PackProperties.TYPE_UNPACK);
	}
}
