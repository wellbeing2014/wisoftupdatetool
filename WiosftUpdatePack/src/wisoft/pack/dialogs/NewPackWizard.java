package wisoft.pack.dialogs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.MessageBox;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.views.NavigationView;
import wisoft.pack.views.Console;

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
		final PackInfoModel pack = new PackInfoModel(packname);
		try
		{
			pack.setSavePath(this.page1.text_2.getText().trim());
			pack.setModuleCode(this.page1.text.getText().trim());
			pack.setModuleName(this.page1.combo.getText().trim());
			pack.setVersion(this.page1.text_3.getText());
			pack.saveUpdateInfoXml();
			nv.addPackInfo(pack);
		}
		catch(Exception e)
		{
			MessageBox mb = new MessageBox(this.getShell());
			mb.setMessage("错误");
			mb.setText(e.toString());
			mb.open();
		}
		Job job = new Job("name") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// TODO Auto-generated method stub
				monitor.beginTask("开始任务", IProgressMonitor.UNKNOWN);
		        monitor.setTaskName("Step 1");
		        Console.getInstance().print("aa", pack.getName(), Console.ConsoleType.INFO);
//		        dothing1();
//		        monitor.setTaskName("Step 2");
//		        Console.getInstance().info("开始第二步");
//		        dothing2();
//		        monitor.setTaskName("Step 3");
//		        Console.getInstance().info("开始第三步");
//		        dothing3();
//		        Console.getInstance().info("完毕");                                                                            
//		        monitor.done();    
//		        Display.getDefault().asyncExec(new Runnable() {                        
//		            public void run() {                                                                                    
//		                //UI任务
//		            }
//		        });
		        return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule();  
		
		
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
