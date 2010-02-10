package cn.tianya.fw.webconsole.module.system.service;

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
import cn.tianya.fw.webconsole.module.config.service.AppService;
import cn.tianya.fw.webconsole.mvc.RequestContext;

public class SecurityService {
	
	private static final Logger logger = LoggerFactory.getLogger(AppService.class);

	public void handleListQuery() {
		
		RequestContext rc = RequestContext.getRequestContext();		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);
		
		StringBuffer sql = FreemarkerFactory.process("ftl.sql.user.list", params);		
		String s = sql.toString();		
		logger.debug("sql:{}", s);
		
		AppClient ac = new AppClient(); 
		//结果集
		RowSetXml rs=null;
		//增加SQL
		ac.addSql(s);
		try{
			String appId = "demo_sys_core";
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
			res.addCell(index, rs.getString("user_id"));
			res.addCell(index, rs.getString("user_label"));
			res.addCell(index, rs.getString("user_name"));
			res.addCell(index, rs.getString("password"));
			res.addCell(index, rs.getString("user_state"));
			res.addCell(index, rs.getString("create_time"));
			res.addCell(index, rs.getString("creator"));
			res.addCell(index, rs.getString("memo"));
			index++;
		}

		StringBuffer sb = FreemarkerFactory.process("grid.data.xml.ftl", res);
		String content = sb.toString();
		logger.debug("content:{}", content);
		rc.setResponseEncoding("utf-8");
		rc.setResponseHeader("Content-type", "text/xml;charset=utf-8");
		rc.getWriter().println(content);
	}
	
	public void handleAddUser() {
		
		RequestContext rc = RequestContext.getRequestContext();	
		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);
		
		String createTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		params.put("create_time", createTime);
		StringBuffer sql = FreemarkerFactory.process("ftl.sql.user.add_item", params);	 
		
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
	
	public void handleEditUser() {
		
	}
	
	public void handleDeleteUser() {
		
	}
}
