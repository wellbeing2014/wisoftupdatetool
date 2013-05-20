package wisoft.pack.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import wisoft.pack.models.Model;
import wisoft.pack.models.PackFolder;
import wisoft.pack.models.PackFolderModel;
import wisoft.pack.models.PackInfoModel;

public class Navinfo {
	private static final String BUNDLE_NAME = "wisoft.pack.utils.navinfo"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static Navinfo nav = null;
	public static XmlOperator xmlo = null;
	
	public static String Operate_Pack = "pack";
	public static String Operate_UnPack = "unpack";

	
	@SuppressWarnings("static-access")
	public static synchronized Navinfo getInstance() {
	  if (nav == null)
		  nav = new Navinfo();
	  nav.xmlo = new XmlOperator(Navinfo.getFileName());
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
	
	public static void SaveNavInfo(Model packroot)
	{
		Element root =xmlo.getRootElement();
		if(root==null)
			xmlo.initXml(Navinfo.getRootName());
		selOperate();
		
		
		List<Element> haveele = root.elements(Navinfo.getPackFolder());
		for(int j=0;j<haveele.size();j++)
		{			
			xmlo.getRootElement().remove(haveele.get(j));
		}
		for(int i=0;i<packroot.getChildren().size();i++)
		{
			if(packroot.getChildren().get(i) instanceof PackFolderModel)
			{	
				PackFolderModel packfolder = (PackFolderModel)packroot.getChildren().get(i);
				PackFolder pf = packfolder.getFolderType();
				String foldername = pf.getFoldername();
				Element folder_ele = xmlo.addElementInRoot(getPackFolder(), getAttriFolderName(),foldername);
				for(Model model:packfolder.getChildren())
				{
					PackInfoModel pm = (PackInfoModel)model;
					Element packxml =xmlo.addElementInElement(folder_ele,Navinfo.getPackName(), Navinfo.getAttriPackName(), pm.getName());
					if(!xmlo.isEqualByAttribute(packxml, Navinfo.getAttriPackPath(), pm.getSavePath()))
					packxml.addAttribute(Navinfo.getAttriPackPath(), pm.getSavePath());
				}
			}
		}
		
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
			if(Navinfo.Operate_Pack.equals(txt))
				return true;
			else
				return false;
		}
		else
		{
			boolean isOperate =MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "请选择你使用工具的方式", "确定为使用打包方式，取消为使用更新方式。")	;
			Element operate =xmlo.OnlyElementInRoot(getOperate());
			operate.setText(isOperate?Navinfo.Operate_Pack:Navinfo.Operate_UnPack);
			xmlo.save();
			return isOperate;
		}
	}
	
	
	
	public static boolean exists()
	{
		return xmlo.exists();
	}
	
	@SuppressWarnings("finally")
	public static PackFolderModel readPackNavInfo()
	{
		//定义一个根目录
		PackFolderModel packs = new PackFolderModel(null,PackFolder.DEFALUT);
		try 
		{ //读取保存的更新包列表
		if(!exists())
		{
			SaveNavInfo(packs);
			return packs;
		}
		
        Element root = xmlo.getRootElement();
        for (Iterator i = root.elementIterator(Navinfo.getPackFolder()); i.hasNext();) {
        	Element packfolder = (Element) i.next();
        	String foldername = packfolder.attributeValue(getAttriFolderName());
        	PackFolder pf = PackFolder.getFolder(foldername);
        	//在根目录下创建一个子目录，名称根据 xmlo读取。
        	PackFolderModel pfm = new PackFolderModel(packs,pf);
    		for (Iterator j = packfolder.elementIterator(Navinfo.getPackName()); j.hasNext();) {
    			Element packinfo = (Element) j.next();
    			String name = packinfo.attributeValue(Navinfo.getAttriPackName());
    			String path = packinfo.attributeValue(Navinfo.getAttriPackPath());
    			PackInfoModel  pack = new PackInfoModel(name,path);
    			pfm.addChild(pack);
    		}
        }
        
        
		}
		catch (Exception e) { 
			e.printStackTrace();
		} 
		finally
		{
			return packs;
		}
	}
}
