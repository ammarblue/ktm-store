<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
var jsonString = $.ajax({url: 'json-select-paymethod', async: false, success: function(data, result) {if (!result) alert('Failure to retrieve the paymethod.');}}).responseText;
function getPayMethod() {
    var str = "";
    prenames = JSON.parse(jsonString);
    var objs = prenames.payMethodMap;
    for(key in objs) {
        if (!objs.hasOwnProperty(key)) {
            continue;
        }
        str += key + ":" + objs[key] + ";";
    }
    str = str.substr(0,str.length-1);
    return str;
}
$.subscribe('rowadd', function(event,data) {
    $("#supplier_entry_table").jqGrid('editGridRow',"new",{height:340,reloadAfterSubmit:false});
});
$.subscribe('rowedit', function(event,data) {
    var gsr = jQuery("#supplier_entry_table").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#supplier_entry_table").jqGrid('editGridRow', gsr, {height:340,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('rowdelete', function(event,data) {
    var gsr = jQuery("#supplier_entry_table").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#supplier_entry_table").jqGrid('delGridRow', gsr, {height:100,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('searchgrid', function(event,data) {
    $("#supplier_entry_table").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
});
var jsonString = $.ajax({url: 'json-select-list?listType=paymethodMap', async: false, success: function(data, result) {if (!result) alert('Failure to retrieve the pay method.');}}).responseText;
function getPaymethod() {
    var str = "";
    paymethod = JSON.parse(jsonString);
    var objs = paymethod.selectMap;
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
    <s:text name="page.supplier.title" />
</h2>
<p class="text">
    <s:text name="page.supplier.desc" />
</p>
<div id="tone">
    <s:url id="supplier_url" action="json-grid-supplier" />
    <s:url id="crud_supplier_url" action="crud-grid-supplier" />
    <sjg:grid
        id="supplier_entry_table"
        caption="%{getText('page.supplier')}"
        href="%{supplier_url}"
        editurl="%{crud_supplier_url}"
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
        editinline="false"
        viewrecords="true"
        autowidth="true"
    >
        
        <sjg:gridColumn name="id" index="id" title="No" width="30" formatter="integer" sortable="false" />
        <sjg:gridColumn name="identifier" index="identifier" title="%{getText('page.supplier.id')}" sortable="true" width="30"
                        editable="true" edittype="text" />
        <sjg:gridColumn name="desc" index="desc" title="%{getText('page.supplier.name')}" sortable="true" 
                        editable="true" edittype="text" />
        <sjg:gridColumn name="addr1" index="addr1"
            title="%{getText('page.supplier.address')} 1" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="addr2" index="addr2"
            title="%{getText('page.supplier.address')} 2" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="addr3" index="addr3"
            title="%{getText('page.supplier.address')} 3" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="tel" index="tel"
            title="%{getText('page.supplier.tel')}" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="fax" index="fax"
            title="%{getText('page.supplier.fax')}" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="payMethod" index="payMethod" title="%{getText('page.supplier.payment')}"
            width="70"  editable="true" edittype="select"
            editoptions="{ value: getPaymethod()}" hidden="true" 
            editrules="{ edithidden : true } " />
        <sjg:gridColumn name="payDuration" index="payDuration"
            title="%{getText('page.supplier.credit_time')}"
            formatter="integer" hidden="true"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="contactName" index="contactName"
            title="%{getText('page.supplier.contact_name')}"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
        <sjg:gridColumn name="mark" index="mark"
            title="%{getText('page.supplier.mark')}"
            editable="true" edittype="text" editrules="{ edithidden : true } " />
    </sjg:grid>
    <br /> <br />
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