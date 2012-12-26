package demo.treeviewer;
 
import java.util.List;
 
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
 
/**
* 内容器：由它决定哪些对象记录应该输出在TreeViewer里显示
* @author YOYO
*
*/
public class TreeViewContentProvider implements ITreeContentProvider {
 
        /**
         * 由这种方法决定父节点应该显示哪些子结点
         */
        public Object[] getChildren(Object parentElement) {
                ITreeEntry entry = (ITreeEntry) parentElement;
                List list = entry.getChildren();
                if( list == null || list.isEmpty() ) {
                        return new Object[0];
                }else{
                        return list.toArray();
                }
        }
 
        public Object getParent(Object element) {
                return null;
        }
 
        /**
         * 判断某节点是否有子结点
         */
        public boolean hasChildren(Object element) {
                ITreeEntry entry = (ITreeEntry) element;
                List list = entry.getChildren();
                if(list==null || list.isEmpty()) {
                        return false;
                }else{
                        return true;
                }
        }
 
        /**
         * 由这种方法决定树的一级显示哪些对象
         */
        public Object[] getElements(Object inputElement) {
                if(inputElement instanceof List) {
                        List list = (List) inputElement;
                        return list.toArray();
                }else{
                        return new Object[0];
                }
        }
 
        public void dispose() {
 
        }
 
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
 
}