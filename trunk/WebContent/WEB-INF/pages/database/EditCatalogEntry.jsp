<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.CatalogEntryBean"></jsp:useBean>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.database.group_product")}</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner" action="CRUDCatalogEntry" method="post">
                    <input type="hidden" name="uniqueId" value="${bean.uniqueId}">
                    <input type="hidden" name="method" value="save">
                    <div class="ym-fbox-text">
                      <label for="identifier">${ktm:getText("nav.database.group_product.id")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="identifier" id="identifier" size="20"
                        required="required" value="${bean.identifier}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="description">${ktm:getText("nav.database.group_product.name")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="description" id="description" size="20"
                        required="required" value="${bean.description}"
                      />
                    </div>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.database.group_product")}' id="submit"
                        name="submit"
                      />
                      <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                        name="cancel" onclick="goTo('CRUDCatalogEntry?method=list')"
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
                <li><a href="CRUDCatalogEntry?method=list">${ktm:getText("menu.main")}</a></li>
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
