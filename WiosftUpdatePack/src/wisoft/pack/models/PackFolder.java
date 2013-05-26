package wisoft.pack.models;

public enum PackFolder {
	UNUPDATE("未更新"), UPDATED("已更新"), ARCHIVED("已归档"),DEFALUT("默认文件夹");
	private String foldername ;
	
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	private PackFolder(String name)
	{
		this.foldername = name;
	}
	public static PackFolder getFolder(String foldername)
	{
		PackFolder rn =PackFolder.DEFALUT;
		for(PackFolder pf:PackFolder.values())
		{
			if(pf.getFoldername().equals(foldername))
			{
				rn = pf;
				return rn;
			}
		}
		return rn;
	}
}
