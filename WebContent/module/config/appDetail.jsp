<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>应用配置</title>
	<%@ include file="/commonHead.jsp" %>
	<link type="text/css" href="${ path }/css/jqGrid/ui.jqgrid.css" rel="Stylesheet" />	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.form.js"></script> 	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.deserialize.js"></script> 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.base64.js"></script> 
	<%@ include file="/commonGrid.jsp" %>
	<script type="text/javascript" src="js/appDetail.js"></script> 
	<script type="text/javascript">

		var rowJson = '${param.rowJson}';
		var rj = null;
		var row = null;
	
	</script> 
</head>
<body>  
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">应用信息</a></li>
			<li><a href="#tabs-2">数据库资源</a></li>
			<li><a href="#tabs-3">socket服务器资源</a></li>	
			<li><a href="#tabs-4">分布式缓存资源</a></li>		
			<li><a href="#tabs-5">组件服务器资源</a></li>		
			<li><a href="#tabs-6">分布式存储资源</a></li>		
		</ul>
		<div id="tabs-1">			 
			<form id="appForm"  class="cmxform" method="get" action="">
				<fieldset>
					<legend> 应用 </legend>
					<p>
						<label for="app_pk">应用编号:</label>
						<input id="app_pk" name="app_pk" class="required" minlength="2" disabled="disabled" />*
					<p>
						<label for="app_name">应用名称:</label>
						<input id="app_name" name="app_name" class="required" disabled="disabled" />*
					</p>
					<p>
						<label for="curl">状态:</label>
						<select name="app_state" class="select" disabled="disabled" >
							<option value='1' selected>启用</option>
							<option value='2'>游览</option>
							<option value='3'>禁用</option> 
						</select>*
					</p>
					<p>
						<label for="remark">备注:</label>
						<textarea id="remark" name="remark" disabled="disabled" ></textarea>
					</p>
					<p> 
					</p>
				</fieldset>
			</form>						
		</div>
		<div id="tabs-2">			 
			<table id="list1" class="scroll"></table> 
			<div id="pager1" class="scroll" style="text-align:center;"></div>  
		</div>
		<div id="tabs-3">			 
			<table id="list2" class="scroll"></table> 
			<div id="pager2" class="scroll" style="text-align:center;"></div>  
		</div> 
		<div id="tabs-4">			 
			<table id="list3" class="scroll"></table> 
			<div id="pager3" class="scroll" style="text-align:center;"></div>  
		</div> 
		<div id="tabs-5">			 
			<table id="list4" class="scroll"></table> 
			<div id="pager4" class="scroll" style="text-align:center;"></div>  
		</div> 
		<div id="tabs-6">			 
			<table id="list5" class="scroll"></table> 
			<div id="pager5" class="scroll" style="text-align:center;"></div>  
		</div> 
	</div>
</body>
</html>