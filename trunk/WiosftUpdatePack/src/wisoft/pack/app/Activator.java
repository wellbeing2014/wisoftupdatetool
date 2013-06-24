package wisoft.pack.app;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.adaptor.LocationManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.PackConfigInfo;

/**
 * The activator class controls the plug-in life cycle
 */
@SuppressWarnings("restriction")
public class Activator extends AbstractUIPlugin {

	
	// The plug-in ID
	public static final String PLUGIN_ID = "WiosftUpdatePack"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private PackInfoModel current_pack;
	public PackInfoModel getCurrent_pack() {
		return current_pack;
	}

	public void setCurrent_pack(PackInfoModel current_pack) {
		this.current_pack = current_pack;
	}
	
	public String getLocationPath()
	{
		Location installLoc = LocationManager.getInstallLocation(); 
	    String path = null; 
	    String installPath = null; 
	    if (installLoc != null) 
	    {  

	    	URL installURL = installLoc.getURL(); 
	        // assume install URL is file: based 
	        path = installURL.getPath(); 
	    } 

	    installPath = path.substring(1, path.length()); 
	    return installPath;
	}

	public String getWorkSpacePath()
	{
		return Platform.getInstanceLocation().getURL().getPath();
	}
	
	private ApplicationContext context ;
	/**
	 * The constructor
	 */
	public Activator() {
		this.context = new ClassPathXmlApplicationContext( "wisoft/pack/data/conf_spring.xml" );
	}

	public Object getSpringBean(String beanname)
	{
		return this.context.getBean(beanname);
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		System.out.println(getLocationPath());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	
}
