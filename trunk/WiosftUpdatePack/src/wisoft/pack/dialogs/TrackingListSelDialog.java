package wisoft.pack.dialogs;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.app.Activator;

import com.wisoft.framework.wims.pojo.WimsSingleIssueTracking;
import com.wisoft.framework.wims.servicein.TrackServicesIn;
import com.wisoft.framework.wims.servicereturn.resultReturn;
import com.wisoft.framework.wims.ws.IWimsManagerWS;

public class TrackingListSelDialog extends Dialog {
	private Text text;
	private Table table;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public TrackingListSelDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell shell) {  
	    super.configureShell(shell);  
	    shell.setText("≤È—Ø—°‘ÒŒ Ã‚µ•");  
	    shell.setImage(new Image(null,Activator.getImageDescriptor("icons/wi_updatetool.ico").getImageData()));
	}  
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		
		text = new Text(container, SWT.BORDER);
		text.setTabs(11);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(text.getText().equals("\u8BF7\u8F93\u5165\u95EE\u9898\u5355\u53F7\u3001\u9879\u76EE\u3001\u7533\u8BF7\u4EBA\uFF08\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2\uFF09"))
				{
					text.setText("");
					text.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
					text.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 9, SWT.NORMAL));
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText().length()==0)
				{
					text.setText("\u8BF7\u8F93\u5165\u95EE\u9898\u5355\u53F7\u3001\u9879\u76EE\u3001\u7533\u8BF7\u4EBA\uFF08\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2\uFF09");
					text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
					text.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 9, SWT.ITALIC));
				}
			}
		});
		
		text.setText("\u8BF7\u8F93\u5165\u95EE\u9898\u5355\u53F7\u3001\u9879\u76EE\u3001\u7533\u8BF7\u4EBA\uFF08\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2\uFF09");
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		text.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 9, SWT.ITALIC));
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				URL url = null;
				try {
					url=new URL("http://58.214.246.37:8120/wisoftintegrateframe/services/WimsManager?wsdl");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Service s=new ObjectServiceFactory().create(IWimsManagerWS.class);
				XFireProxyFactory xf=new XFireProxyFactory(XFireFactory.newInstance().getXFire());
				resultReturn rR = new resultReturn();
				 TrackServicesIn trackparam = new TrackServicesIn();
		        trackparam.setFpqk("all");
		        trackparam.setState("1");
		        trackparam.setZrpersonid("all");
		        trackparam.setSearch(text.getText());
				try{
					IWimsManagerWS iwmg =(IWimsManagerWS)xf.create(s,"http://58.214.246.37:8120/wisoftintegrateframe/services/WimsManager");
					rR = iwmg.findTrackByServicesInParames(trackparam, 0, 10);
				}
				catch(Exception e2)
				{
					e2.printStackTrace();
				}
				
		        table.removeAll();
		        for(int i=0;i<rR.getLst().size();i++)
		        {
		        	WimsSingleIssueTracking single = (WimsSingleIssueTracking)rR.getLst().get(i);
		        	 TableItem item = new TableItem(table, SWT.NONE);
		        	 item.setText( new String[] { single.getLsh(), 
		        			 					  single.getProid(),
		        			 					  single.getSqpersonid(),
		        			 					  single.getContent() });
		        }
		      
			}
		});
		btnNewButton.setText("\u67E5\u8BE2");
		btnNewButton.setFocus();
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u95EE\u9898\u5355\u53F7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(175);
		tblclmnNewColumn_1.setText("\u9879\u76EE");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(282);
		tableColumn.setText("\u63CF\u8FF0");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(589, 392);
	}

}
