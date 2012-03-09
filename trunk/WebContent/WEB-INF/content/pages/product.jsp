<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<p id="select_row" style="display: none;">
    <s:text name="page.error.require_select_row" />
</p>
<h2><s:text name="page.product.title" /></h2>
<p class="text"><s:text name="page.product.desc" /></p>
<div id="tone">
    <s:url id="catalog_entry_url" action="json-catalog-entry" />
    <s:url id="product_type_url" action="json-product" />
    <s:url id="catalog_entry_edit" action="grid-edit-catalog-entry" />
    <s:url id="product_edit" action="grid-edit-product-entry" />
    <sjg:grid id="catalog_entry_table" caption="%{getText('page.product')}"
        dataType="json" href="%{catalog_entry_url}" pager="true"
        navigator="true"
        navigatorAdd="true"
        navigatorEdit="true"
        navigatorView="false"
        navigatorDelete="true"
        navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
        navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
        navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
        navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
        gridModel="gridModel"
        rowList="5,10,15,20"
        rowNum="5"
        rownumbers="true"
        editurl="%{catalog_entry_edit}"
        editinline="true"
        viewrecords="true"
        autowidth="true"
    >
        <sjg:grid 
            id="product_sub_grid_table" 
            subGridUrl="%{product_type_url}"
            gridModel="gridModel"
            rowNum="-1"
            footerrow="true"
            userDataOnFooter="true"
        >
            <sjg:gridColumn name="productname" title="Product" width="300"/>
            <sjg:gridColumn name="quantityordered" title="Quantity" formatter="integer" align="center"/>
            <sjg:gridColumn name="priceeach" title="Price" formatter="currency" align="right"/>
        </sjg:grid>
        
        <sjg:gridColumn name="uniqueId" index="id" title="ID" width="30"
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
            edittype="text" sortable="false" search="true"
            searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
        <sjg:gridColumn name="preName" index="preName" title="คำนำหน้า"
            width="70" editable="true" edittype="select"
            editoptions="{value:'%{getText('prename.mr')}:%{getText('prename.mr')};%{getText('prename.miss')}:%{getText('prename.miss')};%{getText('prename.mis')}:%{getText('prename.mis')}'}"
            sortable="false" search="false"
        />
        <sjg:gridColumn name="firstName" index="firstName" title="ชื่อ"
            width="150" editable="true" edittype="text" sortable="true"
            search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"
        />
        <sjg:gridColumn name="lastName" index="lastName" title="นามสกุล"
            width="150" editable="true" sortable="false" search="true"
            searchoptions="{sopt:['eq','ne','lt','gt']}"
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
    <sj:submit id="grid_edit_searchbutton" key="page.btn.search"
        onClickTopics="searchgrid" button="true"
    />
    <br /> <br />
</div>
