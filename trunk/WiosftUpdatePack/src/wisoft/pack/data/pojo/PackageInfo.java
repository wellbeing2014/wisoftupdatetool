package wisoft.pack.data.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import wisoft.pack.data.dao.NavigatorData;

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
	
	private int type;

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
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void setPackNameBySelf()
	{
		setPackageName(moduleName+"("+moduleCode+")"+moduleVer);
	}
	
	public void saveToDB()
	{
		if(type==IPackProperties.TYPE_PACK)
		{
			PackPackages ppg = NavigatorData.getPackPackageById(this.getId());
			ppg.setPackageName(packageName);
			ppg.setModuleName(moduleName);
			ppg.setModuleVer(moduleVer);
			ppg.setPackageClassId(packageClassId);
			ppg.setPackageFileId(packageFileId);
			ppg.setPackagePublishdate(packagePublishdate);
			ppg.setTestNumber(testNumber);
			ppg.setWimsProjectId(wimsProjectId);
			
			NavigatorData.UpdatePackPackage(ppg);
		}
		else if(type==IPackProperties.TYPE_UNPACK)
		{
			UnpackPackages ppg = NavigatorData.getUPakageById(this.getId());
			ppg.setPackageName(packageName);
			ppg.setModuleName(moduleName);
			ppg.setModuleVer(moduleVer);
			ppg.setPackageClassId(packageClassId);
			ppg.setPackageFileId(packageFileId);
			ppg.setPackagePublishdate(packagePublishdate);
			ppg.setTestNumber(testNumber);
			ppg.setWimsProjectId(wimsProjectId);
			
			NavigatorData.UpdateUnpackPackage(ppg);
		}
	}
	
	@Override
	public boolean equals(Object another)
	{
		if(another instanceof PackageInfo)
		{
			PackageInfo another1 = (PackageInfo)another;
			return this.id.equals(another1.id)||(this.moduleCode.equals(another1.moduleCode)
				&&this.moduleName.equals(another1.moduleName)
				&&this.moduleVer.equals(another1.moduleVer));
		}
		else return false;
	}
	
    /* 
     * 将父类所有的属性COPY到子类中。 
     * 类定义中child一定要extends father； 
     * 而且child和father一定为严格javabean写法，属性为deleteDate，方法为getDeleteDate 
     */  
    public void fatherToChild (Object father,Object child)throws Exception{  
        if(!(child.getClass().getSuperclass()==father.getClass())){  
            throw new Exception("child不是father的子类");  
        }  
        Class fatherClass= father.getClass();  
        Field ff[]= fatherClass.getDeclaredFields();  
        for(int i=0;i<ff.length;i++){  
            Field f=ff[i];//取出每一个属性，如deleteDate  
            Class type=f.getType();  
            Method m=fatherClass.getMethod("get"+upperHeadChar(f.getName()));//方法getDeleteDate  
            Object obj=m.invoke(father);//取出属性值               
            f.set(child,obj);  
        }  
    }  
    /** 
     * 首字母大写，in:deleteDate，out:DeleteDate 
     */  
    private String upperHeadChar(String in){  
        String head=in.substring(0,1);  
        String out=head.toUpperCase()+in.substring(1,in.length());  
        return out;  
    }  
}
