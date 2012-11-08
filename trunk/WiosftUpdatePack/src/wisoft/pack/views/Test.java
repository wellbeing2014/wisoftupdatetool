package wisoft.pack.views;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class Test extends ViewPart {
	public Test() {
	}
 public static final String ID = "rcpdemo.view";

 private TableViewer viewer;

 /**
  * The content provider class is responsible for providing objects to the
  * view. It can wrap existing objects in adapters or simply return objects
  * as-is. These objects may be sensitive to the current input of the view,
  * or ignore it and always show the same content (like Task List, for
  * example).
  */
 class ViewContentProvider implements IStructuredContentProvider {
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
  }
 
  public void dispose() {
  }

  public Object[] getElements(Object parent) {
   if (parent instanceof Object[]) {
    return (Object[]) parent;
   }
         return new Object[0];
  }
 }

 class ViewLabelProvider extends LabelProvider implements
   ITableLabelProvider {
  public String getColumnText(Object obj, int index) {
   return getText(obj);
  }

  public Image getColumnImage(Object obj, int index) {
   return getImage(obj);
  }

  public Image getImage(Object obj) {
   return PlatformUI.getWorkbench().getSharedImages().getImage(
     ISharedImages.IMG_OBJ_ELEMENT);
  }
 }

 //private static String fileSplitString = System.getProperty("file.separator");
 private static String projectPathString = "f:";
 
 private static String rcpfFlagString = ".rcpf";
 private static File file = new File(projectPathString);
 
 Tree upTree = null;
 
 
 
 private void initProjectFile(File file,Set<File> projectSet){ 
  File allFile [] = file.listFiles(); 
  for (File allfile : allFile) {
   if(allfile.isDirectory()){ 
    String list [] = allfile.list(new FilenameFilter() {
     boolean flag = false;
     public boolean accept(File dir, String name) {
      if(name.endsWith(rcpfFlagString)){
       flag = true;
      }
      
      return flag;
     }
    });
    
    if(list !=null && list.length>0){ 
     projectSet.add(allfile);
    }
   }
  }
 }
 
 
 private void showAllProjectFile(Set<File> fileSet){
 for (File allfile : fileSet) {
  String fileName = allfile.getName();
  TreeItem treeItem  = new TreeItem(upTree, SWT.NONE);
  treeItem.setText(fileName);
  if(allfile.isDirectory()){    
   forShowProjectFile(treeItem,allfile); 
  }
   
  
 }
}
 
 int i = 0 ;
 private void forShowProjectFile(TreeItem treeItem ,File file){
  File [] listFiles = file.listFiles();
  Map<TreeItem,File> childFileMap = null;
  for (File allfile : listFiles) {
   i++; 
   String fileName = allfile.getName();
   if(!fileName.endsWith(rcpfFlagString)){
    System.out.println(file.toString()+">>>"+fileName+">>>"+allfile.getParentFile().toString());
    String treeName = fileName.indexOf(".") != -1 ? fileName.substring(0,fileName.indexOf(".")): fileName;
    if(i != listFiles.length){
     TreeItem flagTreeItem = new TreeItem(treeItem, SWT.NULL);
     flagTreeItem.setText(treeName);
     if(allfile.isDirectory() && (allfile.list().length>0)){     
      System.out.println("DfDFDFDfd"+allfile.list().length);
      childFileMap = new HashMap<TreeItem,File>();
      //List<File> list = new ArrayList<File>();
      childFileMap.put(flagTreeItem,allfile);
      //list.add(allfile);
     }
    }else{
     treeItem =  new TreeItem(treeItem, SWT.NULL);
     treeItem.setText(treeName);
     i = 0;
     if(childFileMap != null){
      Set<TreeItem> entrySet = childFileMap.keySet();
      for (TreeItem treeItem2 : entrySet) {
       forShowProjectFile(treeItem2,childFileMap.get(treeItem2));
      }
     }
    }
   }
  }
 }
 /**
  * This is a callback that will allow us to create the viewer and initialize
  * it.
  */
 public void createPartControl(Composite parent) {
  Set<File> projectSet = new HashSet<File>();
  SashForm sashForm = new SashForm(parent, SWT.VERTICAL|SWT.SMOOTH);
  Group upGroup = new Group(sashForm, SWT.NONE);
  upGroup.setLayout(new FillLayout());
  upTree = new Tree(upGroup, SWT.SINGLE);
  initProjectFile(file,projectSet);
  showAllProjectFile(projectSet);

  
  
  /*treeItem.setText("Test Tmss Manager");
  new TreeItem(treeItem, SWT.NULL).setText("tmsskey1");
  new TreeItem(treeItem, SWT.NULL).setText("tmsskey2");
  new TreeItem(treeItem, SWT.NULL).setText("tmsskey3");
  new TreeItem(treeItem, SWT.NULL).setText("tmsskey4");*/
  
  
  viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
    | SWT.V_SCROLL);
  viewer.setContentProvider(new ViewContentProvider());
  viewer.setLabelProvider(new ViewLabelProvider());
  // Provide the input to the ContentProvider
  viewer.setInput(new String[] {"One", "Two", "Three"});
 }

 

 /**
  * Passing the focus request to the viewer's control.
  */
 public void setFocus() {
  //viewer.getControl().setFocus();
 }
}
