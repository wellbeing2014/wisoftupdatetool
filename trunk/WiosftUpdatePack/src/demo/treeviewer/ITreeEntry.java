package demo.treeviewer;
 
import java.util.List;
 
/**
* ���ڵ�Ľӿ�
* @author YOYO
*
*/
public interface ITreeEntry {
       
        //      ������õ����ڵ������
        public String getName();
        public void setName(String name);
       
        //      ������õ��ӽ�㼯��
        public void setChildren(List children);
        public List getChildren();
 
}