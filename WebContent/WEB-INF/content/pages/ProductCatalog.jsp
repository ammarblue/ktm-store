<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowadd', function(event, data) {
    $("#catalog_entry_table").jqGrid('editGridRow', "new", {
        height : 120,
        reloadAfterSubmit : false
    });
});
$.subscribe('rowedit', function(event, data) {
    var gsr = jQuery("#catalog_entry_table").jqGrid('getGridParam', 'selrow');
    if (gsr) {
        jQuery("#catalog_entry_table").jqGrid('editGridRow', gsr, {
            height : 280,
            reloadAfterSubmit : true
        });
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
$.subscribe('searchgrid', function(event, data) {
    $("#catalog_entry_table").jqGrid('searchGrid', {
        sopt : [ 'cn', 'bw', 'eq', 'ne', 'lt', 'gt', 'ew' ]
    });
});
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
    <s:url id="json_catalog_url" action="json-grid-catalog" />
    <s:url id="crud_catalog_url" action="crud-grid-catalog" />
    <s:url id="json_product_type_url" action="json-grid-product-type" />
    <sjg:grid id="catalog_entry_table"
        caption="%{getText('page.product')}" 
        dataType="json"
        href="%{json_catalog_url}" 
        pager="true" 
        navigator="true"
        navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
        navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
        navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
        navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
        navigatorAdd="false"
        navigatorEdit="false"
        navigatorDelete="false"
        navigatorSearch="false"
        navigatorView="false"
        gridModel="gridModel" rowList="5,10,15,20" rowNum="15"
        editurl="%{crud_catalog_url}" editinline="true"
        viewrecords="true" autowidth="true"
    >
        <sjg:grid id="product_type_table"
            subGridUrl="%{json_product_type_url}" gridModel="gridModel"
            footerrow="true" userDataOnFooter="true" viewrecords="true"
            autowidth="true"
        >
            <sjg:gridColumn name="identifier" title="%{getText('page.common.id')}" width="50" />
            <sjg:gridColumn name="name" title="รายละเอียดสินค้า"
                width="100"
            />
            <sjg:gridColumn name="unitType" title="หน่วยนับ"
                align="center"
            />
            <sjg:gridColumn name="unitCount"
                title="หน่วยบรรจุ(ขวด ชิ้น อัน)" formatter="integer"
                align="center"
            />
            <sjg:gridColumn name="price1" title="ราคาทุน"
                formatter="currency" align="right"
            />
            <sjg:gridColumn name="price2" title="ราคาขาย"
                formatter="currency" align="right"
            />
        </sjg:grid>
        <sjg:gridColumn name="id" index="id" title="ID" width="30"
            formatter="integer" editable="false" sortable="false" hidden="true"
        />
        <sjg:gridColumn name="identifier" index="identifier"
            title="%{getText('page.common.id')}" width="50" editable="true" edittype="text"
            sortable="true" search="true"
            searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
        <sjg:gridColumn name="name" index="name" title="%{getText('page.common.name')}"
            width="150" editable="true" edittype="text" sortable="true"
            search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
    </sjg:grid>
    <br /> <br />
    <sj:submit id="grid_edit_addbutton" key="page.btn.add"
        onClickTopics="rowadd" button="true"
    />
    <sj:submit id="grid_edit_deletebutton" key="page.btn.delete"
        onClickTopics="rowdelete" button="true"
    />
    <sj:submit id="grid_edit_searchbutton" key="page.btn.search"
        onClickTopics="searchgrid" button="true"
    />
    <br /> <br />
</div>
