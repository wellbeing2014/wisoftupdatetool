package wisoft.pack.edits;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import wisoft.pack.models.FileModel;

public class FileModelContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		FileModel filemodel = (FileModel)inputElement;
		// TODO Auto-generated method stub
		//Element[] element = (Element[]) ().elements("UpdateFileList").toArray();
		
		return filemodel.getChildren().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return ((FileModel)parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		FileModel paelement = (FileModel)((FileModel)element).getParent();
		return paelement;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return  ((FileModel)element).getChildren().size()>0;
	}

}
