package wisoft.pack.models;

import java.util.List;

import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;

public class PackFolderModel extends Model {
	
	
	private WisoftPackageClass classinfo;
	private List<PackFolderModel> packfloderchildren;
	
	
	public List<PackFolderModel> getPackfloderchildren() {
		return packfloderchildren;
	}

	public void setPackfloderchildren(List<PackFolderModel> packfloderchildren) {
		this.packfloderchildren = packfloderchildren;
	}

	public List<UnpackPackages> getUpackages() {
		return upackages;
	}

	public void setUpackages(List<UnpackPackages> upackages) {
		this.upackages = upackages;
	}


	private List<UnpackPackages> upackages;

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

