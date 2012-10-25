package wisoft.pack.views;


import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
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
 private static final int MSG_INFORMATION = SWT.COLOR_DARK_GREEN;  
 private static final int MSG_ERROR = SWT.COLOR_DARK_RED;  
 private static final int MSG_WARNING = SWT.COLOR_DARK_BLUE;
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
    return (MessageConsole) element;

  // failed to find existing console, create one:
  final MessageConsole myConsole = new MessageConsole(name.toString(), null);
  conMan.addConsoles(new IConsole[] { myConsole });
  return myConsole;
 }

 /**
  * Used for quick writes to consoles, this is not in place of std err/out.
  * If the given console does not exist it will be created.
  * 
  * @param name
  * @param msg
  */
 public static void Write (final String name, String msg) {
    MessageConsole myConsole = findOrCreateConsole (name);
    MessageConsoleStream out = myConsole.newMessageStream ();
    out.println(msg);
 }

 public static void Write (MessageConsoleStream stream, String msg) {
  try {
   stream.write(msg);
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public static void print(String msg,String consolename,ConsoleType type)
 {
	 MessageConsole myConsole = findOrCreateConsole(consolename);
	 MessageConsoleStream out = myConsole.newMessageStream ();
	 int color = MSG_INFORMATION;
	 switch(type)
	 {
		 case INFO : color = MSG_INFORMATION; break;
		 case WARNING : color = MSG_WARNING; break;
		 case ERROR : color = MSG_ERROR; break;
		 default:color = MSG_INFORMATION;
	 }
	 out.setColor(Display.getCurrent().getSystemColor(color));  
	 out.println(msg);
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
  * Create consoles for STDERR and STDOUT, redirect all output
  * to the in-application console. Should only be called once.
  * 
  *//*
 public static void init () {
  MessageConsole outConsole = findOrCreateConsole (ConsoleType.STDOUT.toString());
  IOConsoleOutputStream outStream = outConsole.newOutputStream();
  System.setOut(new PrintStream (outStream));

  MessageConsole errConsole = findOrCreateConsole (ConsoleType.STDERR.toString());
  IOConsoleOutputStream errStream = errConsole.newOutputStream();
  System.setErr(new PrintStream (errStream));
 }*/

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