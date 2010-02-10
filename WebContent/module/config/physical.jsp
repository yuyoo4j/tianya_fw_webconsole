<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>物理资源</title>
	<%@ include file="/commonHead.jsp" %>
	<link type="text/css" href="${ path }/css/jqGrid/ui.jqgrid.css" rel="Stylesheet" />	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.base64.js"></script>	 
	<%@ include file="/commonGrid.jsp" %> 
	<script type="text/javascript"> 
		jQuery(document).ready(function() {   
			try { 
				jQuery("#tabs").tabs({
					collapsible: true 
				}); 
			} catch (em) {
				alert("初始化页面异常:" + em);
			}
		});
	</script> 
</head>
<body>  
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">主机资源</a></li>		
		</ul>
		<div id="tabs-1">			
			<iframe id="contentPane" src="physicalHost.jsp"  scrolling="no" marginwidth="0" marginheight="0" 
			frameborder="0" vspace="0" hspace="0" style="overflow:visible; width:100%; height: 550px;"></iframe> 
		</div> 
	</div>
</body>
</html>