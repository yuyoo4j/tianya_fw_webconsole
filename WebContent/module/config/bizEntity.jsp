<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>应用配置</title>
	<%@ include file="/commonHead.jsp" %>
	<link type="text/css" href="${ path }/css/jqGrid/ui.jqgrid.css" rel="Stylesheet" />
	<style type="text/css" class="source">
		a.search { color:green !important; }
	</style>		 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.base64.js"></script>	
	<%@ include file="/commonJsTree.jsp" %> 
	<script type="text/javascript"> 
 	
		jQuery(document).ready(function() {   
			try {
				doInitTree();
				
				jQuery("#tabs").tabs({
					collapsible: true 
				});			  
				
			} catch (em) { 
				alert('页面初始化出错:' + em); 
			}
		}); 	

		function doInitTree() {
			try { 
						
				jQuery("#bizEntityTree").tree({
					data : { 
						type : "json",
						async : true,
						opts : {
							method : "POST",
							url : "api.do?pageAction=module.config.bizEntity.queryRoot"
						}
					},
					callback : {
						beforedata: function(node, tree) {
							// alert($.toJSON(node));
							if (node != false) {
								tree.settings.data.opts.url = "api.do?pageAction=module.config.bizEntity.queryChildren";
							}  
							return { id : $(node).attr("id") || 0 }
						},		
						ondblclk: function(node, tree) {
							// alert(jQuery.toJSON(node) + '<>' + jQuery.toJSON(tree));
						}
					},
					plugins : { 
						contextmenu : { 
							items : {
								create : {
									label	: "创建业务实体", 
									icon	: "create",
									visible	: function (NODE, TREE_OBJ) { 
										if(NODE.length != 1) return 0; 
										return TREE_OBJ.check("creatable", NODE); 
									}, 
									action	: function (NODE, TREE_OBJ) { 
										TREE_OBJ.create(false, TREE_OBJ.get_node(NODE[0])); 
									},
									separator_after : true
								},
								rename : {
									label	: "重命名", 
									icon	: "rename",
									visible	: function (NODE, TREE_OBJ) { 
										if(NODE.length != 1) return false; 
										return TREE_OBJ.check("renameable", NODE); 
									}, 
									action	: function (NODE, TREE_OBJ) { 
										TREE_OBJ.rename(NODE); 
									} 
								},
								remove : {
									label	: "删除业务实体",
									icon	: "remove",
									visible	: function (NODE, TREE_OBJ) { 
										var ok = true; 
										$.each(NODE, function () { 
											if(TREE_OBJ.check("deletable", this) == false) {
												ok = false; 
												return false; 
											}
										}); 
										return ok; 
									}, 
									action	: function (NODE, TREE_OBJ) { 
										$.each(NODE, function () { 
											TREE_OBJ.remove(this); 
										}); 
									} 
								}
												 
							}
						}
					}
							
				});
			} catch (em) {
				alert("页面初始化异常:" + em);
			}
		} 
	</script> 
</head>
<body>  
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">树视图</a></li>
			<li><a href="#tabs-2">业务实体管理</a></li>			
		</ul>
		<div id="tabs-1">			
			<input type="text" value="" />		
			<input type="button" onclick="jQuery.tree.focused().search(jQuery(this).prev().val())" value="搜索" />
			<div id="bizEntityTree" class="demo" style="overflow:visible; width:100%; height: 550px;">&nbsp;</div>
		</div>
		<div id="tabs-2">			
			<iframe id="contentPane" src="bizEntityManage.jsp"  scrolling="no" marginwidth="0" marginheight="0" 
			frameborder="0" vspace="0" hspace="0" style="overflow:visible; width:100%; height: 550px;"></iframe> 
		</div>
	</div>
</body>
</html>