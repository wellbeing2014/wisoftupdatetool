package wisoft.unpack.edits;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import wisoft.unpack.app.Activator;

public class AFormPage extends FormPage {

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public AFormPage(String id, String title) {
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
	public AFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("更新说明");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		form.getBody().setLayout(layout);
		createFormTextSection(form,toolkit);
	}
	
	private void createFormTextSection(final ScrolledForm form, FormToolkit toolkit) {
		Section section =
			toolkit.createSection(
				form.getBody(),
				Section.TWISTIE | Section.DESCRIPTION);
		section.setActiveToggleColor(
			toolkit.getHyperlinkGroup().getActiveForeground());
		section.setToggleColor(
			toolkit.getColors().getColor(FormColors.SEPARATOR));
		toolkit.createCompositeSeparator(section);
		FormText rtext = toolkit.createFormText(section, false);
		section.setClient(rtext);
		loadFormText(rtext, toolkit);

		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(false);
			}
		});

		section.setText("更新包说明："); //$NON-NLS-1$
		section.setExpanded(true);
		section.setDescription("联系电话 0510-85219300"); //$NON-NLS-1$
		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.FILL;
		td.grabHorizontal = true;
		section.setLayoutData(td);
	}

	private void loadFormText(final FormText rtext, FormToolkit toolkit) {

		rtext.setImage("image1", Activator.getDefault().getImageDescriptor("/icons/QQ截图20121031205402.bmp").createImage()); //$NON-NLS-1$
		InputStream is = AFormPage.class.getResourceAsStream("index.xml"); //$NON-NLS-1$
		if (is!=null) {
			rtext.setContents(is, true);
			try {
				is.close();
			}
			catch (IOException e) {
			}
		}
	}
}
