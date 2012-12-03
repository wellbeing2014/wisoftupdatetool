package wisoft.pack.edits.sql;

import java.util.HashMap;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class SQLWordRule implements IRule
{
	private IToken token;
	private HashMap keywords = new HashMap();

	public SQLWordRule(IToken token) {
		this.token = token;
	}

	public void addKeyword(String word, IToken token) {
	    this.keywords.put(word.toUpperCase(), token);
	}

	public IToken evaluate(ICharacterScanner scanner) {
	    char c = (char)scanner.read();

	    if (Character.isLetter(c))
	    {
	    	StringBuffer value = new StringBuffer();
	    	do {
	    		value.append(c);
	    		c = (char)scanner.read();
	    	}while ((Character.isLetterOrDigit(c)) || (c == '_'));
	    	scanner.unread();
	    	IToken retVal = 
	    		(IToken)this.keywords.get(value.toString().toUpperCase());
	    	if (retVal != null) {
	    		return retVal;
	    	}
	    	return this.token;
	    }
	    scanner.unread();
	    return Token.UNDEFINED;
	}
}
