﻿/*
 * 由SharpDevelop创建。
 * 用户： ZhuXinpei
 * 日期: 2012/7/10
 * 时间: 10:22
 * 
 * 要改变这种模板请点击 工具|选项|代码编写|编辑标准头文件
 */
using System;
using System.Xml;
using System.Collections;
using System.Collections.Generic;

namespace NewWisoftUpdateTool
{
	/// <summary>
	/// Description of XmlHelper.
	/// </summary>
	/// <summary>
    /// XmlHelper 的摘要说明
    /// </summary>
    public class XmlHelper
    {
    	private static string path = GobalParameters.UpdateXmlFilePath;
        public XmlHelper()
        {
        }
        
        public static XmlDocument getXMLdoc
        {
        	get
        	{
        		XmlDocument  xmlDocument = new XmlDocument();
        		xmlDocument.Load(path);
        		return xmlDocument;
        	}
        }
        public static void  CreateXML()
        {
        	XmlDocument  xmlDocument = new XmlDocument();
			// 声明 XML
			 XmlDeclaration xmlDeclare =xmlDocument.CreateXmlDeclaration("1.0","utf-8",null);
			 xmlDocument.AppendChild(xmlDeclare);
			//创建根节点
			XmlElement elementRoot =xmlDocument.CreateElement("root");
			xmlDocument.AppendChild(elementRoot);
			try {
				xmlDocument.Save(path);
			} catch (Exception) {
				
				throw new Exception("创建XML文件失败！");
			}			
        }

        /// <summary>
        /// 读取数据
        /// </summary>
        /// <param name="path">路径</param>
        /// <param name="node">节点</param>
        /// <param name="attribute">属性名，非空时返回该属性值，否则返回串联值</param>
        /// <returns>string</returns>
        /**************************************************
         * 使用示列:
         * XmlHelper.Read(path, "/Node", "")
         * XmlHelper.Read(path, "/Node/Element[@Attribute='Name']", "Attribute")
         ************************************************/
        public static string Read( string node, string attribute)
        {
            string value = "";
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                value = (attribute.Equals("") ? xn.InnerText : xn.Attributes[attribute].Value);
            }
            catch { }
            return value;
        }

        public static XmlNodeList ReadChild( string node)
        {
            XmlNodeList nl =null;
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                nl = xn.ChildNodes;
            }
            catch { }
            return nl;
        }
        /// <summary>
        /// 插入更新文件组
        /// </summary>
        /// <param name="node"></param>
        /// <param name="ar"></param>
        public static void InsertUpdateFiles(string node,ArrayList ar)
        {
        	try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                for (int i = 0; i < ar.Count; i++) {
                	XmlElement xe = doc.CreateElement("update_file");
                	string[] filename = ((string)ar[i]).Split('\\');
                	xe.SetAttribute("fileurl",ar[i] as string);
                	xe.SetAttribute("name",filename[filename.Length-1]);
                	xn.AppendChild(xe);
                }
                
                doc.Save(path);
            }
            catch { }
        }
        /// <summary>
        /// 插入手动配置文件组
        /// </summary>
        /// <param name="node"></param>
        /// <param name="ar"></param>
        public static void InsertConfFiles(string node,List<Update_File> ar)
        {
        	try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                for (int i = 0; i < ar.Count; i++) {
                	XmlElement xe = doc.CreateElement("before_config");
                	xe.SetAttribute("no",i.ToString());
                	xe.SetAttribute("fileurl",ar[i].Fileurl);
                	xe.SetAttribute("name",ar[i].Name);
                	xe.AppendChild(doc.CreateCDataSection(ar[i].ConfContent));
                	xn.AppendChild(xe);
                }
                
                doc.Save(path);
            }
            catch { }
        }
         
        public static void InsertCData( string node, string element, string value)
        {
        	try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                XmlElement xe = doc.CreateElement(element);
                xe.AppendChild(doc.CreateCDataSection(value));
                xn.AppendChild(xe);
                doc.Save(path);
            }
            catch { }
        }
        /// <summary>
        /// 插入数据
        /// </summary>
        /// <param name="path">路径</param>
        /// <param name="node">节点</param>
        /// <param name="element">元素名，非空时插入新元素，否则在该元素中插入属性</param>
        /// <param name="attribute">属性名，非空时插入该元素属性值，否则插入元素值</param>
        /// <param name="value">值</param>
        /// <returns></returns>
        /**************************************************
         * 使用示列:
         * XmlHelper.Insert(path, "/Node", "Element", "", "Value")
         * XmlHelper.Insert(path, "/Node", "Element", "Attribute", "Value")
         * XmlHelper.Insert(path, "/Node", "", "Attribute", "Value")
         ************************************************/
        public static void Insert( string node, string element, string attribute, string value)
        {
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                if (element.Equals(""))
                {
                    if (!attribute.Equals(""))
                    {
                        XmlElement xe = (XmlElement)xn;
                        xe.SetAttribute(attribute, value);
                    }
                }
                else
                {
                    XmlElement xe = doc.CreateElement(element);
                    if (attribute.Equals(""))
                        xe.InnerText = value;
                    else
                        xe.SetAttribute(attribute, value);
                    xn.AppendChild(xe);
                }
                doc.Save(path);
            }
            catch { }
        }

        /// <summary>
        /// 修改数据
        /// </summary>
        /// <param name="path">路径</param>
        /// <param name="node">节点</param>
        /// <param name="attribute">属性名，非空时修改该节点属性值，否则修改节点值</param>
        /// <param name="value">值</param>
        /// <returns></returns>
        /**************************************************
         * 使用示列:
         * XmlHelper.Insert(path, "/Node", "", "Value")
         * XmlHelper.Insert(path, "/Node", "Attribute", "Value")
         ************************************************/
        public static void Update( string node, string attribute, string value)
        {
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                XmlElement xe = (XmlElement)xn;
                if (attribute.Equals(""))
                    xe.InnerText = value;
                else
                    xe.SetAttribute(attribute, value);
                doc.Save(path);
            }
            catch { }
        }

        /// <summary>
        /// 删除数据
        /// </summary>
        /// <param name="path">路径</param>
        /// <param name="node">节点</param>
        /// <param name="attribute">属性名，非空时删除该节点属性值，否则删除节点值</param>
        /// <param name="value">值</param>
        /// <returns></returns>
        /**************************************************
         * 使用示列:
         * XmlHelper.Delete(path, "/Node", "")
         * XmlHelper.Delete(path, "/Node", "Attribute")
         ************************************************/
        public static void Delete( string node, string attribute)
        {
            try
            {
                XmlDocument doc = new XmlDocument();
                doc.Load(path);
                XmlNode xn = doc.SelectSingleNode(node);
                XmlElement xe = (XmlElement)xn;
                if (attribute.Equals(""))
                    xn.ParentNode.RemoveChild(xn);
                else
                    xe.RemoveAttribute(attribute);
                doc.Save(path);
            }
            catch { }
        }
    }
}
