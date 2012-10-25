<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm"%>
<jsp:useBean id="bean" scope="request"
  class="org.ktm.stock.bean.ProductTypeBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login" />
<ktm:isUserNotInRole role="Admin">
  <ktm:redirectPage page="/index.jsp" />
</ktm:isUserNotInRole>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
<jsp:include page="/WEB-INF/template/common/scripting.jsp"></jsp:include>
<style type="text/css">
table#edit-price-table {
  margin: 0px;
  border: 0px;
  border-collapse: collapse;
  width: 100%;
}
table#edit-price-table td {
  border: 0px;
  padding: .6em 10px;
  text-align: left;
}

</style>
<script>
  $(function() {
    var price = $("#price");
    var cost_price = $("#costPrice");
    var edit_price = $("#edit_price");
    var edit_cost_price = $("#edit_cost_price");
    var dateChange = $("#dateChange");
    var username = $("#username");
    var password = $("#password");
    var newDatePrice = $("#newDatePrice");
    var newDatePriceUser = $("#newDatePriceUser");
    var allFields = $([]).add(edit_price).add(username).add(password);
    tips = $(".validateTips");
    $("#dialog-form")
        .dialog(
            {
              autoOpen : false,
              height : 380,
              width : 350,
              modal : true,
              buttons : {
                "${ktm:getText('page.btn.save')}" : function() {
                  var bValid = true;
                  allFields.removeClass("ui-state-error");

                  bValid = bValid && checkLength(edit_cost_price, "${ktm:getText('nav.database.product.cost_price')}", 1, 32);
                  bValid = bValid && checkLength(edit_price, "${ktm:getText('nav.database.product.price')}", 1, 32);
                  bValid = bValid && checkLength(username, "${ktm:getText('page.login.user')}", 3, 16 );
                  bValid = bValid && checkLength(password, "${ktm:getText('page.login.password')}", 4, 16);
                  
                  bValid = bValid
                      && checkRegexp(username, /^[a-z]([0-9a-z_])+$/i,
                          "Username may consist of a-z, 0-9, underscores, begin with a letter." );
                  bValid = bValid
                      && checkRegexp(edit_price, /^[+-]?\d+(\.\d+)?$/,
                          "Product total field only allow : 0-9");
                  bValid = bValid
                  && checkRegexp(password, /^([0-9a-zA-Z])+$/,
                      "Password field only allow : a-z 0-9");
                  
                  if (bValid) {
                    var data = "{'username':'" + username.val()
                             + "','password':'" + password.val()
                             + "','roles':'Admin'}";
                    $.post(
                        'CRUDProductType?method=checkauth',
                         {
                             contentType: "application/x-www-form-urlencoded;charset=utf-8",
                             data : data
                         },
                         function (data, status, jqXHR) {
                           if (data=="success") {
                             cost_price.val(edit_cost_price.val());
                             price.val(edit_price.val());
                             newDatePrice.val(dateChange.val());
                             newDatePriceUser.val(username.val());
                             $("#dialog-form").dialog("close");
                           }
                           else {
                             updateTips(data);
                           }
                         }
                      );
                  }
                },
                "${ktm:getText('page.btn.cancel')}" : function() {
                  $(this).dialog("close");
                }
              },
              close : function() {
                allFields.val("").removeClass("ui-state-error");
              }
            });

  });

  function openDialog() {
    $('#dialog-form').dialog('open');
    $('#edit_cost_price').val($('#costPrice').val());
    $('#edit_price').val($('#price').val());
    $('#username').val("");
    $('#password').val("");
    $('.validateTips').val("");
  }
