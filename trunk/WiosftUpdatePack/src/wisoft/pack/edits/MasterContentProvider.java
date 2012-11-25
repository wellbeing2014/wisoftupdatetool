package wisoft.pack.edits;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Element;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import wisoft.pack.app.Activator;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.FileModel.EditType;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;


public class MasterContentProvider implements ITreeContentProvider {
	private boolean isedit;
	private XmlOperator xmlo;
	private String packPath;
	
	public MasterContentProvider(boolean isedit)
	{
		this.isedit = isedit;
		packPath = Activator.getDefault().getCurrent_pack().getSavePath();
		xmlo = Activator.getDefault().getCurrent_pack().getXmlo();
	}

	public Object[] getChildren(Object element) {
		
		return ((FileModel) element).getChildren().toArray(new FileModel[0]);
	}
	
	/**
	 * 验证文件列表中是否有需要配置或删除的文件，（根据updateinfo.xml的配置）有则标记，没有则创建。
	 * @param files
	 * @return 最后返回FileModel[]
	 */
	private FileModel[] verifyConfXMl(FileModel file)
	{
		FileModel[] files = file.getChildren().toArray(new FileModel[0]);
		//读取xml获取配置项数组
		Iterator confiles = xmlo.OnlyElementInRoot("configFiles").elementIterator();
		//循环数组去与file匹配
		while(confiles.hasNext())
		{
			Element confile = (Element)confiles.next();
			String fullpath = confile.attributeValue("fullpath");
			String name = confile.attributeValue("name");
			String edittype = confile.attributeValue("edittype");
			String myconfpath = packPath+"/"+UpdateInfo.UpdateDirName+fullpath;
			//判断是否存在全路径，如果存在则标记
			boolean ishave = false;
			for(FileModel file1:files)
			{
				String havefilepath = file1.getFile().getPath().replace("\\", "/");
 				if(havefilepath.equals(myconfpath))
				{
					if(EditType.DELETE.toString().equals(edittype))
						file1.setEdittype(EditType.DELETE);
					else if(EditType.UPDATE.toString().equals(edittype))
						file1.setEdittype(EditType.UPDATE);
					ishave = true;
					break;
				}
			}
			if(!ishave)
			{
				String myconfpath1= packPath+"/"+UpdateInfo.UpdateDirName+fullpath.replace("/"+name, "");
				String filepath =file.getFile().getAbsolutePath().replace("\\", "/");
				if(myconfpath1.equals(filepath))
				{
					FileModel newfile = new FileModel( new File(myconfpath));
					if(EditType.DELETE.toString().equals(edittype))
						newfile.setEdittype(EditType.DELETE);
					else if(EditType.UPDATE.toString().equals(edittype))
						newfile.setEdittype(EditType.UPDATE);
					file.addChild(newfile);
				}
			}
		}
		return file.getChildren().toArray(new FileModel[0]);
	}
	
	public Object[] getElements(Object element) {
		if(this.isedit)
			return verifyConfXMl((FileModel)element);
		else
			return ((FileModel)element).getChildren().toArray(new FileModel[0]);
	}

	public boolean hasChildren(Object element) {
	   Object[] obj = getChildren(element);
	   return obj == null ? false : obj.length > 0;
	}

	public Object getParent(Object element) {
	   return ((FileModel) element).getParent();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
