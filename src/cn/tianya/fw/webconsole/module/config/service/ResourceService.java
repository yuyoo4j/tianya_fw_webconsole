package cn.tianya.fw.webconsole.module.config.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.webconsole.mvc.RequestContext;

public final class ResourceService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);
	
	public void handleRootQuery() {

		RequestContext rc = RequestContext.getRequestContext();		
		Map<String, String> params = rc.getParameterMap();
		
		logger.debug("queryStr:{}", rc.getRequest().getQueryString());
		logger.debug("params:{}", params);

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(" { data : '虚拟资源树', children : [ { data : '' } ], state : 'closed' }");
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
			sb.append(" { data : '部落资源 -- rsc_group', children : [ { data : '部落接口组件资源 -- rsc_group_component' }, { data : '部落前台资源 -- rsc_group_front' } ], state : 'open' },");
			sb.append(" { data : '论坛主副版资源 -- rsc_BBS', children : [ { data : '生肖血型资源 -- rsc_BBS_105' }], state : 'open' },");
			sb.append("]");
			rc.setResponseEncoding("utf-8");
			rc.getWriter().println(sb.toString());
		} else {
			rc.getWriter().println("0");
		}
	}
}
