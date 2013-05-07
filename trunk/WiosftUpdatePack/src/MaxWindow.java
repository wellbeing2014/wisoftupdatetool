import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

//import com.swtdesigner.SWTResourceManager;

/**
 * @author Van
 */
public class MaxWindow {

 protected Shell shell;
 private int x_init;//记录初始化X值
 private int y_init;//记录初始化Y值
 private int width_int;//记录初始化width
 private int higth_int;//记录初始化higth
 private int x_mark;//记录x在居中时的变化值
 private int y_mark;//记录y在居中时的变化值
 private boolean clickEr = true;//防止重复双击，导致程序死掉

 /**
  * Launch the application
  * 
  * @param args
  */
 public static void main(String[] args) {
  try {
   MaxWindow window = new MaxWindow();
   window.open();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 /**
  * Open the window
  */
 public void open() {
  final Display display = Display.getDefault();
  createContents();
  shell.open();
  shell.layout();
  shell.setMaximized(true);//初始窗口默认为最大化
  while (!shell.isDisposed()) {
   if (!display.readAndDispatch())
    display.sleep();//不使用情况下暂停
  }
  display.dispose();//结束销毁
 }

 /**
  * Create contents of the window
  */
 protected void createContents() {
  shell = new Shell(SWT.MIN | SWT.RESIZE);//禁用最大化操作
//  shell.setTouchEnabled(true);
//  shell.setImage(SWTResourceManager.getImage(MaxWindow.class,
//    "/app24.ico"));
  shell.setBackground(Display.getCurrent()
    .getSystemColor(SWT.COLOR_BLACK));
  shell.setSize(1262, 465);
  shell.setText("监控系统");

  /**
   * 创建显示屏幕模板
   */
  final Group group = new Group(shell, SWT.NONE);
  group.setText("窗口一");
  group.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_DARK_YELLOW));
  group.setBounds(11, 0, 304, shell.getBounds().height - 23);

  final Group group_1 = new Group(shell, SWT.NONE);
  group_1.setText("窗口二");
  group_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_DARK_GREEN));
  group_1.setBounds(321, 0, 304, shell.getBounds().height - 23);

  final Group group_1_1 = new Group(shell, SWT.NONE);
  group_1_1.setText("窗口三");
  group_1_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_YELLOW));
  group_1_1.setBounds(631, 0, 304, shell.getBounds().height - 23);

  final Group group_1_1_1 = new Group(shell, SWT.NONE);
  group_1_1_1.setText("窗口四");
  group_1_1_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_GRAY));
  group_1_1_1.setBounds(941, 0, 304, shell.getBounds().height - 23);
  //

//补充： // 添加监听事件1
  group.addMouseListener(new MouseListener() {   public void mouseUp(MouseEvent arg0) {
    // TODO Auto-generated method stub
   }
   public void mouseDown(MouseEvent arg0) {
    // TODO Auto-generated method stub
   }
   public void mouseDoubleClick(MouseEvent arg0) {
    //禁止多次双击等问题出现
    if(clickEr){
     clickEr = false;//防止多次点击状态，设置为不可用
     /**
      * 隐藏除自己以外的屏幕组件和内容
      */
     boolean visible_2 = group_1.getVisible();
     visible_2 = !visible_2;
     group_1.setVisible(visible_2);
     group_1.getParent().layout();
     boolean visible_3 = group_1_1.getVisible();
     visible_3 = !visible_3;
     group_1_1.setVisible(visible_3);
     group_1_1.getParent().layout();
     boolean visible_4 = group_1_1_1.getVisible();
     visible_4 = !visible_4;
     group_1_1_1.setVisible(visible_4);
     group_1_1_1.getParent().layout();
     //判断是否已被隐藏，隐藏则最大化自身组件，实现最大化全屏效果，否则缩放组件窗口
     if (visible_2 == false) {
      /**
       * 记录初始化信息，便于组件缩放时调用
       */
      x_init = group.getBounds().x;
      y_init = group.getBounds().y;
      width_int = group.getBounds().width;
      higth_int = group.getBounds().height;
      /**
       * 记录组件居中时坐标
       */
      x_mark = (shell.getClientArea().width / 2 - group.getSize().x/2);
      y_mark = (shell.getClientArea().height / 2 - group.getSize().y/2);
      //定义线程
      new Thread(new Runnable() {
       private boolean startSta = true;//执行状态
       /**
        * 定义坐标，宽高的接收参数
        */
       private int x;
       private int y;
       private int width;
       private int height;
       public void run() {
        while (startSta){
         //实现组件线程的同步机制
         shell.getDisplay().syncExec(new Runnable() {
          public void run() {
           //设置居中位置
           group.setLocation(shell.getClientArea().width / 2 - group.getSize().x/2, shell
                          .getClientArea().height / 2 - group.getSize().y/2);
           /**
            * 实时最大化坐标、高宽变化效果操作
            */
           if(group.getBounds().x >= shell.getBounds().x){
            x = -- group.getBounds().x;
           }
           if(group.getBounds().y >= shell.getBounds().y){
            y = -- group.getBounds().y;
           }
           if(group.getBounds().height <= shell.getBounds().height){
            height = ++ group.getBounds().height;
           }
           if(group.getBounds().width <= shell.getBounds().width){
            width = ++ group.getBounds().width;
           }
           /**
            * 为防止线程结束再运行，并将双击事件启用
            */
           if(x <= shell.getBounds().x && y <= shell.getBounds().y){
            startSta = false;
            clickEr = true;
           }
           //位置
           group.setBounds(x, y, width, height);
          }
          
         });
         Thread.yield(); // 暂停
        }
       }
      }).start(); 
      shell.setFullScreen(true);//实现组件全屏效果
      
     } else {
      new Thread(new Runnable() {
       private boolean startSta = true;
       private boolean initSta = false;
       private int x;
       private int y;
       private int width;
       private int height;
       public void run() {
        while (startSta){
         shell.getDisplay().syncExec(new Runnable() {
          public void run() {
           if(group.getBounds().x <= x_mark){
            x = ++ group.getBounds().x;
           }
           if(group.getBounds().y <= y_mark){
            y = ++ group.getBounds().y;
           } 
            
           if(group.getBounds().height >= higth_int){
            height = -- group.getBounds().height;
           }
           if(group.getBounds().width >= width_int){
            width = -- group.getBounds().width;
           }
           
           if(height <= higth_int  &&  width  <= width_int){
            initSta = true;
           }
           if(initSta){
            if(group.getBounds().x >= x_init){
             x = -- group.getBounds().x;
            }
            if(group.getBounds().y >= y_init){
             y = -- group.getBounds().y;
            } 
            if(x <= x_init  &&  y  <= y_init){
             startSta = false;
             clickEr = true;
            }
            
           }
           group.setBounds(x, y, width, height);
           
          }
          
         });
         Thread.yield(); // 暂停
        }
       }
      }).start(); 
      shell.setFullScreen(false);//实现组件退出全屏效果
     }
    }
   }
  });
  
 }
}
