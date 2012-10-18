package wisoft.pack.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Model {
	protected String name;
	protected Model parent;
	protected List<Model> children = new ArrayList<Model>();
	
	public List<Model> getChildren() {
		return children;
	}

	public void setParent(Model parent) {
		this.parent = parent;
	}
	
	public Model getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Model()
	{
		
	}
	
	public void addChild(Model child)
	{
		child.setParent(this);
		this.children.add(child);
	}
	
	public void removeChild(Model child)
	{
		this.children.remove(child);
	}
	
}
