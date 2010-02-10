
	jQuery(document).ready(function() {  

		try {  
			rj = jQuery.base64Decode(jQuery.decodeBase64URLSafe(rowJson));
			row = jQuery.evalJSON(rj); 
			jQuery("#appForm").deserialize(row);
		} catch (em) {  
			alert(em + ":" + rj);
		}
		
		/*
		jQuery('#appForm').ajaxForm();
		jQuery('#appForm').submit(function() {   
			
			jQuery(this).ajaxSubmit();  
		    return false; 
		});
		jQuery("#appForm").validate();			
		
		jQuery("#resetBtn").bind("click", function(e){
			try { 
				jQuery("#appForm").deserialize(rowJson);
			} catch (em) {  
				alert(em + ":" + rj);
			}
		});
		*/
		
		jQuery("#tabs").tabs({
			 select: function(event, ui) {
				// alert(jQuery.toJSON(event) + '' + jQuery.toJSON(ui));
				switch (ui.index) {
				case 1:
					alert(1112);
					jQuery("#list1").
					break;
				case 2:
					alert(2222);
					break;
				case 3:
					alert(33334);
					break;
				default:
				}
			 } 
		});
		
		if (0 != 1 && '' != rowJson) {
			 
			jQuery("#list1").jqGrid({
			  url:'data.do?pageAction=lab.testing.Test.testTianyafw', 
			  datatype: 'json',
			  mtype: 'GET',
			  colNames:['应用编号','应用名称', '父应用号','状态','创建时间','备注'],
			  colModel :[ 
			    {name:'app_pk', index:'app_pk', editable:true, editrules:{edithidden:true, required:true}, width:180}, 
			    {name:'app_name', index:'app_name asc, appParentId', editable:true, sortable:false, width:180}, 
			    {name:'parents_pk', index:'parents_pk', editable:true, editrules:{edithidden:true, required:true}, width:180, align:'right'}, 
			    {name:'app_state', index:'app_state', editable:true, edittype:'select', editoptions:{value:{1:"启用",2:"浏览", 3:"禁用"}}, sortable:false, width:100, align:'right'}, 
			    {name:'create_time', index:'create_time', sortable:false, width:300, align:'right'}, 
			    {name:'remark', index:'remark', editable:true, edittype:"textarea", sortable:false, width:550, sortable:false} ],
			  rownumbers: true,
			  rownumWidth: 40, 
			  autowidth: false, 
			  gridview: true, 
			  multiselect: false, 
			  pager: jQuery('#pager1'),
			  page: 1, 		    
			  rowNum:10,
			  postData:{provider:"wwww.tianya.cn"},
			  rowList:[10,20,30,100,200,500],
			  viewrecords: true,
			  sortname: 'id',
			  sortorder: "desc",
			  hiddengrid: true,
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
			  ondblClickRow: doDbClickRow,
			  onPaging: doPaging
			}); 
			jQuery("#list1").jqGrid('navGrid','#pager1',{
				 edit:true,
				 add:true,
				 del:false
			},
			{  // mode[edit] property or event
				 mtype : "POST",
				 url : "data.do?pageAction=lab.testing.Test.testPostData",
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
				 url : "data.do?pageAction=lab.testing.Test.testPostData"
			},
			{  // mode[delete] property or event
				 url : "data.do?pageAction=lab.testing.Test.testPostData"
			},
			{  // mode[search] property or event
			},
			{  // mode[view] property or event
			});  

			jQuery("#list2").jqGrid({
				  url:'data.do?pageAction=lab.testing.Test.testTianyafw', 
				  datatype: 'json',
				  mtype: 'GET',
				  colNames:['应用编号','应用名称', '父应用号','状态','创建时间','备注'],
				  colModel :[ 
				    {name:'app_pk', index:'app_pk', editable:true, editrules:{edithidden:true, required:true}, width:180}, 
				    {name:'app_name', index:'app_name asc, appParentId', editable:true, sortable:false, width:180}, 
				    {name:'parents_pk', index:'parents_pk', editable:true, editrules:{edithidden:true, required:true}, width:180, align:'right'}, 
				    {name:'app_state', index:'app_state', editable:true, edittype:'select', editoptions:{value:{1:"启用",2:"浏览", 3:"禁用"}}, sortable:false, width:100, align:'right'}, 
				    {name:'create_time', index:'create_time', sortable:false, width:300, align:'right'}, 
				    {name:'remark', index:'remark', editable:true, edittype:"textarea", sortable:false, width:550, sortable:false} ],
				  rownumbers: true,
				  rownumWidth: 40, 
				  autowidth: false, 
				  gridview: true, 
				  multiselect: false, 
				  pager: jQuery('#pager2'),
				  page: 1, 		    
				  rowNum:10,
				  postData:{provider:"fw.tianya.cn"},
				  rowList:[10,20,30,100,200,500],
				  viewrecords: true,
				  sortname: 'id',
				  sortorder: "desc",
				  hiddengrid: true,
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
				  ondblClickRow: doDbClickRow,
				  onPaging: doPaging
				}); 
				jQuery("#list2").jqGrid('navGrid','#pager2',{
					 edit:true,
					 add:true,
					 del:false
				},
				{  // mode[edit] property or event
					 mtype : "POST",
					 url : "data.do?pageAction=lab.testing.Test.testPostData",
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
					 url : "data.do?pageAction=lab.testing.Test.testPostData"
				},
				{  // mode[delete] property or event
					 url : "data.do?pageAction=lab.testing.Test.testPostData"
				},
				{  // mode[search] property or event
				},
				{  // mode[view] property or event
				});  

				jQuery("#list3").jqGrid({
					  url:'data.do?pageAction=lab.testing.Test.testTianyafw', 
					  datatype: 'json',
					  mtype: 'GET',
					  colNames:['应用编号','应用名称', '父应用号','状态','创建时间','备注'],
					  colModel :[ 
					    {name:'app_pk', index:'app_pk', editable:true, editrules:{edithidden:true, required:true}, width:180}, 
					    {name:'app_name', index:'app_name asc, appParentId', editable:true, sortable:false, width:180}, 
					    {name:'parents_pk', index:'parents_pk', editable:true, editrules:{edithidden:true, required:true}, width:180, align:'right'}, 
					    {name:'app_state', index:'app_state', editable:true, edittype:'select', editoptions:{value:{1:"启用",2:"浏览", 3:"禁用"}}, sortable:false, width:100, align:'right'}, 
					    {name:'create_time', index:'create_time', sortable:false, width:300, align:'right'}, 
					    {name:'remark', index:'remark', editable:true, edittype:"textarea", sortable:false, width:550, sortable:false} ],
					  rownumbers: true,
					  rownumWidth: 40, 
					  autowidth: false, 
					  gridview: true, 
					  multiselect: false, 
					  pager: jQuery('#pager3'),
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
					  ondblClickRow: doDbClickRow,
					  onPaging: doPaging
					}); 
					jQuery("#list3").jqGrid('navGrid','#pager3',{
						 edit:true,
						 add:true,
						 del:false
					},
					{  // mode[edit] property or event
						 mtype : "POST",
						 url : "data.do?pageAction=lab.testing.Test.testPostData",
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
						 url : "data.do?pageAction=lab.testing.Test.testPostData"
					},
					{  // mode[delete] property or event
						 url : "data.do?pageAction=lab.testing.Test.testPostData"
					},
					{  // mode[search] property or event
					},
					{  // mode[view] property or event
					});  
			
			jQuery("#tabs").tabs('select', 0);	
		}
	}); 	
		
	function doDbClickRow(rowid) {

		// var rowData = jQuery("#list1").getRowData(rowid);
		// alert($.toJSON(rowData));
	}

	function doPaging(pgBtn) {
	  //  alert('event:' + pgBtn);
	} 
 