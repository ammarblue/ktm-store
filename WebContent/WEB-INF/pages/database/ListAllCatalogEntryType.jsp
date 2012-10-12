<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<ktm:enforceAuthentication loginPage="/login"/>
<ktm:isUserNotInRole role="Admin">
  <ktm:redirectPage page="/index.jsp"/>
</ktm:isUserNotInRole>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.group_product")}</h3>
                </div>
              </section>
              <div class="ym-wbox">
                <ktm:if>
                  <ktm:condition>${ktm:isEmptyCollection(bean.catalogEntryTypeCollection)}</ktm:condition>
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
                    <ktm:iterate name="bean" property="catalogEntryTypeCollection" id="ptype">
                      <div>
                        <div class="gridBox" style="width: 30px; text-align: right;">${id}.</div>
                        <div class="gridBox" style="width: 400px;">
                          ${ptype.name }
                        </div>
                        <div class="gridBox" style="width: 243px;">
                          <input type="button" value="edit" onclick="goTo('CRUDCatalogEntryType?method=edit&uniqueId=${ptype.uniqueId}')">
                          <input type="button" value="delete" onclick="doDelete('Do you want to delete ?','CRUDCatalogEntryType?method=del&uniqueId=${ptype.uniqueId}')">
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
                <li><a href="CRUDCatalogEntryType?method=new">${ktm:getText("page.btn.add")} ${ktm:getText("nav.database.group_product")}</a></li>
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

