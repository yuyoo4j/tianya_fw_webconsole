package cn.tianya.fw.webconsole.mvc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求上下文
 * @author yuyoo (yuyoo4j@163.com)
 * @date 2010-1-6 上午08:54:41
 */
public final class RequestContext {

	/**
	 * 当前线程请求上下文保持容器
	 */
	private static ThreadLocal<RequestContext> contextHolder = new ThreadLocal<RequestContext>();
	
	/**
	 * http 请求
	 */
	private HttpServletRequest request = null;
	
	/**
	 * http 响应
	 */
	private HttpServletResponse response = null;
	
	/**
	 * 获取当前http请求上下文
	 * @return -- http请求上下文
	 */
	public static RequestContext getRequestContext() {
		
		return contextHolder.get();
	}
	
	/**
	 * 设置当前http请求上下文
	 * @param rc -- http请求上下文
	 */
	public static void setRequestContext(RequestContext rc) {
		
		contextHolder.set(rc);
	}
	
	/**
	 * 根据http请求与响应创建 http请求上下文
	 * @param req -- http请求
	 * @param res -- http响应
	 * @return -- http请求上下文
	 */
	public static RequestContext newInstance(HttpServletRequest req, HttpServletResponse res) {
		
		return new RequestContext(req, res);
	}
	
	/**
	 * 根据http请求与响应设置当前http请求上下文
	 * @param req -- http请求
	 * @param res -- http响应
	 */
	public static void setRequestContext(HttpServletRequest req, HttpServletResponse res) {
		
		RequestContext rc = newInstance(req, res);
		setRequestContext(rc);
	}
	
	private RequestContext(HttpServletRequest req, HttpServletResponse res) {
		
		this.request = req;
		this.response = res;
	}
	
	/**
	 * 获取http请求参数
	 * @param param -- 参数名称
	 * @return -- 参数值
	 */
	public String getParameter(String param) {
		
		return getParameter(param, false);
	}
	
	/**
	 * 获取http请求参数
	 * @param param -- 参数名称
	 * @param decode -- 是否进行URI解码
	 * @return -- 参数值
	 */
	public String getParameter(String param, boolean decode) {
		
		String p = this.request.getParameter(param);
		if (null != p && decode) {
			try {
				p = URLDecoder.decode(p, "utf-8");
			} catch (UnsupportedEncodingException e) { /* swallow exception */ }
		}
		return p;
	}
	
	/**
	 * 获取http请求参数Map
	 * @return 参数Map
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getParameterMap() {
		
		Map<String, String> params = new HashMap<String, String>();
		Map raw = request.getParameterMap();
		for (Object key : raw.keySet()) {
			Object[] arr = (Object[]) raw.get(key);
			try {
				String k = URLDecoder.decode((String)key, "utf-8");
				String v = URLDecoder.decode((String)arr[0], "utf-8");
				params.put(k, v);
			} catch (UnsupportedEncodingException ex) { /* swallow exception */ }  
		}
		return params;
	}
	
	/**
	 * 设置http请求属性
	 * @param key -- 属性key
	 * @param attr -- 属性值
	 */
	public void setAttribute(String key, Object attr) {
		
		this.request.setAttribute(key, attr);
	}
	
	/**
	 * 获取http请求属性
	 * @param key -- 属性key
	 * @return -- 属性值
	 */
	public Object getAttribute(String key) {
		
		return this.request.getAttribute(key);
	}
	
	/**
	 * 获取http请求
	 * @return -- http请求
	 */
	public HttpServletRequest getRequest() {
		
		return this.request;
	}
	
	/**
	 * 获取http响应
	 * @return -- http响应
	 */
	public HttpServletResponse getResponse() {
		
		return this.response;
	}
	
	/**
	 * 设置http请求的字符编码
	 * @param encoding -- 编码类型
	 */
	public void setRequestEncoding(String encoding) {
		
		try {
			this.request.setCharacterEncoding(encoding);
		} catch (UnsupportedEncodingException ex) {
			throw new UnsupportedOperationException("不能设置http请求编码, 不支持此操作", ex);
		}
	}
	
	public void setResponseHeader(String header, String value) {
		
		this.response.setHeader(header, value);
	}
	
	/**
	 * 设置http响应的字符编码
	 * @param encoding -- 编码类型
	 */
	public void setResponseEncoding(String encoding) {
		
		this.response.setCharacterEncoding(encoding);
	}
	
	/**
	 * 获取http响应输出流
	 * @return -- 输出流
	 */
	public OutputStream getOutputStream() {
		
		try {
			return this.response.getOutputStream();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * 获取http响应输出流写入器
	 * @return -- 写入器
	 */
	public PrintWriter getWriter() {
		
		try {
			return this.response.getWriter();
		} catch (IOException ex) { 
			throw new RuntimeException(ex);
		}
	}
}
