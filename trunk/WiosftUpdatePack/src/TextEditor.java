import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Pascal Ma
 * 
 */
public class TextEditor {

    private Shell shell;

    private Text textArea;

    private Button loadButton;

    private Button saveButton;

    private Button exitButton;

    private FileDialog loadDialog;

    private FileDialog saveDialog;

    private MessageBox exitDialog;

    private boolean modified;
    
    private boolean closing;

    private static final String newLine = System.getProperty("line.separator");

    private TextEditor() {
        createShell();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * Before this is run, be sure to set up the launch configuration
         * (Arguments->VM Arguments) for the correct SWT library path in order
         * to run with the SWT dlls. The dlls are located in the SWT plugin jar.
         * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
         * installation_directory/plugins/org.eclipse.swt.win32_3.1.0.jar
         */
        Display display = Display.getDefault();
        TextEditor app = new TextEditor();
        app.open();

        while (!app.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    public boolean isDisposed() {
        return getShell().isDisposed();
    }

    public void open() {
        getShell().open();
    }

    private void setTitle(String title) {
        getShell().setText(title);
    }

    private String getTitle() {
        return getShell().getText();
    }

    /**
     * @return 返回 modified。
     */
    private boolean isModified() {
        return modified;
    }

    /**
     * @param modified
     *            要设置的 modified。
     */
    private void setModified(boolean modified) {
        this.modified = modified;
    }

    private FileDialog getLoadDialog() {
        return loadDialog;
    }

    private FileDialog getSaveDialog() {
        return saveDialog;
    }

    /**
     * @return 返回 shell。
     */
    private Shell getShell() {
        return shell;
    }

    /**
     * @return 返回 textArea。
     */
    private Text getTextArea() {
        return textArea;
    }

    /**
     * This method initializes shell and the components of shell
     */
    private void createShell() {
        shell = new Shell();
        shell.setText("Text Editor");
        shell.setSize(new Point(393, 279));

        GridLayout shellLayout = new GridLayout();
        shellLayout.numColumns = 3;
        shellLayout.makeColumnsEqualWidth = true;

        shell.setLayout(shellLayout);
        shell.addShellListener(new ShellAdapter() {
            public void shellClosed(ShellEvent event) {
                if (!isClosing()) {
                    checkResponses(event.doit = doExit());
                }
            }
        });
        
        createTextArea();
        createLoadButton();
        createSaveButton();
        createExitButton();
    }

    private void createLoadButton() {
        loadButton = new Button(getShell(), SWT.NONE);
        loadButton.setText("Load File");

        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.END;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;
        
        loadButton.setLayoutData(gridData);
        loadButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                try {
                    checkResponses(doLoad());
                } catch (IOException e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            }
        });

        createLoadDialog();
    }

    private void createSaveButton() {
        saveButton = new Button(getShell(), SWT.NONE);
        saveButton.setText("Save File");

        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.CENTER;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;

        saveButton.setLayoutData(gridData);
        saveButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                try {
                    checkResponses(doSave());
                } catch (IOException e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            }
        });

        createSaveDialog();
    }

    private void createExitButton() {
        exitButton = new Button(getShell(), SWT.NONE);
        exitButton.setText("Exit");

        GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;

        exitButton.setLayoutData(gridData);
        exitButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                checkResponses(doExit());
            }
        });

        createExitDialog();
    }

    private void createTextArea() {
        textArea = new Text(getShell(), SWT.MULTI | SWT.WRAP | SWT.V_SCROLL
                | SWT.BORDER);

        GridData gridData = new GridData();
        gridData.horizontalSpan = 3;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.verticalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;

        textArea.setLayoutData(gridData);
        textArea.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                if (isModified()) {
                    return;
                }

                setTitle("*" + getTitle());
                setModified(true);
            }
        });
    }

    private FileDialog newFileDialog(int style) {
        return new FileDialog(getShell(), style);
    }

    private void createLoadDialog() {
        loadDialog = newFileDialog(SWT.OPEN);
    }

    private void createSaveDialog() {
        saveDialog = newFileDialog(SWT.SAVE);
    }

    private void createExitDialog() {
        exitDialog = new MessageBox(getShell(), SWT.ICON_QUESTION | SWT.YES
                | SWT.NO | SWT.CANCEL);
        exitDialog.setText("Save Changes?");
        exitDialog.setMessage("File has been changed. Save before exit?");
    }

    private boolean doSave() throws IOException {
        // FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
        String result = getSaveDialog().open();
        if (null == result) {
            return false;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(result));
        writer.write(getTextArea().getText());
        writer.close();

        setTitle("Text Editor");
        setModified(false);
        return true;
    }

    private boolean doLoad() throws IOException {
        String result = getLoadDialog().open();
        if (null == result) {
            return false;
        }

        BufferedReader reader = new BufferedReader(new FileReader(result));
        StringBuilder builder = new StringBuilder();
        for (String line; null != (line = reader.readLine());) {
            builder.append(line + newLine);
        }
        getTextArea().setText(builder.toString());
        reader.close();
        setTitle("Text Editor");
        setModified(false);
        return true;
    }

    private boolean doExit() {
        if (isModified()) {
            switch (getExitDialog().open()) {
            case SWT.YES:
                try {
                    if (!doSave()) {
                        return false;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
                break;
            case SWT.CANCEL:
                return false;
            default:
                break;
            }
        }
        
        setClosing(true);
        getShell().close();
        getShell().dispose();
        return true;
    }

    /**
     * @return the exitButton
     */
    @SuppressWarnings("unused")
    private Button getExitButton() {
        return exitButton;
    }

    /**
     * @return the exitDialog
     */
    private MessageBox getExitDialog() {
        return exitDialog;
    }

    /**
     * @return the closing
     */
    @SuppressWarnings("unused")
    private boolean isClosing() {
        return closing;
    }

    /**
     * @param closing the closing to set
     */
    private void setClosing(boolean closing) {
        this.closing = closing;
    }
    
    // press when state is cancel then Refocus the textArea component 
    private void checkResponses(boolean state) {
        if (!state) {
            getTextArea().setFocus();
        }
    }
} 