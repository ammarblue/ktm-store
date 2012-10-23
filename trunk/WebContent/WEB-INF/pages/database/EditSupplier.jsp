<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.SupplierBean"></jsp:useBean>
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
          <div class="ym-column">
            <div class="ym-col1">
            <div class="ym-cbox">
              <section class="box info">
                <div class="ym-wbox">
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.supplier")}</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner" action="CRUDSupplier" method="post">
                    <input type="hidden" name="party.uniqueId" value="${bean.party.uniqueId}">
                    <input type="hidden" name="method" value="save">
                    <div class="ym-fbox-text">
                      <label for="party.identifier">${ktm:getText("nav.database.supplier.id")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="party.identifier" id="party.identifier" size="5"
                        required="required" value="${bean.party.identifier}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="description">${ktm:getText("nav.database.supplier.description")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="description" id="description" size="20"
                        required="required" value="${bean.description}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.address">${ktm:getText("nav.database.supplier.address")}<sup class="ym-required">*</sup></label>
                      <textarea name="party.address" id="party.address" cols="30" rows="7">${bean.party.address}</textarea>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.tel">${ktm:getText("nav.database.supplier.tel")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="party.tel" id="party.tel" size="15"
                        required="required" value="${bean.party.tel}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.fax">${ktm:getText("nav.database.supplier.fax")}</label>
                      <input type="text" name="party.fax" id="party.fax" size="15" value="${bean.party.fax}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.email">${ktm:getText("nav.database.supplier.email")}</label>
                      <input type="text" name="party.email" id="party.email" size="15" value="${bean.party.email}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payMethod">${ktm:getText("nav.database.supplier.paypolicy")}</label>
                      <select name="payMethod" id="payMethod" size="1">
                        <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="payMethod" bean="bean">
                            <jsp:attribute name="label">${ktm:getEnumValues("org.ktm.domain.money.EPaymentType")}</jsp:attribute>
                        </ktm:options>
                      </select>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payPolicy">${ktm:getText("nav.database.supplier.paypolicy")}</label>
                      <select name="payPolicy" id="payPolicy" size="1">
                        <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="payPolicy" bean="bean">
                            <jsp:attribute name="label">${ktm:getEnumValues("org.ktm.domain.money.EPaymentPolicy")}</jsp:attribute>
                        </ktm:options>
                      </select>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payDuration">${ktm:getText("nav.database.supplier.payduration")}</label>
                      <input type="text" name="payDuration" id="payDuration" size="5" value="${bean.payDuration}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="contactName">${ktm:getText("nav.database.supplier.contact_name")}</label>
                      <input type="text" name="contactName" id="contactName" size="20" value="${bean.contactName}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="mark">${ktm:getText("nav.database.supplier.mark")}</label>
                      <input type="text" name="mark" id="mark" size="20" value="${bean.mark}"
                      />
                    </div>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.database.group_product")}' id="submit"
                        name="submit"
                      />
                      <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                        name="cancel" onclick="goTo('CRUDSupplier?method=list')"
                      />
                    </div>
                  </form>
                </div>
              </section>
            </div>
            </div>
            <aside class="ym-col3">
            <div class="ym-cbox">
              <h4>${ktm:getText("menu")}</h4><br>
              <ul id="side-menu">
                <li><a href="CRUDSupplier?method=list">${ktm:getText("menu.main")}</a></li>
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
