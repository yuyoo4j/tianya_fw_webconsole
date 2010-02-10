{   
   total: '${total!999999}',  
   page: '${page!0}',  
   records: '${records!0}',
   rows : [
    <#list rows as row>
   	 {id:'${row_index}',
   	  cell:[   	   
   	  	<#list row as item> 
   	  		'${item?if_exists}'<#if item_has_next>,</#if>
   	  	</#list>
   	  ]
   	 }<#if row_has_next>,</#if> 
    </#list>
   ]
}