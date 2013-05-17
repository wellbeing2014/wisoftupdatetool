package wisoft.pack.models;

public class PackFolderModel extends Model {
	public PackFolderModel(Model parent,String name)
	{
		this.parent = parent;
		this.setName(name);
	}

}
