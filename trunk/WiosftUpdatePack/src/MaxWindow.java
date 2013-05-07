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
 private int x_init;//��¼��ʼ��Xֵ
 private int y_init;//��¼��ʼ��Yֵ
 private int width_int;//��¼��ʼ��width
 private int higth_int;//��¼��ʼ��higth
 private int x_mark;//��¼x�ھ���ʱ�ı仯ֵ
 private int y_mark;//��¼y�ھ���ʱ�ı仯ֵ
 private boolean clickEr = true;//��ֹ�ظ�˫�������³�������

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
  shell.setMaximized(true);//��ʼ����Ĭ��Ϊ���
  while (!shell.isDisposed()) {
   if (!display.readAndDispatch())
    display.sleep();//��ʹ���������ͣ
  }
  display.dispose();//��������
 }

 /**
  * Create contents of the window
  */
 protected void createContents() {
  shell = new Shell(SWT.MIN | SWT.RESIZE);//������󻯲���
//  shell.setTouchEnabled(true);
//  shell.setImage(SWTResourceManager.getImage(MaxWindow.class,
//    "/app24.ico"));
  shell.setBackground(Display.getCurrent()
    .getSystemColor(SWT.COLOR_BLACK));
  shell.setSize(1262, 465);
  shell.setText("���ϵͳ");

  /**
   * ������ʾ��Ļģ��
   */
  final Group group = new Group(shell, SWT.NONE);
  group.setText("����һ");
  group.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_DARK_YELLOW));
  group.setBounds(11, 0, 304, shell.getBounds().height - 23);

  final Group group_1 = new Group(shell, SWT.NONE);
  group_1.setText("���ڶ�");
  group_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_DARK_GREEN));
  group_1.setBounds(321, 0, 304, shell.getBounds().height - 23);

  final Group group_1_1 = new Group(shell, SWT.NONE);
  group_1_1.setText("������");
  group_1_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_YELLOW));
  group_1_1.setBounds(631, 0, 304, shell.getBounds().height - 23);

  final Group group_1_1_1 = new Group(shell, SWT.NONE);
  group_1_1_1.setText("������");
  group_1_1_1.setBackground(Display.getCurrent().getSystemColor(
    SWT.COLOR_GRAY));
  group_1_1_1.setBounds(941, 0, 304, shell.getBounds().height - 23);
  //

//���䣺 // ��Ӽ����¼�1
  group.addMouseListener(new MouseListener() {   public void mouseUp(MouseEvent arg0) {
    // TODO Auto-generated method stub
   }
   public void mouseDown(MouseEvent arg0) {
    // TODO Auto-generated method stub
   }
   public void mouseDoubleClick(MouseEvent arg0) {
    //��ֹ���˫�����������
    if(clickEr){
     clickEr = false;//��ֹ��ε��״̬������Ϊ������
     /**
      * ���س��Լ��������Ļ���������
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
     //�ж��Ƿ��ѱ����أ�������������������ʵ�����ȫ��Ч�������������������
     if (visible_2 == false) {
      /**
       * ��¼��ʼ����Ϣ�������������ʱ����
       */
      x_init = group.getBounds().x;
      y_init = group.getBounds().y;
      width_int = group.getBounds().width;
      higth_int = group.getBounds().height;
      /**
       * ��¼�������ʱ����
       */
      x_mark = (shell.getClientArea().width / 2 - group.getSize().x/2);
      y_mark = (shell.getClientArea().height / 2 - group.getSize().y/2);
      //�����߳�
      new Thread(new Runnable() {
       private boolean startSta = true;//ִ��״̬
       /**
        * �������꣬��ߵĽ��ղ���
        */
       private int x;
       private int y;
       private int width;
       private int height;
       public void run() {
        while (startSta){
         //ʵ������̵߳�ͬ������
         shell.getDisplay().syncExec(new Runnable() {
          public void run() {
           //���þ���λ��
           group.setLocation(shell.getClientArea().width / 2 - group.getSize().x/2, shell
                          .getClientArea().height / 2 - group.getSize().y/2);
           /**
            * ʵʱ������ꡢ�߿�仯Ч������
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
            * Ϊ��ֹ�߳̽��������У�����˫���¼�����
            */
           if(x <= shell.getBounds().x && y <= shell.getBounds().y){
            startSta = false;
            clickEr = true;
           }
           //λ��
           group.setBounds(x, y, width, height);
          }
          
         });
         Thread.yield(); // ��ͣ
        }
       }
      }).start(); 
      shell.setFullScreen(true);//ʵ�����ȫ��Ч��
      
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
         Thread.yield(); // ��ͣ
        }
       }
      }).start(); 
      shell.setFullScreen(false);//ʵ������˳�ȫ��Ч��
     }
    }
   }
  });
  
 }
}
