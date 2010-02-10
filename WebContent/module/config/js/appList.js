
	jQuery(document).ready(function() {   
		try {
			jQuery("#tabs").tabs({
				collapsible: true 
			});
					
			jQuery("#list").jqGrid({
			  url:'api.do?pageAction=module.config.app.queryAppList', 
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
			  colNames:['应用编号','应用名称', '父应用号','状态','创建时间','备注'],
			  colModel :[ 
			    {name:'app_pk', index:'app_pk', editable:true, editrules:{edithidden:true, required:true}, width:180}, 
			    {name:'app_name', index:'app_name', editable:true, sortable:false, width:180}, 
			    {name:'parents_pk', index:'parents_pk', 
				    editable:true, 
				    editrules:{edithidden:true, required:true}, 
				    edittype:"select",
				    editoptions:{dataUrl:'api.do?pageAction=module.config.app.queryParentAppSelect', defaultValue:'0'},
				    formoptions:{ rowpos:3,elmprefix:"&nbsp;&nbsp;&nbsp;&nbsp;"},
				    width:180, align:'right'}, 
			    {name:'app_state', index:'app_state', editable:true, edittype:'select', formatter:'select', editoptions:{value:"1:One;2:Two;3:Thred"}, sortable:false, width:100, align:'right'}, 
			    {name:'create_time', index:'create_time', sortable:false, width:300, align:'right'}, 
			    {name:'remark', index:'remark', editable:true, edittype:"textarea", sortable:false, width:550, sortable:false} ],
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
			  width: 1024,
			  height: "auto", 
			  toolbar:[false,"top"],
			  caption: '应用ID列表',
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