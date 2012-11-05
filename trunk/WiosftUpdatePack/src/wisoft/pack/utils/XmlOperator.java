package wisoft.pack.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author wellbeing
 *
 */

public class XmlOperator {
	
	private Document document=null;
	private File xmlfile = null;
	private Element RootElement;
	
	

	private String defaultroot = "root";
	private String defaultxmlpath = "default.xml";
	
	public Document getDocument() {
		if(document==null)
			read();
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}

	public Element getRootElement() {
		return RootElement;
	}
	public void setRootElement(Element rootElement) {
		RootElement = rootElement;
	}

	public File getXmlfile() {
		return xmlfile;
	}
	public void setXmlfile(File xmlfile) {
		this.xmlfile = xmlfile;
		read();
	}

	public XmlOperator()
	{
		xmlfile = new File(defaultxmlpath);
		read();
	}
	
	public XmlOperator(String xmlpath)
	{
		xmlfile = new File(xmlpath);
		read();
	}
	
	private void read()
	{
		if(exists())
			try {
				SAXReader saxReader =new SAXReader();
				saxReader.setEncoding("UTF-8");  
				document = saxReader.read(xmlfile);
				Element root= document.getRootElement();
				if(root!=null)
					RootElement = root;
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			document = DocumentHelper.createDocument();
	}
	public boolean exists()
	{
		return xmlfile.exists();
	}
	
	public void initXml(String root)
	{
		//read();
		if(root.isEmpty())
			root =this.defaultroot;
		RootElement =this.document.getRootElement();
		if(RootElement!=null)
		{
			if(RootElement.getName().equals(root))
				 this.document.remove(RootElement);
		}
		RootElement =this.document.addElement(root);
	}
	
	//public void 
	
	public void save()
	{
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8"); 
			XMLWriter writer;
			writer = new XMLWriter( new FileOutputStream(xmlfile), format );
			writer.write(this.document);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void close() 
	 {
		 this.document=null;
		 this.xmlfile =null;
	 }
	 
}
