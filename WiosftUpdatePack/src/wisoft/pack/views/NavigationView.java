package wisoft.pack.views;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import wisoft.pack.models.Model;
import wisoft.pack.models.PackInfoContentProvider;
import wisoft.pack.models.PackInfoLabelProvider;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.RootModel;

public class NavigationView extends ViewPart {
	public static final String ID = "WiosftUpdatePack.navigationView";
	private TreeViewer viewer;
	public RootModel root;
	

    /**
     * We will set up a dummy model to initialize tree heararchy. In real
     * code, you will connect to a real model and expose its hierarchy.
     */
    private Model createDummyModel() {
         root =new RootModel();
         //root.addPackInfo(new PackInfoModel("cefsifhias"));
        return root;
    }

	/**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		viewer.setContentProvider(new PackInfoContentProvider());
		viewer.setLabelProvider(new PackInfoLabelProvider());
		viewer.setInput(createDummyModel());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public void addPackInfo(PackInfoModel pack)
	{
		this.root.addPackInfo(pack);
		this.viewer.refresh();
	}
	
	public void removePackInfo(PackInfoModel[] packlist)
	{
		for(int i=0;i<packlist.length;i++)
		{
			this.root.removePackInfo(packlist[i]);
		}
		this.viewer.refresh();
	}
	
	public Model[] getSelectPackInfo()
	{
		 IStructuredSelection selection = (IStructuredSelection)this.viewer.getSelection();
		 List<Model> selPack =selection.toList();
		 return selPack.toArray(new Model[0]);
	}
	
	private void hookDoubleClickAction()
	{
		this.viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
				//(PackInfoModel)selection.getFirstElement();
				 
			}
		});
	}
}