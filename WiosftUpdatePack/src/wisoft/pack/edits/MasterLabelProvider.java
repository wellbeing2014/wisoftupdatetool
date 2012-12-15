package wisoft.pack.edits;

import java.io.File;
import java.util.Date;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.FileModel;
import wisoft.pack.models.FileModel.EditType;

public class MasterLabelProvider implements ILabelProvider,IColorProvider {
	private boolean needtime = false;
	public MasterLabelProvider(boolean needtime)
	{
		this.needtime = needtime;
	}
	public Image getImage(Object element) {
		FileModel filemodel= (FileModel) element;
		File file = filemodel.getFile();
		switch(filemodel.getEdittype())
		{
			case DELETE:
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_DELETE);
			case UPDATE:
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_NEW_WIZARD);
//			case NORMAL:
//				if (file.isDirectory())
//					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
//			    else
//			    	return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			default:
				if (file.isDirectory())
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
			    else
			    	return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		}
		 
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
	@Override
	public Color getForeground(Object element) {
		// TODO Auto-generated method stub
		 if(((FileModel) element).getEdittype().equals(EditType.DELETE))
		 	return new Color(null,128,128,128);
		return null;
	}
	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}
