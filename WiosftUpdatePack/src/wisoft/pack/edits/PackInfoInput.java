package wisoft.pack.edits;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import wisoft.pack.models.PackInfoModel;

public class PackInfoInput implements IEditorInput {
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

}
