<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<ktm:enforceAuthentication loginPage="/login"/>
<ktm:isUserNotInRoles roles="Admin,Employee">
  <ktm:redirectPage page="/index.jsp"/>
</ktm:isUserNotInRoles>
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
          <div class="ym-column">
            <div class="ym-col1">
            <div class="ym-cbox">
              <section class="box info">
                <div class="ym-wbox">
                <h3>${ktm:getText("nav.transaction.receive_from_supplier")}</h3>
                </div>
                <div class="ym-wbox">
                  <table class="ui-widget ui-widget-content tblGrid">
                    <thead>
                      <tr class="ui-widget-header ">
                        <th style="width: 10%;">no.</th>
                        <th style="width: 15%;">id</th>
                        <th>name</th>
                        <th style="width: 20%;">date</th>
                        <th style="width: 20%;"></th>
                      </tr>
                    </thead>
                    <ktm:if>
                      <ktm:condition>${ktm:isEmptyCollection(bean.purchaseOrderBeanCollection)}</ktm:condition>
                      <ktm:then>
                        <tr>
                          <td>-</td>
                          <td colspan="4">${ktm:getText("data.norecord")}</td>
                        </tr>
                  </ktm:then>
                  <ktm:else>
                    <ktm:iterate name="bean" property="purchaseOrderBeanCollection" id="order">
                      <tr>
                          <td>${id}.</td>
                          <td>${order.identifier}</td>
                          <td>${order.partySummary.name}</td>
                          <td>${order.dateCreated}</td>
                          <td>
                            <input type="button" value='${ktm:getText("page.btn.edit")}' onclick="goTo('CRUDPurchaseOrder?method=edit&uniqueId=${order.uniqueId}')">
                            <input type="button" value='${ktm:getText("page.btn.delete")}' onclick="goToConfirm('CRUDPurchaseOrder?method=del&uniqueId=${order.uniqueId}')">
                          </td>
                        </tr>
                    </ktm:iterate>
                    </ktm:else>
                  </ktm:if>
                </tbody>
              </table>
                </div>
              </section>
            </div>
            </div>
            <aside class="ym-col3">
            <div class="ym-cbox">
              <ul>
                <li><a href="CRUDPurchaseOrder?method=new">${ktm:getText("page.btn.add")} ${ktm:getText("nav.transaction.receive_from_supplier")}</a></li>
                <li><a href="Main?page=transaction">${ktm:getText("menu.main")}</a></li>
              </ul>
            </div>
          </aside>
          </div>
        </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>
  <div id="dialog-confirm" title='${ktm:getText("page.confirm.delete")} ${ktm:getText("nav.transaction.receive_from_supplier")} ?'>
    <p style="font-size: 0.9em; margin-top:5px;">${ktm:getText("page.confirm.delete.info")}</p>
  </div>
</body>
</html>
