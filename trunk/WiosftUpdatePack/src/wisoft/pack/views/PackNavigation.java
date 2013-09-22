package wisoft.pack.views;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.navigator.CommonNavigator;

import wisoft.pack.app.Activator;
import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.edits.PackInfoEditor;
import wisoft.pack.edits.PackInfoInput;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.FileUtil;
import wisoft.pack.utils.PackConfigInfo;
import wisoft.pack.views.Console.ConsoleType;

public class PackNavigation extends CommonNavigator{

	public static String ID="WisoftUpdatePack.packnavigation";
	
	private boolean isOper ;
	
	public PackNavigation() {
		isOper = PackConfigInfo.getInstance().selOperate();
	}
	
	public TreeViewer treeViewer;
	
	
	@Override
	public void createPartControl(Composite aParent) {
		// TODO Auto-generated method stub
		super.createPartControl(aParent);
		treeViewer = getCommonViewer();
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
					handledoubleclick_pack();
			}
		});
	}
	
	private void handledoubleclick_pack()
	{
		IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection();
		IWorkbenchPage workbenchPage = getViewSite().getPage();
		IEditorPart editorPart ;
		final PackInfoModel packinfo;
		if(selection.getFirstElement() instanceof PackInfoModel)
		{
			packinfo =((PackInfoModel)selection.getFirstElement());
			if(packinfo.getEditInput()==null)
			{
				packinfo.setEditInput(new PackInfoInput(packinfo));
			}
			packinfo.readFromXML(packinfo.getSavePath());
			editorPart = workbenchPage.findEditor(packinfo.getEditInput());
			if(editorPart!=null)
				workbenchPage.bringToTop(editorPart);
			else
			{
				try {
					editorPart = workbenchPage.openEditor(packinfo.getEditInput(), PackInfoEditor.ID);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Activator.getDefault().setCurrent_pack(packinfo);
		}
	}
	
	@Override
	protected Object getInitialInput() {
		// TODO Auto-generated method stub
		if(isOper)
			return NavigatorData.getPackInput();
		else
			return NavigatorData.getUnPackInput();
	}
	
	public void refreshInput(PackageInfo sel)
	{
		TreeViewer treeViewer = getCommonViewer();
		PackFolderModel packinput;
		if(isOper)
			packinput= NavigatorData.getPackInput();
		else
			packinput= NavigatorData.getUnPackInput();
		treeViewer.setInput(packinput);
		
		PackInfoModel selpack = packinput.findPackageInfo(sel);
		if(selpack!=null)
		treeViewer.setSelection(new StructuredSelection(selpack));
		handledoubleclick_pack();
	}
	
	/**
	 * 删除选中的更新
	 */
	public void deleteSelectPack()
	{
		IStructuredSelection selection =(IStructuredSelection)getCommonViewer().getSelection();
		if(selection.getFirstElement()==null)
			MessageDialog.openError(this.getViewSite().getShell(), "提示", "请选择列表中一个更新包进行删除~");
		else
			for(Object obj:selection.toArray())
			{
				if(obj instanceof PackInfoModel)
				{
					PackInfoModel pim =((PackInfoModel)obj);
					//关闭编辑器
					IEditorPart iep = this.getViewSite().getWorkbenchWindow().getActivePage().findEditor(pim.getEditInput());
					this.getViewSite().getWorkbenchWindow().getActivePage().closeEditor(iep, false);
					Console.getInstance().print("关闭编辑器", pim.getName(), ConsoleType.INFO);
					//删除物理工程文件夹
					try {
						FileUtil.delete(pim.getSavePath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//删除数据库记录
					NavigatorData.deletePackPackage(pim.getPackageinfo());
					Console.getInstance().print("记录删除", pim.getName(), ConsoleType.INFO);
				}
			}
		refreshInput(null);
	}
}
