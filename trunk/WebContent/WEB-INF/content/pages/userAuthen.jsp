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
    <h2><s:text name="page.user_authen.title"/></h2>
    <p class="text"><s:text name="page.user_authen.desc"/></p>
      <div id="tone">
          <s:url id="remoteurl" action="jsonauthen"/>
          <s:url id="editurl" action="grid-edit-authen-entry"/>
          <sjg:grid
              id="gridedittable"
              caption="%{getText('page.user_authen')}"
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
              <sjg:gridColumn name="id" index="id" title="ID" width="30" formatter="integer" editable="false" sortable="false" search="false" hidden="true" />
              <sjg:gridColumn name="preName" index="preName" title="คำนำหน้า" width="70" editable="false" edittype="select" sortable="false" search="false"/>
              <sjg:gridColumn name="firstName" index="firstName" title="ชื่อ" width="150" editable="false" edittype="text" sortable="true" search="false" />
              <sjg:gridColumn name="lastName" index="lastName" title="นามสกุล" width="150" editable="false" sortable="false" search="false" />
              <sjg:gridColumn name="username" index="username" title="ชื่อผู้ใช้" width="150" editable="true" sortable="false" search="false" />
              <sjg:gridColumn name="password" index="password" title="รหัสผ่าน" width="70" editable="true" sortable="false" search="false" />
              <sjg:gridColumn name="confirm" index="confirm" title="ยื่นยันรหัสผ่าน" width="70" editable="true" sortable="false" search="false" />
          </sjg:grid>
          <br/>
          <br/>
          <sj:submit id="grid_edit_addbutton" key="page.btn.add" onClickTopics="rowadd" button="true" />
          <sj:submit id="grid_edit_savebutton" key="page.btn.edit" onClickTopics="rowedit" button="true"/>
          <sj:submit id="grid_edit_searchbutton" key="page.btn.search" onClickTopics="searchgrid" button="true"/>
          <br/>
          <br/>
      </div>
