package wisoft.pack.models;

import java.util.ArrayList;

public class PackRelyModel {
	private String name;
	private String code;
	private String version;
	private String publishTime;
	
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	private ArrayList<?> listeners = new ArrayList();
	
	public String toString()
	{
		return name+"("+code+")"+version;
		
	}

}
