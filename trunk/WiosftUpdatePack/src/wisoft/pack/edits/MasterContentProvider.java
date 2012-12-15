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
		
//		if(this.isedit)
//		{
			System.out.println(((FileModel)element).getFile().toString());
//			return verifyConfXMl((FileModel)element);
//		}
//		else
			return ((FileModel)element).getChildren().toArray(new FileModel[0]);

	}
	
	/**
	 * 验证文件列表中是否有需要配置或删除的文件，（根据updateinfo.xml的配置）有则标记，没有则创建。
	 * @param files
	 * @return 最后返回FileModel[]
	 */
	private FileModel[] verifyConfXMl(FileModel file)
	{
		FileModel[] files = file.getChildren().toArray(new FileModel[0]);
		File curfile = file.getFile();       //当前文件
		String curfilepath = curfile.getPath().replace("\\", "/");  //当前文件路径
		String currealpath = curfilepath.replace(packPath+"/"+UpdateInfo.UpdateDirName, "");//当前相对路径
		String curParentPath= currealpath.replace(curfile.getName(), "");// 当前父路径
		//if(curParentPath.equals("/"))
		//	System.out.println(currealpath);
		
		//读取xml获取配置项数组
		Iterator confiles = xmlo.OnlyElementInRoot(UpdateInfo.FileConfs).elementIterator();
		//循环数组去与file匹配
		while(confiles.hasNext())
		{
			Element confile = (Element)confiles.next();
			String fullpath = confile.attributeValue(UpdateInfo.FileConf_attr_fullpath);
			String name = confile.attributeValue(UpdateInfo.FileConf_attr_name);
			String confpackpath = confile.attributeValue(UpdateInfo.FileConf_attr_path);
			String oprtype = confile.attributeValue(UpdateInfo.FileConf_attr_opr);
			
			
			//判断是否存在全路径，如果存在则标记
			boolean ishave = false;
			for(FileModel file1:files)//先去检查子文件中，是否包含配置文件
			{
				//读取文件
				String realfilepath = file1.getFile().getPath().replace("\\", "/");
				String realpath = realfilepath.replace(packPath+"/"+UpdateInfo.UpdateDirName, "");
 				if(realpath.equals(fullpath))//直接匹配
				{
					if(oprtype.equals(UpdateInfo.Con_FileOpr_Del))// 是删除操作
						file1.setEdittype(EditType.DELETE);
					else 
						file1.setEdittype(EditType.UPDATE);//是修改操作
					ishave = true;
					break;
				}
			}
			if(!ishave)//不包含，看文件路径
			{
				if(currealpath.equals(confpackpath))//如果父路径相同说明，配置文件应该就在这个下面
				{
					
					FileModel newfile = null;
					newfile = new FileModel( new File(packPath+"/"+UpdateInfo.UpdateDirName+"/"+curParentPath+"/"+name));
					if(oprtype.equals(UpdateInfo.Con_FileOpr_Del))// 是删除操作
						newfile.setEdittype(EditType.DELETE);
					else 
						newfile.setEdittype(EditType.UPDATE);//是修改操作
					file.addChild(newfile);
				}
			}
		}
		return file.getChildren().toArray(new FileModel[0]);
	}
	
	public Object[] getElements(Object element) {
//		if(this.isedit)
//		{
			System.out.println(((FileModel)element).getFile().toString());
//			return verifyConfXMl((FileModel)element);
//		}
//		else
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
