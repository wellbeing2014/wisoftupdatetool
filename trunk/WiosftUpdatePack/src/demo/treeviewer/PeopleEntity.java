package demo.treeviewer;
 
import java.util.Date;
import java.util.List;
 
/**
* 本类包含五个不同数据类型的变量，分别对应数据库中的五个字段。
*
* @author YOYO
*
*/
public class PeopleEntity implements ITreeEntry {
       
        private Long id;
        private String name;
        private boolean sex;
        private int age;
        private Date createDate;
       
        public PeopleEntity(){}
        public PeopleEntity(String name) { this.name = name; }
       
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
        public boolean isSex() {
                return sex;
        }
        public void setSex(boolean sex) {
                this.sex = sex;
        }
        public int getAge() {
                return age;
        }
        public void setAge(int age) {
                this.age = age;
        }
        public Date getCreateDate() {
                return createDate;
        }
        public void setCreateDate(Date createDate) {
                this.createDate = createDate;
        }
        public List getChildren() {
                return null;
        }
        public void setChildren(List children) {               
        }
 
}