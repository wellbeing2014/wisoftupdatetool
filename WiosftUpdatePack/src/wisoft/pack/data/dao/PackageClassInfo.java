package wisoft.pack.data.dao;

import java.util.List;

import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;


public class PackageClassInfo {
	private PackageClassInfo parent;
	private List<PackageClassInfo> children;
	private List<UnpackPackages> upackages;
	private WisoftPackageClass info;
	
	public WisoftPackageClass getInfo() {
		return info;
	}
	public void setInfo(WisoftPackageClass info) {
		this.info = info;
	}
	public PackageClassInfo getParent() {
		return parent;
	}
	public void setParent(PackageClassInfo parent) {
		this.parent = parent;
	}
	
	public List<PackageClassInfo> getChildren() {
		return children;
	}
	public void setChildren(List<PackageClassInfo> children) {
		this.children = children;
	}
	public List<UnpackPackages> getUpackages() {
		return upackages;
	}
	public void setUpackages(List<UnpackPackages> upackages) {
		this.upackages = upackages;
	}

	

	
}
