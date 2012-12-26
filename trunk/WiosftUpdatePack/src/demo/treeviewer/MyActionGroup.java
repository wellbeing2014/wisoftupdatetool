package demo.treeviewer;
 
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.actions.ActionGroup;
 
public class MyActionGroup extends ActionGroup {
       
        private TreeViewer tv;
       
        public MyActionGroup(TreeViewer treeViewer) {
                this.tv = treeViewer;
        }
 
        @Override
        public void fillContextMenu(IMenuManager mgr) {
                MenuManager menuManager = (MenuManager) mgr;
                menuManager.add(new OpenAction());
                menuManager.add(new RefreshAction());
 
                menuManager.add(new ExpandAction());
                menuManager.add(new CollapseAction());
                menuManager.add(new AddEntryAction());
                menuManager.add(new RemoveEntryAction());
                menuManager.add(new ModifyEntryAction());
               
                Tree tree = tv.getTree();
                Menu menu = menuManager.createContextMenu(tree);
                tree.setMenu(menu);
        }
       
        private class OpenAction extends Action {
                public OpenAction() {
                        setText("打开");
                }
               
                public void run() {
                        IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
                        ITreeEntry obj = (ITreeEntry) (selection.getFirstElement());
                        if(obj != null) {
                                MessageDialog.openInformation(null, null, obj.getName());
                        }
                }
        }
       
        private class RefreshAction extends Action {
                public RefreshAction() {
                        setText("刷新");
                }
               
                public void run() {
                        tv.refresh();
                }
        }
       
        /**
         * 展开当前节点的Action类
         * @author YOYO
         *
         */
        private class ExpandAction extends Action {
                public ExpandAction() {
                        setText("展开");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj != null) {
                                tv.expandToLevel(obj, 1);       //     只展开一层，设置可以超过实际树的层数
                        }
                }
               
        }
       
        /**
         * 收缩当前节点的Action类
         * @author YOYO
         *
         */
        private class CollapseAction extends Action {
                public CollapseAction() {
                        setText("收缩");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj != null) {
                                tv.expandToLevel(obj, -1);      //    -1为将当前节点的所有子结点收缩
                        }
                }
        }
       
        /**
         * 给当前节点添加一个子结点的Action类
         * @author YOYO
         *
         */
        private class AddEntryAction extends Action {
                public AddEntryAction() {
                        setText("增加");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj == null || obj instanceof PeopleEntity ) {
                                return;
                        }
                        InputDialog dialog = new InputDialog(null, "给当前节点增加一个子结点", "输入名称", "abc", null);
                        if(dialog.open() == InputDialog.OK ) {
                                String entryName = dialog.getValue();
                               
                                ITreeEntry newEntry = null;
                                if(obj instanceof CountryEntity) {
                                        newEntry = new CityEntity(entryName);
                                }else if(obj instanceof CityEntity) {
                                        newEntry = new PeopleEntity(entryName);
                                }
                               
                                //      在增加子结点之前把父节点展开
                                if(!tv.getExpandedState(obj)) {
                                        tv.expandToLevel(obj, 1);
                                }
                               
                                tv.add(obj, newEntry);
                        }
                }
        }
       
        /**
         * 删除节点的Action类
         * @author YOYO
         *
         */
        private class RemoveEntryAction extends Action {
                public RemoveEntryAction() {
                        setText("删除");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj == null) {
                                return;
                        }
                        tv.remove(obj);
                }
        }
 
        /**
         * 修改节点名称的Action类
         * @author YOYO
         *
         */
        private class ModifyEntryAction extends Action {
                public ModifyEntryAction() {
                        setText("修改");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj == null) {
                                return;
                        }
                        InputDialog dialog = new InputDialog(null, "修改节点", "输入新名称", obj.getName(), null);
                        if(dialog.open() == InputDialog.OK ) {
                                String entryName = dialog.getValue();
                                obj.setName(entryName);
                                tv.refresh(obj);
                        }
                }
        }
               
        /**
         * 自定义方法：取得当前选择的节点
         * @return
         */
        private ITreeEntry getSelTreeEntry() {
                IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
                ITreeEntry entry = (ITreeEntry) (selection.getFirstElement());
                return entry;
        }
       
}