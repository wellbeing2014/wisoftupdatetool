package wisoft.pack.utils;
import java.util.List;

import org.springframework.core.io.ClassPathResource;


public class App {

	public static void main(String[] args) throws Exception {
		ClassPathResource res = new ClassPathResource("wisoft/pack/utils/test.sql");
		SQLExtractor ext = SQLExtractor.getInstance();
		List<String> list = ext.extract(res.getInputStream());
		for(String a :list)
			System.out.println(a);
	}


}
