package wisoft.pack.models;

import java.util.List;

import org.dom4j.Element;
public class FileModel extends Model {
	//public static enum EditType { NORMAL,UPDATE,DELETE };
//	private File file;
//	public File getFile() {
//		return file;
//	}
//	public void setFile(File file) {
//		this.file = file;
//	}
//	
//	private EditType edittype =EditType.NORMAL;
//	public EditType getEdittype() {
//		return edittype;
//	}
//	public void setEdittype(EditType edittype) {
//		this.edittype = edittype;
//	}
//	
	private Element file;
	public FileModel(Element file)
	{
		if(file!=null)
		{
			this.file = file;
			List<Element> filelist = file.elements();
			for(Element zfile :filelist)
			{
				FileModel zfilemodel = new FileModel(zfile);
				if(!this.children.contains(zfilemodel))
					addChild(new FileModel(zfile));
			}
		}
	}
//	public FileModel(File file)
//	{
//		this.file = file;
//		File[] files =file.listFiles();
//		if(files!=null)
//		{
//			for(File zfile:files)
//			{
//				FileModel zfilemodel = new FileModel(zfile);
//				if(!this.children.contains(zfilemodel))
//				{
//					addChild(new FileModel(zfile));
//				}
//			}
//		}
//	}
	
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
