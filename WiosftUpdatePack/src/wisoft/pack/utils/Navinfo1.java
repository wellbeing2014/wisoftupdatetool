package wisoft.pack.utils;

import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.Model;
import wisoft.pack.models.PackFolder;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoModel;

public class Navinfo1 {
	private static final String BUNDLE_NAME = "wisoft.pack.utils.navinfo"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static Navinfo1 nav = null;
	public static XmlOperator xmlo = null;
	
	public static String Operate_Pack = "pack";
	public static String Operate_UnPack = "unpack";

	
	@SuppressWarnings("static-access")
	public static synchronized Navinfo1 getInstance() {
	  if (nav == null)
		  nav = new Navinfo1();
	  nav.xmlo = new XmlOperator(Navinfo1.getFileName());
	  nav.xmlo.initXml(Navinfo1.getRootName());
	  return nav;
	 }
	public static String getFileName()
	{
		return getString("filename");
	}
	public static String getString(String key) {
		// TODO Auto-generated method stub
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	
	public static String getRootName() {
		// TODO Auto-generated method stub
		return getString("root");
	}
	
	public static String getPackName() {
		// TODO Auto-generated method stub
		return getString("ele_pack");
	}
	
	public static String getAttriPackName() {
		// TODO Auto-generated method stub
		return getString("attr_name_pack");
	}
	
	public static String getAttriPackPath() {
		// TODO Auto-generated method stub
		return getString("attr_path_pack");
	}
	public static String getOperate() {
		// TODO Auto-generated method stub
		return getString("operate");
	}
	
	public static String getPackFolder() {
		// TODO Auto-generated method stub
		return getString("ele_packfolder");
	}
	
	public static String getAttriFolderName() {
		// TODO Auto-generated method stub
		return getString("attr_name_packfolder");
	}
	
	
	
	/**
	 * 将 Model结构 递归写入 Element 最终返回
	 * @param parent
	 * @param model
	 * @return
	 */
	private static Element saveRecursion(Element parent,Model model)
	{
		for(int i=0;i<model.getChildren().size();i++)
		{
			if(model.getChildren().get(i) instanceof PackFolderModel)
			{	
				PackFolderModel packfolder = (PackFolderModel)model.getChildren().get(i);
				PackFolder pf = packfolder.getFolderType();
				String foldername = pf.getFoldername();
				Element folder_ele = xmlo.addElementInElement(parent, getPackFolder(), getAttriFolderName(),foldername);
				saveRecursion(folder_ele,packfolder);
			}
			
			else if(model.getChildren().get(i) instanceof PackInfoModel)
			{
				PackInfoModel pm = (PackInfoModel)model.getChildren().get(i);
				Element packxml =xmlo.addElementInElement(parent,Navinfo1.getPackName(), Navinfo1.getAttriPackName(), pm.getName());
				if(!xmlo.isEqualByAttribute(packxml, Navinfo1.getAttriPackPath(), pm.getSavePath()))
				packxml.addAttribute(Navinfo1.getAttriPackPath(), pm.getSavePath());
			}
		}
		return parent;
	}
	public static void SaveNavInfo(Model packroot)
	{
		Element root =xmlo.getRootElement();
		if(root==null)
			xmlo.initXml(Navinfo1.getRootName());
		selOperate();
		
		
		
		
		Element rootPackElement =xmlo.addElementInRoot(Navinfo1.getPackFolder(), Navinfo1.getAttriFolderName(), PackFolder.DEFALUT.getFoldername());
		rootPackElement.clearContent();
		saveRecursion(rootPackElement,packroot);
		xmlo.save();
		xmlo.close();
	}
	
	public static boolean setedOperate()
	{
		Element operate =xmlo.OnlyElementInRoot(getOperate());
		return !operate.getText().isEmpty();
	}
	
	public static boolean selOperate()
	{
		if(setedOperate())
		{
			String txt =xmlo.OnlyElementInRoot(getOperate()).getText();
			if(Navinfo1.Operate_Pack.equals(txt))
				return true;
			else
				return false;
		}
		else
		{
			boolean isOperate =MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "请选择你使用工具的方式", "确定为使用打包方式，取消为使用更新方式。")	;
			Element operate =xmlo.OnlyElementInRoot(getOperate());
			operate.setText(isOperate?Navinfo1.Operate_Pack:Navinfo1.Operate_UnPack);
			xmlo.save();
			return isOperate;
		}
	}
	
	
	public static boolean exists()
	{
		return xmlo.exists();
	}
	
	/**
	 * 递归读取 元素 写入 rootModel 最终返回
	 * @param element
	 * @param rootModel
	 * @return
	 */
	private static PackFolderModel readRecursion(Element element,PackFolderModel rootModel) {
		for (Iterator i = element.elementIterator(); i.hasNext();) {
			Element zmodel = (Element) i.next();
			
			if(Navinfo1.getPackName().equals(zmodel.getName()))
			{
				String name = zmodel.attributeValue(Navinfo1.getAttriPackName());
    			String path = zmodel.attributeValue(Navinfo1.getAttriPackPath());
    			PackInfoModel  zpack = new PackInfoModel(name,path);
    			rootModel.addChild(zpack);
			}
			else if(Navinfo1.getPackFolder().equals(zmodel.getName()))
			{
				String foldername = zmodel.attributeValue(getAttriFolderName());
	        	PackFolder pf = PackFolder.getFolder(foldername);
	        	//在根目录下创建一个子目录，名称根据 xmlo读取。
	        	PackFolderModel pfm = new PackFolderModel(rootModel,pf);
	        	rootModel.addChild(pfm);
	        	readRecursion(zmodel,pfm);
			}
		}
		return rootModel;
	}
	
	@SuppressWarnings("finally")
	public static PackFolderModel readPackNavInfo()
	{
		//定义一个根目录
		PackFolderModel pfm = new PackFolderModel(null,PackFolder.DEFALUT);
		
		try 
		{ //读取保存的更新包列表
		if(!exists())
		{
			SaveNavInfo(pfm);
			return pfm;
		}
		
        Element root = xmlo.getRootElement();
        if(root.element(Navinfo1.getPackFolder())!=null)
        	readRecursion(root.element(Navinfo1.getPackFolder()),pfm);
		}
		catch (Exception e) { 
			e.printStackTrace();
		} 
		finally
		{
			return pfm;
		}
	}
}
