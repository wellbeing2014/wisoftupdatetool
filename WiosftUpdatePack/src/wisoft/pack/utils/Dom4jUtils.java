package wisoft.pack.utils;

import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.*;
public class Dom4jUtils {
	/** XML输入流 */
	private InputStream inputStream;

	/** 解析的XML Document */
	private Document     document;

	/** XML根元素 */
	private Element      root;

	/** 用于命名空间操作 */
	private HashMap      xmlMap;

	/** 缺省命名空间，即如<root xmlns="url"> */
	private String       nsKey;    // ns
	private String       nsURL;    // http://....
	private String       ns = ""; // like '//ns:', start with the xpath

	/** XML编码，缺省为UTF-8 */
	private String       encoding = "UTF-8";

	public Dom4jUtils() {
	   document = DocumentHelper.createDocument();
	}

	public Dom4jUtils(InputStream inputStream) {
	   document = DocumentHelper.createDocument();
	   this.inputStream = inputStream;
	}

	public void open() {
	   try {
	    SAXReader reader = new SAXReader();
	    document = reader.read(inputStream);
	    root = document.getRootElement();
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	}

	/** <code>setNamespace("ns","> */
	public void setNamespace(String key, String url) {
	   nsKey = key;
	   nsURL = url;
	   ns = "//" + key + ":";
	   xmlMap = new HashMap();
	   xmlMap.put(key, url);
	}

	//----------------------------------------------------------------------------------------
	/**
	   * Get object by namespace and xpath. like:
	   * node1/node2/node3        return an element 
	   * return an attribute
	   */
	public Object selectSingleObject(Element parent, String path) {
	   String attribute = null;
	   String[] aAttribute = path.split("@");

	   String sNode = aAttribute[0];
	   if (aAttribute.length > 1) {
	    attribute = aAttribute[1];
	    sNode = sNode.substring(0, sNode.length() - 1);
	   }
	   System.out.println(parsePath(sNode, false));
	   if (attribute != null)
	    return ((Element) selectObject(parent, parsePath(sNode, false))).attribute(attribute);
	   else
	    return selectObject(parent, parsePath(sNode, false));
	}

	public Object selectSingleObject(String path) {
	   String attribute = null;
	   String[] aAttribute = path.split("@");

	   String sNode = aAttribute[0];
	   if (aAttribute.length > 1) {
	    attribute = aAttribute[1];
	    sNode = sNode.substring(0, sNode.length() - 1);
	   }
	   if (attribute != null)
	    return ((Element) selectObject(parsePath(sNode, true))).attribute(attribute);
	   else
	    return selectObject(parsePath(sNode, true));
	}

	/**
	   * parse xpath to path with namespace
	   * @param path node1/node2/node3
	   * @param full 
	   *    true   - //ns:node1/ns:node2/ns:node3 
	   *    false - ns:node1/ns:node2/ns:node3
	   * @return
	   */
	private String parsePath(String path, boolean full) {
	   String[] nodes = path.split("/");
	   StringBuffer tmp = new StringBuffer();
	   for (int i = 0; i < nodes.length; i++) {
	    tmp.append("/" + nsKey + ":" + nodes[i]);
	   }
	   return full ? "/" + tmp.toString() : tmp.substring(1);
	}

	public List selectObjects(Element parent, String path) {
	   path = parsePath(path, false);
	   XPath xpath = parent.createXPath(path);
	   xpath.setNamespaceURIs(xmlMap);
	   return xpath.selectNodes(parent);
	}

	public List selectObjects(String path) {
	   path = parsePath(path, true);
	   XPath xpath = document.createXPath(path);
	   xpath.setNamespaceURIs(xmlMap);
	   return xpath.selectNodes(document);
	}

	/** Select node by parent element and sub-path. */
	private Object selectObject(Element parent, String path) {
	   XPath xpath = parent.createXPath(path);
	   xpath.setNamespaceURIs(xmlMap);
	   return xpath.selectSingleNode(parent);
	}

	private Object selectObject(String path) {
	   XPath xpath = document.createXPath(path);
	   xpath.setNamespaceURIs(xmlMap);
	   return xpath.selectSingleNode(document);
	}

	//----------------------------------------------------------------------------------------
	/** Node name which is unique in the document. */
	public Element selectSingleElement(String path) {
	   XPath xpath = document.createXPath(ns + path);
	   xpath.setNamespaceURIs(xmlMap);
	   return (Element) xpath.selectSingleNode(document);
	}

	//----------------------------------------------------------------------------------------
	/**
	   * Add root element.
	   * @param rootName the name of the root element.
	   */
	public void addRoot(String rootName) {
	   root = document.addElement(rootName);
	}

	/**
	   * Add namespace to the root element.
	   * @param url the url of namespace.
	   */
	public Namespace addNamespace(String url) {
	   nsURL = url;
	   Namespace ns = new Namespace("", url);
	   root.add(ns);
	   return ns;
	}

	/**
	   * Add namespace to the root element with name
	   * @param name Namespace name
	   * @param url Namespace url
	   */
	public Namespace addNamespace(String key, String url) {
	   Namespace ns = new Namespace(key, url);
	   root.add(ns);
	   return ns;
	}

	/**
	   * Add subnode of root
	   * if namespace, then create subnode void it.
	   * @param nodeName
	   * @return
	   */
	public Element addElement(String nodeName) {
	   if (nsURL != null)
	    return root.addElement(nodeName, nsURL);
	   else
	    return root.addElement(nodeName);
	}

	/**
	   * Output the document to byte[].
	   * @return byte[] xml content
	   */
	public byte[] toBytes() {
	   try {
	    OutputFormat format = new OutputFormat("\t", true);
	    format.setEncoding(encoding);

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    XMLWriter writer = new XMLWriter(out, format);
	    writer.write(document);

	    return out.toByteArray();
	   } catch (UnsupportedEncodingException uee) {
	    System.out.println(uee.getMessage());
	   } catch (IOException ioe) {
	    System.out.println(ioe.getMessage());
	   }
	   return null;
	}

	//---------------------------------------------------------------------------------------- 
	public Document getDocument() { return document; }
	public String getEncoding() { return encoding; }
	public void setEncoding(String encoding) { this.encoding = encoding; }

	//----------------------------------------------------------------------------------------
	/** print the xml tree. */
	public void printXMLTree() {
	   printElement(root, 0);
	   return;
	}

	private void printElement(Element element, int level) {
	   // print indent
	   for (int i = 0; i < level; i++) {
	    System.out.print(" ");
	   }
	   System.out.println(element.getQualifiedName() + "==" + element.getTextTrim());
	   Iterator iter = element.elementIterator();
	   while (iter.hasNext()) {
	    Element sub = (Element) iter.next();
	    printElement(sub, level + 2);
	   }
	   return;
	}

	
}

