package wisoft.pack.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;

import wisoft.pack.data.dao.NavigatorData;

public class PackNavigation extends CommonNavigator{

	public static String ID="WisoftUpdatePack.packnavigation";
	
	public PackNavigation() {
		
	}
	
	
	@Override
	public void createPartControl(Composite aParent) {
		// TODO Auto-generated method stub
		super.createPartControl(aParent);
		
	}
	
	@Override
	protected Object getInitialInput() {
		// TODO Auto-generated method stub
		return NavigatorData.getPackInput();
	}
	
	public void refreshInput()
	{
		TreeViewer treeViewer = getCommonViewer();
		treeViewer.refresh();
	}
	
}
