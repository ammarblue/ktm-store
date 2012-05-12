<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowadd', function(event,data) {
    $("#gridedittable").jqGrid('editGridRow',"new",{height:220,reloadAfterSubmit:false});
});
$.subscribe('rowdel', function(event,data) {
    var gsr = jQuery("#gridedittable").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#gridedittable").jqGrid('delGridRow', gsr, {height:100,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('showSupplierInfo', function(event,data) {
    var ids = data.item.value.split("-");
    var urlRequest = 'json-select-list?listType=supplierList&id=' + ids[0];
    var jsonString = $.ajax({url: urlRequest, async: false, success: function(data, result) {if (!result) alert('Failure to retrieve the prename.');}}).responseText;
    var jsonObj = jQuery.parseJSON(jsonString);
    $("#sinfo").html(jsonObj.description);
})
//-->
</script>
<p id="select_row" style="display: none;">
    <s:text name="page.receive_supplier" />
</p>
<h2>
    <s:text name="page.receive_supplier.title" />
</h2>
<p class="text">
    <s:text name="page.receive_supplier.desc" />
</p>
<div style="padding: 10px; border: 1px solid #77D5F7;"
    class="ui-corner-all">
    <div style="float: right;">
        <s:url id="crud_order_url" action="crud-order" />
        <s:form id="headFrom" action="%{crud_order_url}" target="_blank">
            <sj:datepicker value="today" id="orderDate" name="orderDate"
                displayFormat="dd-mm-yy" label="วันที่" />
            <s:url id="jsonsupplier"
                action="json-select-list?listType=supplierList" />
            <s:url id="jsonorderno" action="" />
            <sj:select id="supplierId" name="supplierId"
                label="Supplier" href="%{jsonsupplier}" list="selectMap"
                autocomplete="true" loadMinimumCount="2"
                indicator="indicator" onChangeTopics="showSupplierInfo" />
            <sj:select id="orderNumber" name="orderNumber" label="รหัส"
                href="%{jsonorderno}" list="selectMap"
                autocomplete="true" loadMinimumCount="2" />
        </s:form>
    </div>
    <div>
        <div id="sinfo" style="height: 65px;">
            <img id="indicator" src="images/indicator.gif"
                alt="Loading..." style="display: none" />
        </div>
    </div>
</div>
<div id="tone">
    <s:url id="order_line_url" action="json-grid-order-line" />
    <s:url id="crud_grid_order_url" action="crud-grid-order-line" />
    <sjg:grid id="gridedittable" dataType="json"
        href="%{order_line_url}" pager="false" navigator="false"
        gridModel="gridModel" editurl="%{crud_grid_order_url}"
        editinline="true" autowidth="true" onSelectRowTopics="rowselect"
        onEditInlineSuccessTopics="oneditsuccess">
        <sjg:gridColumn name="id" index="id" title="ID" width="30"
            formatter="integer" editable="false" sortable="false"
            search="false" hidden="true" />
        <sjg:gridColumn name="productCode" index="productCode"
            title="Product Code" width="130" editable="true"
            edittype="text" />
        <sjg:gridColumn name="desc" index="desc" title="Description"
            width="550" editable="true" edittype="text" sortable="true"
            search="false" />
        <sjg:gridColumn name="unit" index="unit" title="Unit"
            editable="true" edittype="text" />
        <sjg:gridColumn name="cost" index="cost" title="Cost"
            editable="true" edittype="text" />
        <sjg:gridColumn name="quanitity" index="quanitity"
            title="Quantity" editable="true" edittype="text" />
        <sjg:gridColumn name="total" index="total" title="Total"
            editable="true" edittype="text" />
    </sjg:grid>
    <br />
    <sj:submit id="grid_addbutton" value="Add Row"
        onClickTopics="rowadd" button="true" />
    <sj:submit id="grid_deletebutton" value="Delete Row"
        onClickTopics="rowdel" button="true" />
    <sj:submit formIds="headFrom" targets="result" effect="pulsate"
        value="Save" indicator="indicator"
        button="true" />
    <br />
    <div id="result" class="result ui-widget-content ui-corner-all">Submit
        form bellow.</div>
    <br />
</div>
