package demo.treeviewer;
 
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
 
public class TreeViewer1 {
       
        public static void main(String[] args) {
                TreeViewer1 window = new TreeViewer1();
                window.open();
        }
       
        public void open(){
                final Display display = new Display();
                final Shell shell = new Shell();
                shell.setSize(200, 300);
               
                shell.setLayout(new FillLayout());
                Composite c = new Composite(shell, SWT.NONE);
                c.setLayout(new FillLayout());
                TreeViewer tv = new TreeViewer(c, SWT.BORDER);
                tv.setContentProvider(new TreeViewContentProvider());
                tv.setLabelProvider(new TreeViewerLableProvider());
               
                Object inputObj = DataFactory.createTreeData();
                tv.setInput(inputObj);
               
                shell.open();
                while(!shell.isDisposed()) {
                        if(!display.readAndDispatch()) {
                                display.sleep();
                        }
                }
        }
 
}