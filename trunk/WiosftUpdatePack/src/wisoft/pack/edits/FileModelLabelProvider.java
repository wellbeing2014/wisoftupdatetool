package wisoft.pack.edits;

import org.dom4j.Element;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
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
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.models.FileModel;
import wisoft.pack.utils.UpdateInfo;

public class FileModelLabelProvider extends  DecoratingStyledCellLabelProvider   implements ILabelProvider {

	public FileModelLabelProvider() {
		super(new IStyledLabelProvider(){

			@Override
			public void removeListener(ILabelProviderListener listener) {
			}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public void addListener(ILabelProviderListener listener) {
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
						str2.append("(本文件仅配置)",new Styler(){
							@Override
							public void applyStyles(TextStyle textStyle) {
								textStyle.foreground=new Color(null,128,128,128);
								textStyle.font = SWTResourceManager.getFont("微软雅黑", 10, SWT.ITALIC);
							}});
						}
						if(conftype.equals(UpdateInfo.FileOpr_Del))
						{
							Styler red =new Styler(){
								@Override
								public void applyStyles(TextStyle textStyle) {
									textStyle.foreground=new Color(null,128,0,0);
									textStyle.font = SWTResourceManager.getFont("微软雅黑", 10, SWT.ITALIC);
								}};
							str2= new StyledString(str,red);
							str2.append("(本文件需要删除)");
						}
						return str2;
					}
					else
					{
						str1.append("(需要配置)",new Styler(){
							@Override
							public void applyStyles(TextStyle textStyle) {
								textStyle.foreground=new Color(null,128,128,128);
								textStyle.font = SWTResourceManager.getFont("微软雅黑", 10, SWT.ITALIC);
							}});
					}
				}
						
				
				return str1;
			}
			
			@Override
			public Image getImage(Object element) {
				FileModel filemodel = (FileModel)element;
				Element file = filemodel.getFile();
				if(file.attributeValue(UpdateInfo.UpdateFile_filetype).equals(UpdateInfo.FileType_Dir))
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
				else
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			}
			
		}, PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator(), null);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getText(Object element) {
		// TODO Auto-generated method stub
		//return getStyledText(element).getString();
		return ((FileModel)element).getName();
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
