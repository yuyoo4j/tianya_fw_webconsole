<?xml version="1.0" encoding="UTF-8"?>
<result>
	<currentpage>${page!0}</currentpage>
    <totalpages>${total!999999}</totalpages>
    <totalrecords>${records!999999}</totalrecords>
    <rows>
    	<#list rows as row>
    		<row id="${row_index}">
    			<#list row as item> 
    				<cell><![CDATA[${item?if_exists}]]></cell>
    			</#list>
    		</row>
    	</#list>
    </rows>
</result>