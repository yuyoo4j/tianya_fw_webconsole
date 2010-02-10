package cn.tianya.fw.webconsole.data;

import java.sql.Connection;

import javax.sql.RowSet;

public interface PageSQLEnviroment {

	void setStart(int s);
	int getStart();
	
	void setPageSize(int ps);
	int getPageSize();
	
	void setRawSQL(String sql);
	String getRawSQL();
	
	void setRawTotalSize(int ts);
	int getRawTotalSize();
	
	String formatSQL();
	
	int getTotalSize();
	
	RowSet process(Connection conn);
}
