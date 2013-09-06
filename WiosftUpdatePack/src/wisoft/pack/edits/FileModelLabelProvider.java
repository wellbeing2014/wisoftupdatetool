package wisoft.pack.edits;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.FileModel;
import wisoft.pack.sourceprovider.ResourceManager;
import wisoft.pack.sourceprovider.SWTResourceManager;
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
	private static ImageData watermark(ImageData srcData1, ImageData srcData2, double alpha) {  
	    if(srcData1.width != srcData2.width || srcData1.height !=   
	        srcData2.height || srcData1.bytesPerLine != srcData2.bytesPerLine)  
	        //未考虑不同大小图片的叠加  
	        return null;  
	    int bytesPerPixe = srcData1.bytesPerLine / srcData1.width;  
	    int destBytesPerLine = srcData1.width * bytesPerPixe;  
	    byte[] newData = new byte[srcData1.data.length];  
	  
	    ImageData newImageData = new ImageData(srcData1.width, srcData1.height, srcData1.depth,  
	            srcData1.palette, destBytesPerLine, newData);  
	    for (int srcY = 0; srcY < srcData1.height; srcY++) {  
	        for (int srcX = 0; srcX < srcData1.bytesPerLine; srcX++) {  
	            int idx = srcY * srcData1.bytesPerLine + srcX;  
	            newImageData.data[idx] = (byte)(alpha * srcData1.data[idx]  +   
	                    (1- alpha) * srcData2.data[idx]);  
	        }  
	    }  
	    return newImageData;  
	}
	@Override
	public StyledString getStyledText(Object element) {
		FileModel file = (FileModel)element;
		String str =file.getName();
		boolean isconf =file.isConf();
		boolean isVirtual=file.isVirtual();
		String conftype = file.getConftype();
		
		StyledString str1= new StyledString(str, null);
		Styler styler = new Styler(){
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground=new Color(null,128,128,128);
				textStyle.font = SWTResourceManager.getFont("微软雅黑", 10, SWT.ITALIC);
			}
			};
		Styler styler_red = new Styler(){
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground=new Color(null,128,128,128);
				textStyle.font = SWTResourceManager.getFont("微软雅黑", 10, SWT.ITALIC);
			}
			};
		if(isconf)
		{
			if(isVirtual)
			{
				StyledString str2= new StyledString(str, StyledString.COUNTER_STYLER);
				if(conftype!=null&&conftype.equals(UpdateInfo.FileOpr_Mod))
				{
					str2.append("(本文件仅配置)",styler);
				}
				if(conftype!=null&&conftype.equals(UpdateInfo.FileOpr_Del))
				{
					str2= new StyledString(str,styler_red);
					str2.append("(本文件需要删除)");
				}
				return str2;
			}
			else
			{
				str1.append("(需要配置)",styler);
			}
		}
		return str1;
	}

	@Override
	public String getText(Object element) {
		return getStyledText(element).getString();
	}
	
}
