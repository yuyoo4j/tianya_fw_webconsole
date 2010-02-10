<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录页面</title>
	<%@ include file="/commonHead.jsp" %>
	 <style>  
		 #wrapper {  
		   position: absolute;  
		   width:250px;  
		   height:200px;  
		   left:50%;  
		   top:50%;  
		   margin-left:-250px;  
		   margin-top:-100px;  
		   border: solid 0px red;  
		 }  
	 </style>
	<link type="text/css" href="${ path }/css/jquery.tips.css" rel="Stylesheet" />	
	<link type="text/css" href="${ path }/css/jquery.jgrowl.css" rel="Stylesheet" />
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.form.js"></script>	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.cookie.js"></script>	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.tips.js"></script> 	
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.jgrowl.js"></script>	 
	<script type="text/javascript">

		var returnURL = '${param.returnURL}';

		$(document).ready(function() { 

			// 设置tip效果
	        $("#memoryTip").tips({tipType:1});

			// 初始化
			 doInit();

		   // form选项
			var options = { 
		        // target:        '#output2',   // target element(s) to be updated with server response 
		        beforeSubmit:  doBeforeSubmit,  // pre-submit callback 
		        success:       doSuccessSubmit,  // post-submit callback 
		 
		        // other available options: 
		        url : 'login.do?pageAction=sucerity.manager.doLogin',         // override for form's 'action' attribute 
		        type: 'post',        // 'get' or 'post', override for form's 'method' attribute 
		        dataType:  'json',       // 'xml', 'script', or 'json' (expected server response type) 
		        //clearForm: true        // clear all form fields after successful submit 
		        //resetForm: true        // reset the form after successful submit 
		 
		        // $.ajax options can be used here too, for example: 
		        timeout:   6000 
		    }; 
		 
		    // bind to the form's submit event 
		    $('#loginForm').submit(function() { 
			    
		        // inside event callbacks 'this' is the DOM element so we first 
		        // wrap it in a jQuery object and then invoke ajaxSubmit 
		        $(this).ajaxSubmit(options); 
		 
		        // !!! Important !!! 
		        // always return false to prevent standard browser submit and page navigation 
		        return false; 
		    }); 
	    }); 

	    function doInit() {
				    	
		    var c = jQuery.cookie("memory"); 
		    if (null != c ) {
		    	jQuery("#userName").attr("value", c);
		    	jQuery("#memory").attr("checked", true);
			} 

			jQuery("#memory").click(updateMemory); 			
		}

    	function updateMemory() {

			if (jQuery("#memory").attr("checked") == true) {
		        var un = jQuery("#userName").attr("value");
	        	jQuery.cookie("memory", un, { expires: 7});
		    } else {
		    	jQuery.cookie("memory", null);
		    }      	
        }

	    function doBeforeSubmit() {

	        var userName = $('#userName').attr("value");
 
	        var password = $('#password').attr("value"); 
	        updateMemory();
	        return true;
	    }
	
	    function doSuccessSubmit(res) { 
			if ("success" == res.operation) {
				if ('' != returnURL) {
					window.location.href = returnURL;
				} else {
					window.location.href = "index.jsp";
				}
			} else {
				jQuery.jGrowl("登录失败: " + res.info, {header:"错误信息", life: 5000, sticky:false, theme:'ui-state-error'}); 
			}
	    }
	</script>
</head>
<body> 
	<div id="wrapper" title="技术管理后台"> 
		<form id="loginForm"  class="cmxform" method="post" action="">
			<fieldset>
				<legend>技术管理后台</legend>
				<p>
					<div> 
						<div id="memoryTip" style="float:right" title="7天内记住用户">	
							<input id="memory" type="checkbox" name="memory" /> 
						</div>
						<div>
							<label for="userName">用户:</label>
							<input id="userName" name="userName" class="ui-state-default ui-corner-all" title="请输入用户名" />
						</div>
					</div>
				<p>
					<label for="password">密码:</label>
					<input id="password" name="password" type="password" class="ui-state-default ui-corner-all" title="请输入密码" />
				</p> 
				<p> 
					<div style="text-align:center">
						<input id="loginBtn" type="submit" name="submit" value="登录" class="fm-button ui-state-default ui-priority-primary ui-corner-all" />
						<input id="resetBtn" type="reset" name="reset"  value="重置" class="fm-button ui-state-default ui-priority-secondary ui-corner-all" />
					</div>
				</p>   
			</fieldset>
		</form>	
	</div>	
</body>
</html>