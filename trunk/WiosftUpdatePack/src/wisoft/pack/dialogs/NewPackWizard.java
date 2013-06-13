package wisoft.pack.dialogs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import wisoft.pack.data.pojo.PackPackages;
import wisoft.pack.data.pojo.PackageInfo;
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
		setWindowTitle("����һ�����°�");
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
		PackPackages packinfo = new PackPackages();
		String savePath1 = this.page1.text_2.getText().trim();
		final String ModuleName = this.page1.combo.getText().trim();
		final String ModuleCode = this.page1.text.getText().trim();
		final String version = this.page1.text_1.getText().trim();
		final String createMan = this.page1.text_3.getText().trim();
		
		final String releasenot = this.page2.text.getText().trim();
		final String keyword = this.page2.text_1.getText().trim();
		final String packname = ModuleName+"("+ModuleCode+")"+version;
		File savpathdir = new File(savePath1.substring(0, savePath1.lastIndexOf("/"))); 
		final String savePath = savePath1;
		packinfo.setPackageName(packname);
		packinfo.setModuleName(ModuleName);
		packinfo.setModuleCode(ModuleCode);
		packinfo.setModuleVer(version);
		packinfo.setPackPerson(createMan);
		packinfo.setState(0);
		packinfo.setSavePath(savePath);
		
		if(!savpathdir.exists())
		{
			MessageBox mb = new MessageBox(this.getShell(),SWT.ERROR);
			mb.setMessage("���°�����·��������");
			mb.setText("��ʾ");
			mb.open();
			return false;
		}
			
		final PackInfoModel pack  = new PackInfoModel(packinfo);
		Console.getInstance().print("�������°���ʼ����", packname, Console.ConsoleType.INFO);	
		
		Job job = new Job("�������°�") {
			
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
				monitor.beginTask("��ʼ����顭��", 3);
				try
				{
					// TODO Auto-generated method stub
					Thread.sleep(1000);
					//monitor.setTaskName("����");
			        if(createMan.isEmpty())
			        	printlnToConsole("������δ���á�",packname,Console.ConsoleType.WARNING);
			        if(releasenot.isEmpty())
			        	printlnToConsole("����˵��δ��д��",packname,Console.ConsoleType.WARNING);
			        if(keyword.isEmpty())
			        	printlnToConsole("�ؼ���Ϊ�ա�",packname,Console.ConsoleType.WARNING);
			        monitor.worked(1);
			        monitor.setTaskName("�������°�����");
			        Thread.sleep(1000);
					//pack.setSavePath(savePath);
					pack.setModuleCode(ModuleCode);
					pack.setModuleName(ModuleName);
					pack.setVersion(version);
					pack.setKeyWord(keyword);
					pack.setCreateMan(createMan);
					pack.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					pack.setReleaseNote(releasenot);
					pack.saveIntoXML();
					
					File updatedir = new File(savePath+"/"+UpdateInfo.UpdateDirName);
					updatedir.mkdirs();
					monitor.worked(2);
					monitor.setTaskName("���°��Ѵ���");
					Thread.sleep(1000);
					monitor.worked(3);
				}
				catch(Exception e)
				{
					printlnToConsole("�������°�ʧ�ܣ�",packname,Console.ConsoleType.ERROR);
					printlnToConsole(e.toString(),packname,Console.ConsoleType.ERROR);
				}
				printlnToConsole("���°������ɹ�", packname, Console.ConsoleType.INFO);	
		        return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.schedule(); 
	
		nv.addPackInfo(pack);
		return true;
	}
}
