<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowadd', function(event,data) {
    $("#gridedittable").jqGrid('editGridRow',"new",{height:280,reloadAfterSubmit:false});
});
$.subscribe('rowedit', function(event,data) {
    var gsr = jQuery("#gridedittable").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#gridedittable").jqGrid('editGridRow', gsr, {height:280,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('rowdelete', function(event,data) {
    var gsr = jQuery("#gridedittable").jqGrid('getGridParam', 'selrow');
    if(gsr){
        jQuery("#gridedittable").jqGrid('delGridRow', gsr, {height:100,reloadAfterSubmit:true});
    } else {
        var txt = $("#select_row").html();
        alert(txt);
    }
});
$.subscribe('searchgrid', function(event,data) {
    $("#gridedittable").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
});
$.subscribe('showcolumns', function(event,data) {
    $("#gridedittable").jqGrid('setColumns',{});
});
var jsonString = $.ajax({url: 'json-select-list?listType=userlist', async: false, success: function(data, result) {if (!result) alert('Failure to retrieve the prename.');}}).responseText;
function getPrenames() {
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
    <s:text name="page.user_data.title" />
</h2>
<p class="text">
    <s:text name="page.user_data.desc" />
</p>
<div id="tone">
    <s:url id="json_person_url" action="json-grid-person" />
    <s:url id="crud_person_url" action="crud-grid-person" />
    <sjg:grid
        id="gridedittable"
        caption="%{getText('page.user_data')}"
        href="%{json_person_url}"
        editurl="%{crud_person_url}"
        dataType="json"
        pager="true"
        navigator="true"
        navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
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
        onBeforeTopics="before"
    >
        <sjg:gridColumn name="id" index="id" title="ID" width="30"
            formatter="integer" editable="false" sortable="false"
            search="false" hidden="true"
        />
        <sjg:gridColumn name="identifier" index="identifier"
            title="รหัส" width="50" editable="true" edittype="text"
            sortable="true" search="true"
            searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
        <sjg:gridColumn name="registeredIdentifier"
            index="registeredIdentifier" title="หมายเลขบัตรประชาชน"
            width="130" formatter="integer" editable="true"
            edittype="text" sortable="false" search="false"
        />
        <sjg:gridColumn name="prename" index="prename" title="คำนำหน้า"
            width="70" editable="true" edittype="select"
            editoptions="{ value: getPrenames()}"
            sortable="false" search="false"
        />
        <sjg:gridColumn name="firstname" index="firstname" title="ชื่อ"
            width="150" editable="true" edittype="text" sortable="true"
            search="false"
        />
        <sjg:gridColumn name="lastname" index="lastname" title="นามสกุล"
            width="150" editable="true" sortable="false" search="false"
        />
        <sjg:gridColumn name="birthDay" index="birthDay" title="วันเกิด"
            editable="true" sorttype="date" width="90" search="false"
            editoptions="{ size: 12, maxlength: 10 , dataInit: function(element) { $(element).datepicker({dateFormat:'dd-mm-yy'}); } }"
        />
        <sjg:gridColumn name="emailAddress" index="emailAddress"
            title="Email" width="150" editable="true" sortable="false"
            search="false"
        />
        <sjg:gridColumn name="tel" index="tel" title="เบอร์โทรศัพท์"
            width="150" editable="true" sortable="false" search="false"
        />
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
