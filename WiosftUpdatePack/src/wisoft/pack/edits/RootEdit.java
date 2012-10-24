package wisoft.pack.edits;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.events.PackEditEvent;
import wisoft.pack.events.PackEditEventListener;

public class RootEdit extends Composite {
//	private Text text;
//	private Text text_1;
	private Label label;
	protected String titleName;
	private Vector<PackEditEventListener> vectorListeners=new Vector<PackEditEventListener>();
	public synchronized void addPackEditListener(PackEditEventListener ml)
    {
        vectorListeners.addElement(ml);
    }
    
    public synchronized void removePackEditListener(PackEditEventListener ml)
    {
        vectorListeners.removeElement(ml);
    }
    
    //@SuppressWarnings("unchecked")
	protected void activatePackEditEvent()
    {
        Vector<PackEditEventListener> tempVector=null;
        PackEditEvent e=new PackEditEvent(this);
        synchronized(this)
        {
            tempVector=(Vector<PackEditEventListener>)vectorListeners.clone();
            for(int i=0;i<tempVector.size();i++)
            {
            	PackEditEventListener ml=(PackEditEventListener)tempVector.elementAt(i);
                ml.PackEditIsDirty(e);
            }
        }
    }
	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
		this.label.setText(titleName);
	}

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RootEdit(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		Composite comp = new Composite(this, SWT.NONE);
		comp.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		comp.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_comp = new FormData();
		fd_comp.top = new FormAttachment(0, 1);
		fd_comp.bottom = new FormAttachment(0, 32);
		fd_comp.left = new FormAttachment(0);
		fd_comp.right = new FormAttachment(100);
		comp.setLayoutData(fd_comp);
		
		label = new Label(comp, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 13, SWT.BOLD | SWT.ITALIC));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setText("\u57FA\u672C\u4FE1\u606F");
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0, 3);
		fd_toolBar.left = new FormAttachment(80);
		
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
