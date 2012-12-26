package demo.treeviewer;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import wisoft.pack.utils.UpdateInfo;

public class FileModel implements ITreeEntry {
	private Element file;
	private List<FileModel> child;
	public List<FileModel> getChild() {
		return child;
	}
	public void setChild( List<FileModel> child) {
		this.child = child;
	}
	public FileModel(Element file)
	{
		if(file!=null)
		{
			this.file = file;
			List<Element> filelist = file.elements(UpdateInfo.UpdateFile);
			List<FileModel> zfilelist = new ArrayList<FileModel>();
			for(Element zfile :filelist)
			{
				FileModel zfilemodel = new FileModel(zfile);
				if(!this.child.contains(zfilemodel))
					zfilelist.add(new FileModel(zfile));
			}
			setChild(zfilelist);
		}
	}
	public Element getFile() {
		return file;
	}
	public void setFile(Element file) {
		this.file = file;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChildren(List children) {
		// TODO Auto-generated method stub
		List<Element> filelist = file.elements(UpdateInfo.UpdateFile);
		for(Element zfile :filelist)
		{
			FileModel zfilemodel = new FileModel(zfile);
			
		}
	}

	@Override
	public List getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
