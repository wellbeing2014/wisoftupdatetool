package wisoft.pack.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.dom4j.Element;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

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
	
	public static void SaveNavInfo(PackInfoModel[] pack)
	{
		System.out.println("开始保存");
		Element root =xmlo.getRootElement();
		if(root==null)
			xmlo.initXml(Navinfo.getRootName());
		List<Element> haveele = root.elements(Navinfo.getPackName());
		for(int j=0;j<haveele.size();j++)
		{			
			xmlo.getRootElement().remove(haveele.get(j));
		}
		for(int i=0;i<pack.length;i++)
		{
			Element packxml =xmlo.addElementInRoot(Navinfo.getPackName(), Navinfo.getAttriPackName(), pack[i].getName());
			if(!xmlo.isEqualByAttribute(packxml, Navinfo.getAttriPackPath(), pack[i].getSavePath()))
			packxml.addAttribute(Navinfo.getAttriPackPath(), pack[i].getSavePath());
		}
		selOperate();
		
		xmlo.save();
		xmlo.close();
	}
	
	public static boolean setedOperate()
	{
		Element operate =xmlo.OnlyElementInRoot(getOperate());
		System.out.println("---"+operate.getText());
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
	public static List<PackInfoModel> readPackNavInfo()
	{
		List<PackInfoModel> packs = new ArrayList<PackInfoModel>();
		try 
		{ //读取保存的更新包列表
		if(!exists())
		{
			SaveNavInfo(packs.toArray(new PackInfoModel[0]));
			return packs;
		}
		
        Element root = xmlo.getRootElement();
        for (Iterator i = root.elementIterator(Navinfo.getPackName()); i.hasNext();) {
            Element packinfo = (Element) i.next();
            String name = packinfo.attributeValue(Navinfo.getAttriPackName());
            String path = packinfo.attributeValue(Navinfo.getAttriPackPath());
            PackInfoModel  pack = new PackInfoModel(name,path);
            packs.add(pack);
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
