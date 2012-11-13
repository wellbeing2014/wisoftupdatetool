package wisoft.pack.dialogs;

import org.eclipse.jface.wizard.Wizard;

import wisoft.pack.models.PackInfoModel;

public class ExportPackWizard extends Wizard {

	private ExportPackWizardPage page;
	public ExportPackWizard(PackInfoModel pack) {
		setWindowTitle("即将导出一个更新包");
		this.page = new ExportPackWizardPage(pack);
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		
		
		final String exportpath  = this.page.text.getText();
		if(exportpath.trim().isEmpty())
		{
			page.setErrorMessage("请选择一个路径来保存导出的更新包"); 
			return false;
		}
		return true;
	}

}
