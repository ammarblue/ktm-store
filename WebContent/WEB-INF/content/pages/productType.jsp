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
    >
        <sjg:gridColumn name="id" index="id" title="No" width="30"
            formatter="integer" sortable="false"
        />
        <sjg:gridColumn name="identifier" index="identifier"
            title="%{getText('page.productType.identifier')}"
            sortable="true"
        />
        <sjg:gridColumn name="name" index="name"
            title="%{getText('page.productType.name')}" sortable="true"
        />
        <sjg:gridColumn name="unitType" index="unitType"
            title="%{getText('page.productType.unitType')}"
            sortable="true"
        />
        <sjg:gridColumn name="unitCount" index="unitCount"
            title="%{getText('page.productType.unitCount')}"
            sortable="true"
        />
        <sjg:gridColumn name="catalogName" index="catalogName"
            title="grouping" sortable="false"
        />
        <sjg:gridColumn name="price1" index="price1"
            title="%{getText('page.productType.price1')}" align="right"
            formatter="currency" sortable="false"
        />
        <sjg:gridColumn name="price2" index="price2"
            title="%{getText('page.productType.price2')}" align="right"
            formatter="currency" sortable="false"
        />
    </sjg:grid>
    <br />
    <s:form id="ptype_edit" action="%{crud_product_type}" theme="simple"
        cssClass="yform"
    >
        <fieldset>
            <legend>
                <s:text name="page.productType.editProduct" />
            </legend>
            <div class="type-text">
                <s:hidden id="oper" name="oper" value="add" />
                <label for="id">No: </label>
                <s:hidden id="id" name="id" />
                <label for="identifier"><s:text
                        name="page.productType.identifier"
                    />: </label>
                <s:textfield id="identifier" name="identifier"
                    width="50"
                />
                <label for="name"><s:text
                        name="page.productType.name"
                    />: </label>
                <s:textfield id="name" name="name" />
                <label for="unitType"><s:text
                        name="page.productType.unitType"
                    />: </label>
                <s:textfield id="unitType" name="unitType" />
                <label for="unitCount"><s:text
                        name="page.productType.unitCount"
                    />: </label>
                <s:textfield id="unitCount" name="unitCount"
                    key="page.productType.unitCount"
                />
                <label for="catalogName"><s:text
                        name="page.productType.catalog"
                    />: </label>
                <s:url id="catalog_url" action="json-select-catalog" />
                <sj:select href="%{catalog_url}" id="catalogName"
                    name="catalogName" list="catalogList"
                    emptyOption="true" headerKey="-1"
                    headerValue="%{getText('page.productType.selectCatalog')}"
                />
                <label for="price1"><s:text
                        name="page.productType.price1"
                    />: </label>
                <s:textfield id="price1" name="price1" />
                <label for="price2"><s:text
                        name="page.productType.price2"
                    />: </label>
                <s:textfield id="price2" name="price2" />
            </div>
        </fieldset>
    </s:form>
    <br />
    <sj:submit formIds="ptype_edit" targets="result" effect="pulsate"
        button="true" key="page.btn.save"
    />
    <br /> <br />
</div>
