package wisoft.pack.edits;

import java.io.File;

import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class MasterLabelProvider extends  DecoratingStyledCellLabelProvider   implements ILabelProvider {
	
	private ILabelProvider provider;  
	public MasterLabelProvider(MasterStyleLabelProviderC provider) {
		super(provider, PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator(), null);
		// TODO Auto-generated constructor stub
		this.provider = provider;  
	}

	public Image getImage(Object element) {
		File file =(File)element;
		if (file.isDirectory())
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	    else
	    	return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);

		 
	}

	public String getText(Object element) {
		
	//    return ((File)element).getName();
	    return getStyledText(element).getString();  
	}

	public void addListener(ILabelProviderListener listener) {
	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object element, String property) {
	    return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}
	
}
