package demo.treeviewer;
 
import java.util.List;
 
/**
* 国家的实体类
* @author YOYO
*
*/
public class CountryEntity implements ITreeEntry {
 
        private Long id;
        private String name;
        private List cities;
        public CountryEntity() {
                super();
        }
        public CountryEntity(String name) {
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
        public List getCities() {
                return cities;
        }
        public void setCities(List cities) {
                this.cities = cities;
        }
        public List getChildren() {
                return this.getCities();
        }
        public void setChildren(List children) {
                this.setCities(children);
        }
       
}