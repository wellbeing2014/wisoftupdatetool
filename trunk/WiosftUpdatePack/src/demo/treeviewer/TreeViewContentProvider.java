package demo.treeviewer;
 
import java.util.List;
 
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
 
/**
* ������������������Щ�����¼Ӧ�������TreeViewer����ʾ
* @author YOYO
*
*/
public class TreeViewContentProvider implements ITreeContentProvider {
 
        /**
         * �����ַ����������ڵ�Ӧ����ʾ��Щ�ӽ��
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
         * �ж�ĳ�ڵ��Ƿ����ӽ��
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
         * �����ַ�����������һ����ʾ��Щ����
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