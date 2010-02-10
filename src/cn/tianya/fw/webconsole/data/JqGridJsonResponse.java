package cn.tianya.fw.webconsole.data;

import java.util.ArrayList;
import java.util.List;

public class JqGridJsonResponse {

	private int total = 0;
	private int page = 0;
	private int records = 0;
	
	private List<List<Object>> rows = new ArrayList<List<Object>>();
	
	public void addRow(List<Object> row) {
		 
		if(null != row) {
			rows.add(row);
		}
	}
	
	public void addCell(int rowIndex, Object cell) {
		
		List<Object> row = rows.get(rowIndex);
		if (null != row && null != cell) {
			row.add(cell);
		}
	}
	
	public List<Object> setRow(int rowIndex, List<Object> row) {
		
		List<Object> oldRow = rows.get(rowIndex);
		if (null != oldRow && null != row) {
			rows.set(rowIndex, row);
		}
		return oldRow;
	}
	
	public Object setCell(int rowIndex, int cellIndex, Object cell) {
		
		List<Object> row = rows.get(rowIndex);
		Object oldCell = null;
		if (null != row && null != cell) {
			oldCell = row.get(cellIndex);
			row.set(cellIndex, cell);
		}
		return oldCell;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<List<Object>> getRows() {
		return rows;
	}

	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}

}
