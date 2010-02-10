<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎页面</title>
	<%@ include file="/commonHead.jsp" %>
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.layout.js"></script>		
	<script type="text/javascript">
		$(document).ready(function () {
			$('body').layout({ applyDefaultStyles: true });
			$("#accordion").accordion({
				//collapsible: false,
				fillSpace: false,
				navigation: true
			});
			$("#left").resizable({
				resize: function() {
					$("#accordion").accordion("resize");
				},
				minHeight: 140
			});
			$("div[id^=button]").bind("click", function() {
			  	var target = $(this).attr('target');
			  	$('#contentPane').attr('src', target);
			});
			
			jQuery("#logout").bind("click", function() {
				window.location.href = "login.do?pageAction=sucerity.manager.doLogout&&pageView=uri";
			});
		}); 

	</script>
</head>
	<body>
	<DIV class="ui-layout-center" style="color: red; border-color: green; border-width: 20;">
		<iframe id="contentPane" src="welcomeContent.jsp"  scrolling="no" marginwidth="0" marginheight="0" 
		frameborder="0" vspace="0" hspace="0" style="overflow:visible; width:100%; height: 100%"></iframe>
	</DIV>
	<DIV class="ui-layout-north">
		<div>
			<div style="float:right;" >
				<button class="ui-state-default ui-priority-primary ui-corner-all">欢迎 ${ userName } </button>
				<button id="logout" class="fg-button ui-state-default ui-priority-secondary ui-corner-all">退出</button>
			</div>
			<div>天涯基础架构管理控制台(WebConsole)</div>
		</div>
	</DIV>
	<DIV class="ui-layout-west">	 
		<div id="left" style="padding:0px; width:180px;" class="ui-widget-content">	
		<div id="accordion" >
			<h3 ><a href="#">配置管理</a></h3>
			<div>
				<div id="button00" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/wizard.jsp">向导服务</div>
				<div id="button010" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/bizEntity.jsp">业务实体</div>
				<div id="button011" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/resource.jsp">虚拟资源</div>
				<div id="button01" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/pool.jsp">虚拟池</div>
				<div id="button02" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/node.jsp">虚拟节点</div>
				<div id="button03" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/service.jsp">服务节点</div>
				<div id="button04" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/config/physical.jsp">物理资源</div> 
			</div>
			<h3><a href="#">系统管理</a></h3>
			<div >
				<div id="button10" class="ui-state-default ui-corner-all" style="cursor: pointer; margin: 2px;"
				  target="${path }/module/system/security/index.jsp">权限安全</div>
			</div>  
		</div>   		
	</DIV>		
</body>
</html>