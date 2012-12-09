package wisoft.pack.edits.sql;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.RGB;

import wisoft.pack.edits.xml.NonRuleBasedDamagerRepairer;


public class SQLConfiguration extends SourceViewerConfiguration {
	
	 private SQLDoubleClickStrategy doubleClickStrategy;
	 private SQLScanner tagScanner;
	 private ColorManager colorManager;

	 public SQLConfiguration(ColorManager colorManager)
	 {
	    this.colorManager = colorManager;
	 }
	  public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
	    return new String[] { 
	      IDocument.DEFAULT_CONTENT_TYPE,
	      SQLPartitionScanner.SQL_COMMENT1, 
	      SQLPartitionScanner.SQL_COMMENT_MULTI,
	      SQLPartitionScanner.SQL_KEYWORD };
	  }

	  public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType)
	  {
	    if (this.doubleClickStrategy == null)
	      this.doubleClickStrategy = new SQLDoubleClickStrategy();
	    return this.doubleClickStrategy;
	  }

	  protected SQLScanner getSQLScanner() {
	    if (this.tagScanner == null) {
	      this.tagScanner = new SQLScanner(this.colorManager);
	    }

	    return this.tagScanner;
	  }

	  public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
			PresentationReconciler reconciler = new PresentationReconciler();

			DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getSQLScanner());
	        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
	        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
	        
	        dr= new DefaultDamagerRepairer(createSimpleTokenScanner(ColorManager.MULTI_LINE_COMMENT));
	        reconciler.setDamager(dr, SQLPartitionScanner.SQL_COMMENT1);
	        reconciler.setRepairer(dr, SQLPartitionScanner.SQL_COMMENT1);
	        
			NonRuleBasedDamagerRepairer ndr =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(colorManager.getColor(ColorManager.SINGLE_LINE_COMMENT)));
			reconciler.setDamager(ndr, SQLPartitionScanner.SQL_COMMENT_MULTI);
			reconciler.setRepairer(ndr, SQLPartitionScanner.SQL_COMMENT_MULTI);
			

			

			return reconciler;
		}
	  private ITokenScanner createSimpleTokenScanner(RGB color) {
			RuleBasedScanner scanner = new RuleBasedScanner();
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(color))));
			
			return scanner;
		}
}