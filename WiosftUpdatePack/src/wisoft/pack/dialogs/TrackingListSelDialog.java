package wisoft.pack.dialogs;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.app.Activator;

import com.wisoft.wims.IWimsManagerWSPortType;
import com.wisoft.wims.ResultReturn;
import com.wisoft.wims.TrackServicesIn;
import com.wisoft.wims.TrankingManager;
import com.wisoft.wims.WimsSingleIssueTracking;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class TrackingListSelDialog extends Dialog {
	private Text text;
	private Table table;
	public List<WimsSingleIssueTracking> wimstracklist = new ArrayList<WimsSingleIssueTracking>();
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
	
	@Override
	protected void okPressed() {
		for(TableItem item :table.getSelection())
		{
			wimstracklist.add((WimsSingleIssueTracking)item.getData());
		}
		super.okPressed();
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
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode== SWT.CR || e.keyCode== SWT.KEYPAD_CR)
					queryWimsTrack();
			}
		});
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
				queryWimsTrack();
			}
		});
		btnNewButton.setText("\u67E5\u8BE2");
		btnNewButton.setFocus();
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u95EE\u9898\u5355\u53F7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(175);
		tblclmnNewColumn_1.setText("\u9879\u76EE");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(80);
		tableColumn.setText("\u63D0\u5355\u4EBA");
		
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(280);
		tableColumn1.setText("\u63CF\u8FF0");
		//table item ∂‡––œ‘ æ
		Listener paintListener = new Listener() {
            public void handleEvent(Event event) {
                switch(event.type) {        
                    case SWT.MeasureItem: {
                        TableItem item = (TableItem)event.item;
                        String text = getText(item, event.index);
                        Point size = event.gc.textExtent(text);
                        event.width = size.x;
                        event.height = Math.max(event.height, size.y);
                        break;
                    }
                    case SWT.PaintItem: {
                        TableItem item = (TableItem)event.item;
                        String text = getText(item, event.index);
                        Point size = event.gc.textExtent(text);                    
                       // int offset2 = event.index == 0 ? Math.max(0, (event.height - size.y) / 2) : 0;
                        int offset2 = Math.max(0, (event.height - size.y) / 2);
                        event.gc.drawText(text, event.x, event.y + offset2, true);
                        break;
                    }
                    case SWT.EraseItem: {    
                        event.detail &= ~SWT.FOREGROUND;
                        break;
                    }
                }
            }
            String getText(TableItem item, int column) {
                return item.getText(column);
            }
        };
       table.addListener(SWT.MeasureItem, paintListener);
       table.addListener(SWT.PaintItem, paintListener);
       table.addListener(SWT.EraseItem, paintListener);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(688, 485);
	}
	
	private void queryWimsTrack()
	{
		TrankingManager tm = new TrankingManager();
		IWimsManagerWSPortType iww = tm.getTrackingManager();
		TrackServicesIn trackparam = new TrackServicesIn();
	    trackparam.setFpqk("all");
	    trackparam.setState("1");
	    trackparam.setZrpersonid("all");
	    trackparam.setSearch(text.getText());
	    ResultReturn rn =iww.findTrackByServicesInParames(trackparam, 0, 10);
	    //System.out.println(((WimsSingleIssueTracking)rn.getLst().get(0)).getContent());
		
		
        table.removeAll();
        for(int i=0;i<rn.getLst().size();i++)
        {
        	WimsSingleIssueTracking single = (WimsSingleIssueTracking)rn.getLst().get(i);
        	TableItem item = new TableItem(table, SWT.NONE);
        	item.setText( new String[] { single.getLsh(), single.getProid(), single.getSqpersonid(),single.getContent(),single.getId()});
        	item.setData(single);
        }
	}
}
