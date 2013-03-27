package wisoft.pack.utils;

import java.io.File;


public class PackConfigInfo {
	
	private static PackConfigInfo info= null;
	private static String filename = "PackConfig.xml";
	private XmlOperator xmlo;
	public PackConfigInfo() {
		// TODO Auto-generated constructor stub
		File config = new File(filename);
		if(!config.exists())
			xmlo = new XmlOperator(filename);
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
	 
	 private String BuildServerPath;
	 
	public String getBuildServerPath() {
		return BuildServerPath;
	}

	public void setBuildServerPath(String buildServerPath) {
		BuildServerPath = buildServerPath;
	}

}
