package wisoft.unpack.views;

import java.io.PrintStream;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class PackConsoleView implements  IConsoleFactory {
	//public static  MessageConsole console =  Console.findConsole("Console Name");    
    
	@Override
    public   void  openConsole() {  
        showConsole();  
    }  
	  
    public   static   void  showConsole(){  
//        if(console !=  null ){  
//            IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();  
//            IConsole[] existing = manager.getConsoles();  
//            boolean exists = false ;  
//            for  ( int  i = 0; i < existing.length; i++){  
//                if  (console == existing[i])  
//                    exists = true ;  
//            }  
//            if  (!exists){  
//                manager.addConsoles(new  IConsole[]{ console });  
//            }  
//            manager.showConsoleView(console);  
//            MessageConsoleStream stream = console.newMessageStream();  
//            System.setOut(new  PrintStream(stream));  
//        }  
    }  
	  
//    public static void  closeConsole(){  
//        IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();  
//        if  (console !=  null ){  
//            manager.removeConsoles(new  IConsole[]{ console });  
//        }  
//    }  
	  

	

}
