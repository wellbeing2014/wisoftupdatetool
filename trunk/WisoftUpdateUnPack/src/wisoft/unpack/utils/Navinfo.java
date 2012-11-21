package wisoft.unpack.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Navinfo {
	private static final String BUNDLE_NAME = "wisoft.unpack.utils.navinfo"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Navinfo() {
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
}
