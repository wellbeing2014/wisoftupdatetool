package wisoft.pack.models;

import java.util.ArrayList;
import java.util.List;

public class PackInfo extends Model {
	protected PackInfoOfOverview overview;
	protected PackInfoOfSelectFiles selectFiles;
	protected PackInfoOfEditConfs editConfs;
	public PackInfoOfEditConfs getEditConfs() {
		return editConfs;
	}
	public void setEditConfs(PackInfoOfEditConfs editConfs) {
		this.editConfs = editConfs;
	}
	public PackInfoOfEditSql getEditSql() {
		return editSql;
	}
	public void setEditSql(PackInfoOfEditSql editSql) {
		this.editSql = editSql;
	}

	protected PackInfoOfEditSql editSql;
	public PackInfoOfSelectFiles getSelectFiles() {
		return selectFiles;
	}
	public void setSelectFiles(PackInfoOfSelectFiles selectFiles) {
		this.selectFiles = selectFiles;
	}
	public PackInfoOfOverview getOverview() {
		return overview;
	}
	public void setOverview(PackInfoOfOverview overview) {
		this.overview = overview;
	}

	protected List<Model> packs = new ArrayList<Model>();
	public PackInfo(){}
	public PackInfo(String name)
	{
		this.name = name;
	}
	public PackInfo(String name,PackInfo parent)
	{
		this.name = name;
		this.parent = parent;
	}
	public PackInfo(String name,PackInfo parent ,boolean isFolder)
	{
		if(!isFolder)
		{
			this.packs.add(new PackInfoOfOverview(name, this));
			this.packs.add(new PackInfoOfSelectFiles(name, this));
			this.packs.add(new PackInfoOfEditConfs(name, this));
			this.packs.add(new PackInfoOfEditSql(name, this));
		}
		this.name = name;
		this.parent = parent;
	}
	
	public List<Model> getAllPacks()
	{
		return this.packs;
	}
	
	public void addPackInfo(Model pack)
	{
		this.packs.add(pack);
	}
	
	public void removePackInfo(Model pack)
	{
		this.packs.remove(pack);
	}
}
