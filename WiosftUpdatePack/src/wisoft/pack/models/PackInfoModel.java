package wisoft.pack.models;

import java.io.File;

import org.eclipse.ui.IEditorInput;

import wisoft.pack.utils.XmlOperator;

public class PackInfoModel extends Model {
	protected PackInfoOfOverview overview = new PackInfoOfOverview(this);
	protected PackInfoOfSelectFiles selectFiles = new PackInfoOfSelectFiles(this);
	protected PackInfoOfEditConfs editConfs =new PackInfoOfEditConfs(this);
	protected PackInfoOfEditSql editSql = new PackInfoOfEditSql(this);
	public boolean isdirty = false;
	private  IEditorInput editInput;
	
	private String moduleName;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	private String moduleCode;
	private String version ;
	private String savePath;
	
	public void init()throws Exception
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		XmlOperator xmlo;
		xmlo = new XmlOperator(savePath+"/updateinfo.xml");
		xmlo.addRootElement("root");
		xmlo.addElement("root", "ModuleName", "123");
		xmlo.modifyNode("/root/ModuleName", "123", "235");
		xmlo.close();
	}
	
	public void updateXml() throws Exception
	{
		
		
		
	}

	public IEditorInput getEditInput() {
		return editInput;
	}

	public void setEditInput(IEditorInput editInput) {
		this.editInput = editInput;
	}

	public PackInfoOfEditConfs getEditConfs() {
		return editConfs;
	}
	
	public PackInfoOfEditSql getEditSql() {
		return editSql;
	}
	

	public PackInfoOfSelectFiles getSelectFiles() {
		return selectFiles;
	}
	
	public PackInfoOfOverview getOverview() {
		return overview;
	}
	
	public PackInfoModel(String name)
	{
		this.name =name;
		this.addChild(overview);
		this.addChild(selectFiles);
		this.addChild(editConfs);
		this.addChild(editSql);
	}
}
