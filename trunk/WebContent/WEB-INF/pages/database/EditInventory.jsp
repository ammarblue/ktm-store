<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.InventoryBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login"/>
<ktm:isUserNotInRole role="Admin">
  <ktm:redirectPage page="/index.jsp"/>
</ktm:isUserNotInRole>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
<script>
  $(document).ready(function(){
    $("#dvMoving").hide();
    $("#rdcenter").click(function () {
        $("#dvMoving").fadeOut();
    });
    $("#rdsale_point").click(function () {
        $("#dvMoving").fadeOut();
    });
    $("#rdmoving").click(function () {
        $("#dvMoving").fadeIn();
    });
  });
  </script>
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText(bean.selectedInventoryType)}</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner" action="CRUDInventory" method="post">
                    <input type="hidden" name="uniqueId" value="${bean.uniqueId}">
                    <input type="hidden" name="method" value="save">
                    <ktm:if>
                      <ktm:condition>${ktm:isEmpty(bean.uniqueId)}</ktm:condition>
                      <ktm:then>
                        <div class="ym-fbox-check">
                          <label>${ktm:getText("nav.database.inventory_type")}</label><br>
                            <input checked type="radio" name="selectedInventoryType" value="inventory.center" id="rdcenter" />
                            <label for="inventory.center">${ktm:getText("inventory.center")}</label>
                            <br>
                            <input type="radio" name="selectedInventoryType" value="inventory.sale_point" id="rdsale_point" />
                            <label for="inventory.sale_point">${ktm:getText("inventory.sale_point")}</label>
                            <br>
                            <input type="radio" name="selectedInventoryType" value="inventory.moving" id="rdmoving" />
                            <label for="inventory.moving">${ktm:getText("inventory.moving")}</label>
                         </div>
                      </ktm:then>
                      <ktm:else>
                          <input type="hidden" name="selectedInventoryType" value="${bean.selectedInventoryType}">
                      </ktm:else>
                    </ktm:if>
                    <div class="ym-fbox-text">
                      <label for="identifier">${ktm:getText("nav.database.inventory.id")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="identifier" id="identifier" size="20"
                        required="required" value="${bean.identifier}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="name">${ktm:getText("nav.database.inventory.name")}<sup class="ym-required">*</sup></label>
                      <input type="text" name="name" id="name" size="20"
                        required="required" value="${bean.name}"
                      />
                    </div>
                    <div id="dvMoving">
                    <div class="ym-fbox-text">
                      <label for="ownerName">${ktm:getText("nav.database.inventory.owner")}</label>
                      <input type="text" name="ownerName" id="ownerName" size="20"
                        value="${bean.ownerName}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="vehicleRegistration">${ktm:getText("nav.database.inventory.vehicle_reg")}</label>
                      <input type="text" name="vehicleRegistration" id="vehicleRegistration" size="20"
                        value="${bean.vehicleRegistration}"
                      />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="modelName">${ktm:getText("nav.database.inventory.model")}</label>
                      <input type="text" name="modelName" id="modelName" size="20"
                        value="${bean.modelName}"
                      />
                    </div>
                    </div>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.database.store")}' id="submit"
                        name="submit"
                      />
                      <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                        name="cancel" onclick="goTo('CRUDInventory?method=list')"
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
                <li><a href="CRUDInventory?method=list">${ktm:getText("menu.main")}</a></li>
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
