/*
 * JSP generated by Resin-3.0.22 (built Mon, 13 Nov 2006 09:32:38 PST)
 */

package _jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _welcomecontent__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.Application _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = com.caucho.jsp.QJspFactory.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html; charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      
	request.setAttribute("path", request.getContextPath()); //smoothness

      out.write(_jsp_string1, 0, _jsp_string1.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string2, 0, _jsp_string2.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string3, 0, _jsp_string3.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string5, 0, _jsp_string5.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      com.caucho.jsp.QJspFactory.freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.make.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.util.CauchoSystem.getVersionId() != -3988812337524854005L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.make.Dependency depend;
      depend = (com.caucho.make.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("welcomeContent.jsp"), -1997256119106265184L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("commonHead.jsp"), -3243992860496663163L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("path");

  private final static char []_jsp_string6;
  private final static char []_jsp_string1;
  private final static char []_jsp_string3;
  private final static char []_jsp_string2;
  private final static char []_jsp_string4;
  private final static char []_jsp_string0;
  private final static char []_jsp_string5;
  static {
    _jsp_string6 = "';\r\n	</script>\r\n\r\n	<script type=\"text/javascript\" src=\"js/jqueryPlugins/jquery.layout.js\"></script>\r\n</head>\r\n<body > \r\n	 ----------------------------------------------------------------cn.tianya.fw.webconsole--------------------------------------------------------------------------\r\n</body>\r\n</html>".toCharArray();
    _jsp_string1 = "\r\n\r\n	<link type=\"text/css\" href=\"".toCharArray();
    _jsp_string3 = "/js/jquery/jquery-1.3.2.min.js\"></script>\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string2 = "/css/custom-theme/jquery-ui-1.7.2.custom.css\" rel=\"Stylesheet\" />	\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string4 = "/js/jquery/jquery-ui-1.7.2.custom.min.js\"></script>	\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string0 = " \r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n	<title>\u6b22\u8fce\u9875\u9762</title>	\r\n	\r\n".toCharArray();
    _jsp_string5 = "/js/jqueryPlugins/jqueryCustomExtend.js\"></script>	\r\n	<script type=\"text/javascript\">\r\n	\r\n		var contextPath = '".toCharArray();
  }
}
