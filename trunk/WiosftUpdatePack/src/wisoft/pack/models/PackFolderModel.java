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
}

