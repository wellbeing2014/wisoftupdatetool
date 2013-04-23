package wisoft.pack.models;

public class PackConfig_Server {
	private String ServerName;
	private String WebappPath;
	private String DBPath;
	private String WSMPath;
	private String WebPort;
	private String ServerUser;
	
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
}
