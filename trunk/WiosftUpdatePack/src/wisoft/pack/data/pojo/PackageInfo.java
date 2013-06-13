package wisoft.pack.data.pojo;

public class PackageInfo {
	
	protected String id;
	
	private String packageName;

	private String moduleName;

	private String moduleCode;

	private String moduleVer;
	
	private Integer testNumber;

	private String packagePublishdate;
	
	private String savePath;
	
	private String packageClassId;
	
	private String wimsProjectId;
	
	private String packageFileId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleVer() {
		return moduleVer;
	}

	public void setModuleVer(String moduleVer) {
		this.moduleVer = moduleVer;
	}

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
	}

	public String getPackagePublishdate() {
		return packagePublishdate;
	}

	public void setPackagePublishdate(String packagePublishdate) {
		this.packagePublishdate = packagePublishdate;
	}
	
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getPackageClassId() {
		return packageClassId;
	}

	public void setPackageClassId(String packageClassId) {
		this.packageClassId = packageClassId;
	}

	public String getWimsProjectId() {
		return wimsProjectId;
	}

	public void setWimsProjectId(String wimsProjectId) {
		this.wimsProjectId = wimsProjectId;
	}

	public String getPackageFileId() {
		return packageFileId;
	}

	public void setPackageFileId(String packageFileId) {
		this.packageFileId = packageFileId;
	}
	
}
