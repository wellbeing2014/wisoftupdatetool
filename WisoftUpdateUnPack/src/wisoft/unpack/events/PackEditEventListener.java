package wisoft.unpack.events;

import java.util.EventListener;

public class PackEditEventListener implements EventListener {
	  
	public void PackEditIsDirty(PackEditEvent me)
	{
		System.out.println("PackEdit have been dirty");
	}
	

}
