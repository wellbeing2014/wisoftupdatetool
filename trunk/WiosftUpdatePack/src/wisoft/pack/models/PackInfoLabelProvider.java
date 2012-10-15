package wisoft.pack.models;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class PackInfoLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		//return super.getText(element);
		if(element instanceof PackInfo)
			return ((Model)element).getName();
		else if(element instanceof PackInfoOfOverview)
			return "���°�����";
		else if(element instanceof PackInfoOfSelectFiles)
			return "��Ӹ����ļ�";
		else if(element instanceof PackInfoOfEditConfs)
			return "�༭�����ļ�";
		else if(element instanceof PackInfoOfEditSql)
			return "�༭���ݽű�";
		else
			return null;
			
	}
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}
}
