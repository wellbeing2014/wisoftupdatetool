package wisoft.pack.models;

public enum PackFolder {
	UNUPDATE("δ����"), UPDATED("�Ѹ���"), ARCHIVED("�ѹ鵵"),DEFALUT("Ĭ���ļ���");
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
