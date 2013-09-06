package wisoft.pack.models;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PackInfoContentProvider extends ObservableListTreeContentProvider implements ITreeContentProvider {

	
	public PackInfoContentProvider() {
		// TODO Auto-generated constructor stub
		super(getObservableListFactory(), getTreeStructureAdvisor());
	}
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
		// TODO Auto-generated method stub
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		
		return ((Model)parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return ((Model)element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return getChildren(element).length > 0;
	}
	
	private static IObservableFactory getObservableListFactory() {
		return new IObservableFactory() {
			public IObservable createObservable(Object parent) {
				if (parent instanceof PackFolderModel) {
					return BeanProperties.list("packfloderchildren").observe(parent);
				}
				return null;
			}
		};
	}

	// The following is optional, you can pass null as the advisor, but then
	// setSelection() will not find elements that have not been expanded.
	private static TreeStructureAdvisor getTreeStructureAdvisor() {
		return new TreeStructureAdvisor() {
			public Object getParent(Object element) {
				
				return super.getParent(element);
			}
		};
	}
}
