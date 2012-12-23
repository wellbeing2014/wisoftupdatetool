package wisoft.pack.edits;

import org.dom4j.Element;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.FileModel;
import wisoft.pack.utils.UpdateInfo;

public class FileModelLabelProvider extends  DecoratingStyledCellLabelProvider   implements ILabelProvider {

	public FileModelLabelProvider(IStyledLabelProvider labelProvider) {
		super(labelProvider, PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator(), null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		return getStyledText(element).getString();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return super.getImage(element);
		
	}
	

}
