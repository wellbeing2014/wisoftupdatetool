package wisoft.pack.data.mapper;

import java.util.List;

import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.WisoftPackageClass;
import wisoft.pack.models.PackFolderModel;

public interface WisoftPackageClassMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WISOFT_PACKAGE_CLASS
	 * @mbggenerated  Sun Jun 09 13:16:06 CST 2013
	 */
	int insert(WisoftPackageClass record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table WISOFT_PACKAGE_CLASS
	 * @mbggenerated  Sun Jun 09 13:16:06 CST 2013
	 */
	int insertSelective(WisoftPackageClass record);
	
	List<PackFolderModel> selectUnPackClassWithPack();
	List<PackFolderModel> selectPackClassWithPack();
	
	WisoftPackageClass selectClassInfoById(String id);
	
	PackFolderModel selectClassById(String id);
	List<PackFolderModel> selectClassByParentId(String parent_id);
	
	int deleteClassById(String id);
	
	List<PackageInfo> selectPackageInfoByClassid(String package_class_id);
	
	
	/**
	 * 获取一个默认更新包分类
	 * @param type 1 pack 0 unpack
	 * @return
	 */
	WisoftPackageClass selectDefaultClassInfo(int type);
	
}