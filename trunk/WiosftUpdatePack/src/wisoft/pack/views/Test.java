package wisoft.pack.views;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Document document=null;
		try {
			File file =new File("navinfo.xml");
			if(file.exists())
				document = new SAXReader().read("navinfo.xml");
			else
				document=DocumentHelper.createDocument();
				
			Element root = document.getRootElement();
			//Node root = document.selectSingleNode( "//root" );
	        Element author1 = root.addElement( "author" )
	            .addAttribute( "name", "James" )
	            .addAttribute( "location", "UK" )
	            .addText( "James Strachan" );
	        Element author2 = root.addElement( "author" )
	            .addAttribute( "name", "Bob1" )
	            .addAttribute( "location", "US" )
	            .addText( "Bob McWhirter" );
	        OutputFormat format = OutputFormat.createPrettyPrint(); //美化格式
	        XMLWriter writer = new XMLWriter(new FileWriter(new File("navinfo.xml")), format );
	        writer.write( document);
	        writer.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
