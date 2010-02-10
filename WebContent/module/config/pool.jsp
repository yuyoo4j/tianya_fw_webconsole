<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>业务实体管理页面</title>
	<%@ include file="/commonHead.jsp" %>
	<link type="text/css" href="${ path }/css/jqGrid/ui.jqgrid.css" rel="Stylesheet" />	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.json-2.2.min.js"></script>	 
	<script type="text/javascript" src="${ path }/js/jqueryPlugins/jquery.base64.js"></script>	 
	<%@ include file="/commonGrid.jsp" %>
	<script type="text/javascript"> 
 	
		jQuery(document).ready(function() {   
			try {
				/*
				jQuery("#tabs").tabs({
					collapsible: true 
				});
				*/
						
				jQuery("#list").jqGrid({
				  caption: '虚拟池列表',
				  url: contextPath + '/testing/demo/pool.xml', // 'api.do?pageAction=module.config.app.queryAppList', 
				  datatype: 'xml',
				  mtype: 'POST',
				  xmlReader : { 
					  root:"rows", 
					  row:"row",
					  page:"result>currentpage", 
					  total:"result>totalpages", 
					  records:"result>totalrecords", 
					  repeatitems:true,
					  cell:"cell",
					  id: "[id]",
					  userdata: "userdata"
					},
				  colNames:['虚拟资源池ID', '虚拟资源池名称','共享模式', '池类型', '池状态', '备注'],
				  colModel :[ 
				    {name:'pool_id', index:'pool_id',  editable:true, editrules:{edithidden:true, required:true}, width:120}, 
				    {name:'pool_name', index:'pool_name', editable:true, sortable:false, width:120}, 
				    {name:'share_mode', index:'share_mode', editable:true,
					    edittype:'select', formatter:'select', 
					    editoptions:{value:"0:保留;1:独占;2:共享"}, sortable:false,
					    width:120, align:'center'}, 
					{name:'pool_type', index:'pool_type', editable:true, 
							edittype:'select', formatter:'select', 
						    editoptions:{value:"db:数据库;db_proxy:数据访问代理;cache:分布式换成;dfs:分布式文件存储"}, sortable:false, width:120}, 
				    {name:'pool_state', index:'pool_state', editable:true, 
							    edittype:'select', formatter:'select', 
							    editoptions:{value:"0:停止;1:启动;2:离线;3:在线"}, sortable:false,
							    sortable:false, width:100, align:'center'},  
				    {name:'remark', index:'remark', editable:true, edittype:"textarea", sortable:false, width:120, sortable:false} ],
				  rownumbers: true,
				  rownumWidth: 40, 
				  autowidth: true, 
				  gridview: true, 
				  multiselect: false, 
				  pager: jQuery('#pager'),
				  page: 1, 		    
				  rowNum:10,
				  postData:{provider:"fw.tianya.cn"},
				  rowList:[10,20,30,100,200,500],
				  viewrecords: true,
				  sortname: 'id',
				  sortorder: "desc",
				  hiddengrid: false,
				  loadonce: false,
				  loadui:'enable',
				  loadtext:'loading...',  	  
				  cellEdit:false,
				  cellsubmit:'remote',
				  cellurl:' ', 
				  editurl:' ',
				  width: "auto",
				  height: "auto", 
				  toolbar:[false,"top"],
				 // loadBeforeSend : doTesting,
				 // loadComplete : doTesting,
				  ondblClickRow: doDbClickRow,
				  onPaging: doPaging
				}); 
				jQuery("#list").jqGrid('navGrid','#pager',{
					 edit:true,
					 add:true,
					 del:false
				},
				{  // mode[edit] property or event
					 width : 400,
					 height : 300,
					 checkOnSubmit : true,
					 mtype : "POST",
					 url : "api.do?pageAction=module.config.app.editAppItem",
					 processData : "background processing data!!!",
					 beforeShowForm : function(formid) { 
						jQuery("#app_pk", formid).attr("disabled", true);
						jQuery("#parents_pk", formid).attr("disabled", true); 
					 },
					onClose : function(formid) {
						jQuery("#app_pk", formid).attr("disabled", false);
						jQuery("#parents_pk", formid).attr("disabled", false);
						return true;
					}
				},
				{  // mode[add] property or event
					 width : 400,
					 height : 300,
					 mtype : "POST",
					 url : "api.do?pageAction=module.config.app.addAppItem"
				},
				{  // mode[delete] property or event
					 url : "api.do?pageAction=module.config.app.deleteAppItem"
				},
				{  // mode[search] property or event
				},
				{  // mode[view] property or event
				}); 
			} catch (em) { 
				alert('页面初始化出错:' + em); 
			}
		}); 	
	
		function doDbClickRow(rowid) {
			try {
				var rowData = jQuery("#list").getRowData(rowid); 
				var json = jQuery.toJSON(rowData); 	
				var jp = jQuery.encodeBase64URLSafe(jQuery.base64Encode(json));		 
				var url = contextPath + '/module/config/appDetail.jsp?rowJson=' + jp;
				jQuery("#tabs").tabs('option', 'select', function(event, ui) {
					if (ui.index != 0) {  				
					  	$('#contentPane').attr('src', url);
					}
				});						
				jQuery("#tabs").tabs('select', 1);		
			} catch (em) { alert("页面异常:" + em);}	
		}
	
		function doPaging(pgBtn) {
		   // alert('event:' + pgBtn);
		}  
	
		function doTesting(pgBtn) {
		  // alert('event:' + pgBtn);
		} 
	</script> 
</head>
<body>  
	<table id="list" class="scroll"></table> 
	<div id="pager" class="scroll" style="text-align:center;"></div>  
</body>
</html>