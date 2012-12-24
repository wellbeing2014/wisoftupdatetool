package wisoft.pack.models;

import java.util.List;

import org.dom4j.Element;

import wisoft.pack.utils.UpdateInfo;
public class FileModel extends Model {

	private Element file;
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
}
