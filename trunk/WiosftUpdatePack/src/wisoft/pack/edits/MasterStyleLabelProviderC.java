package wisoft.pack.edits;

import java.io.File;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.wb.swt.SWTResourceManager;

public class MasterStyleLabelProviderC extends LabelProvider implements IStyledLabelProvider{
	private final Styler fBoldStyler; 
	public MasterStyleLabelProviderC()
	{
		fBoldStyler= new Styler() {
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.foreground=new Color(null,128,128,128);
				
				textStyle.font = SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.ITALIC);
			}
		};
	}
	@Override
	public StyledString getStyledText(Object element) {
		// TODO Auto-generated method stub
		File file =((File) element);
		String text = file.getName();
	    if (text.length() == 0) {
	     text = file.getPath();
	    }
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	   String lasttime =format.format(file.lastModified()) ;
	   StyledString str= new StyledString(text, null);  
	   
        str.append(" "+lasttime, fBoldStyler);  
        return str;
	}

}
