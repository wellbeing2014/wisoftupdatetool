package wisoft.pack.data;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import wisoft.pack.dao.PersonDao;
import wisoft.pack.pojo.Person;

public class DataTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSessionFactory sql =WisoftSessionFactory.instance.sqlSessionFactory;
		SqlSession session = null;
		try {
	        session = sql.openSession();
	        PersonDao persondao = (PersonDao) session.getMapper(PersonDao.class);
	        Person person = persondao.selectPerson(1);
	        if(person == null)
	            System.out.println("null");
	        else
	            System.out.println(person.getName());
	        } 
        finally {
            session.close();
        }
	}

}
