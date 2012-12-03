package wisoft.pack.edits.sql;

import java.util.Vector;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;


public class SQLScanner extends RuleBasedScanner {

	  private static final String[] KEYWORDS = { 
		    "@", 
		    "WHEN", "PACKAGE", "CREATE", "FUNCTION", "IS", "RETURN", "RETURNING", "BEGIN", "END", "EXCEPTION", 
		    "WHEN", "THEN", "PROCEDURE", "EXIT", "BODY", "REPLACE", "ACCESS", "ACTIVATE", "ADD", "ADMIN", 
		    "AFTER", "ALL", "ALLOCATE", "ALL_ROWS", "ALTER", "ANALYZE", "AND", "ANY", "ARRAY", "AS", 
		    "ASC", "AT", "AUDIT", "AUTHENTICATED", "AUTHORIZATION", "AUTOEXTEND", "AUTOMATIC", "BACKUP", "BECOME", "BEFORE", 
		    "BETWEEN", "BITMAP", "BLOCK", "BY", "CACHE", "CANCEL", "CASCADE", "CAST", "CFILE", "CHAINED", 
		    "CHANGE", "CHARACTER", "CHAR_CS", "CHECK", "CHECKPOINT", "CHOOSE", "CHUNK", "CLEAR", "CLUSTER", "COALESCE", 
		    "COLUMN", "COLUMNS", "COMMENT", "COMMIT", "COMPATIBILITY", "COMPILE", "COMPLETE", "COMPRESS", "COMPUTE", "CONNECT", 
		    "CONSTRAINT", "CONSTRAINTS", "CONTENTS", "CONTINUE", "CONTROLFILE", "COST", "CURRENT", "CURSOR", "CYCLE", "DANGLING", 
		    "DATABASE", "DATAFILE", "DBA", "DEALLOCATE", "DEBUG", "DEFERRABLE", "DEFERRED", "DEGREE", "DELETE", "DESC", 
		    "DIRECTORY", "DISABLE", "DISCONNECT", "DISTINCT", "DISTRIBUTED", "DOUBLE", "DROP", "EACH", "ENABLE", "ENFORCE", 
		    "ENTRY", "ESCAPE", "ESTIMATE", "EVENTS", "EXCEPTIONS", "EXCHANGE", "EXCLUDING", "EXCLUSIVE", "EXECUTE", "EXISTS", 
		    "EXPIRE", "EXPLAIN", "EXTENT", "EXTENTS", "EXTERNALLY", "FAST", "FILE", "FIRST_ROWS", "FLUSH", "FORCE", 
		    "FOREIGN", "FOUND", "FREELIST", "FREELISTS", "FROM", "FULL", "GLOBAL", "GLOBAL_NAME", "GRANT", "GROUP", 
		    "GROUPS", "HASH", "HASHKEYS", "HAVING", "HEADER", "HEAP", "IDENTIFIED", "IDLE_TIME", "IMMEDIATE", "IN", 
		    "INCLUDING", "INCREMENT", "INDEX", "INDEXED", "INDEXES", "INDICATOR", "IND_PARTITION", "INITIAL", "INITIALLY", "INITRANS", 
		    "INSERT", "INSTANCE", "INSTANCES", "INSTEAD", "INTERSECT", "INTO", "IS NULL", "ISOLATION", "ISOLATION_LEVEL", "KEEP", 
		    "KEY", "KILL", "LAYER", "LESS", "LEVEL", "LIBRARY", "LIKE", "LIMIT", "LINK", "LIST", 
		    "LOB", "LOCAL", "LOCK", "LOGFILE", "LOGGING", "MASTER", "MAXEXTENTS", "MEMBER", "MINEXTENTS", "MINIMUM", 
		    "MINUS", "MINVALUE", "MODE", "MODIFY", "MOUNT", "MOVE", "MULTISET", "NATIONAL", "NCHAR_CS", "NEEDED", 
		    "NESTED", "NETWORK", "NEW", "NEXT", "NLS_CALENDAR", "NLS_CHARACTERSET", "NLS_ISO_CURRENCY", "NLS_LANGUAGE", "NLS_NUMERIC_", "NLS_SORT", 
		    "NLS_TERRITORY", "NOARCHIVELOG", "NOAUDIT", "NOCACHE", "NOCOMPRESS", "NOCYCLE", "NOFORCE", "NOLOGGING", "NOMAXVALUE", "NOMINVALUE", 
		    "NONE", "NOORDER", "NOOVERIDE", "NOPARALLEL", "NORESETLOGS", "NOREVERSE", "NORMAL", "NOSORT", "NOT", "NOTHING", 
		    "NOWAIT", "NULL", "NUMERIC", "OBJECT", "OF", "OFF", "OFFLINE", "OID", "OIDINDEX", "OLD", 
		    "ON", "ONLINE", "ONLY", "OPCODE", "OPEN", "OPTIMAL", "OPTIMIZER_GOAL", "OPTION", "OR", "ORDER", 
		    "OVERFLOW", "OWN", "PARALLEL", "PARTITION", "PASSWORD", "PCTFREE", "PCTINCREASE", "PCTUSED", "PERMANENT", "PLAN", 
		    "PLSQL_DEBUG", "PRECISION", "PRESERVE", "PRIMARY", "PRIOR", "PRIVATE", "PRIVILEGE", "PRIVILEGES", "PROFILE", "PUBLIC", 
		    "PURGE", "QUEUE", "QUOTA", "RANGE", "REBUILD", "RECOVER", "RECOVERABLE", "RECOVERY", "REF", "REFERENCES", 
		    "REFERENCING", "REFRESH", "RENAME", "RESET", "RESETLOGS", "RESIZE", "RESOURCE", "RESTRICTED", "REUSE", "REVERSE", 
		    "REVOKE", "ROLE", "ROLES", "ROLLBACK", "ROW", "ROWLABEL", "ROWNUM", "ROWS", "RULE", "SAMPLE", 
		    "SAVEPOINT", "SCHEMA", "SCOPE", "SELECT", "SEQUENCE", "SERIALIZABLE", "SESSION", "SET", "SHARE", "SHARED", 
		    "SHARED_POOL", "SHRINK", "SIZE", "SNAPSHOT", "SOME", "SORT", "SPECIFICATION", "SPLIT", "SQLERROR", "SQL_TRACE", 
		    "STANDBY", "START", "STATEMENT_ID", "STATISTICS", "STOP", "STORAGE", "STORE", "STRUCTURE", "SUCCESSFUL", "SWITCH", 
		    "SYNONYM", "SYSDBA", "SYSOPER", "SYSTEM", "TABLE", "TABLES", "TABLESPACE", "TEMPORARY", "THAN", "THE", 
		    "TIME", "TIMESTAMP", "TO", "TRACE", "TRACING", "TRANSACTION", "TRANSITIONAL", "TRIGGER", "TRIGGERS", "TRUNCATE", 
		    "TYPE", "UNDER", "UNDO", "UNION", "UNIQUE", "UNLIMITED", "UNLOCK", "UNRECOVERABLE", "UNTIL", "UNUSABLE", 
		    "UNUSED", "UPDATABLE", "UPDATE", "USAGE", "USE", "USING", "VALIDATE", "VALIDATION", "VALUE", "VALUES", 
		    "VARCHAR", "VARRAY", "VARYING", "VIEW", "WHENEVER", "WHERE", "WITH", "WITHOUT", "WORK", "ARRAYLEN", 
		    "CASE", "CLOSE", "CONSTANT", "CURRVAL", "DEBUGOFF", "DEBUGON", "DECLARE", "DEFAULT", "DEFINTION", "DELAY", 
		    "DIGITS", "DISPOSE", "DO", "ELSE", "ELSIF", "EXCEPTION_INIT", "FALSE", "FETCH", "FOR", "FORM", 
		    "GENERIC", "GOTO", "IF", "INTERFACE", "LIMITED", "LOOP", "NEXTVAL", "PRAGMA", "RAISE", "RECORD", 
		    "RELEASE", "ROWTYPE", "SIGNTYPE", "SPACE", "SQL", "STATEMENT", "SUBTYPE", "TASK", "TERMINATE", "TRUE", 
		    "VIEWS", "WHILE" };

		  public SQLScanner(ColorManager provider)
		  {
		    IToken def = 
		      new Token(
		      new TextAttribute(provider.getColor(ColorManager.DEFAULT)));

		    IToken string = 
		      new Token(
		      new TextAttribute(provider.getColor(ColorManager.STRING)));

		    IToken keyword = 
		      new Token(
		      new TextAttribute(
		      provider.getColor(ColorManager.KEYWORD), 
		      provider.getColor(ColorManager.BACKGROUND), 
		      1));

		    Vector rules = new Vector();

		    rules.add(new SingleLineRule("\"", "\"", string, '\\'));
		    rules.add(new SingleLineRule("'", "'", string, '\\'));

		    rules.add(new WhitespaceRule(new SQLWhitespaceDetector()));

		    SQLWordRule wordRule = new SQLWordRule(def);

		    for (int i = 0; i < KEYWORDS.length; i++) {
		      wordRule.addKeyword(KEYWORDS[i], keyword);
		    }

		    rules.add(wordRule);

		    IRule[] result = new IRule[rules.size()];
		    rules.copyInto(result);
		    setRules(result);
		  }

		  public IToken nextToken() {
		    return super.nextToken();
		  }
}
