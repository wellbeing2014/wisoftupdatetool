package wisoft.pack.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.data.pojo.WisoftPackageClass;

public class PackFolderModel extends Model {
	
	public PackFolderModel() {
		// TODO Auto-generated constructor stub
	}
	private WisoftPackageClass classinfo =new WisoftPackageClass();
	private List<PackFolderModel> packfloderchildren =new ArrayList<PackFolderModel>();
	private List<PackageInfo> packages = new ArrayList<PackageInfo>();
	
	
	public List<PackFolderModel> getPackfloderchildren() {
		return packfloderchildren;
	}

	public void setPackfloderchildren(List<PackFolderModel> packfloderchildren) {
		for(PackFolderModel pfm :packfloderchildren)
		{
			this.addChild(pfm);
		}
	}

	public List<PackageInfo> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageInfo> packages) {
		for(PackageInfo upg :packages)
		{
			this.addChild(new PackInfoModel(upg));
		}
	}


	public WisoftPackageClass getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(WisoftPackageClass classinfo) {
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
	
	/**根据已知的更新包信息查找结构内相同的更新包对象
	 * @param linkpack
	 * @return
	 */
	public PackInfoModel findPackageInfo(PackageInfo linkpack)
	{
		for(Model model : this.getChildren())
		{
			if(model instanceof PackInfoModel)
			{
				PackInfoModel mymodel = (PackInfoModel)model;
				if(mymodel.getPackageinfo().equals(linkpack))
				return mymodel;
			}
			if(model instanceof PackFolderModel)
			{
				PackFolderModel mymodelFolder = (PackFolderModel)model;
				PackInfoModel mymodel= mymodelFolder.findPackageInfo(linkpack);
				if(mymodel!=null)
					return mymodel;
			}
		}
		return null;
		
	}
}

