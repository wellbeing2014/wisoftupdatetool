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
                        setText("��");
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
                        setText("ˢ��");
                }
               
                public void run() {
                        tv.refresh();
                }
        }
       
        /**
         * չ����ǰ�ڵ��Action��
         * @author YOYO
         *
         */
        private class ExpandAction extends Action {
                public ExpandAction() {
                        setText("չ��");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj != null) {
                                tv.expandToLevel(obj, 1);       //     ֻչ��һ�㣬���ÿ��Գ���ʵ�����Ĳ���
                        }
                }
               
        }
       
        /**
         * ������ǰ�ڵ��Action��
         * @author YOYO
         *
         */
        private class CollapseAction extends Action {
                public CollapseAction() {
                        setText("����");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj != null) {
                                tv.expandToLevel(obj, -1);      //    -1Ϊ����ǰ�ڵ�������ӽ������
                        }
                }
        }
       
        /**
         * ����ǰ�ڵ����һ���ӽ���Action��
         * @author YOYO
         *
         */
        private class AddEntryAction extends Action {
                public AddEntryAction() {
                        setText("����");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj == null || obj instanceof PeopleEntity ) {
                                return;
                        }
                        InputDialog dialog = new InputDialog(null, "����ǰ�ڵ�����һ���ӽ��", "��������", "abc", null);
                        if(dialog.open() == InputDialog.OK ) {
                                String entryName = dialog.getValue();
                               
                                ITreeEntry newEntry = null;
                                if(obj instanceof CountryEntity) {
                                        newEntry = new CityEntity(entryName);
                                }else if(obj instanceof CityEntity) {
                                        newEntry = new PeopleEntity(entryName);
                                }
                               
                                //      �������ӽ��֮ǰ�Ѹ��ڵ�չ��
                                if(!tv.getExpandedState(obj)) {
                                        tv.expandToLevel(obj, 1);
                                }
                               
                                tv.add(obj, newEntry);
                        }
                }
        }
       
        /**
         * ɾ���ڵ��Action��
         * @author YOYO
         *
         */
        private class RemoveEntryAction extends Action {
                public RemoveEntryAction() {
                        setText("ɾ��");
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
         * �޸Ľڵ����Ƶ�Action��
         * @author YOYO
         *
         */
        private class ModifyEntryAction extends Action {
                public ModifyEntryAction() {
                        setText("�޸�");
                }
               
                public void run() {
                        ITreeEntry obj = getSelTreeEntry();
                        if(obj == null) {
                                return;
                        }
                        InputDialog dialog = new InputDialog(null, "�޸Ľڵ�", "����������", obj.getName(), null);
                        if(dialog.open() == InputDialog.OK ) {
                                String entryName = dialog.getValue();
                                obj.setName(entryName);
                                tv.refresh(obj);
                        }
                }
        }
               
        /**
         * �Զ��巽����ȡ�õ�ǰѡ��Ľڵ�
         * @return
         */
        private ITreeEntry getSelTreeEntry() {
                IStructuredSelection selection = (IStructuredSelection) tv.getSelection();
                ITreeEntry entry = (ITreeEntry) (selection.getFirstElement());
                return entry;
        }
       
}