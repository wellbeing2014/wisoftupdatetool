package wisoft.pack.dialogs;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

import com.wisoft.wims.WimsSingleIssueTracking;

import wisoft.pack.edits.XmlSqlEditorInput;
import wisoft.pack.events.ZipHandleEvent;
import wisoft.pack.events.ZipHandleEventListener;
import wisoft.pack.models.FileModel;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.PackRelyModel;
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
					
					zip.appendText(pack.getName()+"_RealseNote.txt", createRealseNote());
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
	
	private String createRealseNote()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("************************************************************************\r\n");
		sb.append("                           ��"+pack.getName()+"����˵����\r\n");
		sb.append("    ƽ̨���ƣ�  "+pack.getModuleName()+"\r\n");
		sb.append("    ƽ̨���룺  "+pack.getModuleCode()+"\r\n");
		sb.append("    ƽ̨�汾��  "+pack.getVersion()+"\r\n");
		sb.append("    �������ڣ�  "+pack.getCreateTime()+"\r\n");
		sb.append("    �����ˣ�       "+pack.getCreateMan()+"\r\n");
		sb.append("    �������ڣ�  "+(new Date().toLocaleString())+"\r\n");
		sb.append("    �ؼ��֣�       "+pack.getKeyWord()+"\r\n");
		sb.append("************************************************************************\r\n");
		sb.append("\r\n");
		sb.append("�����·�Χ��\r\n");
		if(pack.getScopeFront())
			sb.append("   ǰ̨");
		if(pack.getScopeBack())
			sb.append("   ��̨");
		if(pack.getScopeDB())
			sb.append("   ���ݿ�");
		sb.append("\r\n");
		sb.append("\r\n");
		sb.append("������˵����\r\n");
		sb.append("   "+pack.getReleaseNote()+"\r\n");
		sb.append("\r\n");
		sb.append("���ֶ�����˵����\r\n");
		List<FileModel> conffiles = pack.getConfFiles();
		for(int i=0;i<conffiles.size();i++)
		{
			sb.append("   "+(i+1)+"��"+"��"+conffiles.get(i).getConftype()+"�� ·��Ϊ�� " +conffiles.get(i).getFullPath()+"\r\n");
			sb.append("       ���޸ķ�����   "+conffiles.get(i).getContent()+"\r\n");
		}
		
		sb.append("\r\n");
		sb.append("������������\r\n");
		List<PackRelyModel> relys = pack.getPackRelys();
		for(int i=0;i<relys.size();i++)
		{
			sb.append("   "+(i+1)+"��"+relys.get(i).toString()+"     �������ڣ�"+relys.get(i).getPublishTime()+"\r\n");
		}
		
		sb.append("\r\n");
		sb.append("���������ⵥ��\r\n");
		List<WimsSingleIssueTracking> trackrelys = pack.getTrackRelys();
		for(int i=0;i<trackrelys.size();i++)
		{
			sb.append("   "+(i+1)+"�� ���ⵥ�ţ�"+trackrelys.get(i).getLsh()+"     �����ˣ�"+trackrelys.get(i).getSqpersonid()
					+"      ������Ŀ��"+trackrelys.get(i).getProid()+"\r\n");
			sb.append("     ���ⵥ���ݣ�"+trackrelys.get(i).getContent()+"\r\n");
		}
		sb.append("\r\n");
		
		return sb.toString();
	}

}
