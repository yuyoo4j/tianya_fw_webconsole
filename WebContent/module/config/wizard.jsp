<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>应用配置</title>
	<%@ include file="/commonHead.jsp" %>
	<link type="text/css" href="${ path }/css/jqGrid/ui.jqgrid.css" rel="Stylesheet" />	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.base64.js"></script>	 
	<%@ include file="/commonGrid.jsp" %>
	<script type="text/javascript" src="js/appList.js"></script>	 
	<script type="text/javascript"> 
	</script> 
</head>
<body>  
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">应用查询</a></li>
			<li><a href="#tabs-2">资源管理</a></li>			
		</ul>
		<div id="tabs-1">			
			<table id="list" class="scroll"></table> 
			<div id="pager" class="scroll" style="text-align:center;"></div>  
		</div>
		<div id="tabs-2">			
			<iframe id="contentPane" src=""  scrolling="no" marginwidth="0" marginheight="0" 
			frameborder="0" vspace="0" hspace="0" style="overflow:visible; width:100%; height: 550px;"></iframe> 
		</div>
	</div>
</body>
</html>