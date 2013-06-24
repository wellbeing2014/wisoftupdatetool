package wisoft.pack.models;

import java.util.List;

import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.WisoftPackageClass;

public class PackFolderModel extends Model {
	
	public PackFolderModel() {
		// TODO Auto-generated constructor stub
	}
	private WisoftPackageClass classinfo;
	private List<PackFolderModel> packfloderchildren;
	private List<PackageInfo> packages;
	
	public List<PackFolderModel> getPackfloderchildren() {
		return packfloderchildren;
	}

	public void setPackfloderchildren(List<PackFolderModel> packfloderchildren) {
		this.packfloderchildren = packfloderchildren;
		for(PackFolderModel pfm :packfloderchildren)
		{
			this.addChild(pfm);
		}
	}

	public List<PackageInfo> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageInfo> packages) {
		this.packages = packages;
		for(PackageInfo upg :packages)
		{
			this.addChild(new PackInfoModel(upg));
		}
	}


	public WisoftPackageClass getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(WisoftPackageClass classinfo) {
		this.classinfo = classinfo;
		this.name = classinfo.getClassName();
	}
	
	
	public PackFolderModel(Model parent,WisoftPackageClass classinfo) {
		// TODO Auto-generated constructor stub
		if(parent!=null)
			setParent(parent);
		if(classinfo!=null)
			setClassinfo(classinfo);
	}
	
	public boolean removeChildrenFromDB()
	{
		return NavigatorData.deleteClassById(this.classinfo.getId());
	}
}

