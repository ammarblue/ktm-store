<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.CustomerBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login"/>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.customer")}</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner" action="CRUDCustomer" method="post">
                    <input type="hidden" name="party.uniqueId" value="${bean.party.uniqueId}">
                    <input type="hidden" name="method" value="save">
                    <div class="ym-fbox-text">
                      <label for="party.identifier">${ktm:getText("nav.database.customer.id")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="party.identifier" id="party.identifier" size="5"
                        required="required" value="${bean.party.identifier}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="description">${ktm:getText("nav.database.customer.description")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="description" id="description" size="20"
                        required="required" value="${bean.description}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.address">${ktm:getText("nav.database.customer.address")}<sup class="ym-required">*</sup></label>
                      <textarea name="party.address" id="party.address" cols="30" rows="7">${bean.party.address}</textarea>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.tel">${ktm:getText("nav.database.customer.tel")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="party.tel" id="party.tel" size="15"
                        required="required" value="${bean.party.tel}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.fax">${ktm:getText("nav.database.customer.fax")}</label>
                      <input type="text" name="party.fax" id="party.fax" size="15" value="${bean.party.fax}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="party.email">${ktm:getText("nav.database.customer.email")}</label>
                      <input type="text" name="party.email" id="party.email" size="15" value="${bean.party.email}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payMethod">${ktm:getText("nav.database.customer.paypolicy")}</label>
                      <select name="payMethod" id="payMethod" size="1">
                        <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="payMethod" bean="bean" label="payment.cash,payment.check,payment.credit_card,payment.debit_card"/>
                      </select>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payPolicy">${ktm:getText("nav.database.customer.paypolicy")}</label>
                      <select name="payPolicy" id="payPolicy" size="1">
                        <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="payPolicy" bean="bean" label="payment_policy.credit,payment_policy.no_credit"/>
                      </select>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="payDuration">${ktm:getText("nav.database.customer.payduration")}</label>
                      <input type="text" name="payDuration" id="payDuration" size="5" value="${bean.payDuration}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="contactName">${ktm:getText("nav.database.customer.contact_name")}</label>
                      <input type="text" name="contactName" id="contactName" size="20" value="${bean.contactName}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="mark">${ktm:getText("nav.database.customer.mark")}</label>
                      <input type="text" name="mark" id="mark" size="20" value="${bean.mark}"
                      />
                    </div>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.database.group_product")}' id="submit"
                        name="submit"
                      />
                      <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                        name="cancel" onclick="goTo('CRUDCustomer?method=list')"
                      />
                    </div>
                  </form>
                </div>
              </section>
            </div>
            </div>
            <aside class="ym-col3">
            <div class="ym-cbox">
              <ul>
                <li><a href="CRUDCustomer?method=list">${ktm:getText("menu.main")}</a></li>
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