</script>
</head>
<body>
  <div class="ym-wrapper">
    <div class="ym-wbox">
      <header>
        <h1>${ktm:getText("app.title")}</h1>
      </header>
      <div id="main">
        <div class="ym-column">
          <div class="ym-col1">
            <div class="ym-cbox">
              <section class="box info">
                <div class="ym-wbox">
                  <h3>${ktm:getText("nav.database")}
                    ${ktm:getText("nav.database.group_product")}
                    ${bean.catalogEntryTypeName }</h3>
                </div>
                <div class="ym-wbox">
                  <form class="ym-form ym-columner"
                    action="CRUDProductType" method="post">
                    <input type="hidden" name="uniqueId"
                      value="${bean.uniqueId}"> <input
                      type="hidden" name="method" value="save">
                    <div class="ym-fbox-text">
                      <label for="identifier">${ktm:getText("nav.database.product.id")}<sup
                        class="ym-required">*</sup></label> <input type="text"
                        name="identifier" id="identifier" size="20"
                        required="required" value="${bean.identifier}" />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="name">${ktm:getText("nav.database.product.name")}<sup
                        class="ym-required">*</sup></label> <input type="text"
                        name="name" id="name" size="20"
                        required="required" value="${bean.name}" />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="description">${ktm:getText("nav.database.product.desc")}</label>
                      <input type="text" name="description"
                        id="description" size="20"
                        value="${bean.description}" />
                    </div>
                    <div class="ym-fbox-select">
                      <label for="unit">${ktm:getText("nav.database.product.unit")}</label>
                      <select name="unit" id="unit" size="1">
                        <option value="0" selected="selected"
                          disabled="disabled">${ktm:getText("choose")}</option>
                        <ktm:options selected="unit" bean="bean">
                          <jsp:attribute name="label">${ktm:getAllUnits()}</jsp:attribute>
                        </ktm:options>
                      </select>
                    </div>
                    <div class="ym-fbox-select">
                      <label for="packAmount">${ktm:getText("nav.database.product.pack_amount")}</label>
                      <input type="text" name="packAmount"
                        id="packAmount" size="20" value="${bean.packAmount}" />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="quantity">${ktm:getText("nav.database.product.quantity_size")}</label>
                      <input type="text" name="quantity"
                        id="quantity" size="20" value="${bean.quantity}" />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="costPrice">${ktm:getText("nav.database.product.cost_price")} (${bean.unitPrice})</label>
                      <input readonly type="text" name="costPrice"
                        id="costPrice" size="20" value="${bean.costPrice}" />
                    </div>
                    <div class="ym-fbox-text">
                      <label for="price">${ktm:getText("nav.database.product.price")} (${bean.unitPrice})</label>
                      <input readonly type="text" name="price"
                        id="price" size="20" value="${bean.price}" />
                      <input type="hidden" name="newDatePrice" id="newDatePrice">
                      <input type="hidden" name="newDatePriceUser" id="newDatePriceUser">
                    </div>
                    <div class="ym-fbox-button">
                      <input type="submit" class="ym-button"
                        value="${ktm:getText('page.btn.save')} ${ktm:getText('nav.database.product')}"
                        id="submit" name="submit" />
                      <input id="edit-price"
                        <ktm:isUserNotInRoles roles="Admin">disabled="disabled"</ktm:isUserNotInRoles>
                        type="button" onclick="openDialog();"
                        value="${ktm:getText('page.btn.edit')} ${ktm:getText('nav.database.product.price')}">
                      <input
                        type="button" class="ym-button"
                        value='${ktm:getText("page.btn.cancel")}'
                        id="cancel" name="cancel"
                        onclick="goTo('CRUDProductType?method=list')" />
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
                <li><a href="CRUDProductType?method=list">${ktm:getText("menu.main")}</a></li>
              </ul>
            </div>
          </aside>
        </div>
      </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>

  <div id="dialog-form"
    title="${ktm:getText('page.btn.edit')} ${ktm:getText('nav.database.product.price')}">
    <p class="validateTips"></p>
    <form>
      <fieldset>
        <table id="edit-price-table">
          <tr>
            <td><label for="edit_cost_price">${ktm:getText('nav.database.product.cost_price')}</label></td>
            <td><input type="text" name="edit_cost_price" id="edit_cost_price"
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td><label for="edit_price">${ktm:getText('nav.database.product.price')}</label></td>
            <td><input type="text" name="edit_price" id="edit_price"
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td><label for="dateChange">${ktm:getText("nav.database.product.date")}</label></td>
            <td><input type="text" name="dateChange"
              id="dateChange" value="${bean.toDay}" disabled="disabled"
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td><label for="username">${ktm:getText("page.login.user")}</label></td>
            <td><input type="text" name="username" id="username"
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td><label for="password">${ktm:getText("page.login.password")}</label></td>
            <td><input type="password" name="password"
              id="password" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
        </table>
      </fieldset>
    </form>
  </div>
</body>
</html>
