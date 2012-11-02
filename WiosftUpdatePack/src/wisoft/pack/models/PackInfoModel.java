package wisoft.pack.models;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.ui.IEditorInput;

import wisoft.pack.utils.XmlOperator;

public class PackInfoModel extends Model {
	
	protected PackInfoOfOverview overview = new PackInfoOfOverview(this);
	protected PackInfoOfSelectFiles selectFiles = new PackInfoOfSelectFiles(this);
	protected PackInfoOfEditConfs editConfs =new PackInfoOfEditConfs(this);
	protected PackInfoOfEditSql editSql = new PackInfoOfEditSql(this);
	
	public boolean isdirty = false;
	private  IEditorInput editInput;
	private XmlOperator xmlo = new XmlOperator();
	
	private String moduleName;
	private String moduleCode;
	private String version ;
	private String savePath;
	private String CreateMan;
	private String releaseNote;
	private String keyWord;
	
	//private File updateinfoxml;
	
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
	public String getCreateMan() {
		return CreateMan;
	}
	public void setCreateMan(String createMan) {
		CreateMan = createMan;
	}
	public String getReleaseNote() {
		return releaseNote;
	}
	public void setReleaseNote(String releaseNote) {
		this.releaseNote = releaseNote;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	
	public void updateXml() 
	{
		Element modelname_el = xmlo.getRootElement().addElement("ModuleName");
		modelname_el.setText(moduleName);
		
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
	
	public void readFromXML()
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		xmlo.setXmlfile(new File(savePath+"/updateinfo.xml"));
		
		try {
			Document document =xmlo.getDocument();
			//document = reader.read(xmlfile);
			Element root = document.getRootElement();// 得到根节点
			
			String ModuleCode =null ;
			if(root.element("ModuleCode")!=null)
				ModuleCode= root.element("ModuleCode").getText();
			String ModuleName = null;
			if(root.element("ModuleName")!=null)
				ModuleName=root.element("ModuleName").getText();
			String version = null;
			if(root.element("Version")!=null)
				version =root.element("Version").getText();
			String createMan = null;
			if(root.element("CreateMan")!=null)
				createMan =root.element("CreateMan").getText();
			String keyword = null;
			if(root.element("KeyWord")!=null)
				keyword =root.element("KeyWord").getText();
			String releasenot = null;
			if(root.element("Releasenote")!=null)
				releasenot =root.element("Releasenote").getText();
			
			setModuleCode(ModuleCode);
			setModuleName(ModuleName);
			setVersion(version);
			setCreateMan(createMan);
			setKeyWord(keyword);
			setReleaseNote(releasenot);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
