package wisoft.pack.edits;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.models.FileModel;
import wisoft.pack.utils.UpdateInfo;

public class FileModelLabelProvider      implements IStyledLabelProvider,ILabelProvider {
	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		FileModel file = (FileModel)element;
		if(file.isDir())
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		else
		{
			String[] fileext =file.getName().split("\\.");
			String fileextend = fileext[fileext.length-1];
			if(fileextend.isEmpty())
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			if("xml".equals(fileextend.toLowerCase()))
				return ResourceManager.getPluginImage("WiosftUpdatePack", "icons/xml.png");
			if("swf".equals(fileextend.toLowerCase()))
				return ResourceManager.getPluginImage("WiosftUpdatePack", "icons/swf.png");
			if("jar".equals(fileextend.toLowerCase()))
				return ResourceManager.getPluginImage("WiosftUpdatePack", "icons/jar.png");
			if("png".equals(fileextend.toLowerCase())||"ico".equals(fileextend.toLowerCase())||
					"gif".equals(fileextend.toLowerCase()))
				return ResourceManager.getPluginImage("WiosftUpdatePack", "icons/image.png");
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		}
		
	}

	@Override
	public StyledString getStyledText(Object element) {
		FileModel file = (FileModel)element;
		String str =file.getName();
		boolean isconf =file.isConf();
		boolean isVirtual=file.isVirtual();
		String conftype = file.getConftype();
		
		StyledString str1= new StyledString(str, null);
		if(isconf)
		{
			if(isVirtual)
			{
				StyledString str2= new StyledString(str, StyledString.COUNTER_STYLER);
				if(conftype.equals(UpdateInfo.FileOpr_Mod))
				{
				str2.append("(���ļ�������)",new Styler(){
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.foreground=new Color(null,128,128,128);
						textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
					}});
				}
				if(conftype.equals(UpdateInfo.FileOpr_Del))
				{
					Styler red =new Styler(){
						@Override
						public void applyStyles(TextStyle textStyle) {
							textStyle.foreground=new Color(null,128,0,0);
							textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
						}};
					str2= new StyledString(str,red);
					str2.append("(���ļ���Ҫɾ��)");
				}
				return str2;
			}
			else
			{
				str1.append("(��Ҫ����)",new Styler(){
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.foreground=new Color(null,128,128,128);
						textStyle.font = SWTResourceManager.getFont("΢���ź�", 10, SWT.ITALIC);
					}});
			}
		}
		return str1;
	}

	@Override
	public String getText(Object element) {
		return getStyledText(element).getString();
	}
	
}
