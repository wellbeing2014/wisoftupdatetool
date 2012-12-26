package demo.treeviewer;
 
import java.util.List;
 
/**
* 树节点的接口
* @author YOYO
*
*/
public interface ITreeEntry {
       
        //      设置与得到树节点的名称
        public String getName();
        public void setName(String name);
       
        //      设置与得到子结点集合
        public void setChildren(List children);
        public List getChildren();
 
}