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
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.form.js"></script>	 
	<%@ include file="/commonGrid.jsp" %>
	<script type="text/javascript" src="js/index.js"></script>	 
	<script type="text/javascript"> 
	</script> 
</head>
<body>  
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">用户管理</a></li>
			<li><a href="#tabs-2">角色管理</a></li>			
			<li><a href="#tabs-2">权限管理</a></li>					
			<li><a href="#tabs-2">对象管理</a></li>
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
	
	<div id="userDialog" title="创建新用户" style="white-space:normal; width:200px;">	
		<form >
		<fieldset>
		    <p>		    
				<label for="name">用户名称</label>
				<input type="text" name="user_label" id="user_label" class="text ui-widget-content ui-corner-all" />
			</p>
			<p>
				<label for="email">登录ID&nbsp;&nbsp;&nbsp;</label>
				<input type="text" name="user_name" id="user_name" value="" class="text ui-widget-content ui-corner-all" />
			</p>
			<p>
				<label for="password">密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" /> 
			</p>
			<p>
				<label for="cfm_password">确认密码</label>
				<input type="password" name="cfm_password" id="cfm_password" value="" class="text ui-widget-content ui-corner-all" /> 
			</p>
			<p>
				<label for="user_state">状态&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<select id="user_state" name="user_state" >
					<option value="1" default="1">启动</option>
					<option value="0" >禁用</option>
				</select>
			</p>
			<p>
				<label for="security_question">安全问题</label>
				<input type="text" name="security_question" id="security_question" value="" class="text ui-widget-content ui-corner-all" />
			</p>
			<p>
				<label for="security_answer">安全答案</label> 
				<input type="text" name="security_answer" id="security_answer" value="" class="text ui-widget-content ui-corner-all" />
			</p>
			<p>
				<label for="memo">备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<textarea name="memo" id="memo" ></textarea>
			</p>
			<p> 
				<div style="text-align:center">
					<input id="submitBtn" type="submit" name="submit" value="提交" class="fm-button ui-state-default ui-priority-primary ui-corner-all" />
					<input id="resetBtn" type="reset" name="reset"  value="重置" class="fm-button ui-state-default ui-priority-secondary ui-corner-all" />
				</div>
			</p>  		
		</fieldset>
		</form>
	</div>
	
</body>
</html>