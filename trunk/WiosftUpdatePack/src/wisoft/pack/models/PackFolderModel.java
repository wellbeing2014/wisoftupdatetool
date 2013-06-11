package wisoft.pack.models;

import wisoft.pack.data.pojo.WisoftPackageClass;

public class PackFolderModel extends Model {
	
	
	private WisoftPackageClass classinfo;

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

