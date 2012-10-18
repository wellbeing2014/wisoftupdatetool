package wisoft.pack.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

import wisoft.pack.app.Activator;


public class MessagePopupAction extends Action {

    private final IWorkbenchWindow window;

    public MessagePopupAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_OPEN_MESSAGE);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.CMD_OPEN_MESSAGE);
        setImageDescriptor(wisoft.pack.app.Activator.getImageDescriptor("/icons/sample3.gif"));
    }

    public void run() {
        MessageDialog.openInformation(window.getShell(), "Open", "Open Message Dialog!");
    }
}