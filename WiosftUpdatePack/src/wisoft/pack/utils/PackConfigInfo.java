package wisoft.pack.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.app.Activator;
import wisoft.pack.models.PackConfig_Server;

import com.wisoft.wims.WimsProInfo;


public class PackConfigInfo{
	
	private static PackConfigInfo info= null;
	private static String filename = "PackConfig.xml";
	
	public static String Operate_Pack = "pack";
	public static String Operate_UnPack = "unpack";
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
	
	public PackConfig_Server[] getUnPackProInfos(){
		xmlo.read();
		Element path =xmlo.OnlyElementInRoot("UnPackServers");
		List<Element> servers = path.elements("Server");
		List<PackConfig_Server> serverlist = new ArrayList<PackConfig_Server>();
		for(Element ele:servers)
		{
			PackConfig_Server ps = new PackConfig_Server();
			Element ele_pro = ele.element("ProInfo");
			String id = ele_pro.attributeValue("id");
			String name = ele_pro.attributeValue("proname");
			WimsProInfo pro = new WimsProInfo();
			pro.setId(id);
			pro.setProname(name);
			ps.setProinfo(pro);
			ps.setServerName(ele.attributeValue("name"));
			ps.setDBPath(ele.elementText("DBpath"));
			ps.setServerPwd(ele.elementText("ServerPwd"));
			ps.setServerUser(ele.elementText("ServerUser"));
			ps.setWebappPath(ele.elementText("WebappPath"));
			ps.setWebPort(ele.elementText("WebPort"));
			ps.setWSMPath(ele.elementText("WSMPath"));
			serverlist.add(ps);
		}
		return serverlist.toArray(new PackConfig_Server[0]);
	}
	
	public void setUnPackProInfos(PackConfig_Server[] servers)
	{
		Element path =xmlo.OnlyElementInRoot("UnPackServers");
		xmlo.getRootElement().remove(path);
		Element path1 =xmlo.OnlyElementInRoot("UnPackServers");
		for(PackConfig_Server server:servers)
		{
			Element ele =xmlo.addElementInElement(path1, "Server", "name", server.getServerName());
			Element ele_pro =xmlo.addElementInElement(ele, "ProInfo", "id", server.getProinfo().getId());
			ele_pro.addAttribute("proname", server.getProinfo().getProname());
			ele.addElement("DBpath").addText(server.getDBPath());
			ele.addElement("ServerPwd").addText(server.getServerPwd());
			ele.addElement("ServerUser").addText(server.getServerUser());
			ele.addElement("WebappPath").addText(server.getWebappPath());
			ele.addElement("WebPort").addText(server.getWebPort());
			ele.addElement("WSMPath").addText(server.getWSMPath());
		}
		xmlo.save();
	}
	
	
	/**是否设置了OperateType属性。
	 * @return
	 */
	public  boolean setedOperate()
	{
		Element operate =xmlo.OnlyElementInRoot("OperateType");
		return !operate.getText().isEmpty();
	}
	
	/**获取设置系统的使用方式，true为打包方式，false为解包方式。
	 * @return
	 */
	public  boolean selOperate()
	{
		if(setedOperate())
		{
			String txt =xmlo.OnlyElementInRoot("OperateType").getText();
			if(PackConfigInfo.Operate_Pack.equals(txt))
				return true;
			else
				return false;
		}
		else
		{
			boolean isOperate =MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "请选择你使用工具的方式", "确定为使用打包方式，取消为使用更新方式。")	;
			Element operate =xmlo.OnlyElementInRoot("OperateType");
			operate.setText(isOperate?PackConfigInfo.Operate_Pack:PackConfigInfo.Operate_UnPack);
			xmlo.save();
			return isOperate;
		}
	}
	
}
