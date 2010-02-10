
	jQuery(document).ready(function() {   
		
		try {			
			
			doInit();
			doForm();
			doGrid();					
		} catch (em) { 
			alert('页面初始化出错:' + em); 
		}
	}); 	
	
	function doInit() {

		jQuery("#tabs").tabs({
			collapsible: true 
		});
		
		$("#userDialog").dialog({ 
			autoOpen: false,
	        modal: true,
	        title: '创建新用户'
	    });
	}
	
	function doForm() {
		
		var options = { 
		        // target:        '#output2',   // target element(s) to be updated with server response 
		        beforeSubmit:  doBeforeSubmit,  // pre-submit callback 
		        success:       doSuccessSubmit,  // post-submit callback 
		 
		        // other available options: 
		        url : 'api.do?pageAction=module.system.security.', 
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
	}
	
	function doBeforeSubmit(formData, jqForm, options) {
 
		var queryString = $.param(formData);  	 
	    alert('About to submit: \n\n' + queryString); 
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
	
	function doGrid() {
		
		jQuery("#list").jqGrid({
			  caption: '用户列表',
			  url:'api.do?pageAction=module.system.security.queryUserList', 
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
			  colNames:['用户ID','用户名称', '登录ID', '密码', '状态','创建时间', '创建者', '备注'],
			  colModel :[ 
			    {name:'user_id', index:'user_id', editable:false, editrules:{edithidden:true, required:true}, width:180}, 
			    {name:'user_label', index:'user_label', editable:true, sortable:false, width:180}, 
			    {name:'user_name', index:'user_name', 
				    editable:true,  
				    sortable:false, 
				    width:180, align:'center'}, 
				{name:'password', index:'password', 
					    editable:true,  
					    edittype:'password',
					    editoptions:{defaultValue:'tianya'},
					    sortable:false, 
					    width:180, align:'center'}, 
			    {name:'user_state', index:'user_state', editable:true, edittype:'select', formatter:'select', editoptions:{value:"0:无效;1:有效", defaultValue:1}, sortable:false, width:100, align:'right'}, 
			    {name:'create_time', index:'create_time', sortable:false, width:300, align:'right'}, 
			    {name:'creator', index:'creator', sortable:false, width:300, align:'center'}, 
				{name:'memo', index:'memo', editable:true, edittype:"textarea", sortable:false, width:550, sortable:false} ],
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
			 // loadBeforeSend : doTesting,
			 // loadComplete : doTesting,
			  ondblClickRow: doDbClickRow,
			  onPaging: doPaging
			}); 
			jQuery("#list").jqGrid('navGrid','#pager',{
				 edit:false,
				 add:false,
				 del:false
			},
			{  /* mode[edit] property or event */ },
			{  /* mode[add] property or event */ },
			{  /* mode[delete] property or event */ },
			{  /* mode[search] property or event */ },
			{  /* mode[view] property or event */ }); 
			jQuery("#list").jqGrid('navButtonAdd', "#pager",{
				caption:"", 
				buttonicon:"ui-icon-plus", 
				onClickButton:function() {
					$('#userDialog').dialog('open');
					
				}, 	
				position: "first", 
				title:"新增用户", 
				cursor: "pointer"
			});
	}

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