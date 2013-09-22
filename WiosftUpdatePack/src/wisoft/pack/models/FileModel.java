package wisoft.pack.models;

import java.util.List;

import org.dom4j.Element;

import wisoft.pack.utils.UpdateInfo;
public class FileModel extends Model {

	private Element file;
	
	//name
	private String name;
	public String getName() {
		if(file==null)
			return name;
		return file.attributeValue(UpdateInfo.UpdateFile_filename);
	}
	public void setName(String name) {
		if(file==null)
			this.name = name;
		else
			file.addAttribute(UpdateInfo.UpdateFile_filename,name);
	}
	//isconf
	private boolean isconf;
	public boolean isConf() {
		if(file==null)
			return isconf;
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isconf));
	}
	public void setIsConf(boolean isconf) {
		if(file==null)
			this.isconf = isconf;
		else
			file.addAttribute(UpdateInfo.UpdateFile_isconf,Boolean.toString(isconf));
	}
	//isvirtual
	private boolean isVirtual;
	public boolean isVirtual() {
		if(file==null)
			return this.isVirtual;
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isVirtual));
	}
	public void setVirtual(boolean isVirtual) {
		if(file==null)
			this.isVirtual = isVirtual;
		else file.addAttribute(UpdateInfo.UpdateFile_isVirtual,Boolean.toString(isVirtual));
	}
	//conftype
	private String conftype;
	public String getConftype() {
		if(file==null)
			return conftype;
		return file.attributeValue(UpdateInfo.UpdateFile_conftype);
	}
	public void setConftype(String conftype) {
		if(file==null)
			this.conftype = conftype;
		else
			file.addAttribute(UpdateInfo.UpdateFile_conftype,conftype);
	}
	//content
	private String content;
	public String getContent() {
		if(file==null)
			return content;
		return file.elementText(UpdateInfo.UpdateFile_conftent);
	}
	public void setContent(String content) {
		if(file==null)
			this.content = content;
		else
		{
			Element element_content =file.element(UpdateInfo.UpdateFile_conftent);
			if(element_content!=null)
				file.remove(element_content);
			element_content = file.addElement(UpdateInfo.UpdateFile_conftent);
			element_content.addCDATA(content);
		}
	}
	//isdir
	private boolean isdir;
	public String getFileType() {
		return file.attributeValue(UpdateInfo.UpdateFile_filetype);
	}
	public void setFileType(String filetype) {
		file.addAttribute(UpdateInfo.UpdateFile_filetype,filetype);
	}
	public void setDir(boolean isdir)
	{
		if(file==null)
			this.isdir = isdir;
		else
			setFileType(isdir?UpdateInfo.FileType_Dir:UpdateInfo.FileType_File);
	}
	public boolean isDir()
	{
		if(file==null)
			return isdir;
		return UpdateInfo.FileType_Dir.equals(getFileType());
	}
	//fullpath
	private String fullpath;
	public String getFullPath() {
		if(file==null)
			return fullpath;
		return file.attributeValue(UpdateInfo.UpdateFile_fullpath);
	}
	public void setFullPath(String fullpath) {
		if(file==null)
			this.fullpath =fullpath;
		else
			file.addAttribute(UpdateInfo.UpdateFile_fullpath,fullpath);
	}
	
	public FileModel(Element file)
	{
		if(file!=null)
		{
			this.file = file;
			List<Element> filelist = file.elements(UpdateInfo.UpdateFile);
			for(Element zfile :filelist)
			{
				FileModel zfilemodel = new FileModel(zfile);
				if(!this.children.contains(zfilemodel))
					addChild(new FileModel(zfile));
			}
		}
	}
	
	public Element getFile() {
		return file;
	}

	public void setFile(Element file) {
		this.file = file;
		this.setDir(isdir);
		this.setContent(content);
		this.setIsConf(isconf);
		this.setName(name);
		this.setFullPath(fullpath);
		this.setVirtual(isVirtual);
	}

	@Override
	public List<Model> getChildren() {
		// TODO Auto-generated method stub
		
		return super.getChildren();
	}
	
	public FileModel isContain(String filename)
	{
		List<Model> zfilelist = getChildren();
		for(Model zfile :zfilelist)
		{
			if(((FileModel)zfile).getName().equals(filename))
				return (FileModel)zfile;
		}
		return null;
	}
	 
	@Override
	public void removeChild(Model model)
	{
		this.getFile().remove(((FileModel)model).getFile());
		super.removeChild(model);
		
	}
	
	@Override
	public void addChild(Model model)
	{
		FileModel child = (FileModel)model;
		Element childfile = child.getFile();
		if(childfile==null)
		{
			childfile = this.getFile().addElement(UpdateInfo.UpdateFile);
			child.setFile(childfile);
		}
		super.addChild(model);
		
	}
	
	public void remove()
	{
		FileModel parent = (FileModel)this.getParent();
		parent.removeChild(this);
	}
	
	/** 获取子文件数据量
	 * @return
	 */
	public int countFiles()
	{
		int num = this.getChildren().size();
		for(Model zfile:this.getChildren())
		{
			num+=((FileModel)zfile).countFiles();
		}
		return num;
	}
}
