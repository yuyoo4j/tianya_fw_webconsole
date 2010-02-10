<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setAttribute("path", request.getContextPath()); //smoothness
%>

	<link type="text/css" href="${ path }/css/custom-theme/jquery-ui-1.7.2.custom.css" rel="Stylesheet" />	
	<script type="text/javascript" src="${ path }/js/jquery/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${ path }/js/jquery/jquery-ui-1.7.2.custom.min.js"></script>	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.customExtend.js"></script>	
	<script type="text/javascript">
	
		var contextPath = '${ path }';
				
	</script>
