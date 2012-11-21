package wisoft.unpack.models;

public class RootModel extends Model {
	private static RootModel root = null;
	public static synchronized RootModel getRootModel()
	{
		if(root==null)
		{
			root.setParent(null);
			return new RootModel();
			
		}
		else
			return root;
	}
	
	public void addPackInfo(PackInfoModel packinfo)
	{
		this.addChild(packinfo);
	}
	
	public void removePackInfo(PackInfoModel packinfo)
	{
		this.removeChild(packinfo);
	}

}
