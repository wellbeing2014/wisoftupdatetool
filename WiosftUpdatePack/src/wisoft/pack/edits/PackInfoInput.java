package wisoft.pack.edits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.PackRelyModel;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;

public class PackInfoInput implements IPathEditorInput {
	//private IPath fPath;
	private PackInfoModel packinfo;
	public PackInfoModel getPackinfo() {
		return packinfo;
	}
	public PackInfoInput(PackInfoModel name)
	{
		this.packinfo = name;
	}
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return new File(packinfo.getSavePath()+"/updateinfo.xml").exists();
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.packinfo.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return getName();
	}
	@Override
	public IPath getPath() {
		// TODO Auto-generated method stub
		return new Path(this.packinfo.getSavePath()+"/updateinfo.xml");
	}
	
	@Override
    public int hashCode() {
		return this.packinfo.getSavePath().hashCode();
	}

	
}
