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
$.subscribe('rowselect', function(event, data) {
    var gsr = $("#supplier_entry_table").jqGrid('getGridParam', 'selrow');
    if (gsr) {
        jQuery("#supplier_entry_table").jqGrid('GridToForm', gsr, "#supplier_edit");
        $("#oper").val("edit");
    } else {
        alert("Please select Row");
    }
});
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
    <sjg:grid
        id="supplier_entry_table"
        caption="%{getText('page.supplier')}"
        href="%{supplier_url}"
        onSelectRowTopics="rowselect" 
        dataType="json"
        pager="true"
        navigator="true"
        navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
        navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
        navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
        navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
        navigatorAdd="true"
        navigatorEdit="true"
        navigatorDelete="true"
        navigatorView="false"
        gridModel="gridModel"
        rowList="5,10,15,20"
        rowNum="5"
        editinline="false"
        viewrecords="true"
        autowidth="true"
    >
        
        <sjg:gridColumn name="id" index="id" title="No" width="30" formatter="integer" sortable="false" />
        <sjg:gridColumn name="identifier" index="identifier" title="%{getText('page.supplier.id')}" sortable="true" width="30"/>
        <sjg:gridColumn name="desc" index="desc" title="%{getText('page.supplier.name')}" sortable="true" />
        <sjg:gridColumn name="addr1" index="addr1"
            title="%{getText('page.supplier.address')} 1"
            sortable="true" hidden="true"/>
        <sjg:gridColumn name="addr2" index="addr2"
            title="%{getText('page.supplier.address')} 2"
            sortable="true" hidden="true"/>
        <sjg:gridColumn name="addr3" index="addr3"
            title="%{getText('page.supplier.address')} 3"
            sortable="true" hidden="true"/>
        <sjg:gridColumn name="tel" index="tel"
            title="%{getText('page.supplier.tel')}"
            sortable="true" hidden="true"/>
        <sjg:gridColumn name="fax" index="fax"
            title="%{getText('page.supplier.fax')}"
            sortable="false" hidden="true"/>
        <sjg:gridColumn name="payMethod" index="payMethod"
            title="%{getText('page.supplier.payment')}"
            formatter="integer" sortable="false" hidden="true"/>
        <sjg:gridColumn name="payDuration" index="payDuration"
            title="%{getText('page.supplier.credit_time')}"
            formatter="integer" sortable="false" hidden="true"/>
        <sjg:gridColumn name="contactName" index="contactName"
            title="%{getText('page.supplier.contact_name')}"
            sortable="false" />
        <sjg:gridColumn name="mark" index="mark"
            title="%{getText('page.supplier.mark')}"
            sortable="false" />
    </sjg:grid>
    <br />
    <s:url id="crud_supplier_url" action="crud-grid-supplier" />
    <s:form id="supplier_edit" action="%{crud_supplier_url}" theme="simple"
        cssClass="yform">
        <fieldset>
            <legend>
                <s:text name="page.productType.editProduct" />
            </legend>
            <div class="type-text">
                <s:hidden id="oper" name="oper" value="add" />
                <s:hidden id="id" name="id" />
                
                <label for="identifier"><s:text
                        name="page.supplier.id" />: </label>
                <s:textfield id="identifier" name="identifier"
                    width="50" />
                <label for="desc"><s:text name="page.supplier.name" />: </label>
                <s:textfield id="desc" name="desc" />
                <label for="addr1"><s:text name="page.supplier.address" /> 1 : </label>
                <s:textfield id="addr1" name="addr1" />
                <label for="addr2"><s:text name="page.supplier.address" /> 2 : </label>
                <s:textfield id="addr2" name="addr2" />
                <label for="addr3"><s:text name="page.supplier.address" /> 3 : </label>
                <s:textfield id="addr3" name="addr3" />
                <label for="tel"><s:text name="page.supplier.tel" />: </label>
                <s:textfield id="tel" name="tel" />
                <label for="fax"><s:text name="page.supplier.fax" />: </label>
                <s:textfield id="fax" name="fax" />
                <s:url id="paymethod_url" action="json-select-list?listType=paymethodMap" />
                <label for="payment"><s:text name="page.supplier.payment" />: </label>
                <sj:select id="payMethod" name="payMethod"
                    href="%{paymethod_url}"
                    list="selectMap"
                    listKey="key"
                    listValue="value"
                    headerKey="-1"
                    headerValue="%{getText('page.supplier.select_pay')}" />
                <label for="payDuration"><s:text name="page.supplier.credit_time" />: </label>
                <s:textfield id="payDuration" name="payDuration" />
                <label for="contactName"><s:text name="page.supplier.contact_name" />: </label>
                <s:textfield id="contactName" name="contactName" />
                <label for="mark"><s:text name="page.supplier.mark" />: </label>
                <s:textfield id="mark" name="mark" />
            </div>
        </fieldset>
    </s:form>
    <br />
    <sj:submit formIds="supplier_edit" targets="result" effect="pulsate"
        button="true" key="page.btn.save" />
    <br /> <br />
</div>