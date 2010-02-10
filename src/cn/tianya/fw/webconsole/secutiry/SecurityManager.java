package cn.tianya.fw.webconsole.secutiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tianya.fw.webconsole.mvc.RequestContext;

public class SecurityManager {

	private static Logger log = LoggerFactory.getLogger(SecurityManager.class);
	
	/**
	 * 登录
	 */
	public void doLogin() {
		
		RequestContext rc = RequestContext.getRequestContext();
		String userName = rc.getParameter("userName");
		String password = rc.getParameter("password");
		log.debug("userName:{}, password:{}", userName, password);
		Boolean isLogin = Boolean.FALSE;
		if (null != userName) {
			isLogin = Boolean.valueOf(userName.equals(password));
		}	
		if (isLogin.booleanValue()) {
			rc.getRequest().getSession().setAttribute("isLogin", isLogin);
			rc.getRequest().getSession().setAttribute("userName", userName);
			rc.getWriter().println("{operation:'success', info:''}");
		} else {
			rc.getWriter().println("{operation:'failure', info:'user != password'}");
		}
	}
	
	/**
	 * 注销
	 */
	public void doLogout() {
		
		RequestContext rc = RequestContext.getRequestContext();
		rc.getRequest().getSession().removeAttribute("isLogin");
		rc.getRequest().getSession().invalidate();
	}
}
