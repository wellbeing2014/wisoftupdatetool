package wisoft.pack.models;

import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.utils.OracleDbAccess;

import com.wisoft.wims.WimsProInfo;

public class PackConfig_Server {
	private String ServerName;
	private String WebappPath;
	private String DBPath;
	private String WSMPath;
	private String WebPort;
	private String ServerUser;
	private WimsProInfo proinfo;
	private boolean isNewStruct;
	
	public boolean isNewStruct() {
		return isNewStruct;
	}
	public void setNewStruct(boolean isNewStruct) {
		this.isNewStruct = isNewStruct;
	}
	public WimsProInfo getProinfo() {
		return proinfo;
	}
	public void setProinfo(WimsProInfo proinfo) {
		this.proinfo = proinfo;
	}
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public String getWebappPath() {
		return WebappPath;
	}
	public void setWebappPath(String webappPath) {
		WebappPath = webappPath;
	}
	public String getDBPath() {
		return DBPath;
	}
	public void setDBPath(String dBPath) {
		DBPath = dBPath;
	}
	public String getWSMPath() {
		return WSMPath;
	}
	public void setWSMPath(String wSMPath) {
		WSMPath = wSMPath;
	}
	public String getWebPort() {
		return WebPort;
	}
	public void setWebPort(String webPort) {
		WebPort = webPort;
	}
	public String getServerUser() {
		return ServerUser;
	}
	public void setServerUser(String serverUser) {
		ServerUser = serverUser;
	}
	public String getServerPwd() {
		return ServerPwd;
	}
	public void setServerPwd(String serverPwd) {
		ServerPwd = serverPwd;
	}
	private String ServerPwd;
	
	public String getPackUpdateTime(PackageInfo packinfo)
	{
		String ret ;
		try {
			OracleDbAccess ob = new OracleDbAccess();
			ob.setValue(DBPath);
			ret = ob.getFirstUnit("select update_date from system_version_info where modulecode='"+packinfo.getModuleCode()+"' and version ='"+packinfo.getModuleVer()+"' and modulename='"+packinfo.getModuleName()+"' order by update_date desc");
			ob.close();
		} catch (Exception e) {
			ret = null;
		}
		if(ret==null)
			return "Î´¸üÐÂ";
		else
			return ret;
	}
}
