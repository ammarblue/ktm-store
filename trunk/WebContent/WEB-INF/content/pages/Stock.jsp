<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowadd', function(event,data) {
    $("#stock_entry_table").jqGrid('editGridRow',"new",{height:200,reloadAfterSubmit:false});
});
$.subscribe('rowdelete', function(event,data) {
    var gsr = jQuery("#stock_entry_table").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#stock_entry_table").jqGrid('delGridRow', gsr, {height:100,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('searchgrid', function(event,data) {
    $("#stock_entry_table").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
});
//-->
</script>
<p id="select_row" style="display: none;">
    <s:text name="page.error.require_select_row" />
</p>
<h2>
    <s:text name="page.stock.title" />
</h2>
<p class="text">
    <s:text name="page.stock.desc" />
</p>
<div id="tone">
    <s:url id="stock_url" action="json-grid-inventory?type=Fixed" />
    <s:url id="crud_stock_url" action="crud-grid-stock?type=Fixed" />
    <sjg:grid id="stock_entry_table"
        caption="%{getText('page.stock')}"
        href="%{stock_url}"
        editurl="%{crud_stock_url}"
        onSelectRowTopics="rowselect" 
        dataType="json"
        pager="true"
        navigator="true"
        navigatorAdd="false"
        navigatorEdit="false"
        navigatorDelete="false"
        navigatorSearch="false"
        navigatorView="false"
        gridModel="gridModel"
        rowList="5,10,15,20"
        rowNum="5"
        editinline="true"
        viewrecords="true"
        autowidth="true"
    >
        <sjg:gridColumn name="id" index="id" title="No" width="30" formatter="integer" sortable="false" />
        <sjg:gridColumn name="identifier" index="identifier" width="60"
            editable="true" edittype="text" title="%{getText('page.stock.id')}" sortable="true" />
        <sjg:gridColumn name="name" index="name"
            editable="true" edittype="text" title="%{getText('page.stock.name')}" sortable="true" />
    </sjg:grid>
    <br/><br/>
    <sj:submit id="grid_edit_addbutton" key="page.btn.add"
        onClickTopics="rowadd" button="true"
    />
    <sj:submit id="grid_edit_deletebutton" key="page.btn.delete"
        onClickTopics="rowdelete" button="true"
    />
    <sj:submit id="grid_edit_searchbutton" key="page.btn.search"
        onClickTopics="searchgrid" button="true"
    />
    <br/><br/>
</div>