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
$.subscribe('showcolumns', function(event,data) {
    $("#gridedittable").jqGrid('setColumns',{});
});
//-->
</script>

    <h2>Grid (Editable)</h2>
    <p class="text">
        A editable Grid with pager and navigator. Entries are editable when a cell is selected. This Grid is sortable by name column and searchable by id. The first two Columns are frozen.
    </p>
      <div id="tone">
          <s:url id="remoteurl" action="jsonperson"/>
          <s:url id="editurl" action="grid-edit-person-entry"/>
          <sjg:grid
              id="gridedittable"
              caption="%{getText('page.user_data')}"
              dataType="json"
              href="%{remoteurl}"
              pager="true"
              navigator="true"
              navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
              navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
              navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
              navigatorEdit="false"
              navigatorView="false"
              navigatorDelete="true"
              navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
              navigatorExtraButtons="{
                  seperator: { 
                      title : 'seperator'  
                  }, 
                  hide : { 
                      title : 'Show/Hide', 
                      icon: 'ui-icon-wrench', 
                      topic: 'showcolumns'
                  },
                  alert : { 
                      title : 'Edit', 
                      onclick: function(){ 
                        $.subscribe('rowadd', function(event,data) {
                            $('#gridedittable').jqGrid('editGridRow','edit',{height:280,reloadAfterSubmit:false});
                        });
                      }
                  }
              }"
              gridModel="gridModel"
              rowList="10,15,20"
              rowNum="15"
              editurl="%{editurl}"
              editinline="true"
              onSelectRowTopics="rowselect"
              onEditInlineSuccessTopics="oneditsuccess"
              viewrecords="true"
          >
              <sjg:gridColumn name="uniqueId" index="id" title="ID" width="30" formatter="integer" editable="false" sortable="false" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
              <sjg:gridColumn name="identifier" index="identifier" title="Identifier" width="130" editable="true" edittype="text" sortable="false" search="false"/>
              <sjg:gridColumn name="registeredIdentifier" index="registeredIdentifier" title="Citizen Id" width="130" editable="true" edittype="text" sortable="false" search="false"/>
              <sjg:gridColumn name="preName" index="preName" title="Pre Name" width="70" editable="true" edittype="select" editoptions="{value:'%{getText('prename.mr')}:%{getText('prename.mr')};%{getText('prename.miss')}:%{getText('prename.miss')};%{getText('prename.mis')}:%{getText('prename.mis')}'}" sortable="false" search="false"/>
              <sjg:gridColumn name="firstName" index="firstName" title="First Name" width="150" editable="true" edittype="text" sortable="true" search="false"/>
              <sjg:gridColumn name="lastName" index="lastName" title="Last Name" width="150" editable="true" sortable="false" />
              <sjg:gridColumn name="birthDay" index="birthDay" title="Birth Day" editable="true" sortable="false" formatter="date" hidden="true" />
              <sjg:gridColumn name="emailAddress" index="emailAddress" title="Email" width="150" editable="true" sortable="false" hidden="true" />
              <sjg:gridColumn name="tel" index="tel" title="Tel" width="150" editable="true" sortable="false" hidden="true" />
              <sjg:gridColumn name="username" index="username" title="Username" width="150" editable="true" sortable="false" hidden="true" />
              <sjg:gridColumn name="password" index="password" title="Password" width="150" editable="false" sortable="false" hidden="true" />
          </sjg:grid>
          <br/>
          <sj:submit id="grid_edit_addbutton" value="Add Row" onClickTopics="rowadd" button="true"/>
          <sj:submit id="grid_edit_searchbutton" value="Search" onClickTopics="searchgrid" button="true"/>
          <sj:submit id="grid_edit_colsbutton" value="Show/Hide Columns" onClickTopics="showcolumns" button="true"/>
          <br/>
          <br/>
          <div id="gridinfo" class="ui-widget-content ui-corner-all"><p>Edit Mode for Row :</p></div>
      </div>
