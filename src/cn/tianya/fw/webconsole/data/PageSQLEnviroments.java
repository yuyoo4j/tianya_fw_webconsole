package cn.tianya.fw.webconsole.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

/**
 * 数据库分页环境集合
 * @author yuyoo (yuyoo4j@163.com)
 * @date 2010-1-7 下午12:41:36
 */
public final class PageSQLEnviroments {
	

	public static PageSQLEnviroment newInstance(String dbType) {
		
		if ("mysql".equals(dbType)) {
			return new MySqlPageSQLEnviroment();
		}
		return null;
	}
	
	/**
	 * mysql分页环境
	 * @author yuyoo (yuyoo4j@163.com)
	 * @date 2010-1-7 下午12:01:28
	 */
	private static class MySqlPageSQLEnviroment implements PageSQLEnviroment {

		private String rawSQL = null;
		
		private int start = 0;
		
		private int pageSize = 0;
		
		private int rawTotalSize = 0;
		
		private int totalSize = 0;
		
		public String formatSQL() {
			
			StringBuilder sb = new StringBuilder(50);
			sb.append(rawSQL);
			sb.append(" limit ").append(start).append(" , ").append(pageSize);
			return sb.toString();
		}

		public int getPageSize() {
			
			return pageSize;
		}

		public String getRawSQL() { 
			
			return rawSQL;
		}

		public int getRawTotalSize() { 
			
			return rawTotalSize;
		}

		public int getStart() { 
			
			return start;
		}

		public int getTotalSize() { 
			
			return totalSize;
		}

		public RowSet process(Connection conn) { 
			
			String sql = formatSQL();
			Statement sm = null;
			ResultSet rs = null;
			
			CachedRowSet crs = null;
			try {
				sm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				rs = sm.executeQuery(sql);
				crs = new CachedRowSetImpl();
				crs.populate(rs);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
			return crs;
		}

		public void setPageSize(int ps) {
			
			this.pageSize = ps;
		}

		public void setRawSQL(String sql) {
			
			this.rawSQL = sql;
		}

		public void setRawTotalSize(int ts) { 
			
			this.rawTotalSize = ts;
		}

		public void setStart(int s) {

			this.start = s;
		}
		
	} 
	 
}
