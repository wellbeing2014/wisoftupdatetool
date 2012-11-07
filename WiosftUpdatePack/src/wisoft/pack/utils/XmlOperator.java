package wisoft.pack.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.dom4j.Attribute;
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
			if(!RootElement.getName().equals(root))
			{
				this.document.remove(RootElement);
				RootElement =this.document.addElement(root);
			}
		}
		else
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
	
	public  Element OnlyElementInRoot(String elementname)
	{
		Element et =RootElement.element(elementname);
		if(et!=null)
			return et;
		else 
			return RootElement.addElement(elementname);
	}
	
	public  Element OnlyElementInElemnt(Element father,String elementname)
	{
		Element et =father.element(elementname);
		if(et!=null)
			return et;
		else 
			return father.addElement(elementname);
	}

	
	/**
	 * 在ROOT下添加element，并根据属性判断是否已存在，如果不存在添加，如果存在
	 * 则返回存在的element
	 * @param elementname
	 * @param Attributename
	 * @param AttributeValue
	 * @return
	 */
	public Element addElementInRoot(String elementname,String Attributename,String AttributeValue)
	{
		List<Element> ets =RootElement.elements(elementname);
		boolean ishave=false;
		Element returnele = null;
		for(int i=0;i<ets.size();i++)
		{
			if(isEqualByAttribute(ets.get(i),Attributename,AttributeValue))
			{
				ishave=true;
				returnele = ets.get(i);
				break;
			}
		}
		if(!ishave&&returnele==null)
		{
			returnele =RootElement.addElement(elementname);
			returnele.addAttribute(Attributename, AttributeValue);
		}
		return returnele;
	}
	
	/**
	 * 在element下添加element，并根据属性判断是否已存在，如果不存在添加，如果存在
	 * @param elementname
	 * @param Attributename
	 * @param AttributeValue
	 * @return
	 */
	public Element addElementInElement(Element element ,String elementname,String Attributename,String AttributeValue)
	{
		List<Element> ets =element.elements(elementname);
		boolean ishave=false;
		Element returnele = null;
		for(int i=0;i<ets.size();i++)
		{
			if(isEqualByAttribute(ets.get(i),Attributename,AttributeValue))
			{
				ishave=true;
				returnele = ets.get(i);
				break;
			}
		}
		if(!ishave&&returnele==null)
		{
			returnele =element.addElement(elementname);
			returnele.addAttribute(Attributename, AttributeValue);
		}
		return returnele;
	}
	
	/**在element 中删除element 根据属性名和值
	 * @param element
	 * @param elementname
	 * @param Attributename
	 * @param AttributeValue
	 * @return 返回删除后的element 
	 */
	public Element RemoveElementInElement(Element element ,String elementname,String Attributename,String AttributeValue)
	{
		List<Element> ets =element.elements(elementname);
		for(int i=0;i<ets.size();i++)
		{
			if(isEqualByAttribute(ets.get(i),Attributename,AttributeValue))
				element.remove( ets.get(i));
		}
		return element;
	}
	
	/**
	 * 根据属性判段element是不是所要的
	 * @param element
	 * @param Attributename
	 * @param AttributeValue
	 * @return true 表示属性相同，false表示属性不同或不存在
	 */
	public boolean isEqualByAttribute(Element element,String Attributename,String AttributeValue)
	{
		Attribute myattri = element.attribute(Attributename);
		if(myattri!=null)
			return myattri.getValue().equals(AttributeValue);
		else
			return false;
	}
	
	 public void close() 
	 {
		 this.document=null;
		 this.xmlfile =null;
	 }
	 
}
