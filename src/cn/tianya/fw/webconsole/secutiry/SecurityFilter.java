package cn.tianya.fw.webconsole.secutiry;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	
	private boolean sucerityFilter = true;
	
	private String failureRedirect = "/login.html";
	
	private Set<String> securtiryURL = new HashSet<String>(250, 0.9f);
	
	private String contextPath = null;

    /**
     * Default constructor. 
     */
    public SecurityFilter() { 
    	
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		contextPath = "/" + fConfig.getServletContext().getServletContextName();
		
		String sf = fConfig.getInitParameter("security_filter");
		sucerityFilter = !"false".equals(sf); // 除非配置为不安全过滤,否则需要进行安全过滤
		
		String su = fConfig.getInitParameter("security_url");
		if (null != su) {
			String[] ss = su.trim().split(";");
			for (String item : ss) {
				securtiryURL.add(item.trim());
			}
		}
		
		String fr = fConfig.getInitParameter("failure_redirect");		 
		failureRedirect = (null == fr) ? contextPath + failureRedirect : contextPath + fr.trim();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() { 
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (!sucerityFilter) {	// 配置为不用安全过滤, 执行请求链
			chain.doFilter(request, response);
			return;
		}

		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
 		
		Boolean isLogin = (Boolean)req.getSession().getAttribute("isLogin");
		
		if (null != isLogin && isLogin.booleanValue()) { // 已经登录	,执行请求链
			chain.doFilter(request, response);
			return;
		} else { // 执行登录行为
			 
			String servletPath = req.getServletPath();
			if (securtiryURL.contains(servletPath)) {
				chain.doFilter(request, response);	
			} else {
				String qs = req.getQueryString(); 
				String returnURL = (null == qs) ? contextPath + servletPath : contextPath + servletPath + "?" + qs;
				String url = failureRedirect + "?returnURL=" + returnURL;
				System.out.println("url------->" + url);
				res.sendRedirect(url);
			} 
		}
	}
}
