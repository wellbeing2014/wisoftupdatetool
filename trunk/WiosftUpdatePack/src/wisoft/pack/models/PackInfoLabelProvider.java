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
			return "更新包概览";
		else if(element instanceof PackInfoOfSelectFiles)
			return "添加更新文件";
		else if(element instanceof PackInfoOfEditConfs)
			return "编辑配置文件";
		else if(element instanceof PackInfoOfEditSql)
			return "编辑数据脚本";
		else
			return null;
			
	}
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
	}
}
