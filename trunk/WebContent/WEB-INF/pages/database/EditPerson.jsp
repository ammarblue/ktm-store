<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.PersonBean"></jsp:useBean>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.user")}</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner" action="CRUDPerson" method="post">
                    <input type="hidden" name="uniqueId" value="${bean.uniqueId}">
                    <input type="hidden" name="method" value="save">
                    <div class="ym-fbox-text">
                      <label for="citizenId">${ktm:getText("nav.database.user.citizenId")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="citizenId" id="citizenId" size="20"
                        required="required" value="${bean.citizenId}"
                      />
                    </div>
                    <div class="ym-fbox-select">
                      <label for="preName">${ktm:getText("prename")}</label>
                      <select name="preName" id="preName" size="1">
                        <option value="0" selected="selected" disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="preName" bean="bean" label="prename.mr,prename.miss,prename.mis"/>
                      </select>
                    </div>
                    <div class="ym-fbox-text">
                      <label for="firstName">${ktm:getText("nav.database.user.firstname")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="firstName" id="firstName" size="20"
                        required="required" value="${bean.firstName}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="lastName">${ktm:getText("nav.database.user.lastname")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="lastName" id="lastName" size="20"
                        required="required" value="${bean.lastName}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="birthDay">${ktm:getText("nav.database.user.birthday")}</label>
                      <input type="text" name="birthDay" id="birthDay" size="20" value="${bean.birthDay}"
                      />
                    </div>
                    <ktm:if>
                      <ktm:condition>${ktm:isEmpty(bean.uniqueId)}</ktm:condition>
                      <ktm:then>
                        <div class="ym-fbox-text">
                          <label for="username">${ktm:getText("page.login.user")}</label>
                          <input type="text" name="username" id="username" size="20" value="${bean.username}"
                          />
                        </div>
                        <div class="ym-fbox-text">
                          <label for="password">${ktm:getText("page.login.password")}</label>
                          <input type="password" name="password" id="password" size="20" value="${bean.password}"
                          />
                        </div>
                      </ktm:then>
                    </ktm:if>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.database.user")}' id="submit"
                        name="submit"
                      />
                      <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                        name="cancel" onclick="goTo('CRUDPerson?method=list')"
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
                <li><a href="CRUDPerson?method=list">${ktm:getText("menu.main")}</a></li>
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
