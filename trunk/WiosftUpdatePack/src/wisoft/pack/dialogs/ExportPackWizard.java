package wisoft.pack.dialogs;

import org.eclipse.jface.wizard.Wizard;

import wisoft.pack.models.PackInfoModel;

public class ExportPackWizard extends Wizard {

	private ExportPackWizardPage page;
	public ExportPackWizard(PackInfoModel pack) {
		setWindowTitle("��������һ�����°�");
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
			page.setErrorMessage("��ѡ��һ��·�������浼���ĸ��°�"); 
			return false;
		}
		return true;
	}

}
