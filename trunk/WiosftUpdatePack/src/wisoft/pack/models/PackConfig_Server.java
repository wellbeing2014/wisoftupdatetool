package wisoft.pack.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.sql.DATE;
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
			return "未更新";
		else
			return ret;
	}
	
	/**向服务数据库写入更新包更新记录
	 * @param packinfo
	 * @return
	 */
	public boolean setPackVersionRecord(PackageInfo packinfo)
	{
		
		try {
			OracleDbAccess ob = new OracleDbAccess();
			ob.setValue(DBPath);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String SQL = "insert into system_version_info values( '','";
			//    ID    MODULENAME                 MODULECODE         VERSION  PUBLISH_DATE   UPDATE_DATE        REMARK
			SQL+= packinfo.getModuleName()
				 +"','"+packinfo.getModuleCode()
				 +"','"+packinfo.getModuleVer()
				 +"','"+packinfo.getPackagePublishdate()
				 +"','"+sdf.format(new Date())
				 +"','')";
			ob.getInt(SQL);
			ob.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
