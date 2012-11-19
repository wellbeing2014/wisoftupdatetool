package wisoft.pack.edits;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class MasterContentProvider implements ITreeContentProvider {

	 public Object[] getChildren(Object element) {
	    return ((File) element).listFiles();
	 }

	 public Object[] getElements(Object element) {
	    return ((File) element).listFiles();
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
