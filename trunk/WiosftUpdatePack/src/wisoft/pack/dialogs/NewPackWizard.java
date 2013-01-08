package wisoft.pack.dialogs;

import java.io.File;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;
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
		final String savePath = this.page1.text_2.getText().trim();
		final String ModuleName = this.page1.combo.getText().trim();
		final String ModuleCode = this.page1.text.getText().trim();
		final String version = this.page1.text_1.getText().trim();
		final String createMan = this.page1.text_3.getText().trim();
		
		final String releasenot = this.page2.text.getText().trim();
		final String keyword = this.page2.text_1.getText().trim();
		final String packname = ModuleName+"("+ModuleCode+")"+version;
		final PackInfoModel pack  = new PackInfoModel(packname,savePath);
		Console.getInstance().print("创建更新包开始……", packname, Console.ConsoleType.INFO);	
		
		Job job = new Job("创建更新包") {
			
			private void printlnToConsole(final String msg,final String packname,final ConsoleType type)
			{
				Display.getDefault().asyncExec(new Runnable() {                        
	    			public void run() {                                                                                    
	    				Console.getInstance();
	    				Console.print(msg, packname, type);
	    				//nv.addPackInfo(pack);
	    			}});
			}
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("初始化检查……", 3);
				try
				{
					// TODO Auto-generated method stub
					Thread.sleep(1000);
					//monitor.setTaskName("……");
			        if(createMan.isEmpty())
			        	printlnToConsole("创建人未设置。",packname,Console.ConsoleType.WARNING);
			        if(releasenot.isEmpty())
			        	printlnToConsole("更新说明未填写。",packname,Console.ConsoleType.WARNING);
			        if(keyword.isEmpty())
			        	printlnToConsole("关键词为空。",packname,Console.ConsoleType.WARNING);
			        monitor.worked(1);
			        monitor.setTaskName("建立更新包……");
			        Thread.sleep(1000);
					pack.setSavePath(savePath);
					pack.setModuleCode(ModuleCode);
					pack.setModuleName(ModuleName);
					pack.setVersion(version);
					pack.setKeyWord(keyword);
					pack.setCreateMan(createMan);
					pack.setCreateTime(new Date().toLocaleString());
					pack.setReleaseNote(releasenot);
					pack.saveIntoXML();
					
					File updatedir = new File(savePath+"/"+UpdateInfo.UpdateDirName);
					updatedir.mkdirs();
					monitor.worked(2);
					monitor.setTaskName("更新包已创建");
					Thread.sleep(1000);
					monitor.worked(3);
				}
				catch(Exception e)
				{
					printlnToConsole("创建更新包失败！",packname,Console.ConsoleType.ERROR);
					printlnToConsole(e.toString(),packname,Console.ConsoleType.ERROR);
				}
				printlnToConsole("更新包创建成功", packname, Console.ConsoleType.INFO);	
		        return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule(); 
	
		nv.addPackInfo(pack);
		return true;
	}
}
