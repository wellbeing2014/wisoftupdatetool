package demo.treeviewer;
 
import java.util.List;
 
/**
* 城市的实体类
* @author YOYO
*
*/
public class CityEntity implements ITreeEntry {
       
        private Long id;
       
        private String name;
       
        private List peoples;
 
        public CityEntity() {
                super();
        }
 
        public CityEntity(String name) {
                super();
                this.name = name;
        }
 
        public Long getId() {
                return id;
        }
 
        public void setId(Long id) {
                this.id = id;
        }
 
        public String getName() {
                return name;
        }
 
        public void setName(String name) {
                this.name = name;
        }
 
        public List getPeoples() {
                return peoples;
        }
 
        public void setPeoples(List peoples) {
                this.peoples = peoples;
        }
 
        public List getChildren() {
                return this.getPeoples();
        }
 
        public void setChildren(List children) {
                this.setPeoples(children);
        }
 
}