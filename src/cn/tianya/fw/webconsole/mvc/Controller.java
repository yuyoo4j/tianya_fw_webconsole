package cn.tianya.fw.webconsole.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http服务中MVC的控制器
 * @author yuyoo (yuyoo4j@163.com)
 * @date 2010-1-6 上午10:06:50
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		String pageAction = request.getParameter("pageAction");
		String pageView = request.getParameter("pageView");
		
		if (null == pageAction || "".equals(pageAction)) {
			throw new ServletException("not exists request handle method hint parameter[pageAction]");
		}
		// 如果没有设置pageView参数,将请求路径转成页面路径并转向(forward)
		if ("uri".equals(pageView)) {
			
			String reqPath = request.getServletPath();
			int qIndix = reqPath.indexOf("?");
			int index = reqPath.lastIndexOf(".", reqPath.length() - qIndix);
			pageView = reqPath.substring(0, index) + ".jsp";
		} 		 
		RequestContext.setRequestContext(request, response);
		RequestMethodFactory.invokeMethod(pageAction, new Object[]{ });
		
		// 不需要转向(forward)页面处理数据
		if (null != pageView && !"".equals(pageView)) {
			RequestDispatcher rd = request.getRequestDispatcher(pageView);  
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		doGet(request, response);
	}

}
