package wisoft.unpack.edits;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;

import wisoft.unpack.models.PackInfoModel;
import wisoft.unpack.models.PackRelyModel;
import wisoft.unpack.utils.UpdateInfo;
import wisoft.unpack.utils.XmlOperator;

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

	public PackRelyModel[] getPackRelyData()
	{
		XmlOperator xmlo = new XmlOperator(getPath().toString());
		//Element root =xmlo.getDocument().selectNodes("/root/PackRely");
		ArrayList<PackRelyModel> relyr = new ArrayList<PackRelyModel>();
		List el = xmlo.getRootElement().elements(UpdateInfo.PackRelys);
		Element el1 = null;
		if(el!=null&&el.size()>0)
		{
			el1 = (Element)(el.get(0));
			List<Element> relys = el1.elements();
			for(int i = 0;i<relys.size();i++)
			{
				PackRelyModel rm = new PackRelyModel();
				rm.setName(relys.get(i).attributeValue(UpdateInfo.PackRely_attr_name));
				rm.setCode(relys.get(i).attributeValue(UpdateInfo.PackRely_attr_code));
				rm.setVersion(relys.get(i).attributeValue(UpdateInfo.PackRely_attr_ver));
				relyr.add(rm);
			}
		}
		return relyr.toArray(new PackRelyModel[0]);
	}
}
