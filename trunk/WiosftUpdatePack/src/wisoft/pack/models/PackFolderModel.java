package wisoft.pack.models;

public class PackFolderModel extends Model {
	
	private PackFolder FolderType = PackFolder.DEFALUT;
	public PackFolder getFolderType() {
		return FolderType;
	}
	public void setFolderType(PackFolder folderType) {
		FolderType = folderType;
	}
	public PackFolderModel(Model parent,PackFolder name)
	{
		this.parent = parent;
		this.setFolderType(name);
		this.setName(name.getFoldername());
	}
	public PackFolderModel getChildFolder(PackFolder folder,boolean force)
	{
		for(Model model :this.getChildren())
		{
			if((model instanceof PackFolderModel)  )
			{
				PackFolder pf =((PackFolderModel) model).getFolderType();
				if(pf.equals(folder))
				return (PackFolderModel)model;
			}
		}
		if(force)
		{
			PackFolderModel zpf =new PackFolderModel(this,folder);
			this.addChild(zpf);
			return zpf;
		}
		else
			return null;
	}
}

