package wisoft.pack.models;

public abstract class Model {
	protected String name;
	protected PackInfo parent;

	public PackInfo getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Model(String name,PackInfo parent)
	{
		this.parent = parent;
	}
	public Model()
	{
		
	}
}
