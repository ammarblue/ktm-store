<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowselect', function(event, data) {
    var gsr = $("#catalog_entry_table").jqGrid('getGridParam', 'selrow');
    if (gsr) {
        jQuery("#catalog_entry_table").jqGrid('GridToForm', gsr,
                "#ptype_edit");
        $("#oper").val("edit");
    } else {
        alert("Please select Row");
    }
});
$.subscribe('rowadd', function(event,data) {
    $("#catalog_entry_table").jqGrid('editGridRow',"new",{height:240,reloadAfterSubmit:false});
});
$.subscribe('rowedit', function(event,data) {
    var gsr = jQuery("#catalog_entry_table").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#catalog_entry_table").jqGrid('editGridRow', gsr, {height:240,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('rowdelete', function(event,data) {
    var gsr = jQuery("#catalog_entry_table").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#catalog_entry_table").jqGrid('delGridRow', gsr, {height:100,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('searchgrid', function(event,data) {
    $("#catalog_entry_table").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
});
//$.subscribe('completeForm', function(event,data) {
//    alert('status: ' + event.originalEvent.status + '\n\nresponseText: \n' + event.originalEvent.request.responseText + '\n\nThe output div should have already been updated with the responseText.');
//    $("#catalog_entry_table").trigger("reloadGrid");
//});
var jsonString = $.ajax({url: 'json-select-list?listType=catalogList', async: false, success: function(data, result) {if (!result) alert('Failure to retrieve the prename.');}}).responseText;
function getCatalog() {
    var str = "";
    prenames = JSON.parse(jsonString);
    var objs = prenames.selectMap;
    for(key in objs) {
        if (!objs.hasOwnProperty(key)) {
            continue;
        }
        str += key + ":" + objs[key] + ";";
    }
    str = str.substr(0,str.length-1);
    return str;
}
//-->
</script>
<p id="select_row" style="display: none;">
    <s:text name="page.error.require_select_row" />
</p>
<h2>
    <s:text name="page.product.title" />
</h2>
<p class="text">
    <s:text name="page.product.desc" />
</p>
<div id="tone">
    <s:url id="product_type_url" action="json-grid-product-type" />
    <s:url id="crud_product_type" action="crud-grid-product-type" />
    <sjg:grid id="catalog_entry_table"
        caption="%{getText('page.productType')} (%{getText('page.productType.grouping')})"
        loadonce="false"
        href="%{product_type_url}"
        gridModel="gridModel"
        groupField="['catalogName']"
        groupColumnShow="[true]"
        groupCollapse="true"
        groupText="['<b>{0} - {1} %{getText('page.productType.group')}</b>']"
        navigator="true"
        navigatorAdd="false"
        navigatorEdit="false"
        navigatorDelete="false"
        navigatorSearch="false"
        navigatorView="false"
        rowTotal="70"
        rowNum="-1"
        altRows="true"
        viewrecords="true"
        pager="true"
        pagerButtons="false"
        pagerInput="false"
        onSelectRowTopics="rowselect"
        editurl="%{crud_product_type}"
    >
        <sjg:gridColumn name="id" index="id" title="No" width="30"
            formatter="integer" sortable="false"
        />
        <sjg:gridColumn name="identifier" index="identifier"
            title="%{getText('page.productType.identifier')}"
            editable="true" edittype="text" sortable="true" search="true"
            searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
        <sjg:gridColumn name="name" index="name"
            title="%{getText('page.productType.name')}" sortable="true"
            editable="true" edittype="text" 
        />
        <sjg:gridColumn name="unitType" index="unitType"
            title="%{getText('page.productType.unitType')}" sortable="false"
            editable="true" edittype="text" 
        />
        <sjg:gridColumn name="unitCount" index="unitCount"
            title="%{getText('page.productType.unitCount')}" sortable="false"
            formatter="integer" editable="true" edittype="text" 
        />
        <sjg:gridColumn name="catalogName" index="catalogName" title="%{getText('page.productType.catalog')}"
            width="70" editable="true" edittype="select"
            editoptions="{ value: getCatalog()}"
            sortable="false" search="false"
        />
        <sjg:gridColumn name="price1" index="price1"
            title="%{getText('page.productType.price1')}" align="right"
            formatter="currency" sortable="false"
            editable="true" edittype="text" 
        />
        <sjg:gridColumn name="price2" index="price2"
            title="%{getText('page.productType.price2')}" align="right"
            formatter="currency" sortable="false"
            editable="true" edittype="text" 
        />
    </sjg:grid>
    <br /><br />
    <sj:submit id="grid_edit_addbutton" key="page.btn.add"
        onClickTopics="rowadd" button="true"
    />
    <sj:submit id="grid_edit_savebutton" key="page.btn.edit"
        onClickTopics="rowedit" button="true"
    />
    <sj:submit id="grid_edit_deletebutton" key="page.btn.delete"
        onClickTopics="rowdelete" button="true"
    />
    <sj:submit id="grid_edit_searchbutton" key="page.btn.search"
        onClickTopics="searchgrid" button="true"
    />
    <br /> <br />
</div>
