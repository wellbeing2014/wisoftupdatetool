package wisoft.pack.edits;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;

import wisoft.pack.app.Activator;
import wisoft.pack.models.PackInfoModel;
import wisoft.pack.models.PackRelyModel;
import wisoft.pack.utils.AutoResizeTableLayout;

import com.wisoft.wims.WimsSingleIssueTracking;

public class EFormPage extends FormPage {
	private Table table;
	private Table table_1;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	
	private PackInfoModel packinfo;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public EFormPage(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public EFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		packinfo =((PackInfoInput)editor.getEditorInput()).getPackinfo();
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("更新包基本信息");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new GridLayout(2, false));
		
		Composite composite = new Composite(managedForm.getForm().getBody(), SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		composite.setLayout(new GridLayout(2, false));
		managedForm.getToolkit().adapt(composite);
		managedForm.getToolkit().paintBordersFor(composite);
		
		Label lblNewLabel = toolkit.createLabel(composite, "\u8BE5\u66F4\u65B0\u5305\u4E0B\u8F7D\u5730\u5740\uFF1A", SWT.NONE);
		
		Hyperlink hyperlink = toolkit.createHyperlink(composite, "\u4E0B\u8F7D\u5730\u5740", SWT.WRAP);
		hyperlink.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			 public void linkActivated(HyperlinkEvent e) {
				    System.out.println("Link activated!");
				    IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport(); 
				    IWebBrowser browser;
					try {
						browser = support.createBrowser("someId");
						browser.openURL(new URL("ftp://wisoft:wisoft2005@58.214.246.41:9021/实施工具/JDK/jdk-1_5_0_08-windows-i586-p.exe"));
					} catch (PartInitException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MalformedURLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} 
				   }
		});
		
		managedForm.getToolkit().paintBordersFor(hyperlink);
		
		Label label_1 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(label_1, true, true);
		label_1.setText(" \u5E73\u53F0\u540D\u79F0\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_1.setEditable(false);
		managedForm.getToolkit().adapt(text_1, true, true);
		
		
		text_1.setText(packinfo.getModuleName());
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(lblNewLabel_1, true, true);
		lblNewLabel_1.setText("\u5E73\u53F0\u4EE3\u7801\uFF1A");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_2.setEditable(false);
		managedForm.getToolkit().adapt(text_2, true, true);
		text_2.setText(packinfo.getModuleCode());
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(lblNewLabel_2, true, true);
		lblNewLabel_2.setText("\u5E73\u53F0\u7248\u672C\uFF1A");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_3.setEditable(false);
		managedForm.getToolkit().adapt(text_3, true, true);
		text_3.setText(packinfo.getVersion());
		
		Label label_2 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(label_2, true, true);
		label_2.setText("\u521B\u5EFA\u4EBA\uFF1A");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_4.setEditable(false);
		managedForm.getToolkit().adapt(text_4, true, true);
		text_4.setText(packinfo.getCreateMan());
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		managedForm.getToolkit().adapt(lblNewLabel_3, true, true);
		lblNewLabel_3.setText("\u521B\u5EFA\u65E5\u671F\uFF1A");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_5.setEditable(false);
		managedForm.getToolkit().adapt(text_5, true, true);
		text_5.setText(packinfo.getCreateTime());
		
		
		//---------------------------------------------------
		
		Section sctnNewSection = managedForm.getToolkit().createSection(composite, Section.TWISTIE);
		sctnNewSection.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 2, 1));
		managedForm.getToolkit().paintBordersFor(sctnNewSection);
		sctnNewSection.setText("更新范围");
		sctnNewSection.setExpanded(true);
		toolkit.createCompositeSeparator(sctnNewSection);
		
		FormText formText = managedForm.getToolkit().createFormText(sctnNewSection, false);
		managedForm.getToolkit().paintBordersFor(formText);
		sctnNewSection.setClient(formText);
		
		
		//---------------------------------------------------
		formText.setImage("image_back",Activator.getImageDescriptor("/icons/jar.png").createImage());
		formText.setImage("image_front",Activator.getImageDescriptor("/icons/swf.png").createImage());
		formText.setImage("image_db",Activator.getImageDescriptor("/icons/db.png").createImage());
		
		//---------------------------------------------------
		Section sctnNewSection_1 = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE);
		sctnNewSection_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		managedForm.getToolkit().paintBordersFor(sctnNewSection_1);
		sctnNewSection_1.setText("更新说明");
		sctnNewSection_1.setExpanded(true);
		toolkit.createCompositeSeparator(sctnNewSection_1);
		FormText formText_1 = managedForm.getToolkit().createFormText(sctnNewSection_1, false);
		formText_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		managedForm.getToolkit().paintBordersFor(formText_1);
		sctnNewSection_1.setClient(formText_1);
		//formText_1.setText(packinfo.getReleaseNote(), false, false); //更新说明
		createRtextContent(formText_1,packinfo.getReleaseNote());
		String ret ="<form>" ;
		ret+="<p>";
		if(packinfo.getScopeBack())
			ret+="<img href=\"image_back\"/>后台   ";
		if(packinfo.getScopeDB())
			ret+="<img href=\"image_db\"/>数据库   ";
		if(packinfo.getScopeFront())
			ret+="<img href=\"image_front\"/>前台    ";
		ret+="</p></form>";
		formText.setText(ret, true, false);
		//---------------------------------------------------
		Section sctnNewSection_2 = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.DESCRIPTION | Section.TWISTIE);
		sctnNewSection_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().paintBordersFor(sctnNewSection_2);
		sctnNewSection_2.setText("更新依赖");
		sctnNewSection_2.setExpanded(true);
		toolkit.createCompositeSeparator(sctnNewSection_2);
		sctnNewSection_2.setDescription("本更新包需要如下的更新包来支持更新的内容。否则服务将不能启动或功能不能使用");
		
		table = managedForm.getToolkit().createTable(sctnNewSection_2, SWT.FULL_SELECTION);
		managedForm.getToolkit().paintBordersFor(table);
		sctnNewSection_2.setClient(table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		AutoResizeTableLayout layout1 = new AutoResizeTableLayout(table);
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		//tableColumn.setWidth(120);
		layout1.addColumnData(new ColumnWeightData(120));
		tableColumn.setText("\u66F4\u65B0\u5305\u540D\u79F0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		//tableColumn_1.setWidth(50);
		layout1.addColumnData(new ColumnWeightData(50));
		tableColumn_1.setText("\u4EE3\u7801");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		//tableColumn_2.setWidth(50);
		layout1.addColumnData(new ColumnWeightData(50));
		tableColumn_2.setText("\u7248\u672C");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		//tableColumn_3.setWidth(80);
		layout1.addColumnData(new ColumnWeightData(80));
		tableColumn_3.setText("\u53D1\u5E03\u65F6\u95F4");
		//更新依赖
		for(PackRelyModel prm:packinfo.getPackRelys())
		{	
			TableItem item  = new TableItem(table, SWT.NONE);
			item.setText(new String[]{prm.getName(),prm.getCode(),prm.getVersion(),prm.getPublishTime()});
		}
		
		Section sctnNewSection_3 = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.DESCRIPTION|Section.TWISTIE);
		sctnNewSection_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		managedForm.getToolkit().paintBordersFor(sctnNewSection_3);
		sctnNewSection_3.setText("修正问题单");
		sctnNewSection_3.setExpanded(true);
		toolkit.createCompositeSeparator(sctnNewSection_3);
		sctnNewSection_3.setDescription("本更新包修正了如下问题单的内容");
		
		table_1 = managedForm.getToolkit().createTable(sctnNewSection_3, SWT.FULL_SELECTION);
		managedForm.getToolkit().paintBordersFor(table_1);
		sctnNewSection_3.setClient(table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		AutoResizeTableLayout layout2 = new AutoResizeTableLayout(table_1);
		TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.NONE);
		//tblclmnNewColumn.setWidth(80);
		tblclmnNewColumn.setText("\u95EE\u9898\u5355\u53F7");
		layout2.addColumnData(new ColumnWeightData(80));
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		//tblclmnNewColumn_1.setWidth(50);
		layout2.addColumnData(new ColumnWeightData(50));
		tblclmnNewColumn_1.setText("\u63D0\u5355\u4EBA");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		layout2.addColumnData(new ColumnWeightData(120));
		tblclmnNewColumn_2.setText("\u63CF\u8FF0");
		//text_6.setText("");//发布日期
		
		
		
		//更新问题单
		for(WimsSingleIssueTracking track :packinfo.getTrackRelys())
		{
			String tackid = track.getLsh();
			String tackperson = track.getSqpersonid();
			String tackcontent = track.getContent();
			TableItem item  = new TableItem(table_1, SWT.NONE);
			item.setText(new String[]{tackid,tackperson,tackcontent});
		}
	}
	
	
	private void createRtextContent(FormText rtext,String str)
	{
		
		String[] strs = str.replace("\t", "").split("\n");
		StringBuffer buf = new StringBuffer();
		buf.append("<form>"); 
		for(String zstr:strs)
		{
			buf.append("<p>");
			buf.append(zstr.replace("&", ""));
			buf.append("</p>");
		}
		buf.append("</form>");
		
		rtext.setText(buf.toString(), true, false);
	}
}
