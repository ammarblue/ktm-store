<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<ktm:enforceAuthentication loginPage="/login"/>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
<style type="text/css">
.gridBox {
    background-color: rgb(220,220,220);
    padding-left: 5px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-right: 5px;
    margin-bottom: 2px;
    margin-right: 2px;
    color: #4d87c7;
    height: 20px;
    float: left;
}
</style>
</head>
<body>
  <div class="ym-wrapper">
    <div class="ym-wbox">
      <header><h1>${ktm:getText("app.title")}</h1></header>
        <div id="main">
          <div class="ym-column linearize-level-1">
            <div class="ym-col1">
            <div class="ym-cbox">
              <section class="box info">
                <div class="ym-wbox">
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.store")}</h3>
                </div>
              </section>
              <div class="ym-wbox">
                <div class="ym-fbox-select" style="margin-top: 10px; margin-bottom: 10px">
                  <form name="myform" action="CRUDInventory?method=list" method="post">
                    <label for="selectedInventoryType">${ktm:getText("nav.database.inventory_type")}</label>
                    <select name="selectedInventoryType" id="selectedInventoryType" size="1" onchange="document.forms['myform'].submit()">
                      <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                      <ktm:options selected="selectedInventoryType" bean="bean" value="uniqueId" label="inventory.all,inventory.center,inventory.sale_point,inventory.moving"/>
                    </select>
                  </form>
                </div>
                <ktm:if>
                  <ktm:condition>${ktm:isEmptyCollection(bean.inventoryCollection)}</ktm:condition>
                  <ktm:then>
                    <div>
                      <div class="gridBox" style="width: 30px; text-align: right;">-</div>
                      <div class="gridBox" style="width: 400px;">
                        ${ktm:getText("data.norecord")}
                      </div>
                      <div class="gridBox" style="width: 243px;"></div>
                    </div>
                  </ktm:then>
                  <ktm:else>
                    <ktm:iterate name="bean" property="inventoryCollection" id="inventory">
                      <div>
                        <div class="gridBox" style="width: 30px; text-align: right;">${id}.</div>
                        <div class="gridBox" style="width: 400px;">
                          ${inventory.name }
                        </div>
                        <div class="gridBox" style="width: 243px;">
                          <input type="button" value="edit" onclick="goTo('CRUDInventory?method=edit&uniqueId=${inventory.uniqueId}')">
                          <input type="button" value="delete" onclick="doDelete('Do you want to delete ?','CRUDInventory?method=del&uniqueId=${inventory.uniqueId}')">
                        </div>
                      </div>
                    </ktm:iterate>
                    </ktm:else>
                  </ktm:if>
              </div>
            </div>
            </div>
            <aside class="ym-col3">
            <div class="ym-cbox">
              <ul>
                <li><a href="CRUDInventory?method=new">${ktm:getText("page.btn.add")} ${ktm:getText("nav.database.store")}</a></li>
                <li><a href="Main?page=database">${ktm:getText("menu.main")}</a></li>
              </ul>
            </div>
          </aside>
          </div>
        </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>
</body>
</html>

