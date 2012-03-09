<%@ page contentType="text/html; charset=UTF-8" %>
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
        jQuery("#gridedittable").jqGrid('editGridRow', gsr, {height:280,reloadAfterSubmit:false});
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
//-->
</script>

    <p id="select_row" style="display: none;"><s:text name="page.error.require_select_row"/></p>
    <h2><s:text name="page.user_data.title"/></h2>
    <p class="text"><s:text name="page.user_data.desc"/></p>
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
              navigatorEditOptions="{height:280,reloadAfterSubmit:true}"
              navigatorAdd="true"
              navigatorEdit="true"
              navigatorView="false"
              navigatorDelete="true"
              navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
              gridModel="gridModel"
              rowList="5,10,15,20"
              rowNum="5"
              rownumbers="true"
              editurl="%{editurl}"
              editinline="false"
              viewrecords="true"
              autowidth="true"
          >
              <sjg:gridColumn name="uniqueId" index="id" title="ID" width="30" formatter="integer" editable="false" sortable="false" search="false" hidden="true" />
              <sjg:gridColumn name="identifier" index="identifier" title="รหัส" width="50" editable="true" edittype="text" sortable="true" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
              <sjg:gridColumn name="registeredIdentifier" index="registeredIdentifier" title="หมายเลขบัตรประชาชน" width="130" formatter="integer" editable="true" edittype="text" sortable="false" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
              <sjg:gridColumn name="preName" index="preName" title="คำนำหน้า" width="70" editable="true" edittype="select" editoptions="{value:'%{getText('prename.mr')}:%{getText('prename.mr')};%{getText('prename.miss')}:%{getText('prename.miss')};%{getText('prename.mis')}:%{getText('prename.mis')}'}" sortable="false" search="false"/>
              <sjg:gridColumn name="firstName" index="firstName" title="ชื่อ" width="150" editable="true" edittype="text" sortable="true" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
              <sjg:gridColumn name="lastName" index="lastName" title="นามสกุล" width="150" editable="true" sortable="false" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}" />
              <sjg:gridColumn name="birthDay" index="birthDay" title="วันเกิด" editable="true" sorttype="date" width="90" search="false" editoptions="{ size: 12, maxlength: 10 , dataInit: function(element) { $(element).datepicker({dateFormat:'dd-mm-yy'}); } }" />
              <sjg:gridColumn name="emailAddress" index="emailAddress" title="Email" width="150" editable="true" sortable="false" search="false" />
              <sjg:gridColumn name="tel" index="tel" title="เบอร์โทรศัพท์" width="150" editable="true" sortable="false" search="false" />
          </sjg:grid>
          <br/>
          <br/>
          <sj:submit id="grid_edit_addbutton" key="page.btn.add" onClickTopics="rowadd" button="true" />
          <sj:submit id="grid_edit_savebutton" key="page.btn.edit" onClickTopics="rowedit" button="true"/>
          <sj:submit id="grid_edit_searchbutton" key="page.btn.search" onClickTopics="searchgrid" button="true"/>
          <br/>
          <br/>
      </div>
