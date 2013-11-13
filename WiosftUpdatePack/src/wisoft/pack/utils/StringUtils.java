package wisoft.pack.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static boolean checkStrFormat(String str) {
	    Pattern pattern1 = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
	    Matcher matcher = pattern1.matcher(str);
	    boolean bool = matcher.matches();
	    return bool;
	}
	
	
}
