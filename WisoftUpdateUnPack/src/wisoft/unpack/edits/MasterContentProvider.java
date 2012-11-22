package wisoft.unpack.edits;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import wisoft.unpack.utils.XmlOperator;


public class MasterContentProvider implements ITreeContentProvider {
	
//	private boolean isedit;
//	private XmlOperator xmlo;
	
	

	public Object[] getChildren(Object element) {
	   return ((File) element).listFiles();
	}

	public Object[] getElements(Object element) {
		File[] files =((File) element).listFiles();
	   return files;
	}

	public boolean hasChildren(Object element) {
	   Object[] obj = getChildren(element);
	   return obj == null ? false : obj.length > 0;
	}

	public Object getParent(Object element) {
	   return ((File) element).getParentFile();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
