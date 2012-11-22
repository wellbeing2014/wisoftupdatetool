package wisoft.pack.edits;

import java.io.File;
import java.util.Date;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.FileModel;

public class MasterLabelProvider implements ILabelProvider {
	private boolean needtime = false;
	public MasterLabelProvider(boolean needtime)
	{
		this.needtime = needtime;
	}
	public Image getImage(Object element) {
		File file = ((FileModel) element).getFile();
	    if (file.isDirectory())
	     return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
	    else
	     return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
	}

	public String getText(Object element) {
		File file =((FileModel) element).getFile();
		String text = file.getName();
	    if (text.length() == 0) {
	     text = file.getPath();
	    }
	    if (needtime)
	    {
	    	text +=" "+(new Date(file.lastModified())).toLocaleString();
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
