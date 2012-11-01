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
	
	private File updateinfoxml;
	
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
	
	public File init()throws Exception
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		xmlo.loadXml(savePath+"/updateinfo.xml");
		xmlo.addRootElement("root");
		updateXml();
		xmlo.writeToFile();
		xmlo.close();
		//updateinfoxml = dir;
		return dir;
	}
	
	public void updateXml() 
	{
		xmlo.AddOnlyNode("/root/ModuleName", moduleName);
		xmlo.AddOnlyNode("/root/ModuleCode", moduleCode);
		xmlo.AddOnlyNode("/root/Version", version);
		xmlo.AddOnlyNode("/root/CreateMan", CreateMan);
		xmlo.setCdataValue("/root/ReleaseNote", releaseNote);
		xmlo.AddOnlyNode("/root/KeyWord", keyWord);
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
	
//	public PackInfoModel(String name)
//	{
//		this.name =name;
//		this.addChild(overview);
//		this.addChild(selectFiles);
//		this.addChild(editConfs);
//		this.addChild(editSql);
//		try {
//			updateinfoxml =init();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public PackInfoModel(String name,String path)
	{
		//PackInfoModel(name);
		this.name =name;
		//this.setSavePath(path);
		this.addChild(overview);
		this.addChild(selectFiles);
		this.addChild(editConfs);
		this.addChild(editSql);
		setSavePath(path);
		readFromXML(path);
	}
	
	public void readFromXML(String path)
	{
		File xmlfile = new File(path+"/updateinfo.xml");
		if(xmlfile.exists())
		{
			this.updateinfoxml = xmlfile;
			SAXReader reader = new SAXReader();
			Document document;
			try {
				document = reader.read(xmlfile);
				Element root = document.getRootElement();// 得到根节点
				
				String ModuleCode = root.element("ModuleCode").getText();
				String ModuleName = root.element("ModuleName").getText();
				String version = root.element("Version").getText();
				String createMan = root.element("CreateMan").getText();
				String keyword = root.element("KeyWord").getText();
				String releasenot = root.element("Releasenote").getText();
				
				setModuleCode(ModuleCode);
				setModuleName(ModuleName);
				setVersion(version);
				setCreateMan(createMan);
				setKeyWord(keyword);
				setReleaseNote(releasenot);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				updateinfoxml =init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
