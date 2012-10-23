package wisoft.pack.events;

import java.util.EventListener;

public class PackEditEventListener implements EventListener {
	  
	public void EventActivated(PackEditEvent me)
	{
		System.out.println("事件已经被触发");
	}

}
