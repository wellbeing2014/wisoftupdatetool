package demo.treeviewer;
 
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
 
/**
* 标签提供器：控制记录在树中显示的文字和图像等
*
* @author YOYO
*
*/
public class TreeViewerLableProvider implements ILabelProvider {
 
        /**
         * 记录显示的图像，可以返回null值
         */
        public Image getImage(Object element) {
                return null;
        }
 
        /**
         * 记录显示的文字，不能返回null
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