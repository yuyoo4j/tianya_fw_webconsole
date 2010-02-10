package cn.tianya.lab.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.webconsole.data.FreemarkerFactory;

public class TestFreemarker {
	
	private static final Logger logger = LoggerFactory.getLogger(TestFreemarker.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// testFreemarker();
		testSpringMapBeanTemplateLoader();
	}
	
	public static void testSpringMapBeanTemplateLoader() {
		
		StringBuffer sql = FreemarkerFactory.process("sql.demo", null);
		logger.debug("sql:{}", sql.toString());
	}

	public static void testFreemarker() {
		
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("total", "55");
		rootMap.put("page", "0");
		rootMap.put("records", "9999");
		
		List<Object> rows = new ArrayList<Object>();
		rootMap.put("rows", rows);
		
		List<Object> row = new ArrayList<Object>();
		row.add("101");
		row.add("了望台1");
		row.add("启用1");
		row.add("2009-05-22");
		row.add("天涯了望台1"); 
		rows.add(row);
		
		row = new ArrayList<Object>();
		row.add("102");
		row.add("了望台2");
		row.add("启用2");
		row.add("2009-05-22");
		row.add("天涯了望台2"); 
		rows.add(row);
		
		row = new ArrayList<Object>();
		row.add("103");
		row.add("了望台3");
		row.add("启用3");
		row.add("2009-05-22");
		row.add("天涯了望台3"); 
		rows.add(row);
		
		row = new ArrayList<Object>();
		row.add("104");
		row.add("了望台4");
		row.add("启用4");
		row.add("2009-05-22");
		row.add("天涯了望台4"); 
		rows.add(row);
		
		logger.debug(rootMap.toString());
		
		StringBuffer sb = FreemarkerFactory.process("gridData.ftl", rootMap);
		logger.debug(sb.toString());
	}
}
