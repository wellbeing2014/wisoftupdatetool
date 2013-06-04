package wisoft.pack.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

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
	
	public void read()
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
		save();
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
	
	/**根据属性和值返回element 中的element。
	 * @param element
	 * @param elementname
	 * @param Attributename
	 * @param AttributeValue
	 * @return
	 */
	public Element getElementInElement(Element element ,String elementname,String Attributename,String AttributeValue)
	{
		List<Element> ets =element.elements(elementname);
		for(int i=0;i<ets.size();i++)
		{
			if(isEqualByAttribute(ets.get(i),Attributename,AttributeValue))
				return ets.get(i);
		}
		return null;
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
	 
	 
	 /** 
     * 通过XSD（XML Schema）校验XML 
     */ 
    public static boolean validateXMLByXSD(String xmlFileName) { 
        
        String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
               CHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
        try { 
        	InputStream   inputStream   =   new   ByteArrayInputStream(xmlFileName.getBytes("UTF-8"));
            //创建默认的XML错误处理器 
            XMLErrorHandler errorHandler = new XMLErrorHandler(); 
            //获取基于 SAX 的解析器的实例 
            SAXParserFactory factory = SAXParserFactory.newInstance(); 
            //解析器在解析时验证 XML 内容。 
            factory.setValidating(true); 
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。 
            factory.setNamespaceAware(true); 
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。 
            SAXParser parser = factory.newSAXParser(); 
            //创建一个读取工具 
            SAXReader xmlReader = new SAXReader(); 
            //获取要校验xml文档实例 
            Document xmlDocument = (Document) xmlReader.read(inputStream); 
            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。 
            parser.setProperty( SCHEMA_LANGUAGE,XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            parser.setProperty( CHEMA_SOURCE,  XmlOperator.class.getResource("/spring-beans-2.5.xsd")); 
            //创建一个SAXValidator校验工具，并设置校验工具的属性 
            SAXValidator validator = new SAXValidator(parser.getXMLReader()); 
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。 
            validator.setErrorHandler(errorHandler); 
            //校验 
            validator.validate(xmlDocument); 

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint()); 
            //如果错误信息不为空，说明校验失败，打印错误信息 
            if (errorHandler.getErrors().hasContent()) { 
                System.out.println("XML文件通过XSD文件校验失败！"); 
                writer.write(errorHandler.getErrors()); 
                String errorstr = "";
                for(Element element:errorHandler.getErrors().elements())
                {
                	errorstr += element.getText()+"\n";
                }
                MessageDialog.openError(Display.getDefault().getActiveShell(), "xml验证失败！", errorstr);
                return false;
            } else { 
                System.out.println("Good! XML文件通过XSD文件校验成功！");
                return true;
            } 
        } catch (Exception ex) { 
            System.out.println("XML文件: " + xmlFileName + " 通过XSD文件:检验失败。\n原因： " + ex.getMessage()); 
            MessageDialog.openError(Display.getDefault().getActiveShell(), "xml验证失败！", ex.getMessage());
            return false;
        } 
    } 
}
