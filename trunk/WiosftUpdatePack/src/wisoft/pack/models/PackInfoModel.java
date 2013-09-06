package wisoft.pack.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.eclipse.ui.IEditorInput;

import wisoft.pack.data.dao.NavigatorData;
import wisoft.pack.data.dao.UUIDGenerator;
import wisoft.pack.data.pojo.PackPackages;
import wisoft.pack.data.pojo.PackProperties;
import wisoft.pack.data.pojo.PackageInfo;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.XmlOperator;

import com.wisoft.wims.WimsSingleIssueTracking;

public class PackInfoModel extends Model {
	
	private  IEditorInput editInput;
	private XmlOperator xmlo = new XmlOperator();
	
	public XmlOperator getXmlo() {
		return xmlo;
	}
	
	private PackageInfo packageinfo;

	
	public PackageInfo getPackageinfo() {
		return packageinfo;
	}
	public void setPackageinfo(PackageInfo packageinfo) {
		this.packageinfo = packageinfo;
	}
	
	
	public String getSavePath()
	{
		return this.packageinfo.getSavePath();
	}
	@Override
	public String getName()
	{
		if(!getModuleName().isEmpty()&&!getModuleCode().isEmpty()&&!getVersion().isEmpty())
			return getModuleName()+"("+getModuleCode()+")"+getVersion();
		else
			return super.getName();
	}
	@Override
	public void setName(String name) {
		if(!getModuleName().isEmpty()&&!getModuleCode().isEmpty()&&!getVersion().isEmpty())
			this.name = getModuleName()+"("+getModuleCode()+")"+getVersion();
		else
			this.name=name;
	};
	
	public String getModuleName() {
		return xmlo.OnlyElementInRoot(UpdateInfo.ModuleName).getText();
	}
	public void setModuleName(String moduleName) {
		xmlo.OnlyElementInRoot(UpdateInfo.ModuleName).setText(moduleName);
		xmlo.save();
	}
	public String getModuleCode() {
		return xmlo.OnlyElementInRoot(UpdateInfo.ModuleCode).getText();
	}
	public void setModuleCode(String moduleCode) {
		xmlo.OnlyElementInRoot(UpdateInfo.ModuleCode).setText(moduleCode);
		xmlo.save();
	}
	public String getVersion() {
		return xmlo.OnlyElementInRoot(UpdateInfo.Version).getText();
	}
	public void setVersion(String version) {
		xmlo.OnlyElementInRoot(UpdateInfo.Version).setText(version);
		xmlo.save();
	}
	
	public String getKeyWord() {
		return xmlo.OnlyElementInRoot(UpdateInfo.KeyWord).getText();
	}
	public void setKeyWord(String KeyWord) {
		xmlo.OnlyElementInRoot(UpdateInfo.KeyWord).setText(KeyWord);
		xmlo.save();
	}
	
	public String getCreateMan() {
		return xmlo.OnlyElementInRoot(UpdateInfo.CreateMan).getText();
	}
	public void setCreateMan(String CreateMan) {
		xmlo.OnlyElementInRoot(UpdateInfo.CreateMan).setText(CreateMan);
		xmlo.save();
	}
	
	public String getCreateTime() {
		return xmlo.OnlyElementInRoot(UpdateInfo.CreateTime).getText();
	}
	public void setCreateTime(String CreateTime) {
		xmlo.OnlyElementInRoot(UpdateInfo.CreateTime).setText(CreateTime);
		xmlo.save();
	}
	
