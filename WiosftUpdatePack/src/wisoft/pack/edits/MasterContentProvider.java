package wisoft.pack.edits;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class MasterContentProvider implements ITreeContentProvider {
//	private boolean isedit;
//	private XmlOperator xmlo;
//	private String packPath;
//	private Iterator<Element> confiles ;
//	
	public MasterContentProvider()
	{
//		this.isedit = isedit;
//		packPath = Activator.getDefault().getCurrent_pack().getSavePath();
//		xmlo = Activator.getDefault().getCurrent_pack().getXmlo();
//		confiles =xmlo.OnlyElementInRoot(UpdateInfo.FileConfs).elementIterator();
//		for (int i =0 ;i<confi1.size();i++)
//		{
//			confiles.add(confi1.get(i));
//		}
	}

	public Object[] getChildren(Object element) {
		
		return ((File)element).listFiles();
//		if(this.isedit)
//			return verifyConfXMl((FileModel)element);
//		else
//			return ((FileModel)element).getChildren().toArray(new FileModel[0]);

	}
	
	
	
	public Object[] getElements(Object element) {
		return ((File)element).listFiles();
	}

	public boolean hasChildren(Object element) {
	   Object[] obj = getChildren(element);
	   return obj == null ? false : obj.length > 0;
	}

	public Object getParent(Object element) {
		if(((File) element).getParent()!=null)
			return new File(((File) element).getParent());
		else 
			return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
