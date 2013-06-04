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
	 * ��ROOT�����element�������������ж��Ƿ��Ѵ��ڣ������������ӣ��������
	 * �򷵻ش��ڵ�element
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
	 * ��element�����element�������������ж��Ƿ��Ѵ��ڣ������������ӣ��������
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
	
	/**��element ��ɾ��element ������������ֵ
	 * @param element
	 * @param elementname
	 * @param Attributename
	 * @param AttributeValue
	 * @return ����ɾ�����element 
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
	
	/**�������Ժ�ֵ����element �е�element��
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
	 * ���������ж�element�ǲ�����Ҫ��
	 * @param element
	 * @param Attributename
	 * @param AttributeValue
	 * @return true ��ʾ������ͬ��false��ʾ���Բ�ͬ�򲻴���
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
     * ͨ��XSD��XML Schema��У��XML 
     */ 
    public static boolean validateXMLByXSD(String xmlFileName) { 
        
        String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
               CHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
        try { 
        	InputStream   inputStream   =   new   ByteArrayInputStream(xmlFileName.getBytes("UTF-8"));
            //����Ĭ�ϵ�XML�������� 
            XMLErrorHandler errorHandler = new XMLErrorHandler(); 
            //��ȡ���� SAX �Ľ�������ʵ�� 
            SAXParserFactory factory = SAXParserFactory.newInstance(); 
            //�������ڽ���ʱ��֤ XML ���ݡ� 
            factory.setValidating(true); 
            //ָ���ɴ˴������ɵĽ��������ṩ�� XML ���ƿռ��֧�֡� 
            factory.setNamespaceAware(true); 
            //ʹ�õ�ǰ���õĹ����������� SAXParser ��һ����ʵ���� 
            SAXParser parser = factory.newSAXParser(); 
            //����һ����ȡ���� 
            SAXReader xmlReader = new SAXReader(); 
            //��ȡҪУ��xml�ĵ�ʵ�� 
            Document xmlDocument = (Document) xmlReader.read(inputStream); 
            //���� XMLReader �Ļ���ʵ���е��ض����ԡ����Ĺ��ܺ������б������ [url]http://sax.sourceforge.net/?selected=get-set[/url] ���ҵ��� 
            parser.setProperty( SCHEMA_LANGUAGE,XMLConstants.W3C_XML_SCHEMA_NS_URI); 
            parser.setProperty( CHEMA_SOURCE,  XmlOperator.class.getResource("/spring-beans-2.5.xsd")); 
            //����һ��SAXValidatorУ�鹤�ߣ�������У�鹤�ߵ����� 
            SAXValidator validator = new SAXValidator(parser.getXMLReader()); 
            //����У�鹤�ߵĴ�������������������ʱ�����ԴӴ����������еõ�������Ϣ�� 
            validator.setErrorHandler(errorHandler); 
            //У�� 
            validator.validate(xmlDocument); 

            XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint()); 
            //���������Ϣ��Ϊ�գ�˵��У��ʧ�ܣ���ӡ������Ϣ 
            if (errorHandler.getErrors().hasContent()) { 
                System.out.println("XML�ļ�ͨ��XSD�ļ�У��ʧ�ܣ�"); 
                writer.write(errorHandler.getErrors()); 
                String errorstr = "";
                for(Element element:errorHandler.getErrors().elements())
                {
                	errorstr += element.getText()+"\n";
                }
                MessageDialog.openError(Display.getDefault().getActiveShell(), "xml��֤ʧ�ܣ�", errorstr);
                return false;
            } else { 
                System.out.println("Good! XML�ļ�ͨ��XSD�ļ�У��ɹ���");
                return true;
            } 
        } catch (Exception ex) { 
            System.out.println("XML�ļ�: " + xmlFileName + " ͨ��XSD�ļ�:����ʧ�ܡ�\nԭ�� " + ex.getMessage()); 
            MessageDialog.openError(Display.getDefault().getActiveShell(), "xml��֤ʧ�ܣ�", ex.getMessage());
            return false;
        } 
    } 
}
