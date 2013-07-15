package wisoft.pack.models;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.IDescriptionProvider;

import wisoft.pack.app.Activator;

public class PackInfoLabelProvider extends LabelProvider implements ILabelProvider,
IDescriptionProvider {
	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		//return super.getText(element);
		return ((Model)element).getName();
		
			
	}
	
	
	
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof PackFolderModel)
			return Activator.getImageDescriptor("/icons/open.png").createImage();
		else if(element instanceof PackInfoModel)
			return Activator.getImageDescriptor("/icons/wi_updatetool.ico").createImage();
		else
			return super.getImage(element);
		
	}



	@Override
	public String getDescription(Object anElement) {
		// TODO Auto-generated method stub
		return ((Model)anElement).getName();
	}
}
