package cn.tianya.lab.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.webconsole.data.FreemarkerFactory;
import cn.tianya.fw.webconsole.data.JqGridJsonResponse;
import cn.tianya.fw.webconsole.mvc.RequestContext;
import cn.tianya.fw.webconsole.sys.BeanFactory;

public class Test {
	
	private static final Logger log = LoggerFactory.getLogger(Test.class);

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//testSpingAC();
		//testSQLFreeMarker();
		testBase64();
	}

	public static void testSpingAC() {
		 
		String beanId = "sys.parameters";
		Object bean = BeanFactory.getBean(beanId);
		log.debug(bean.toString());
	}
	
	public static void testBase64() throws Exception {
		
		Base64 base64 = new Base64();
		String base = "{\"rn\":\"10\",\"app_pk\":\"sub\",\"app_name\":\"s副 s\",\"parents_pk\":\"0\",\"app_state\":\"1\",\"create_time\":\"2008-07-04 15:31:05.0\",\"remark\":\"updated by zhandl@tinaya.fw\"}";
		String rtv = new String(base64.decodeBase64(base.getBytes("utf-8")));
		log.debug("rtv:" + rtv);
	}
	
	public void testVoid() {
		
		RequestContext rc = RequestContext.getRequestContext(); 
		String json = rc.getParameter("rowJson", true);
		if (null != json) {
			rc.setAttribute("rowJson", json);
		}
		log.debug("params:" + rc.getParameterMap());
		log.debug("Test.testVoid invoked");
	}
	
	public static void testSQLFreeMarker() {
		
		StringBuffer sb = FreemarkerFactory.process("sqls/app_list_sql.ftl", null);
		String content = sb.toString();
		log.debug(content);
	}
	
	public void testJsonData() {
		
		RequestContext rc = RequestContext.getRequestContext();
		log.debug(rc.getRequest().getQueryString());
		
		StringBuffer sb = FreemarkerFactory.process("gridData.ftl", getJsonObject());
		String content = sb.toString();
		//log.debug("content:" + content); 

		rc.setResponseEncoding("utf-8");
		rc.getWriter().println(content);
	}
	
	/*
	@SuppressWarnings("unchecked")
	public void testTianyafw() { 
		
		RequestContext rc = RequestContext.getRequestContext();
		
		Map<String, String> params = rc.getParameterMap();
		log.debug("queryStr:{}", rc.getRequest().getQueryString());
		log.debug("params:{}", params);
		
		StringBuffer sql = FreemarkerFactory.process("sqls/app_list_sql.ftl", params);
		
		String s = sql.toString();
		
		log.debug("sql:{}", s);
		
		AppClient ac = new AppClient(); 
		//结果集
		RowSetXml rs=null;
		//增加SQL
		ac.addSql(s);
		try{
			String appId = "conf_cache";
			String xml = ac.send(appId, RequestPacker.RW_FLAG_READ);
						
			rs = new RowSetXml(xml, false);
			if(!StringUtil.isNullOrBlank(rs.getErrorMsg())){
				throw new DAOException(rs.getErrorMsg());
			}
		} catch (Exception ex){
			log.error("分页查询应用失败!",ex);
		}

		JqGridJsonResponse res = new JqGridJsonResponse();
		res.setPage(Integer.parseInt(params.get("page")));
		res.setTotal(999);
		res.setRecords(rs.getSize());
		int index = 0;
		while (rs.next()) {
			res.addRow(new ArrayList<Object>());
			res.addCell(index, rs.getString("app_pk"));
			res.addCell(index, rs.getString("app_name"));
			res.addCell(index, rs.getString("parents_pk"));
			res.addCell(index, rs.getString("app_state"));
			res.addCell(index, rs.getString("create_time"));
			res.addCell(index, rs.getString("remark"));
			index++;
		}

		StringBuffer sb = FreemarkerFactory.process("gridData.ftl", res);
		String content = sb.toString();
		rc.setResponseEncoding("utf-8");
		rc.getWriter().println(content);
	}
	
	public void testPostData() { 
		
		RequestContext rc = RequestContext.getRequestContext();
		
		Map<String, String> params = rc.getParameterMap();
		log.debug("queryStr:{}", rc.getRequest().getQueryString());
		log.debug("params:{}", params);
	}
	*/
	 	
	public void testAppDb() {

		RequestContext rc = RequestContext.getRequestContext();
		Map<String, String> params = rc.getParameterMap();
		StringBuffer sql = FreemarkerFactory.process("sqls/app_list_sql.ftl", params);
	}
	
	public void testSelectOptionData() {
		
		RequestContext rc = RequestContext.getRequestContext();
		StringBuilder select = new StringBuilder();
		select.append("<select>");
		select.append("<option value='1'>One</option>");
		select.append("<option value='2'>Two</option>");
		select.append("</select>");
		rc.getWriter().println(select.toString());
	}
	
	public JqGridJsonResponse getJsonObject() {
		
		JqGridJsonResponse res = new JqGridJsonResponse();
		res.setPage(7);
		res.setRecords(99);
		res.setTotal(55);
		
		res.addRow(new ArrayList<Object>());
		res.addCell(0, "101");
		res.addCell(0, "了望台1");
		res.addCell(0, "启用1");
		res.addCell(0, "2009-05-22");
		res.addCell(0, "天涯了望台1");

		res.addRow(new ArrayList<Object>());
		res.addCell(1, "102");
		res.addCell(1, "了望台2");
		res.addCell(1, "启用2");
		res.addCell(1, "2009-05-22");
		res.addCell(1, "天涯了望台2");

		res.addRow(new ArrayList<Object>());
		res.addCell(2, "103");
		res.addCell(2, "了望台3");
		res.addCell(2, "启用3");
		res.addCell(2, "2009-05-22");
		res.addCell(2, "天涯了望台3");
		
		return res;
	}
	
	public Map getDataMap() {
		
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("total", "55");
		rootMap.put("page", "7");
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
		
		return rootMap;
	}
}
