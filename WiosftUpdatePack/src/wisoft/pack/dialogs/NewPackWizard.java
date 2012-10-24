package wisoft.pack.dialogs;

import org.eclipse.jface.wizard.Wizard;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.NavigationView;

public class NewPackWizard extends Wizard {
	private NewPackWizardPage page1;
	private NewPackReleaseNoteWizardPage page2;
	private NavigationView nv ;

	public NewPackWizard(NavigationView nv) {
		setWindowTitle("创建一个更新包");
		page1 =new NewPackWizardPage();
		page2 = new NewPackReleaseNoteWizardPage();
		this.nv = nv;
	}

	@Override
	public void addPages() {
		
		addPage(page1);
		addPage(page2);
	}

	@Override
	public boolean performFinish() {
		String packname =this.page1.combo.getText().trim()
						+"("+this.page1.text.getText().trim()+")"
						+this.page1.text_1.getText().trim();
		PackInfoModel pack = new PackInfoModel(packname);
		nv.addPackInfo(pack);
		
		System.out.println(this.page1.text.getText());
		System.out.println(this.page1.text_1.getText());
		System.out.println(this.page1.text_2.getText());
		System.out.println(this.page1.text_3.getText());
		System.out.println(this.page1.combo.getText());
		System.out.println(this.page2.text.getText());
		System.out.println(this.page2.text_1.getText());
		return true;
	}

}
