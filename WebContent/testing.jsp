<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>测试页面</title>
	<%@ include file="/commonHead.jsp" %>	
	<script type="text/javascript">
		$(document).ready(function() {

			var params = {};
			jQuery.ajax('data.do?pageAction=module.config.app.queryAppList&pageView=null',
					params, 
					function(res) {
						alert( jQuery.isXMLDoc(res) );
					}
			);			
		}); 

	</script>
</head>
	<body> 
	</body>
</html>