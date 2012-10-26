package wisoft.pack.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
	private String xmlpath = "default.xml";
	public XmlOperator(String xmlpath) throws Exception
	{
		this.xmlpath = xmlpath;
		if(fileExist())
			read();
		else
			createXMLFile();
	}
	 
	public void read() throws Exception 
	{
		if (fileExist()) {
			SAXReader reader = new SAXReader();
			try 
			{
				this.document = reader.read(new File(xmlpath));
			} 
			catch(DocumentException e) 
			{
				throw new Exception("XML��������"+e.getMessage());
			}   
		} 
		else 
			throw new Exception("XML�����ڣ�"+xmlpath);
	}
	
	 /**
	  * XML �Ƿ����
	 * @param fileName
	 * @return
	 */
	private boolean fileExist() {
		File objFile = new File(this.xmlpath);
		if (objFile.exists()) 
			return true;
		else 
			return false;
	}
	 
	 
	 /**
	  * ����һ��XML
	 * @param XMLFileName
	 * @param rootName
	 * @throws Exception 
	 */
	public void createXMLFile() throws Exception  {
		if (!fileExist()) 
		{
			this.document = DocumentHelper.createDocument();
			//Element element = this.document.addElement(rootName);
		   // ����ע�� element.addComment(String)
		   // ����ڵ� element.addElement(String);
		   // ������������ element.addAttribute(NAME,VALUE);
		   // �������� element.setText(String);
		   //System.out.println("File created!");
			writeToFile();
		} 
		else {
			throw new Exception("XML�Ѿ����ڣ�"+this.xmlpath);
		}
	 }
	
	 public void addRootElement(String root)throws Exception {   
		 if(this.document==null)
			 throw new Exception("XML��ȡ�쳣��");
		 if(root.trim().isEmpty())
			 throw new Exception("���ڵ����ô���");
		 this.document.addElement(root);
		 writeToFile();
	 }
	 
	 public String getRootElement(String root)throws Exception {   
		 if(this.document==null)
			 throw new Exception("XML��ȡ�쳣��");
		 if(root.trim().isEmpty())
			 throw new Exception("���ڵ����ô���");
		 return this.document.getRootElement().getName();
	 }
	 
	 
	 public void addElement(String fatherPath,String childName, String childValue)throws Exception {   
		 if(this.document==null)
			 throw new Exception("XML��ȡ�쳣��");
		 if(fatherPath.trim().isEmpty())
			 throw new Exception("���ڵ����ô���");
		 List list = this.document.selectNodes(fatherPath);
		 Iterator iter = list.iterator();
		 if (iter.hasNext()) {
			 Element element = (Element) iter.next();
			 Element childelement = element.addElement(childName);
			 childelement.setText(childValue);   
		 } 
		 else 
			 throw new Exception("��ȡ�����ڵ㣺"+fatherPath);
		 writeToFile();
		 
	 }
	 
	 public void modifyNode( String nodePath,
			 					String nodeValue, String newValue) throws Exception
	 {
		 if (this.document==null)
			 throw new Exception("XML��ȡ�쳣");
		 List list = this.document.selectNodes(nodePath);
		 Iterator iter = list.iterator();
		 boolean nodeExist = false;
		 while (iter.hasNext()) {
			 Element element = (Element) iter.next();
			 if (element.getText().equals(nodeValue)) 
			 {
				 element.setText(newValue);
				 nodeExist = true;
			 }   
		 }
		 if (!nodeExist) 
			 throw new Exception("��ȡ�����ڵ㣺"+nodePath);
		 writeToFile();
	 }
	 
	
	 
	 
	 public String getNodeValue(String nodePath) throws Exception{
		 if (this.document==null)
			 throw new Exception("XML���ڴ���");
		 List list = this.document.selectNodes(nodePath);
		 Iterator iter = list.iterator();
		 boolean nodeExist = false;
		 String nodeValue = null;
		 if (iter.hasNext()) {
			 Element element = (Element) iter.next();
			 nodeValue = element.getText();
			 return nodeValue;
		 } else 
			 throw new Exception("��ȡ�����ڵ㣺"+nodePath);
	 }
	 
	 public void close() 
	 {
		 this.document=null;
	 }
	 
	 public void writeToFile()
	 {
		 OutputFormat format=OutputFormat.createPrettyPrint();
		 format.setEncoding("UTF-8");
		 XMLWriter w;
		 try {
			 w = new XMLWriter(new FileWriter(new File(this.xmlpath)),format);
			 w.write(document) ;  
			 w.close();
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }  
	 }
	 
}
