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
				jQuery("#tabs").tabs({
					collapsible: true 
				});
						
				jQuery("#list").jqGrid({
				  caption: '业务实体列表',
				  url: contextPath + '/testing/demo/bizEntity.xml', // 'api.do?pageAction=module.config.app.queryAppList', 
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
				  colNames:['实体ID', '实体名称','实体类型', '父实体ID', '父实体名称', '父实体类型','关联虚拟资源ID','备注'],
				  colModel :[ 
				    {name:'entity_id', index:'entity_id',  editable:true, editrules:{edithidden:true, required:true}, width:120}, 
				    {name:'entity_name', index:'entity_name', editable:true, sortable:false, width:120}, 
				    {name:'entity_type', index:'entity_type', 
					    editable:true, 
					    editrules:{edithidden:true, required:true}, 
					    edittype:"text", 
					    width:120, align:'center'}, 
					{name:'parent_id', index:'parent_id', editable:true, editrules:{edithidden:true, required:true}, width:120}, 
				    {name:'parent_name', index:'parent_name', editable:true, sortable:false, width:100, align:'left'}, 
				    {name:'parent_type', index:'parent_type',  editable:true,sortable:false, width:150, align:'center'}, 
				    {name:'resource_id', index:'resource_id',  editable:true,sortable:false, width:150, align:'left'}, 
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