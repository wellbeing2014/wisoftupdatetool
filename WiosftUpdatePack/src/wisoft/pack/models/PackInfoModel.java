package wisoft.pack.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.eclipse.ui.IEditorInput;

public class PackInfoModel extends Model {
	protected PackInfoOfOverview overview = new PackInfoOfOverview(this);
	protected PackInfoOfSelectFiles selectFiles = new PackInfoOfSelectFiles(this);
	protected PackInfoOfEditConfs editConfs =new PackInfoOfEditConfs(this);
	protected PackInfoOfEditSql editSql = new PackInfoOfEditSql(this);
	public boolean isdirty = false;
	private  IEditorInput editInput;
	
	private String moduleName;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	private String moduleCode;
	private String version ;
	private String savePath;
	
	public void saveUpdateInfoXml() throws Exception
	{
		//创建文件夹
		File dir = new File(savePath);
		dir.mkdirs();
		
		StringBuffer xmlcontent =  new StringBuffer();
		BufferedWriter bw = null;
		try {
			xmlcontent.delete(0, xmlcontent.length());
			xmlcontent.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			xmlcontent.append("<root>\n");
			OutputStream os1= new FileOutputStream(new File(savePath+"/updateinfo.xml"));
			OutputStreamWriter osw1 = new OutputStreamWriter(os1,"UTF-8");
			 bw = new BufferedWriter(osw1);//包装一下
			 
			 xmlcontent.append("</root>\n");
			bw.write(new String(xmlcontent.toString().getBytes("UTF-8"),"UTF-8"));//写出到文件
			bw.flush(); //刷新输出流
			bw.close();
		} catch (IOException e) {
			throw new Exception("创建 更新包工程失败！");
		} 
	}

	public IEditorInput getEditInput() {
		return editInput;
	}

	public void setEditInput(IEditorInput editInput) {
		this.editInput = editInput;
	}

	public PackInfoOfEditConfs getEditConfs() {
		return editConfs;
	}
	
	public PackInfoOfEditSql getEditSql() {
		return editSql;
	}
	

	public PackInfoOfSelectFiles getSelectFiles() {
		return selectFiles;
	}
	
	public PackInfoOfOverview getOverview() {
		return overview;
	}
	
	public PackInfoModel(String name)
	{
		this.name =name;
		this.addChild(overview);
		this.addChild(selectFiles);
		this.addChild(editConfs);
		this.addChild(editSql);
	}
}
