<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>应用配置</title>
	<%@ include file="/commonHead.jsp" %> 
	<%@ include file="/commonJsTree.jsp" %> 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	
	<script type="text/javascript"> 
	
		jQuery(document).ready(function() {
			try {
				jQuery("#resourceTree").tree({
					data : { 
						type : "json",
						async : true,
						opts : {
							method : "POST",
							url : "api.do?pageAction=module.config.resource.queryRoot"
						}
					},
					callback : {
						beforedata: function(node, treeObj) {
							if (node != false) {
								treeObj.settings.data.opts.url = "api.do?pageAction=module.config.resource.queryChildren";
							} 
							return { id : $(node).attr("id") || 0 }
						},		
						ondblclk: function(node, treeObj) {
							// alert(jQuery.toJSON(node) + '<>' + jQuery.toJSON(treeObj));
						}
					},
					plugins : { 
						contextmenu : { 
							items : {
								create : {
									label	: "注册虚拟资源", 
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
									label	: "回收虚拟资源",
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
		});
	</script> 
</head>
<body>  
	<div ></div>
	<input type="text" value="" />		
	<input type="button" onclick="jQuery.tree.focused().search(jQuery(this).prev().val())" value="搜索" />
	<div id="resourceTree" class="demo" > 
	</div>
</body>
</html>