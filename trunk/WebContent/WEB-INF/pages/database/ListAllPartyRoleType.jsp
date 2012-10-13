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
<jsp:include page="/WEB-INF/template/common/scripting.jsp"></jsp:include>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.party_role_type")}</h3>
                </div>
              </section>
              <div class="ym-wbox">
              <table class="ui-widget ui-widget-content tblGrid">
                <thead>
                  <tr class="ui-widget-header ">
                    <th style="width: 10%;">${ktm:getText("nav.database.party_role_type.id")}</th>
                    <th>${ktm:getText("nav.database.party_role_type.type")}</th>
                    <th style="width: 20%;"></th>
                  </tr>
                </thead>
                <tbody>
	                <ktm:if>
	                  <ktm:condition>${ktm:isEmptyCollection(bean.partyRoleTypeCollection)}</ktm:condition>
	                  <ktm:then>
	                    <tr>
	                      <td>-</td>
	                      <td>${ktm:getText("data.norecord")}</td>
	                      <td></td>
	                    </tr>
	                  </ktm:then>
	                  <ktm:else>
	                    <ktm:iterate name="bean" property="partyRoleTypeCollection" id="partyRoleType">
	                    <tr>
	                      <td>${id}.</td>
	                      <td>${ktm:getText(partyRoleType.name)}</td>
	                      <td>
	                          <input type="button" value='${ktm:getText("page.btn.edit")}' onclick="goTo('CRUDPartyRoleType?method=edit&uniqueId=${partyRoleType.uniqueId}')">
	                          <input type="button" value='${ktm:getText("page.btn.delete")}' onclick="goToConfirm('CRUDPartyRoleType?method=del&uniqueId=${partyRoleType.uniqueId}')">
	                      </td>
	                    </tr>
	                    </ktm:iterate>
	                    </ktm:else>
	                  </ktm:if>
                </tbody>
              </table>
              </div>
            </div>
            </div>
            <aside class="ym-col3">
            <div class="ym-cbox">
              <ul>
                <li><a href="CRUDPartyRoleType?method=new">${ktm:getText("page.btn.add")} ${ktm:getText("nav.database.party_role_type")}</a></li>
                <li><a href="Main?page=database">${ktm:getText("menu.main")}</a></li>
              </ul>
            </div>
          </aside>
          </div>
        </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>
  <div id="dialog-confirm" title='${ktm:getText("page.confirm.delete")} ${ktm:getText("nav.database.party_role_type")} ?'>
    <p style="font-size: 0.9em; margin-top:5px;">${ktm:getText("page.confirm.delete.info")}</p>
  </div>
</body>
</html>

