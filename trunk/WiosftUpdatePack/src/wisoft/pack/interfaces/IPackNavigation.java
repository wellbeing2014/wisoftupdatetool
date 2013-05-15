package wisoft.pack.interfaces;

import wisoft.pack.models.PackInfoModel;

public interface IPackNavigation {

	public void SaveNavInfo();
	public PackInfoModel[] getAllPackInfo();
}
