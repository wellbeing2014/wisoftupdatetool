package wisoft.pack.models;

import java.io.File;
import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.ui.IEditorInput;

import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;

public class PackInfoModel extends Model {
	
	protected PackInfoOfOverview overview = new PackInfoOfOverview(this);
	protected PackInfoOfSelectFiles selectFiles = new PackInfoOfSelectFiles(this);
	protected PackInfoOfEditConfs editConfs =new PackInfoOfEditConfs(this);
	protected PackInfoOfEditSql editSql = new PackInfoOfEditSql(this);
	
	private  IEditorInput editInput;
	private XmlOperator xmlo = new XmlOperator();
	
	public XmlOperator getXmlo() {
		return xmlo;
	}

	private String moduleName="";
	private String moduleCode="";
	private String version ="";
	private String savePath="";

	
	
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
	
	
	
	public void refresh() 
	{
//		Element modelname_el = xmlo.getRootElement().addElement("ModuleName");
//		modelname_el.setText(moduleName);
		
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
	
	public PackInfoModel(String name,String path)
	{
		this.name =name;
		this.addChild(overview);
		this.addChild(selectFiles);
		this.addChild(editConfs);
		this.addChild(editSql);
		setSavePath(path);
		readFromXML();
	}
	
	public PackInfoModel(String name)
	{
		this.name =name;
		//this.setSavePath(path);
		this.addChild(overview);
		this.addChild(selectFiles);
		this.addChild(editConfs);
		this.addChild(editSql);
//		setSavePath(path);
//		readFromXML();
	}
	
	public void saveIntoXML()
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		xmlo.setXmlfile(new File(savePath+"/"+UpdateInfo.FileName));
		xmlo.initXml("root");
		//Element root  = xmlo.getRootElement();
		if(moduleName!=null)
			xmlo.OnlyElementInRoot(UpdateInfo.ModuleName).setText(moduleName);
		if(moduleCode!=null)
			xmlo.OnlyElementInRoot(UpdateInfo.ModuleCode).setText(moduleCode);
		if(version!=null)
			xmlo.OnlyElementInRoot(UpdateInfo.Version).setText(version);
		
		Element scope =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_Back).setText("false");
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_DB).setText("false");
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_Front).setText("false");
		xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
		
		xmlo.save();
	}
	
	public void readFromXML()
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		xmlo.setXmlfile(new File(savePath+"/"+UpdateInfo.FileName));
		xmlo.initXml("root");
		try {
			Document document =xmlo.getDocument();
			//document = reader.read(xmlfile);
			Element root = document.getRootElement();// 得到根节点
			//String ModuleCode =null ;
			if(root.element("ModuleCode")!=null)
				moduleCode= root.element(UpdateInfo.ModuleCode).getText();
			//String ModuleName = null;
			if(root.element("ModuleName")!=null)
				moduleName=root.element(UpdateInfo.ModuleName).getText();
			//String version = null;
			if(root.element("Version")!=null)
				version =root.element(UpdateInfo.Version).getText();
			setModuleCode(moduleCode);
			setModuleName(moduleName);
			setVersion(version);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
