<%@page import="org.ktm.stock.transaction.PurchaseOderServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm"%>
<jsp:useBean id="bean" scope="request"
  class="org.ktm.stock.bean.PurchaseOrderBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login" />
<ktm:isUserNotInRoles roles="Admin,Employee">
  <ktm:redirectPage page="/index.jsp" />
</ktm:isUserNotInRoles>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
<style type="text/css">
h1 {
  font-size: 1.2em;
  margin: .6em 0;
}

.ui-dialog .ui-state-error {
  padding: .3em;
}

.validateTips {
  border: 1px solid transparent;
  padding: 0.3em;
}

#dvPurchaseOrder {
  height: 100px;
  background-color: #FFFFEE;
  border-top-color: black;
}

#dvOrderLine {
  height: 300px;
  font-size: 0.8em;
  background-color: #FFFFEE;
}

#dvOrderLineHeader {
  background-color: #CDCDCD;
}

#dvOrderSummary {
  height: 20px;
  padding: 5px;
  background-color: #CDCDCD;
}
.order-summary-cell {
  width: 73px;
  padding-left: 5px;
  border: 1px solid black;
  border-top-color: #666666;
  border-left-color: #666666;
  border-right-color: #888888;
  border-bottom-color: #888888;
  background-color: #FFFFEE;
  float: right;
}
</style>
<script>
$.po = { 
    edit : false,
    edit_id : "",
    order_line_tr: function(max_no,unique_id,product_id,product_desc,product_unit,product_price,product_quanitity,product_total) {
      return "<tr id='tr-product-line-" + max_no + "'>"
      + "<td style='width: 35px;'><div style='display: none;' name='unique_id'></div><div class='grid-cell grid-cell-no'>"
      + "<input class='chk-dels' type='checkbox' value='"+ max_no +"'>&nbsp;</div></td>"
      + "<td style='width: 80px;'><div name='product_id' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_id)
      + "</div></td>"
      + "<td><div name='product_desc' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_desc)
      + "</div></td>"
      + "<td style='width: 80px;'><div name='product_unit' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_unit)
      + "</div></td>"
      + "<td style='width: 80px;'><div name='product_price' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_price)
      + "</div></td>"
      + "<td style='width: 80px;'><div name='product_quanitity' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_quanitity)
      + "</div></td>"
      + "<td style='width: 80px;'><div name='product_total' class='grid-cell' onclick='editOrderLine(" + max_no + ")'>"
      + getSafeValue(product_total)
      + "</div></td>" + "</tr>";
    },
    onLoad: function() {
      var data = "<%=PurchaseOderServlet.getJSONPurchaseOrder(request)%>";
        
      var jsonObject = eval("(" + data + ")");
      if (typeof jsonObject.supplier_desc != "undefined") {
        $("#supplier-desc").html(jsonObject.supplier_desc);
      }
      if (typeof jsonObject.date_created != "undefined") {
        $("#date_created").val(jsonObject.date_created);
      }
      if (typeof jsonObject.supplier_id != "undefined") {
        $("#supplier_id").val(jsonObject.supplier_id);
      }
      if (typeof jsonObject.order_id != "undefined") {
        $("#order_id").val(jsonObject.order_id);
      }

      if (typeof jsonObject.order_lines != "undefined") {
        for (var i=0; i<jsonObject.order_lines.length; i++) {
          $("#tblOrderLine tbody")
            .append($.po.order_line_tr(i+1,
                jsonObject.order_lines[i].unqiue_id,
                jsonObject.order_lines[i].product_id,
                jsonObject.order_lines[i].product_desc,
                jsonObject.order_lines[i].product_unit,
                jsonObject.order_lines[i].product_price,
                jsonObject.order_lines[i].product_quanitity,
                jsonObject.order_lines[i].product_total));
        }
      }
    }
}; 
  $(function() {
    var max_no = 0;
    var order_total = 0;
    var order_num = 0;
    var date_created = $("#date_created");
    var supplier_id = $("#supplier_id");
    var order_id = $("#order_id");
    var product_id = $("#product_id");
    var product_desc = $("#product_desc");
    var product_price = $("#product_price");
    var product_unit = $("#product_unit"); 
    var product_quanitity = $("#product_quanitity");
    var product_total = $("#product_total");
    var pid = $("#_pid_");
    var allFields = $([]).add(product_id).add(product_quanitity).add(product_total);
    tips = $(".validateTips");
    $.datepicker.setDefaults($.datepicker.regional["th"]);
    $.getJSON("CRUDPurchaseOrder?method=getid", function(result) {
      order_id.val(result.identifier);
      $.po.onLoad();
    });
    date_created.datepicker({
      showOn : "button",
      dateFormat: "dd-mm-yy",
      buttonImage : "images/calendar.gif",
      buttonImageOnly : true,
      changeMonth : true,
      changeYear : true,
      showButtonPanel : true
    });
    var cache_employee = {};
    var cache_product = {};
    supplier_id.autocomplete(
        {
          minLength : 0,
          source : function(request, response) {
            var term = request.term;
            if (term in cache_employee) {
              response(cache_employee[term]);
              return;
            }
            $.getJSON("CRUDSupplier?method=listJSON", request, function(data,
                status, xhr) {
              cache_employee[term] = data;
              response(data);
            });
          },
          focus : function(event, ui) {
            supplier_id.val(ui.item.identifier + " - " + ui.item.name);
            $("#supplier_id_hidden").val(ui.item.identifier);
            return false;
          },
          select : function(event, ui) {
            supplier_id.val(ui.item.identifier + " - " + ui.item.name);
            $("#supplier_id_hidden").val(ui.item.identifier);
            $.get(
                "CRUDPurchaseOrder?method=setemp&partySummary.partyIdentifier="
                    + ui.item.identifier, function(data, status) {
                  $("#supplier-desc").html(data);
                });
            return false;
          }
        }).data("autocomplete")._renderItem = function(ul, item) {
      return $("<li>").data("item.autocomplete", item).append(
          "<a>" + item.identifier + " - " + item.name + "</a>").appendTo(ul);
    };
    product_id.autocomplete(
        {
          minLength : 0,
          source : function(request, response) {
            var term = request.term;
            if (term in cache_product) {
              response(cache_product[term]);
              return;
            }
            $.getJSON("CRUDProductType?method=listJSON", request, function(
                data, status, xhr) {
              cache_product[term] = data;
              response(data);
            });
          },
          focus : function(event, ui) {
            pid.val(ui.item.identifier);
            product_id.val(ui.item.identifier + " - " + ui.item.name);
            return false;
          },
          select : function(event, ui) {
            pid.val(ui.item.identifier);
            product_id.val(ui.item.identifier + " - " + ui.item.name);
            product_desc.val(ui.item.name);
            product_price.val(ui.item.price);
            product_unit.val(ui.item.unit);
            return false;
          }
        }).data("autocomplete")._renderItem = function(ul, item) {
      return $("<li>").data("item.autocomplete", item).append(
          "<a>" + item.identifier + " - " + item.name + "</a>").appendTo(ul);
    };
    $("#dialog-form")
        .dialog(
            {
              autoOpen : false,
              height : 300,
              width : 350,
              modal : true,
              buttons : {
                "${ktm:getText('choose')}" : function() {
                  var bValid = true;
                  allFields.removeClass("ui-state-error");

                  bValid = bValid
                      && checkRegexp(product_quanitity, /^([0-9])+$/,
                          "Product quanitity field only allow : 0-9");
                  bValid = bValid
                      && checkRegexp(product_total, /^[+-]?\d+(\.\d+)?$/,
                          "Product total field only allow : 0-9");

                  if (bValid) {
                    var obj = getProductLine($.po.edit_id);
                    if ($.po.edit && obj != null) {
                      setProductId(obj,pid.val());
                      setProductDesc(obj,product_desc.val());
                      setProductUnit(obj,product_unit.val());
                      setProductPrice(obj,product_price.val());
                      setProductQuanitity(obj,product_quanitity.val());
                      setProductTotal(obj,product_total.val());
                    } else {
                      max_no++;
                      $("#tblOrderLine tbody")
                          .append($.po.order_line_tr(max_no,null,pid.val(),product_desc.val(),product_unit.val(),product_price.val(),product_quanitity.val(),product_total.val()));
                    }
                    order_total = sumOrderTotal();
                    order_num = sumOrderNum();
                    $("#order-total").html(order_total.toFixed(2));
                    $("#order-num").html(order_num);
                    $(this).dialog("close");
                  }
                },
                "${ktm:getText('page.btn.cancel')}" : function() {
                  $(this).dialog("close");
                }
              },
              close : function() {
                $.po.edit = false;
                $.po.edit_id = "";
                allFields.val("").removeClass("ui-state-error");
              }
            });
    $("#save-order").click(function(event) {
      var bValid = true;
      event.preventDefault();

      date_created.removeClass("ui-state-error");
      $("#supplier_id").removeClass("ui-state-error");

      bValid = bValid
      && checkRegexp(date_created, /^(\d{1,2})-(\d{1,2})-(\d{4})$/,
          "Date field only allow : dd/MM/yyyy");
      bValid = bValid && checkLength($("#supplier_id"), "supplier_id", 4, 128);

      if (bValid) {
      var data = "{'date_created':'" + date_created.val() + "',"+
      "'supplier_id':'" + $("#supplier_id_hidden").val() + "',"+
      "'order_id':'" + order_id.val() + "',"+
      "'orderlines' : [";
      $("#tblOrderLine tbody tr").each(function (index) {
        var obj = getProductLineJSON($(this));
        order_line = "{'product_id':'" + obj.product_id + "'" +
        ", 'unique_id':'" + obj.unique_id + "'" +
        ", 'product_desc':'" + obj.product_desc + "'" +
        ", 'product_unit':'" + obj.product_unit + "'" +
        ", 'product_price':'" + obj.product_price  + "'" +
        ", 'product_quanitity':'" + obj.product_quanitity + "'" +
        ", 'product_total':'" + obj.product_total + "'}";
        data += order_line + ",";
      });
      data += "]}";
      $.post(
          'CRUDPurchaseOrder?method=save',
           {
               contentType: "application/x-www-form-urlencoded;charset=utf-8",
               data : data
           },
           function (data, status, jqXHR){
             if (status=="success") {
               goTo("CRUDPurchaseOrder?method=list");
             }
           }
        );
      }
  });
  });
  function getProductLineJSON(obj) {
    var order_line = "",
    sproduct_id = "",
    sunique_id = "",
    sproduct_desc = "",
    sproduct_unit = "",
    sproduct_price = "",
    sproduct_quanitity = "",
    sproduct_total = "";
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_id']").html();
      if ((typeof obj != "undefined")) {
        sproduct_id = obj;
      }
      obj = $(this).children("div[name*='unique_id']").html();
      if (typeof obj != "undefined") {
        sunique_id = obj;
      }
      obj = $(this).children("div[name*='product_desc']").html();
      if (typeof obj != "undefined") {
        sproduct_desc = obj;
      }
      obj = $(this).children("div[name*='product_unit']").html();
      if (typeof obj != "undefined") {
        sproduct_unit = obj;
      }
      obj = $(this).children("div[name*='product_price']").html();
      if (typeof obj != "undefined") {
        sproduct_price = obj;
      }
      obj = $(this).children("div[name*='product_quanitity']").html();
      if (typeof obj != "undefined") {
        sproduct_quanitity = obj;
      }
      obj = $(this).children("div[name*='product_total']").html();
      if (typeof obj != "undefined") {
        sproduct_total = obj;
      }
    });
    
    order_line = "{'product_id':'" + getClearValue(sproduct_id) + "'" +
    ", 'unique_id':'" + getClearValue(sunique_id) + "'" +
    ", 'product_desc':'" + getClearValue(sproduct_desc) + "'" +
    ", 'product_unit':'" + getClearValue(sproduct_unit) + "'" +
    ", 'product_price':'" + getClearValue(sproduct_price)  + "'" +
    ", 'product_quanitity':'" + getClearValue(sproduct_quanitity) + "'" +
    ", 'product_total':'" + getClearValue(sproduct_total) + "'}";
    return eval("(" + order_line + ")");
  }
  function sumOrderTotal() {
    var total = 0.0;
    $("#tblOrderLine tbody tr").each(function (index) {
      var obj = getProductLineJSON($(this));
      var t =  parseFloat(obj.product_total);
      total += t;
    });
    return total;
  }
  function sumOrderNum() {
    var num = 0;
    $("#tblOrderLine tbody tr").each(function (index) {
      var obj = getProductLineJSON($(this));
      var q = parseInt(obj.product_quanitity);
      num += q;
    });
    return num;
  }
  function getProductLine(id) {
    var result = null;
    try {
      $("#tblOrderLine tbody tr").each(function (index) {
        var obj = getProductLineJSON($(this));
        if (id == obj.product_id) {
          result = $(this);
        }
      });
    }
    catch(err) {}
    return result;
  }
  function deleteSelected() {
    $.each($("input[type=checkbox]:checked"), function(index, v) {
      var del_element = "#tr-product-line-" + $(v).val();
      $(del_element).remove();
      var order_total = sumOrderTotal();
      var order_num = sumOrderNum();
      $("#order-total").html(order_total.toFixed(2));
      $("#order-num").html(order_num);
    });
  }
  function openDialog() {
    updateTips("");
    $('#dialog-form').dialog('open');
    $('#product_id').val("");
    $('#product_desc').val("");
    $('#product_unit').val("");
    $('#product_price').val("");
    $('#product_quanitity').val("");
    $('#product_total').val("");
  }
  function editOrderLine(id) {
    updateTips("");
    $('#dialog-form').dialog('open');
    var data = getProductLineJSON($('#tr-product-line-' + id));
    $('#product_id').val(data.product_id);
    $('#product_desc').val(data.product_desc);
    $('#product_unit').val(data.product_unit);
    $('#product_price').val(data.product_price);
    $('#product_quanitity').val(data.product_quanitity);
    $('#product_total').val(data.product_total);
    $.po.edit = true;
    $.po.edit_id = data.product_id;
  }
  function setProductId(obj, product_id) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_id']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_id));
      }
    });
  }
  function setProductDesc(obj, product_desc) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_desc']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_desc));
      }
    });
  }
  function setProductUnit(obj, product_unit) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_unit']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_unit));
      }
    });
  }
  function setProductPrice(obj, product_price) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_price']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_price));
      }
    });
  }
  function setProductQuanitity(obj, product_quanitity) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_quanitity']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_quanitity));
      }
    });
  }
  function setProductTotal(obj, product_total) {
    obj.children("td").each(function (index) {
      var obj = $(this).children("div[name*='product_total']");
      if ((typeof obj.html() != "undefined")) {
        obj.html(getSafeValue(product_total));
      }
    });
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
                <div class="dvTitle">
                  <h3>${ktm:getText("nav.transaction.receive_from_supplier")}</h3>
                </div>
                <div class="dvBody">
                  <div id="dvPurchaseOrder" class="dvContent">
                    <table>
                      <tr>
                        <td style="width: 59%;">
                          <div id="dvSupplier_desc">
                            <p>${ktm:getText("nav.transaction.receive_from_supplier.supplier_desc")}:</p>
                            <p id="supplier-desc"></p>
                          </div>
                        </td>
                        <td>
                          <div id="dvPurchaseOrderForm">
                            <fieldset>
                              <table style="border: 0; margin: 0;">
                                <tr>
                                  <td class="cLabel"><label
                                    for="date_created">${ktm:getText("nav.transaction.receive_from_supplier.date_created")}:</label></td>
                                  <td>
                                    <input style="width: 170px"
                                    type="text" name="date_created"
                                    id="date_created"
                                    class="text ui-widget-content ui-corner-all" />
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cLabel"><label
                                    for="supplier_id">${ktm:getText("nav.transaction.receive_from_supplier.supplier_id")}:</label></td>
                                  <td>
                                    <input style="width: 200px"
                                    type="text" name="supplier_id"
                                    id="supplier_id"
                                    class="text ui-widget-content ui-corner-all">
                                    <input type="hidden" name="supplier_id_hidden" id="supplier_id_hidden">
                                  </td>
                                </tr>
                                <tr>
                                  <td class="cLabel"><label
                                    for="order_id">${ktm:getText("nav.transaction.receive_from_supplier.order_id")}:</label></td>
                                  <td><input style="width: 200px"
                                    type="text" name="order_id"
                                    id="order_id" value=""
                                    readonly
                                    class="text ui-widget-content ui-corner-all" /></td>
                                </tr>
                              </table>
                            </fieldset>
                          </div>
                        </td>
                      </tr>
                    </table>
                  </div>
                  <div id="dvOrderLine" class="dvContent">
                    <div id="dvOrderLineHeader">
                      <table>
                        <tr>
                          <th style="width: 35px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.no")}</span>
                            </div></th>
                          <th style="width: 80px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.product_id")}</span>
                            </div></th>
                          <th><div class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.product_desc")}</span>
                            </div></th>
                          <th style="width: 80px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.unit")}</span>
                            </div></th>
                          <th style="width: 80px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.cost")}</span>
                            </div></th>
                          <th style="width: 80px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.quanitity")}</span>
                            </div></th>
                          <th style="width: 80px;"><div
                              class="gridHeader">
                              <span>${ktm:getText("nav.transaction.receive_from_supplier.total")}</span>
                            </div></th>
                        </tr>
                      </table>
                    </div>
                    <div>
                      <table id="tblOrderLine">
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div id="dvOrderSummary" class="dvContent">
                    <div style="width: 300px; float: left;">
                      <input onclick="openDialog();" type="button" value="${ktm:getText('nav.transaction.receive_from_supplier.add_product_line')}">&nbsp; 
                      <input onclick="deleteSelected();" type="button" value="${ktm:getText('nav.transaction.receive_from_supplier.delete_selected')}">
                    </div>
                    <div style="width: 300px; float: right;">
                      <div id="order-total" class="order-summary-cell">0.00</div>
                      <div id="order-num" class="order-summary-cell">0</div>
                    </div>
                  </div>
                  <div class="ym-fbox-button">
                    <input id="save-order" type="button"
                      class="ym-button"
                      value="${ktm:getText('page.btn.save')} ${ktm:getText('nav.transaction.receive_from_supplier')}" />
                    <input type="button" class="ym-button"
                      value='${ktm:getText("page.btn.cancel")}'
                      id="cancel" name="cancel"
                      onclick="goTo('CRUDPurchaseOrder?method=list')" />
                  </div>
                </div>
              </section>
            </div>
          </div>
          <aside class="ym-col3">
            <div class="ym-cbox">
              <ul>
                <li><a href="CRUDPurchaseOrder?method=list">${ktm:getText("menu.main")}</a></li>
              </ul>
            </div>
          </aside>
        </div>
      </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>

  <div id="dialog-form" class="dvContent" title="${ktm:getText('nav.transaction.receive_from_supplier.choose_product')}">
    <p class="validateTips"></p>
    <form>
      <fieldset>
        <table style="font-size: 0.85em;">
          <tr>
            <td style="text-align: right;"><label for="product_id">${ktm:getText("nav.transaction.receive_from_supplier.product_id")}:
            </label></td>
            <td><input type="hidden" name="_pid_" id="_pid_">
              <input type="text" name="product_id" id="product_id"
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td style="text-align: right;"><label
              for="product_desc">${ktm:getText("nav.transaction.receive_from_supplier.product_desc")}:
            </label></td>
            <td><input readonly type="text" name="product_desc"
              id="product_desc" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td style="text-align: right;"><label
              for="product_unit">${ktm:getText("nav.transaction.receive_from_supplier.unit")}:
            </label></td>
            <td><input readonly type="text" name="product_unit"
              id="product_unit" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td style="text-align: right;"><label
              for="product_price">${ktm:getText("nav.transaction.receive_from_supplier.cost")}:
            </label></td>
            <td><input readonly type="text" name="product_price"
              id="product_price" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td style="text-align: right;"><label
              for="product_quanitity">${ktm:getText("nav.transaction.receive_from_supplier.quanitity")}:
            </label></td>
            <td><input type="text" name="product_quanitity"
              id="product_quanitity" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
          <tr>
            <td style="text-align: right;"><label
              for="product_total">${ktm:getText("nav.transaction.receive_from_supplier.total")}:
            </label></td>
            <td><input type="text" name="product_total"
              id="product_total" value=""
              class="text ui-widget-content ui-corner-all" /></td>
          </tr>
        </table>
      </fieldset>
    </form>
  </div>
</body>
</html>

