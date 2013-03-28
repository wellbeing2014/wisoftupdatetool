package wisoft.pack.utils;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.InvalidXPathException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.NodeType;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.XPath;

import wisoft.pack.app.Activator;


public class PackConfigInfo {
	
	private static PackConfigInfo info= null;
	private static String filename = "PackConfig.xml";
	private XmlOperator xmlo;
	public PackConfigInfo() {
		// TODO Auto-generated constructor stub
		File config = new File(filename);
		if(!config.exists())
		{
			xmlo = new XmlOperator(filename);
			xmlo.initXml("PackConfig");
		}
		else
		{
			xmlo = new XmlOperator();
			xmlo.setXmlfile(config);
		}
		
	}
	
	 public static synchronized PackConfigInfo getInstance() {
		 if(info == null)
			 info =new PackConfigInfo();
		 return info;
	 }
	 
	public String getBuildServerPath() {
		xmlo.read();
		Element path =xmlo.OnlyElementInRoot("BuidServerPath");
		if(path.getTextTrim().isEmpty())
		{	
			setBuildServerPath("\\\\192.10.110.112\\auto_build_output");
			return getBuildServerPath();
		}
		else
			return path.getTextTrim();
	}

	public void setBuildServerPath(String buildServerPath) {
		Element path =xmlo.OnlyElementInRoot("BuidServerPath");
		path.setText(buildServerPath);
		xmlo.save();
	}
	
	public String getWimsTrackManagerPath() {
		xmlo.read();
		Element path =xmlo.OnlyElementInRoot("WimsPath");
		if(path.getTextTrim().isEmpty())
		{	
			setWimsTrackManagerPath("http://58.214.246.37:8120/wisoftintegrateframe/services/WimsManager?wsdl");
			return getWimsTrackManagerPath();
		}
		else
			return path.getTextTrim();
	}

	public void setWimsTrackManagerPath(String wimsTrackManagerPath) {
		Element path =xmlo.OnlyElementInRoot("WimsPath");
		path.setText(wimsTrackManagerPath);
		xmlo.save();
	}
	
	public String getDefaultExportPath() {
		xmlo.read();
		Element path =xmlo.OnlyElementInRoot("ExportPath");
		if(path.getTextTrim().isEmpty())
		{	
			setDefaultExportPath(Activator.getDefault().getLocationPath());
			return getDefaultExportPath();
		}
		else
			return path.getTextTrim();
	}

	public void setDefaultExportPath(String defaultExportPath) {
		Element path =xmlo.OnlyElementInRoot("ExportPath");
		path.setText(defaultExportPath);
		xmlo.save();
	}

}
