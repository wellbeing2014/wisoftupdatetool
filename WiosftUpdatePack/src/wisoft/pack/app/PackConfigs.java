package wisoft.pack.app;

import org.eclipse.osgi.util.NLS;

public class PackConfigs extends NLS {
	private static final String BUNDLE_NAME = "wisoft.pack.app.PackConfigs"; //$NON-NLS-1$
	public static String BuildPath;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, PackConfigs.class);
	}

	private PackConfigs() {
	}
}
