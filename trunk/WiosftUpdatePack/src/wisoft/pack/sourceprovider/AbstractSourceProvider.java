package wisoft.pack.sourceprovider;

import java.util.HashMap;
import java.util.Map;

import wisoft.pack.utils.PackConfigInfo;

public class AbstractSourceProvider extends
		org.eclipse.ui.AbstractSourceProvider {
	
	 public final static String IS_OPR = "wisoftupdatepack.isopr";

	public AbstractSourceProvider() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public Map getCurrentState() {
		// TODO Auto-generated method stub
		Map	map = new HashMap();
		boolean isOpr = PackConfigInfo.getInstance().selOperate();
		map.put(IS_OPR, isOpr);
		return map;
	}

	@Override
	public String[] getProvidedSourceNames() {
		// TODO Auto-generated method stub
		return new String[]{IS_OPR};
	}

}
