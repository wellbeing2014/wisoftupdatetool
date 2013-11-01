package wisoft.pack.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;

/**
 * Extract SQL statements in file.<br/>
 * These code are picked-up from ant org.apache.tools.ant.taskdefs.SQLExec and modified a little.
 * 
 * @author Kyle Li
 */
public class SQLExtractor {

	private static SQLExtractor instance;

	/**
	 * Encoding to use when reading SQL statements from a file
	 */
	private String encoding = null;

	/**
	 * Keep the format of a sql block?
	 */
	private boolean keepformat = false;

	/**
	 * delimiters must match in case and whitespace is significant.
	 */
	private boolean strictDelimiterMatching = true;

	/**
	 * SQL Statement delimiter
	 */
	private String delimiter = ";";

	/**
	 * keep the delimiter in sql or not
	 */
	private boolean keepDelimiter = true;

	/**
	 * The delimiter type indicating whether the delimiter will only be recognized on a line by itself
	 */
	private String delimiterType = DelimiterType.NORMAL;

	/**
	 * delimiters we support, "normal" and "row"
	 */
	public static class DelimiterType extends EnumeratedAttribute {
		/** The enumerated strings */
		public static final String NORMAL = "normal", ROW = "row";

		/** @return the enumerated strings */
		public String[] getValues() {
			return new String[] { NORMAL, ROW };
		}
	}

	private SQLExtractor() {

	}

	public static synchronized SQLExtractor getInstance() {
		if (instance == null) {
			instance = new SQLExtractor();
		}
		return instance;
	}

	public List<String> extract(InputStream is) throws IOException {
		Reader reader = null;
		try {
			reader = (encoding == null) ? new InputStreamReader(is) : new InputStreamReader(is, encoding);
			return extract(reader);
		} finally {
			FileUtils.close(is);
			FileUtils.close(reader);
		}
	}

	public List<String> extract(Reader reader) throws IOException {
		List<String> sqlList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		String line;

		BufferedReader in = new BufferedReader(reader);

		while ((line = in.readLine()) != null) {
			if (!keepformat) {
				line = line.trim();
			}
			// if (expandProperties) {
			// line = getProject().replaceProperties(line);
			// }

			if (!keepformat) {
				if (line.startsWith("//")) {
					continue;
				}
				if (line.startsWith("--")) {
					continue;
				}
				StringTokenizer st = new StringTokenizer(line);
				if (st.hasMoreTokens()) {
					String token = st.nextToken();
					if ("REM".equalsIgnoreCase(token)) {
						continue;
					}
				}
			}

			sql.append(keepformat ? "\n" : " ").append(line);

			// SQL defines "--" as a comment to EOL
			// and in Oracle it may contain a hint
			// so we cannot just remove it, instead we must end it
			if (!keepformat && line.indexOf("--") >= 0) {
				sql.append("\n");
			}
			int lastDelimPos = lastDelimiterPosition(sql, line);
			if (lastDelimPos > -1) {
				// execSQL(sql.substring(0, lastDelimPos), out);
				String sqlStr = keepDelimiter ? sql.toString() : eliminateLastColon(sql.toString());
				sqlList.add(sqlStr);
				sql.replace(0, sql.length(), "");
			}
		}

		// Catch any statements not followed by ;
		if (sql.length() > 0) {
			// execSQL(sql.toString(), out);
			sqlList.add(sql.toString());
		}

		return sqlList;
	}

	private String eliminateLastColon(String sql) {
		sql = sql.trim();
		if (sql.endsWith(";")) {
			return sql.substring(0, sql.length() - 1);
		}
		return sql;
	}

	private int lastDelimiterPosition(StringBuffer buf, String currentLine) {
		if (strictDelimiterMatching) {
			if ((delimiterType.equals(DelimiterType.NORMAL) && StringUtils.endsWith(buf, delimiter))
					|| (delimiterType.equals(DelimiterType.ROW) && currentLine.equals(delimiter))) {
				return buf.length() - delimiter.length();
			}
			// no match
			return -1;
		} else {
			String d = delimiter.trim().toLowerCase(Locale.ENGLISH);
			if (delimiterType.equals(DelimiterType.NORMAL)) {
				// still trying to avoid wasteful copying, see
				// StringUtils.endsWith
				int endIndex = delimiter.length() - 1;
				int bufferIndex = buf.length() - 1;
				while (bufferIndex >= 0 && Character.isWhitespace(buf.charAt(bufferIndex))) {
					--bufferIndex;
				}
				if (bufferIndex < endIndex) {
					return -1;
				}
				while (endIndex >= 0) {
					if (buf.substring(bufferIndex, bufferIndex + 1).toLowerCase(Locale.ENGLISH).charAt(0) != d.charAt(endIndex)) {
						return -1;
					}
					bufferIndex--;
					endIndex--;
				}
				return bufferIndex + 1;
			} else {
				return currentLine.trim().toLowerCase(Locale.ENGLISH).equals(d) ? buf.length() - currentLine.length() : -1;
			}
		}
	}

	// --------------------------------------------------------------------------------
	//
	// getter and setter
	//
	// --------------------------------------------------------------------------------

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isKeepformat() {
		return keepformat;
	}

	public void setKeepformat(boolean keepformat) {
		this.keepformat = keepformat;
	}

	public boolean isStrictDelimiterMatching() {
		return strictDelimiterMatching;
	}

	public void setStrictDelimiterMatching(boolean strictDelimiterMatching) {
		this.strictDelimiterMatching = strictDelimiterMatching;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public boolean isKeepDelimiter() {
		return keepDelimiter;
	}

	public void setKeepDelimiter(boolean keepDelimiter) {
		this.keepDelimiter = keepDelimiter;
	}

}
