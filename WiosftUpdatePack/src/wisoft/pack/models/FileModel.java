package wisoft.pack.models;

import java.util.List;

import org.dom4j.Element;

import wisoft.pack.utils.UpdateInfo;
public class FileModel extends Model {

	private Element file;
	
	//name
	public String getName() {
		return file.attributeValue(UpdateInfo.UpdateFile_filename);
	}
	public void setName(String name) {
		file.addAttribute(UpdateInfo.UpdateFile_filename,name);
	}
	//isconf
	public boolean isConf() {
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isconf));
	}
	public void setIsConf(boolean isconf) {
		file.addAttribute(UpdateInfo.UpdateFile_isconf,Boolean.toString(isconf));
	}
	//isvirtual
	public boolean isVirtual() {
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isVirtual));
	}
	public void setVirtual(boolean isVirtual) {
		file.addAttribute(UpdateInfo.UpdateFile_isVirtual,Boolean.toString(isVirtual));
	}
	//conftype
	public String getConftype() {
		return file.attributeValue(UpdateInfo.UpdateFile_conftype);
	}
	public void setConftype(String conftype) {
		file.addAttribute(UpdateInfo.UpdateFile_isVirtual,conftype);
	}
	//content
	public String getContent() {
		return file.elementText(UpdateInfo.UpdateFile_conftent);
	}
	public void setContent(String conftype) {
		Element element_content =file.element(UpdateInfo.UpdateFile_conftent);
			if(element_content!=null)
				file.remove(element_content);
			element_content = file.addElement(UpdateInfo.UpdateFile_conftent);
			element_content.addCDATA(conftype);
	}
	//isdir
	public String getFileType() {
		return file.attributeValue(UpdateInfo.UpdateFile_filetype);
	}
	public void setFileType(String filetype) {
		file.addAttribute(UpdateInfo.UpdateFile_filetype,filetype);
	}
	public boolean isDir()
	{
		return UpdateInfo.FileType_Dir.equals(getFileType());
	}
	//fullpath
	public String getFullPath() {
		String fullpath=file.attributeValue(UpdateInfo.UpdateFile_fullpath);
		return fullpath;
	}
	public void setFullPath(String fullpath) {
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
	}

	@Override
	public List<Model> getChildren() {
		// TODO Auto-generated method stub
		
		return super.getChildren();
	}
	
	public FileModel isContain(String filename)
	{
		for(Model zfile :getChildren())
		{
			if(((FileModel)zfile).getName().equals(filename))
				return (FileModel)zfile;
		}
		return null;
	}
}
