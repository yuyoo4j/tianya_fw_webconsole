package cn.tianya.fw.webconsole.module.config.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.webconsole.mvc.RequestContext;

public final class BizEntityService {

	private static final Logger logger = LoggerFactory.getLogger(BizEntityService.class);
	
	public void handleRootQuery() {

		RequestContext rc = RequestContext.getRequestContext();		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(" { data : '业务实体树', children : [ { data : '' } ], state : 'closed' }");
		sb.append("]");
		rc.setResponseEncoding("utf-8");
		rc.getWriter().println(sb.toString());
	}

	public void handleChildrenQuery() {

		RequestContext rc = RequestContext.getRequestContext();		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);

		if (!params.containsKey("search")) {
		
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(" { data : '天涯部落 -- group', children : [ { data : '部落官方后台 -- group_admin' }, { data : '部落官方 -- group_blgf' }, { data : '部落接口组件 -- group_component' }, { data : '部落前台 -- group_front' }, { data : '部落用户管理后台 -- group_manage' }], state : 'open' },");
			sb.append(" { data : '论坛主副版 -- BBS', children : [ { data : '天涯医院 -- BBS_100' }, { data : '没话找话 -- BBS_102' }, { data : '收藏天地 -- BBS_103' }, { data : 'QQ驿站 -- BBS_104' }, { data : '生肖血型 -- BBS_105' }], state : 'open' },");
			sb.append("]");
			rc.setResponseEncoding("utf-8");
			rc.getWriter().println(sb.toString()); 
		} else {
			rc.getWriter().println("0");
		}
	}
}
