package wisoft.pack.data.dao;

import wisoft.pack.data.pojo.PackPackages;
import wisoft.pack.data.pojo.UnpackPackages;
import wisoft.pack.data.pojo.WisoftPackageClass;


public class PackageAndClass {
	private WisoftPackageClass calssinfo;
	private UnpackPackages upackinfo;
	private PackPackages packinfo;
	private PackageAndClass pclass;
	
	public WisoftPackageClass getCalssinfo() {
		return calssinfo;
	}
	public void setCalssinfo(WisoftPackageClass calssinfo) {
		this.calssinfo = calssinfo;
	}
	public UnpackPackages getUpackinfo() {
		return upackinfo;
	}
	public void setUpackinfo(UnpackPackages upackinfo) {
		this.upackinfo = upackinfo;
	}
	public PackPackages getPackinfo() {
		return packinfo;
	}
	public void setPackinfo(PackPackages packinfo) {
		this.packinfo = packinfo;
	}
	public PackageAndClass getPclass() {
		return pclass;
	}
	public void setPclass(PackageAndClass pclass) {
		this.pclass = pclass;
	} 
}
