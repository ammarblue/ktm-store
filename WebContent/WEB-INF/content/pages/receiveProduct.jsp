<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<script type="text/javascript">
<!--
$.subscribe('rowselect', function(event,data) {
    $("#gridinfo").html('<p>Edit Mode for Row : '+event.originalEvent.id+'</p>');
});
$.subscribe('oneditsuccess', function(event, data){
    var message = event.originalEvent.response.statusText;
    $("#gridinfo").html('<p>Status: ' + message + '</p>');
});
$.subscribe('rowadd', function(event,data) {
    $("#gridedittable").jqGrid('editGridRow',"new",{height:280,reloadAfterSubmit:false});
});
$.subscribe('searchgrid', function(event,data) {
    $("#gridedittable").jqGrid('searchGrid', {sopt:['cn','bw','eq','ne','lt','gt','ew']} );
});
$.subscribe('showSupplierInfo', function(event,data) {
    $("#sinfo").html('as;lfdjsl;df<br/>;asdlfjdkf;lfsdf,=<br/>;lsdfja;slfdjka;sldf');
})
//-->
</script>

    <h2>Grid (Editable)</h2>
    <p class="text">
        A editable Grid with pager and navigator. Entries are editable when a cell is selected. This Grid is sortable by name column and searchable by id. The first two Columns are frozen.
    </p>
    <div style="padding: 10px; border: 1px solid #77D5F7;" class="ui-corner-all">
        <div style="float: right;">
            <s:form id="form">
                <sj:datepicker value="today" id="date3" name="date3" displayFormat="dd-mm-yy" label="Today" />
                <s:url id="jsonsupplier" action="json-select-list?listType=supplierList"/>
                <sj:select 
                    id="customersjson" 
                    name="echo"
                    label="Supplier"
                    href="%{jsonsupplier}" 
                    list="selectMap"
                    autocomplete="true"
                    loadMinimumCount="2"
                    onChangeTopics="showSupplierInfo"
                />
                <sj:textfield id="No" name="No" value="" label="No"/>
            </s:form>
        </div>
        <div>
            <div style="height: 65px;">
                <span id="sinfo"></span>
            </div>
        </div>
    </div>
      <div id="tone">
          <s:url id="remoteurl" action="jsonperson"/>
          <s:url id="editurl" action="grid-edit-person-entry"/>
          <sjg:grid
              id="gridedittable"
              dataType="json"
              href="%{remoteurl}"
              pager="false"
              navigator="false"
              gridModel="gridModel"
              editurl="%{editurl}"
              editinline="true"
              autowidth="true"
              onSelectRowTopics="rowselect"
              onEditInlineSuccessTopics="oneditsuccess"
          >
            <sjg:gridColumn name="id" index="id" title="ID" width="30"
                formatter="integer" editable="false" sortable="false"
                search="false" hidden="true"
            />
            <sjg:gridColumn name="productCode" index="productCode"
                title="Product Code" width="130" editable="true" edittype="text"
            />
            <sjg:gridColumn name="desc" index="desc" title="Description"
                width="550" editable="true" edittype="text" sortable="true"
                search="false"
            />
            <sjg:gridColumn name="unit" index="unit"
                title="Unit" editable="true" edittype="text"
            />
            <sjg:gridColumn name="cost" index="cost"
                title="Cost" editable="true" edittype="text"
            />
            <sjg:gridColumn name="quanitity" index="quanitity"
                title="Quantity" editable="true" edittype="text"
            />
            <sjg:gridColumn name="total" index="total"
                title="Total" editable="true" edittype="text"
            />
          </sjg:grid>
          <br/>
          <sj:submit id="grid_edit_addbutton" value="Add Row" onClickTopics="rowadd" button="true"/>
          <sj:submit id="grid_edit_searchbutton" value="Search" onClickTopics="searchgrid" button="true"/>
          <sj:submit id="grid_edit_colsbutton" value="Show/Hide Columns" onClickTopics="showcolumns" button="true"/>
          <br/>
          <br/>
      </div>
