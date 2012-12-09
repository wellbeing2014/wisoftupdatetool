package wisoft.pack.edits.sql;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class SQLPartitionScanner extends RuleBasedPartitionScanner{
	
	  public static final String SQL_COMMENT1 = "__sql_comment1";
	  public static final String SQL_COMMENT_MULTI = "__sql_comment_multi";
	  public static final String SQL_KEYWORD = "__sql_keyword";

	  public SQLPartitionScanner()
	  {
	    List<IPredicateRule> rules = new ArrayList<IPredicateRule>();
	    IToken sqlComment = new Token(SQLPartitionScanner.SQL_COMMENT1);
	    IToken sqlComment_multi = new Token(SQLPartitionScanner.SQL_COMMENT_MULTI);
	    rules.add(new EndOfLineRule("--",sqlComment));
	    rules.add(new MultiLineRule("/*","*/",sqlComment_multi));
	    IPredicateRule[] result = new IPredicateRule[rules.size()];
	    rules.toArray(result);
	    setPredicateRules(result);	
	  }
}
