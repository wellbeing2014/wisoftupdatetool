package demo.treeviewer;
 
import java.util.ArrayList;
 
public class DataFactory {
       
        public static Object createTreeData() {
                PeopleEntity p1 = new PeopleEntity("Yahreso Lin");
                PeopleEntity p2 = new PeopleEntity("Queeny Lee");
                PeopleEntity p3 = new PeopleEntity("Skittles");
                PeopleEntity p4 = new PeopleEntity("Cyin");
                PeopleEntity p5 = new PeopleEntity("Lienxq");
                PeopleEntity p6 = new PeopleEntity("Mini Yang");
                PeopleEntity p7 = new PeopleEntity("ChenST");
                PeopleEntity p8 = new PeopleEntity("ZhengC");
                PeopleEntity p9 = new PeopleEntity("BlackBeetle");
               
                CityEntity city1 = new CityEntity("龙岩");
                CityEntity city2 = new CityEntity("郑州");
                CityEntity city3 = new CityEntity("福州");
                CityEntity city4 = new CityEntity("厦门");
                CityEntity city5 = new CityEntity("天津");
               
                CountryEntity c1 = new CountryEntity("省内");
                CountryEntity c2 = new CountryEntity("省外");
               
                {
                        ArrayList list = new ArrayList();
                        list.add(p1);
                        list.add(p2);
                        city1.setChildren(list);
                }
                {
                        ArrayList list = new ArrayList();
                        list.add(p3);
                        city2.setChildren(list);
                }
                {
                        ArrayList list = new ArrayList();
                        list.add(p4);
                        list.add(p7);
                        list.add(p8);
                        city3.setChildren(list);
                }
                {
                        ArrayList list = new ArrayList();
                        list.add(p5);
                        list.add(p9);
                        city4.setChildren(list);
                }
                {
                        ArrayList list = new ArrayList();
                        list.add(p6);
                        city5.setChildren(list);
                }
               
 
                {
                        ArrayList list = new ArrayList();
                        list.add(city1);
                        list.add(city3);
                        list.add(city4);
                        c1.setChildren(list);
                }
                {
                        ArrayList list = new ArrayList();
                        list.add(city2);
                        list.add(city5);
                        c2.setChildren(list);
                }
 
                {
                        ArrayList list = new ArrayList();
                        list.add(c1);
                        list.add(c2);
                        return list;
                }
        }
 
}