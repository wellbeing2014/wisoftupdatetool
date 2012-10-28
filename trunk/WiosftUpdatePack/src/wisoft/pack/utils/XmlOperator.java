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
import org.dom4j.Node;
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
	
	public XmlOperator(){}
	
	/**
	 * 读取指定路径下的XML ，不存在则创建
	 * @param xmlpath
	 * @throws Exception
	 */
	public void loadXml(String xmlpath) throws Exception
	{
		this.xmlpath = xmlpath;
		if(fileExist())
			read();
		else
			createXMLFile(xmlpath);
	}
	
	
	/**
	 * 读取XML
	 * @throws Exception
	 */
	private void read() throws Exception 
	{
		if (fileExist()) {
			SAXReader reader = new SAXReader();
			try 
			{
				this.document = reader.read(new File(xmlpath));
			} 
			catch(DocumentException e) 
			{
				throw new Exception("XML解析错误："+e.getMessage());
			}   
		} 
		else 
			throw new Exception("XML不存在："+xmlpath);
	}
	
	 /**
	  * XML 是否存在
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
	  * 创建一个空XML
	 * @param XMLFileName
	 * @param rootName
	 * @throws Exception 
	 */
	public void createXMLFile(String xmlpath) throws Exception  {
		if (!fileExist()) 
		{
			this.document = DocumentHelper.createDocument();
			writeToFile();
		} 
		else {
			throw new Exception("XML已经存在："+xmlpath);
		}
	 }
	
	 /**
	  * 增加一个根节点
	 * @param root
	 * @throws Exception
	 */
	public void addRootElement(String root)throws Exception {   
		 if(this.document==null)
			 throw new Exception("XML获取异常。");
		 if(root.trim().isEmpty())
			 throw new Exception("根节点设置错误。");
		 
		 Element rootelement =this.document.getRootElement();
		 if(rootelement!=null)
		 {
			 if(!rootelement.getName().equals(root))
				 this.document.remove(rootelement);
			 else
				 this.document.addElement(root);
		 }
		 else 
			 this.document.addElement(root);
		 writeToFile();
	 }
	 
	 public String getRootElement(String root)throws Exception {   
		 if(this.document==null)
			 throw new Exception("XML获取异常。");
		 if(root.trim().isEmpty())
			 throw new Exception("根节点设置错误。");
		 return this.document.getRootElement().getName();
	 }
	 
	 
	 /**
	  * 根据实际存在farnode，创建 子 node
	 * @param farnode
	 * @param node
	 * @throws Exception
	 */
	public void addElement(String farnode,String node)
	{ 
		List list =  this.document.selectNodes(farnode);
		
		((Element)(list.get(0))).addElement(node);
	}
	 
	
	private void addElement(String abstractPath)
	{
		String myHavePath ="/";
		String reqPath = abstractPath.substring(1);
		int i = 0;
		while((i=reqPath.indexOf("/"))!=-1)
		{
			myHavePath +=reqPath.substring(0,i);
			if(this.document.selectSingleNode(myHavePath)!=null)
				reqPath = reqPath.substring(i+1, reqPath.length());
			else
				break;
		}
		String[] rest = reqPath.split("/");
		for(int j = 0;j<rest.length;j++)
		{
			addElement(myHavePath,rest[j]);
			myHavePath+="/"+rest[j];
		}
	}
	
	/**
	 * 增加一个全局唯一节点并赋值
	 * @param nodePath
	 * @param nodeValue
	 * @throws Exception
	 */
	public void AddOnlyNode( String nodePath,String nodeValue) throws Exception
	 {
		addElement(nodePath);
		 
		Node only = this.document.selectSingleNode(nodePath);
		only.setText(nodeValue);
		
		 //writeToFile();
	 }
	 
	
	 
	 
	 public String getNodeValue(String nodePath){
		
		 List list = this.document.selectNodes(nodePath);
		 Iterator iter = list.iterator();
		 boolean nodeExist = false;
		 String nodeValue = null;
		 if (iter.hasNext()) {
			 Element element = (Element) iter.next();
			 nodeValue = element.getText();
			 return nodeValue;
		 } else 
		 {
			 System.out.println("获取不到节点："+nodePath);
			 return null;
		 }
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
