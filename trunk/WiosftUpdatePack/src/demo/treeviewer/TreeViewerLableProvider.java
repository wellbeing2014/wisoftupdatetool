package demo.treeviewer;
 
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
 
/**
* ��ǩ�ṩ�������Ƽ�¼��������ʾ�����ֺ�ͼ���
*
* @author YOYO
*
*/
public class TreeViewerLableProvider implements ILabelProvider {
 
        /**
         * ��¼��ʾ��ͼ�񣬿��Է���nullֵ
         */
        public Image getImage(Object element) {
                return null;
        }
 
        /**
         * ��¼��ʾ�����֣����ܷ���null
         */
        public String getText(Object element) {
                ITreeEntry entry = (ITreeEntry) element;
                return entry.getName();
        }
 
        public void addListener(ILabelProviderListener listener) {
        }
 
        public void dispose() {
        }
 
        public boolean isLabelProperty(Object element, String property) {
                return false;
        }
 
        public void removeListener(ILabelProviderListener listener) {
        }
 
}