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
	 * ��֤�ļ��б����Ƿ�����Ҫ���û�ɾ�����ļ���������updateinfo.xml�����ã������ǣ�û���򴴽���
	 * @param files
	 * @return ��󷵻�FileModel[]
	 */
	private FileModel[] verifyConfXMl(FileModel file)
	{
		FileModel[] files = file.getChildren().toArray(new FileModel[0]);
		File curfile = file.getFile();       //��ǰ�ļ�
		String curfilepath = curfile.getPath().replace("\\", "/");  //��ǰ�ļ�·��
		String currealpath = curfilepath.replace(packPath+"/"+UpdateInfo.UpdateDirName, "");//��ǰ���·��
		String curParentPath= currealpath.replace(curfile.getName(), "");// ��ǰ��·��
		//if(curParentPath.equals("/"))
		//	System.out.println(currealpath);
		
		//��ȡxml��ȡ����������
		Iterator confiles = xmlo.OnlyElementInRoot(UpdateInfo.FileConfs).elementIterator();
		//ѭ������ȥ��fileƥ��
		while(confiles.hasNext())
		{
			Element confile = (Element)confiles.next();
			String fullpath = confile.attributeValue(UpdateInfo.FileConf_attr_fullpath);
			String name = confile.attributeValue(UpdateInfo.FileConf_attr_name);
			String confpackpath = confile.attributeValue(UpdateInfo.FileConf_attr_path);
			String oprtype = confile.attributeValue(UpdateInfo.FileConf_attr_opr);
			
			
			//�ж��Ƿ����ȫ·���������������
			boolean ishave = false;
			for(FileModel file1:files)//��ȥ������ļ��У��Ƿ���������ļ�
			{
				//��ȡ�ļ�
				String realfilepath = file1.getFile().getPath().replace("\\", "/");
				String realpath = realfilepath.replace(packPath+"/"+UpdateInfo.UpdateDirName, "");
 				if(realpath.equals(fullpath))//ֱ��ƥ��
				{
					if(oprtype.equals(UpdateInfo.Con_FileOpr_Del))// ��ɾ������
						file1.setEdittype(EditType.DELETE);
					else 
						file1.setEdittype(EditType.UPDATE);//���޸Ĳ���
					ishave = true;
					break;
				}
			}
			if(!ishave)//�����������ļ�·��
			{
				if(currealpath.equals(confpackpath))//�����·����ͬ˵���������ļ�Ӧ�þ����������
				{
					
					FileModel newfile = null;
					newfile = new FileModel( new File(packPath+"/"+UpdateInfo.UpdateDirName+"/"+curParentPath+"/"+name));
					if(oprtype.equals(UpdateInfo.Con_FileOpr_Del))// ��ɾ������
						newfile.setEdittype(EditType.DELETE);
					else 
						newfile.setEdittype(EditType.UPDATE);//���޸Ĳ���
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
