package wisoft.pack.components;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
 
public class LaunchApp {
    protected Shell shell;
 
    private Text nameT;
    private Combo cityC;
    private Text remarksT;
 
    /**
     * Launch the application
     * @param args
     */
    public static void main(String[] args) {
        try {
            LaunchApp window = new LaunchApp();
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
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
 
    /**
     * Create contents of the window
     */
    protected void createContents() {
        shell = new Shell();
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        shell.setLayout(gridLayout);
        shell.setSize(226, 122);
        shell.setText("Field Assist");
 
        final Label nameL = new Label(shell, SWT.NONE);
        nameL.setText("����");
 
        nameT = new Text(shell, SWT.BORDER);
        nameT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
 
        final Label cityL = new Label(shell, SWT.NONE);
        cityL.setText("����");
 
        cityC = new Combo(shell, SWT.NONE);
        cityC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         
        final Label remarksL = new Label(shell, SWT.NONE);
        remarksL.setText("��ע");
 
        remarksT = new Text(shell, SWT.BORDER);
        remarksT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
         
        //
        Dialog.applyDialogFont(this.shell);
         
        //
        this.addNameTextFieldAssist();
        this.addCityComboFieldAssist();
        this.addRemarksTextFieldAssist();
    }
 
    /**
     * ������Text����Զ���ɹ���
     */
    private void addNameTextFieldAssist() {
        // ��text���Խ��д�����ʾ. ��ʾ����Ϊ: "aa", "BB", "�޵�".
        // ע��: �����ִ�Сд. [��: ����'b', �����л����"BB"]
        new AutoCompleteField(nameT, new TextContentAdapter(), new String[]{"aa", "BB", "�޵�"});
    }
     
    /**
     * ������Combo����Զ���ɹ���
     */
    private void addCityComboFieldAssist() {
        // ��combo���Դ�����ʾ. ��ʾ����Ϊ: "BeiJing", "�Ͼ�", "����"
        new AutoCompleteField(cityC, new ComboContentAdapter(), new String[] {"BeiJing", "�Ͼ�", "����"});
    }
     
    /**
     * ����עText����Զ���ɹ���
     */
    private void addRemarksTextFieldAssist() {
        // ����ʹ��ContentProposalAdapter,��û�м���ʹ��AutoCompleteField.
        // [ȥ�鿴������ᷢ��:AutoCompleteFiledʵ�ֺ�����Ĵ��뼸��һ��. ]
        // AutoCompleteFiledʹ�õ�ͬ���ͽ������String[]ȥ����һ��SimpleContentProposalProvider.
        // ����,AutoCompleteFiled�ڲ���ContentProposalAdapter���޷����ⲿ�õ���.
        // ����,Ϊ���ܹ��Զ���ContentProposalAdapter, �����뽫AutoCompleteField�ڲ�ʵ�ֵĴ������ⲿ��дһ��.
        KeyStroke keyStroke = null; // null ��ʾ�����ܿ�ݼ�
        try {
            keyStroke = KeyStroke.getInstance("Ctrl+1"); // ��text�ϰ�Ctrl+1����popup��shell.
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContentProposalAdapter adapter = new ContentProposalAdapter(remarksT, new TextContentAdapter(), new SimpleContentProposalProvider(new String[] {"one", "two", "three"}), keyStroke, new char[] {'.', ' '});
        adapter.setAutoActivationDelay(200); // ��ʱ200ms
        adapter.setPropagateKeys(true); // ����û�������ֵ�������б���[��������'o',����������'one'],�򵯳�popup��shell
        adapter.setFilterStyle(ContentProposalAdapter.FILTER_CUMULATIVE); // �û�ͬ�����������Ҳ�����б�[��:�û�����'o',�򵯳�popup��shell�е������б�����,���ж���'o'��ͷ��, ������һ��'n', �������б��б�����,ֻ����'on'��ͷ��]
        adapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_INSERT); // ��д����
//        adapter.setLabelProvider(new LabelProvider() { // ���Բ���ָ��LabelProvider. ���ָ��,�򲻽���������ʾText, ��������ʾImage.
//            @Override
//            public String getText(Object element) {
//                IContentProposal proposal = (IContentProposal) element;
//                return "XX" + proposal.getContent();
//            }
//            @Override
//            public Image getImage(Object element) {
//                return super.getImage(element);
//            }
//        });
         
        // ����Ĵ�����ʹ�õ���SimpleContentProposalProvider, �����ÿ��Stringȥ����Ĭ�ϵ�һ��IContentProposal,
        // �����߼���: SimpleContentProposalProvider.makeContentProposal
         
        // ��ע��: ���Բ�������setLabelProvider��, ��ô����ֱ�Ӵ�IContentProposal��ȡlabel��content��ʾ.
        // ��labelProvider���labelProvider�õ�������list����ʾ��ֵ.
        // �����߼���: ContentProposalAdapter.getString()����
//        if (labelProvider == null) {
//            return proposal.getLabel() == null ? proposal.getContent() : proposal.getLabel();
//        }
//        return labelProvider.getText(proposal);
         
        // ͬ����, ����������labelProvider, ��ôҲ���Ը�ÿ��IContentProposal����Image.
        // �����߼���: ContentProposalAdapter.getImage()����
         
    }
 
     
    // ContentProposalAdapter.setAutoActivationDelay ����popup���ӳ�ʱ��
     
    // ContentProposalAdapter.setPropagateKeys(true);
    // ˵��: ����û��������ĸ�������б���ʱ,�Ƿ񵯳�popup�����б�.
    // true ����. �û�����'o'Ҳ�ᵯ��popup��shell. ����'.'Ҳ�ᵯ��.
    // false ������. �û�ֻ������'.'�ŵ���popup��shell. ����'o'��,������.
     
    // ContentProposalAdapter.setFilterStyle(ContentProposalAdapter.FILTER_*);
    // ����: ���û�������ĸ��ʱ���Ƿ����popup������shell���������.
    // ContentProposalAdapter.FILTER_NONE ������. ˵��: ����������б���Զ����.
    // ContentProposalAdapter.FILTER_CHARACTER ֻ��һ�������ַ�Ϊ������������������б�. ˵��:���������ַ���,����������б�ᱻ���.
    // ContentProposalAdapter.FILTER_CUMULATIVE �����û����벻ͣ�Ĺ�������������б�. ע����3.4��@deprecated��. ˵��: �����û�������,���������һֱ�ڹ���
     
    // ContentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_*);
    // ˵��: �û���popup��shell�еõ���������ô��д���ؼ���.
    // ContentProposalAdapter.PROPOSAL_INSERT ����.
    // ContentProposalAdapter.PROPOSAL_REPLACE ����.
    // ContentProposalAdapter.PROPOSAL_IGNORE ����. Ӧ�ý�׷�ӱȽϺ���.
 
     
    // TextContentAdapterֻ��������Text.
    // ComboContentAdapterֻ��������Combo.
    // ����, ����StyledText��Snipper�ȶ���Ҫ�Զ���ContentAdapter. 
     
}