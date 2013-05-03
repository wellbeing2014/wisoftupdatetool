package wisoft.pack.edits;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.UpdateInfo;

public class XmlSqlEditorInput implements IPathEditorInput {

	public static final String TYPE_SQL = UpdateInfo.SqlFileName;
	public static final String TYPE_XML = UpdateInfo.FileName;
	
	private String type ;
	private PackInfoModel packinfo;
	public XmlSqlEditorInput(String type,PackInfoModel packinfo)
	{
		this.type = type;
		this.packinfo  = packinfo;
	}
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPath getPath() {
		// TODO Auto-generated method stub
		if(type.equals(XmlSqlEditorInput.TYPE_XML))
			return new Path(this.packinfo.getSavePath()+"/"+UpdateInfo.FileName);
		if(type.equals(XmlSqlEditorInput.TYPE_SQL))
			return new Path(this.packinfo.getSavePath()+"/"+UpdateInfo.SqlFileName);
		return null;
	}

}
