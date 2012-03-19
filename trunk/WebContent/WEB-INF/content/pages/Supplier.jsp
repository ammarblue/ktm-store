<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
	
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
    <sjg:grid id="catalog_entry_table"
        caption="%{getText('page.supplier')}"
        loadonce="false" 
        href="%{product_type_url}"
        gridModel="gridModel" 
        groupField="['catalogName']"
        groupColumnShow="[false]" 
        groupCollapse="true"
        groupText="['<b>{0} - {1} %{getText('page.productType.group')}</b>']"
        navigator="true" 
        navigatorAdd="false" 
        navigatorEdit="false"
        navigatorDelete="true" 
        navigatorView="true" 
        rowTotal="70"
        rowNum="-1" 
        altRows="true" 
        viewrecords="true" 
        pager="true"
        pagerButtons="false" 
        pagerInput="false"
        onSelectRowTopics="rowselect" 
        editurl="%{crud_product_type}"
        autowidth="true">
        
        <sjg:gridColumn name="id" index="id" title="No" width="30" formatter="integer" sortable="false" />
        <sjg:gridColumn name="identifier" index="identifier" title="%{getText('page.supplier.id')}" sortable="true" />
        <sjg:gridColumn name="name" index="name" title="%{getText('page.supplier.name')}" sortable="true" />
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
        <sjg:gridColumn name="payment" index="payment"
            title="%{getText('page.supplier.payment')}"
            formatter="currency" sortable="false" hidden="true"/>
        <sjg:gridColumn name="creditTime" index="creditTime"
            title="%{getText('page.supplier.credit_time')}"
            formatter="currency" sortable="false" hidden="true"/>
        <sjg:gridColumn name="contact" index="contact"
            title="%{getText('page.supplier.contact_name')}"
            sortable="false" />
        <sjg:gridColumn name="mark" index="mark"
            title="%{getText('page.supplier.mark')}"
            sortable="false" />
    </sjg:grid>
    <br />
    <s:url id="crud_supplier_url" action="crud-grid-supplier" />
    <s:form id="ptype_edit" action="%{crud_supplier_url}" theme="simple"
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
                <label for="name"><s:text name="page.supplier.name" />: </label>
                <s:textfield id="name" name="name" />
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
                <s:url id="catalog_url" action="json-select-catalog" />
                <label for="payment"><s:text name="page.supplier.payment" />: </label>
                <sj:select href="%{catalog_url}" id="payment" name="payment" list="catalogList"
                    emptyOption="true" 
                    headerKey="-1"
                    headerValue="%{getText('page.productType.selectCatalog')}" />
                <label for="creditTime"><s:text name="page.supplier.credit_time" />: </label>
                <s:textfield id="creditTime" name="creditTime" />
                <label for="contactName"><s:text name="page.supplier.contact_name" />: </label>
                <s:textfield id="contactName" name="contactName" />
                <label for="mark"><s:text name="page.supplier.mark" />: </label>
                <s:textfield id="mark" name="mark" />
            </div>
        </fieldset>
    </s:form>
    <br />
    <sj:submit formIds="ptype_edit" targets="result" effect="pulsate"
        button="true" key="page.btn.save" />
    <br /> <br />
</div>