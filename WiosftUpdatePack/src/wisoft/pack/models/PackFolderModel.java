package wisoft.pack.models;

import java.util.List;

import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;

public class PackFolderModel extends Model {
	
	public PackFolderModel() {
		// TODO Auto-generated constructor stub
	}
	private WisoftPackageClass classinfo;
	private List<PackFolderModel> packfloderchildren;
	private List<PackageInfo> upackages;
	
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

	public List<PackageInfo> getUpackages() {
		return upackages;
	}

	public void setUpackages(List<PackageInfo> upackages) {
		this.upackages = upackages;
		for(PackageInfo upg :upackages)
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
}

