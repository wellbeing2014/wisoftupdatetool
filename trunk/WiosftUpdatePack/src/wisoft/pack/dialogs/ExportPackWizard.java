package wisoft.pack.dialogs;

import java.io.File;
import java.io.FileWriter;

import javax.sound.sampled.AudioFormat.Encoding;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import wisoft.pack.edits.XmlSqlEditorInput;
import wisoft.pack.events.ZipHandleEvent;
import wisoft.pack.events.ZipHandleEventListener;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.utils.UpdateInfo;
import wisoft.pack.utils.ZipUtil;
import wisoft.pack.views.Console;
import wisoft.pack.views.Console.ConsoleType;

public class ExportPackWizard extends Wizard {
	private PackInfoModel pack;
	private ExportPackWizardPage page;
	public ExportPackWizard(PackInfoModel pack) {
		setWindowTitle("��������һ�����°�");
		this.pack = pack;
		this.page = new ExportPackWizardPage(pack);
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		
		
		final String exportpath  = this.page.text.getText()+"/"+this.page.text_1.getText();
		if(exportpath.trim().isEmpty())
		{
			page.setErrorMessage("��ѡ��һ��·�������浼���ĸ��°�"); 
			return false;
		}
		Console.getInstance().print("�������°���ʼ����", pack.getName(), Console.ConsoleType.INFO);	
		Job job = new Job("�������°�") {
			
			private void printlnToConsole(final String msg,final ConsoleType type)
			{
				Display.getDefault().asyncExec(new Runnable() {                        
	    			public void run() {                                                                                    
	    				Console.getInstance();
	    				Console.print(msg, pack.getName(), type);
	    				//nv.addPackInfo(pack);
	    			}});
			}
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				// TODO Auto-generated method stub
				monitor.beginTask("������ʼ",  IProgressMonitor.UNKNOWN);
				ZipUtil zip = new ZipUtil(exportpath,"GBK");
				zip.addZipEventListener(new ZipHandleEventListener(){
					@Override
					public void ZipHandle(ZipHandleEvent me) {
						monitor.worked(me.curFileNum);
						monitor.setTaskName(me.curFileName);
						try {
							Thread.sleep(80);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				try {
					StringBuffer sb = new StringBuffer();
					sb.append("************************************************************************\r\n");
					sb.append("                       "+pack.getName()+"����˵��\r\n");
					sb.append("    ���������ڡ�  "+pack.getModuleCode()+"\r\n");
					sb.append("************************************************************************\r\n");
					
					zip.appendText(pack.getName()+"_RealseNote.txt", sb.toString());
					zip.appendFile(pack.getSavePath()+File.separator+UpdateInfo.UpdateDirName,"");
					zip.appendFile(pack.getSavePath()+File.separator+XmlSqlEditorInput.TYPE_SQL, pack.getName()+"_DataBase.sql");
					zip.close();
					printlnToConsole("�������°���ɣ�",ConsoleType.INFO);
					monitor.done();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					printlnToConsole(e.toString(),ConsoleType.ERROR);;
				}
				return Status.OK_STATUS;
			}
			
		};
		job.setUser(true);
		job.schedule();
		return true;
	}

}