	public String getReleaseNote() {
		return xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote).getText();
	}
	public void setReleaseNote(String ReleaseNote) {
		
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
		 rn.clearContent(); 
		 rn.addCDATA(ReleaseNote);
		
		xmlo.save();
	}
	
	
	public boolean getScopeBack() {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		String isneed = rn.elementText(UpdateInfo.Scope_Back);
		return Boolean.valueOf(isneed);
	}
	public void setScopeBack(boolean isneed) {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		Element front = xmlo.OnlyElementInElemnt(rn, UpdateInfo.Scope_Back);
		front.setText(Boolean.toString(isneed));
		xmlo.save();
	}
	
	public boolean getScopeDB() {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		String isneed = rn.elementText(UpdateInfo.Scope_DB);
		return Boolean.valueOf(isneed);
	}
	public void setScopeDB(boolean isneed) {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		Element front = xmlo.OnlyElementInElemnt(rn, UpdateInfo.Scope_DB);
		front.setText(Boolean.toString(isneed));
		xmlo.save();
	}
	
	public boolean getScopeFront() {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		String isneed = rn.elementText(UpdateInfo.Scope_Front);
		return Boolean.valueOf(isneed);
	}
	public void setScopeFront(boolean isneed) {
		Element rn =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		Element front = xmlo.OnlyElementInElemnt(rn, UpdateInfo.Scope_Front);
		front.setText(Boolean.toString(isneed));
		xmlo.save();
	}
	
	
	/** 获取更新包依赖数组
	 * @return
	 */
	public List<PackRelyModel> getPackRelys()
	{
		List<PackRelyModel> relysret = new ArrayList<PackRelyModel>();
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.PackRelys);
		for(Element element:relys.elements(UpdateInfo.PackRely))
		{
			PackRelyModel prm = new PackRelyModel();
			prm.setCode(element.attributeValue(UpdateInfo.PackRely_attr_code));
			prm.setName(element.attributeValue(UpdateInfo.PackRely_attr_name));
			prm.setVersion(element.attributeValue(UpdateInfo.PackRely_attr_ver));
			prm.setPublishTime(element.attributeValue(UpdateInfo.PackRely_attr_time));
			relysret.add(prm);
		}
		return relysret; 
	}
	
	/** 加入 更新包依赖
	 * @param prm
	 */
	public void addPackRely(PackRelyModel prm)
	{
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.PackRelys);
		Element rely =xmlo.addElementInElement(relys, UpdateInfo.PackRely,UpdateInfo.PackRely_attr_name,prm.getName());
		rely.addAttribute(UpdateInfo.PackRely_attr_code, prm.getCode());
		rely.addAttribute(UpdateInfo.PackRely_attr_ver, prm.getVersion());
		rely.addAttribute(UpdateInfo.PackRely_attr_time, prm.getPublishTime());
		xmlo.save();
	}
	
	/** 移除更新包依赖
	 * @param prm
	 */
	public void removePackRely(PackRelyModel prm)
	{
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.PackRelys);
		 xmlo.RemoveElementInElement(relys, UpdateInfo.PackRely, UpdateInfo.PackRely_attr_name, prm.getName());
		xmlo.save();
	}
	
	
	
	/** 获取更新包依赖数组
	 * @return
	 */
	public List<WimsSingleIssueTracking> getTrackRelys()
	{
		List<WimsSingleIssueTracking> relysret = new ArrayList<WimsSingleIssueTracking>();
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.TackRelys);
		for(Element element:relys.elements(UpdateInfo.TackRely))
		{
			WimsSingleIssueTracking track = new WimsSingleIssueTracking();
			track.setLsh(element.attributeValue(UpdateInfo.TackRely_attr_id));
			track.setSqpersonid(element.attributeValue(UpdateInfo.TackRely_attr_personname));
			track.setProid(element.attributeValue(UpdateInfo.TackRely_attr_proname));
			track.setContent(element.elementText(UpdateInfo.TackRely_elem_content));
			relysret.add(track);
		}
		return relysret; 
	}
	
	/** 加入问题单
	 * @param track
	 */
	public void addTrackRely(WimsSingleIssueTracking track )
	{
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.TackRelys);
		Element rely =xmlo.addElementInElement(relys, UpdateInfo.TackRely,UpdateInfo.TackRely_attr_id,track.getLsh());
		rely.addAttribute(UpdateInfo.TackRely_attr_personname,track.getSqpersonid());
		rely.addAttribute(UpdateInfo.TackRely_attr_proname,track.getProid());
		if(rely.element(UpdateInfo.TackRely_elem_content)!=null)
			rely.remove(rely.element(UpdateInfo.TackRely_elem_content));
		Element rely_content = rely.addElement(UpdateInfo.TackRely_elem_content);
		rely_content.addCDATA(track.getContent());
		xmlo.save();
	}
	
	/** 移除 问题单关联
	 ** @param trackid
	 */
	public void removeTrackRely(String trackid)
	{
		Element relys =xmlo.OnlyElementInRoot(UpdateInfo.TackRelys);
		 xmlo.RemoveElementInElement(relys, UpdateInfo.TackRely,UpdateInfo.TackRely_attr_id,trackid);
		xmlo.save();
	}
	
	public void refresh() 
	{
		
	}

	public IEditorInput getEditInput() {
		return editInput;
	}
	public void setEditInput(IEditorInput editInput) {
		this.editInput = editInput;
	}

	
	public PackInfoModel(PackageInfo pageinfo)
	{
		this.packageinfo = pageinfo;
		this.name =pageinfo.getPackageName();
		readFromXML(this.packageinfo.getSavePath());
	}
	
	public PackInfoModel()
	{

	}
	
	
	public PackInfoModel(String name)
	{
		this.name =name;
	}
	
	public void saveIntoXML()
	{
		//创建文件夹
		File dir = new File(this.packageinfo.getSavePath());
		dir.mkdirs();
		xmlo.setXmlfile(new File(this.packageinfo.getSavePath()+"/"+UpdateInfo.FileName));
		xmlo.initXml("root");
		
		Element scope =xmlo.OnlyElementInRoot(UpdateInfo.Scope);
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_Back).setText("false");
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_DB).setText("false");
		xmlo.OnlyElementInElemnt(scope, UpdateInfo.Scope_Front).setText("false");
		xmlo.OnlyElementInRoot(UpdateInfo.ReleaseNote);
		xmlo.save();
	}
	
	public void readFromXML(String savepath)
	{
		//创建文件夹
		File dir = new File(savepath);
		dir.mkdirs();
		xmlo.setXmlfile(new File(savepath+"/"+UpdateInfo.FileName));
		xmlo.initXml("root");
		
	}
	
	
	/**获取需要配置的文件
	 * @return
	 */
	public List<FileModel> getConfFiles()
	{
		List<FileModel> confiles = new ArrayList<FileModel>();
		Element filelist =xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
		FileModel fm =new FileModel(filelist);
		getConfFiles(confiles,new FileModel[]{fm});
		return confiles;
	}
	private void getConfFiles(List<FileModel> filelist,FileModel[] files)
	{
		for(FileModel fm :files)
		{
			if(fm.isConf()&&fm.getConftype()!=null)
				filelist.add(fm);
			if(fm.getChildren().size()>0)
				getConfFiles(filelist,fm.getChildren().toArray(new FileModel[0]));
		}
		//return confiles;
	}
	
	public FileModel getUpdateFileRoot()
	{
		Element filelist =xmlo.OnlyElementInRoot(UpdateInfo.UpdateFileList);
		FileModel fm =new FileModel(filelist);
		return fm;
	}
	
	public void saveToDB()
	{
		PackPackages ppg ;
		if(this.packageinfo.getId()==null)
		{
			this.packageinfo.setId(UUIDGenerator.getUUID());
			ppg = (PackPackages)this.packageinfo;
			ppg.setPackPerson(getCreateMan());
			ppg.setCreateTime(getCreateTime());
			ppg.setState(0);
			NavigatorData.insertPackPackage(ppg);
		}
		else
		{
			ppg = NavigatorData.getPackPackageById(this.packageinfo.getId());
			ppg.setPackPerson(getCreateMan());
			ppg.setCreateTime(getCreateTime());
			ppg.setState(0);
			NavigatorData.insertPackPackage(ppg);
		}
	}
}
