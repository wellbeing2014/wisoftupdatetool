package wisoft.pack.edits.sql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public final class ColorManager {
	public static final RGB BACKGROUND = new RGB(255, 255, 255);
	public static final RGB MULTI_LINE_COMMENT = new RGB(255, 0, 0);
	public static final RGB SINGLE_LINE_COMMENT = new RGB(128, 128 , 128);
	public static final RGB KEYWORD = new RGB(0, 0, 128);
	public static final RGB TYPE = new RGB(128, 0, 0);
	public static final RGB STRING = new RGB(0, 96, 0);
	public static final RGB DEFAULT = new RGB(0, 0, 0);

	protected Map fColorTable = new HashMap(7);

	public void dispose()
	{
		Iterator e = this.fColorTable.values().iterator();
		while (e.hasNext())
			((Color)e.next()).dispose();
	}

	public Color getColor(RGB rgb)
	{
		Color color = (Color)this.fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			this.fColorTable.put(rgb, color);
		}
		return color;
	}
}
