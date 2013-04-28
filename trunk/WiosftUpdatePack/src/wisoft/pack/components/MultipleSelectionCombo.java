package wisoft.pack.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MultipleSelectionCombo extends Composite { 
	
	 Text _text=null; 
	 String[] _items=null; 
	 int[] _selection=null; 
	 Shell _floatShell = null; 
	 List _list = null; 

	 public MultipleSelectionCombo(Composite parent, 
					 String[] items, 
					 int[] selection, 
					 int style){ 
		 super(parent, style); 
		 _selection = selection; 
		 _items = items; 
		 init(); 
		 } 
		
		 private void init(){ 
			 GridLayout layout = new GridLayout(); 
			 layout.marginBottom =0; 
			 layout.marginTop = 0; 
			 layout.marginLeft =0; 
			 layout.marginRight = 0; 
			 layout.marginWidth = 0; 
			 layout.marginHeight = 0; 
			 setLayout(new GridLayout()); 
			 _text = new Text(this, SWT.BORDER | SWT.READ_ONLY); 
			 _text.setLayoutData(new GridData(GridData.FILL_BOTH)); 
		 } 

	 }
