package wisoft.pack.events;

import java.util.EventObject;

import wisoft.pack.utils.ZipUtil;


public class ZipHandleEvent extends EventObject{

	public int totalFileNum;
	public int curFileNum;
	public String curFileName;
	private static final long serialVersionUID = 1L;
	public ZipHandleEvent(Object source) {
		super(source);
		ZipUtil zip = (ZipUtil)source;
		totalFileNum = zip.FilesNum;
		curFileNum = zip.curHandleNum;
		curFileName = zip.curHandleFileName;
		// TODO Auto-generated constructor stub
	}

}
