package cn.tianya.fw.webconsole.module.config.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.exception.DAOException;
import cn.tianya.fw.service.AppClient;
import cn.tianya.fw.service.RequestPacker;
import cn.tianya.fw.service.RowSetXml;
import cn.tianya.fw.util.StringUtil;
import cn.tianya.fw.webconsole.data.FreemarkerFactory;
import cn.tianya.fw.webconsole.data.JqGridJsonResponse;
import cn.tianya.fw.webconsole.mvc.RequestContext;

public class AppService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);

	public void handleListQuery() {
		
		RequestContext rc = RequestContext.getRequestContext();		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);
		
		StringBuffer sql = FreemarkerFactory.process("ftl.sql.app.list", params);		
		String s = sql.toString();		
		logger.debug("sql:{}", s);
		
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
			logger.error("分页查询应用失败!",ex);
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

		StringBuffer sb = FreemarkerFactory.process("grid.data.xml.ftl", res);
		String content = sb.toString();
		logger.debug("content:{}", content);
		rc.setResponseEncoding("utf-8");
		rc.setResponseHeader("Content-type", "text/xml;charset=utf-8");
		rc.getWriter().println(content);
	}
	
	public void handleEditApp() {

		RequestContext rc = RequestContext.getRequestContext();	
		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);
		 		
		StringBuffer sql = FreemarkerFactory.process("ftl.sql.app.edit_item", params);	
		
		AppClient ac = new AppClient(); 
		
		//结果集
		RowSetXml rs=null;
		//增加SQL
		ac.addSql(sql.toString());
		try{
			String appId = "conf_cache";
			String xml = ac.send(appId, RequestPacker.RW_FLAG_WRITE);
						
			rs = new RowSetXml(xml, false);
			if(!StringUtil.isNullOrBlank(rs.getErrorMsg())){
				throw new DAOException(rs.getErrorMsg());
			}
		} catch (Exception ex){
			logger.error("编辑应用信息失败!",ex);
		}
	}
	
	public void handleAddApp() {

		RequestContext rc = RequestContext.getRequestContext();	
		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);
		
		String createTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		params.put("create_time", createTime);
		StringBuffer sql = FreemarkerFactory.process("ftl.sql.app.add_item", params);	 
		
		AppClient ac = new AppClient(); 
		
		//结果集
		RowSetXml rs=null;
		//增加SQL
		ac.addSql(sql.toString());
		try{
			String appId = "conf_cache";
			String xml = ac.send(appId, RequestPacker.RW_FLAG_WRITE);
						
			rs = new RowSetXml(xml, false);
			if(!StringUtil.isNullOrBlank(rs.getErrorMsg())){
				throw new DAOException(rs.getErrorMsg());
			}
		} catch (Exception ex){
			logger.error("新增应用信息失败!",ex);
		}
	}
	
	public void handleDeleteApp() {

		throw new UnsupportedOperationException("当前不能删除应用ID");
	}

	public void handleSelectHtmlQuery() {

		RequestContext rc = RequestContext.getRequestContext();	

		StringBuffer sql = FreemarkerFactory.process("ftl.sql.app.query_parent_list", null);		
				
		AppClient ac = new AppClient(); 
		
		//结果集
		RowSetXml rs=null;
		//增加SQL
		ac.addSql(sql.toString());
		try{
			String appId = "conf_cache";
			String xml = ac.send(appId, RequestPacker.RW_FLAG_READ);
						
			rs = new RowSetXml(xml, false);
			if(!StringUtil.isNullOrBlank(rs.getErrorMsg())){
				throw new DAOException(rs.getErrorMsg());
			}
		} catch (Exception ex){
			logger.error("获取父应用下拉列表html输出失败!",ex);
		}
		
		StringBuilder rtv = new StringBuilder(250);
		rtv.append("<select>");
		while (rs.next()) {
			rtv.append("<option value='").append(rs.getString("app_pk")).append("'>")
			   .append(rs.getString("app_pk")).append(" ")
			   .append(rs.getString("app_name")).append("</option>");
		}
		rtv.append("</select>");
		// 输出
		rc.setResponseEncoding("utf-8");
		rc.getWriter().println(rtv.toString());
	}
}
