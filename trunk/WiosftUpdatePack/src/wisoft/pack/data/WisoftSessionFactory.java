package wisoft.pack.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.eclipse.core.runtime.FileLocator;

public class WisoftSessionFactory {
	
	public static WisoftSessionFactory instance = new WisoftSessionFactory();
	public  SqlSessionFactory sqlSessionFactory = null;
	public   String   inputStream2String   (InputStream   is)  { 
        StringBuilder sb = new StringBuilder();
        String readline = "";
        try
        {
            /**
             * ÈôÂÒÂë£¬Çë¸ÄÎªnew InputStreamReader(is, "GBK").
             */
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while (br.ready())
            {
                readline = br.readLine();
                sb.append(readline);
            }
            br.close();
        } catch (IOException ie)
        {
            System.out.println("converts failed.");
        }
        return sb.toString();
	}
	
	public WisoftSessionFactory() {
		
		 createSessionFactory();
	}
	
	public void createSessionFactory()
	{
		String resource = "mybatis-config.xml";
		InputStream inputStream =WisoftSessionFactory.class.getResourceAsStream(resource);
		
		String aa=inputStream2String(inputStream);
		String mdbpath = "";
		try {
			URL url =FileLocator.toFileURL(WisoftSessionFactory.class.getResource("wup.mdb"));
			File file =new File(url.toURI());
			mdbpath = file.getAbsolutePath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aa=aa.replace("${mymdbpath}", mdbpath);
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(new ByteArrayInputStream(aa.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
