package wisoft.pack.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.apache.tools.ant.taskdefs.SQLExec;


public class SqlPlus extends SQLExec{
	
	
	public void execSql(String sql)
	{
		PrintStream out = new PrintStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
		});
		try	
		{
			super.execSQL(sql, out);
		}
		catch(Exception e)
		{
			
		}
	}

}
