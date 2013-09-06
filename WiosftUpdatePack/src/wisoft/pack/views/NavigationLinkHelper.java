package wisoft.pack.views;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.ILinkHelper;

import wisoft.pack.models.PackInfoModel;

public class NavigationLinkHelper implements ILinkHelper {

	@Override
	public IStructuredSelection findSelection(IEditorInput anInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateEditor(IWorkbenchPage aPage,
			IStructuredSelection aSelection) {
		// TODO Auto-generated method stub
		if(aSelection == null|| aSelection.isEmpty())
			return;
		if(aSelection.getFirstElement() instanceof PackInfoModel ){
			IEditorInput pim = ((PackInfoModel)aSelection.getFirstElement()).getEditInput();
			IEditorPart editor = null;
			if((editor = aPage.findEditor(pim))!=null)
				aPage.bringToTop(editor);
		}
			
	}

}
