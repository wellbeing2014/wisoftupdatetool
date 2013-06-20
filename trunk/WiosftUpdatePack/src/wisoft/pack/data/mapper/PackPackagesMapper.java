package wisoft.pack.data.mapper;

import java.util.List;

import wisoft.pack.data.pojo.PackPackages;

public interface PackPackagesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PACK_PACKAGES
	 * @mbggenerated  Sun Jun 09 13:16:06 CST 2013
	 */
	int insert(PackPackages record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table PACK_PACKAGES
	 * @mbggenerated  Sun Jun 09 13:16:06 CST 2013
	 */
	int insertSelective(PackPackages record);
	
	
	List<PackPackages> selectPakageByClassId(String parent_class_id);
	    
	void deletepackageByClassId(String classid);
	
	PackPackages selectPakageById(String id);
	
	int update(PackPackages record);
}