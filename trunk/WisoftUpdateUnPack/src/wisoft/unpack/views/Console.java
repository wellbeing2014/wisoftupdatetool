package wisoft.unpack.views;


import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public final class Console {
 private static Console mInstance = null;

 public static enum ConsoleType { INFO,WARNING,ERROR };
 
 private static final Color error_color = new Color(null,176,23,31 ); 
 private static final Color warning_color = new Color(null, 255,97,0); 
 private static final Color info_color = new Color(null,30,144,255 ); 
 //private static MessageConsole myConsole = new MessageConsole(name.toString(), null);

 private Console () { }

 /**
  * Singleton pattern to match Eclipse's console plugin's behavior.
  * @return
  */
 public static synchronized Console getInstance() {
  if (mInstance == null)
   mInstance = new Console ();

  return mInstance;
 }

 
 private static MessageConsole findOrCreateConsole(final String name) {
  final ConsolePlugin plugin = ConsolePlugin.getDefault();
  final IConsoleManager conMan = plugin.getConsoleManager();
  final IConsole[] existing = conMan.getConsoles();

  for (final IConsole element : existing)
   if (name.toString().compareTo(element.getName()) == 0)
   {   
	   conMan.showConsoleView(element);
	   return (MessageConsole) element;
   
   }

  // failed to find existing console, create one:
  final MessageConsole myConsole = new MessageConsole(name.toString(), null);
  conMan.addConsoles(new IConsole[] { myConsole });
  conMan.showConsoleView(myConsole);
  return myConsole;
 }

 public static synchronized void print(String msg,String consolename,ConsoleType type)
 {
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 
	 MessageConsole myConsole = findOrCreateConsole(consolename);
	 MessageConsoleStream out = myConsole.newMessageStream ();
	 Color color = null;
	 String title = "info:";
	 switch(type)
	 {
		 case INFO : color = new Color(null,30,144,255 ); title ="info:"; break;
		 case WARNING : color = new Color(null, 255,97,0); title ="warning:"; break;
		 case ERROR : color = new Color(null,176,23,31 ); title ="error:"; break;
		 default:color = info_color; title ="info";
	 }
	 out.setColor(color);  
	 out.println(df.format(new Date())+":");
	 out.println(title+msg);
 }
 /**
  * Return a new output stream for a given console. If the console does
  * not exist it will be created. 
  * @param type A console type defined in Console's ConsoleType enum.
  * @return An open stream for writing to.
  */
 public static OutputStream GetOutputStream(final String type) {
  MessageConsole ib = findOrCreateConsole (type);
  IOConsoleOutputStream mcs = ib.newOutputStream(); 
  mcs.setActivateOnWrite(true);
  return mcs;
 }

 

 /**
  * Alternate way to bring up the console view. Don't know
  * which one is better / the differences.
  * @param myConsole Console to show, must not be null.
  */
 public static void ShowConsole (IConsole myConsole) {
  IWorkbench workbench = PlatformUI.getWorkbench();
  IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

  IWorkbenchPage page = window.getActivePage();
  String id = IConsoleConstants.ID_CONSOLE_VIEW;
  IConsoleView view;
  try {
   view = (IConsoleView) page.showView(id);
   view.display(myConsole);
  } catch (PartInitException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 /**
  * Show the application console. If the given console is null, attempt
  * to find an existing console and use it. If the given console is null
  * and no existing consoles exist, exit without doing anything.
  * 
  * @param myConsole An existing console.
  */
 public static void DisplayConsole (IConsole myConsole) {

  // try to grab any old console and display it if given null
  if (myConsole == null) {
   final ConsolePlugin plugin = ConsolePlugin.getDefault();
   final IConsoleManager conMan = plugin.getConsoleManager();
   final IConsole[] existing = conMan.getConsoles();

   if (existing.length == 0)
    return;

   for (final IConsole element : existing)
    myConsole = element;
  }

  ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {myConsole});
  ConsolePlugin.getDefault().getConsoleManager().showConsoleView(myConsole);
 }

 /**
  * Show the console view with the given ConsoleType. Will not
  * create one if one does not already exist.
  * 
  * @param type Non-null enum of existing console (stdout/err probably safe)
  */
 public static void DisplayConsole (final String type) {

  final ConsolePlugin plugin = ConsolePlugin.getDefault();
  final IConsoleManager conMan = plugin.getConsoleManager();
  final IConsole[] existing = conMan.getConsoles();

  for (final IConsole element : existing)
   if (type.toString().compareTo(element.getName()) == 0) {
    ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {element});
    ConsolePlugin.getDefault().getConsoleManager().showConsoleView(element);
    return;
   }
 }
 
}  