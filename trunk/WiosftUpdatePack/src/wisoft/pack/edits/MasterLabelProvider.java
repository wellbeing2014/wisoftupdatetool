package wisoft.pack.edits;

import java.io.File;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class MasterLabelProvider implements ILabelProvider {

	public Image getImage(Object element) {
		File file = (File) element;
	    if (file.isDirectory())
	     return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	    else
	     return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
	}

	public String getText(Object element) {
		String text = ((File) element).getName();
	    if (text.length() == 0) {
	     text = ((File) element).getPath();
	    }
	    return text;
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
