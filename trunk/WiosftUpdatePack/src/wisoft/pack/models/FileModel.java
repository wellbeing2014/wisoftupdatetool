package wisoft.pack.models;

import java.util.List;

import org.dom4j.Element;

import wisoft.pack.utils.UpdateInfo;
public class FileModel extends Model {

	private Element file;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConf() {
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isconf));
	}

	public void setIsConf(boolean isconf) {
		file.addAttribute(UpdateInfo.UpdateFile_isconf,Boolean.toString(isconf));
	}

	public boolean isVirtual() {
		return Boolean.valueOf(file.attributeValue(UpdateInfo.UpdateFile_isVirtual));
	}

	public void setVirtual(boolean isVirtual) {
		file.addAttribute(UpdateInfo.UpdateFile_isVirtual,Boolean.toString(isVirtual));
	}

	public String getConftype() {
		return file.attributeValue(UpdateInfo.UpdateFile_conftype);
	}

	public void setConftype(String conftype) {
		file.addAttribute(UpdateInfo.UpdateFile_isVirtual,conftype);
	}
	
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
	
	public FileModel(Element file)
	{
		if(file!=null)
		{
			this.file = file;
			this.setName(file.attributeValue(UpdateInfo.UpdateFile_filename));
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
}
