/*
 * JSP generated by Resin-3.0.22 (built Mon, 13 Nov 2006 09:32:38 PST)
 */

package _jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _index__jsp extends com.caucho.jsp.JavaPage{
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
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string7, 0, _jsp_string7.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string8, 0, _jsp_string8.length);
      _caucho_expr_0.print(out, pageContext, false);
      out.write(_jsp_string9, 0, _jsp_string9.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("index.jsp"), -3947918500196929103L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("commonHead.jsp"), -3243992860496663163L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private final static com.caucho.el.Expr _caucho_expr_0 =
    new com.caucho.el.IdExpr("path");

  private final static char []_jsp_string8;
  private final static char []_jsp_string1;
  private final static char []_jsp_string3;
  private final static char []_jsp_string2;
  private final static char []_jsp_string4;
  private final static char []_jsp_string6;
  private final static char []_jsp_string0;
  private final static char []_jsp_string7;
  private final static char []_jsp_string5;
  private final static char []_jsp_string9;
  static {
    _jsp_string8 = "/module/config/appList.jsp\">\u5e94\u7528\u914d\u7f6e</div>\r\n				<div id=\"button1\" class=\"ui-state-default ui-corner-all\" style=\"cursor: pointer; margin: 2px;\"\r\n				  target=\"".toCharArray();
    _jsp_string1 = "\r\n\r\n	<link type=\"text/css\" href=\"".toCharArray();
    _jsp_string3 = "/js/jquery/jquery-1.3.2.min.js\"></script>\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string2 = "/css/custom-theme/jquery-ui-1.7.2.custom.css\" rel=\"Stylesheet\" />	\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string4 = "/js/jquery/jquery-ui-1.7.2.custom.min.js\"></script>	\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string6 = "';\r\n	</script>\r\n\r\n	<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string0 = " \r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n	<title>\u6b22\u8fce\u9875\u9762</title>\r\n	\r\n".toCharArray();
    _jsp_string7 = "/js/jqueryPlugins/jquery.layout.js\"></script>		\r\n	<script type=\"text/javascript\">\r\n		$(document).ready(function () {\r\n			$('body').layout({ applyDefaultStyles: true });\r\n			$(\"#accordion\").accordion({\r\n				//collapsible: false,\r\n				fillSpace: false,\r\n				navigation: true\r\n			});\r\n			$(\"#left\").resizable({\r\n				resize: function() {\r\n					$(\"#accordion\").accordion(\"resize\");\r\n				},\r\n				minHeight: 140\r\n			});\r\n			$(\"div[id^=button]\").bind(\"click\", function(){\r\n			  	var target = $(this).attr('target');\r\n			  	$('#contentPane').attr('src', target);\r\n			});\r\n		}); \r\n\r\n	</script>\r\n</head>\r\n	<body>\r\n	<DIV class=\"ui-layout-center\" style=\"color: red; border-color: green; border-width: 20;\">\r\n		<iframe id=\"contentPane\" src=\"welcomeContent.jsp\"  scrolling=\"no\" marginwidth=\"0\" marginheight=\"0\" \r\n		frameborder=\"0\" vspace=\"0\" hspace=\"0\" style=\"overflow:visible; width:100%; height: 100%\"></iframe>\r\n	</DIV>\r\n	<DIV class=\"ui-layout-north\">\r\n		<div>\u5929\u6daf\u57fa\u7840\u67b6\u6784\u7ba1\u7406\u63a7\u5236\u53f0(WebConsole)</div>\r\n	</DIV>\r\n	<DIV class=\"ui-layout-west\">	 \r\n		<div id=\"left\" style=\"padding:0px; width:180px;\" class=\"ui-widget-content\">	\r\n		<div id=\"accordion\" >\r\n			<h3 ><a href=\"#\">\u914d\u7f6e\u7ba1\u7406</a></h3>\r\n			<div>\r\n				<div id=\"button0\" class=\"ui-state-default ui-corner-all\" style=\"cursor: pointer; margin: 2px;\"\r\n				  target=\"".toCharArray();
    _jsp_string5 = "/js/jqueryPlugins/jqueryCustomExtend.js\"></script>	\r\n	<script type=\"text/javascript\">\r\n	\r\n		var contextPath = '".toCharArray();
    _jsp_string9 = "/module/config/appConfig.jsp\">Socket Server\u67e5\u8be2</div>\r\n				<div id=\"button2\" class=\"ui-state-default ui-corner-all\" style=\"cursor: pointer; margin: 2px;\"\r\n				  target=\"http://www.g.cn\">Memcached\u67e5\u8be2</div>\r\n				<div id=\"button3\" class=\"ui-state-default ui-corner-all\" style=\"cursor: pointer; margin: 2px;\"\r\n				  target=\"http://www.g.cn\">Component Server\u67e5\u8be2</div> \r\n			</div>\r\n			<h3><a href=\"#\">\u7cfb\u7edf\u7ba1\u7406</a></h3>\r\n			<div >\r\n				<div>hello</div> \r\n			</div>  \r\n		</div>   		\r\n	</DIV>		\r\n</body>\r\n</html>".toCharArray();
  }
}
