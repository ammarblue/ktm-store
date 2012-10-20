<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="request" class="org.ktm.stock.bean.PurchaseOrderBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login"/>
<ktm:isUserNotInRoles roles="Admin,Employee">
  <ktm:redirectPage page="/index.jsp"/>
</ktm:isUserNotInRoles>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
<style type="text/css">
h1 { font-size: 1.2em; margin: .6em 0; }
div#users-contain { width: 350px; margin: 20px 0; }
div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
.ui-dialog .ui-state-error { padding: .3em; }
.validateTips { border: 1px solid transparent; padding: 0.3em; }

#dvOrder {
    padding: 5px;
    width: 720px;
    display: block;
    border-radius: 5px;
    border:1px solid black;
}
#dvPurchaseOrder
{
    height: 100px;
    font-size: 1.0em;
    padding: 5px;
    border:1px solid black;
    border-radius: 5px;
    background-color: #FFFFEE;
}
#dvOrderLine
{
    height: 300px;
    margin: 0;
    padding: 5px;
    border:1px solid black;
    display: block;
    font-size: 0.8em;
    background-color: #FFFFEE;
    border-top-color: #CDCDCD;
    border-radius: 5px;
}
#dvOrderLineHeader
{
    width: 100%;
    margin: 0;
    padding: 0;
    background-color: #CDCDCD;
}
#dvOrderSummary {
    height: 25px;
    padding: 5px;
    border:1px solid black;
    display: block;
    background-color: #CDCDCD;
    border-radius: 5px;
    font-size: 0.8em;
}
#supplier-desc {
    font-size: 0.85em;
}
.cLabel {
    text-align: right;
    border-radius: 5px;
}
.gridHeader {
  border: 1px solid black;
  border-top-color: #666666;
  border-left-color: #666666;
  border-right-color: #888888;
  border-bottom-color: #888888;
  text-align: center;
}
.order-line-cell {
  margin: 0;
  padding-left: 5px;
  border: 1px solid black;
  border-top-color: #666666;
  border-left-color: #666666;
  border-right-color: #888888;
  border-bottom-color: #888888;
}
.order-line-no {
  text-align: right;
  background-color: #CDCDCD;
}
.order-summary-cell {
    width: 73px;
    margin: 0;
    padding-left: 5px;
    border: 1px solid black;
    border-top-color: #666666;
    border-left-color: #666666;
    -right-color: #888888;
    border-bottom-color: #888888;
    background-color: #FFFFEE;
    float: right;
}
table {
    padding: 0px;
    margin: 0px;
    border: 0px;
}
th {
  border: 0px;
  padding: 0px;
  margin: 0px;
}
tr td{
    padding: 0px;
    margin: 0px;
    border: 0px;
}
p {
    margin-top: 0;
}
</style>
<script>
$(function() {
    $.datepicker.setDefaults( $.datepicker.regional[ "th" ] );
    $.getJSON("CRUDPurchaseOrder?method=getid",function(result) {
        $("#identifier").val(result.identifier);
    });
    var max_no = 0;
    var order_total = 0;
    var order_num = 0;
    var product_id = $( "#product_id" ),
    product_desc = $("#product_desc"),
    product_price = $("#product_price"),
    product_unit = $("#product_unit"),
    product_quanitity = $( "#product_quanitity" ),
    product_total = $( "#product_total" ),
    pid = $("#_pid_"),
    allFields = $( [] ).add( product_id ).add( product_quanitity ).add( product_total );
    tips = $( ".validateTips" );
    $("#dateCreated").datepicker({
        showOn: "button",
        buttonImage: "images/calendar.gif",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
    var cache_employee = {};
    var cache_product = {};
    $( "#supplierId" ).autocomplete({
        minLength: 0,
        source: function( request, response ) {
            var term = request.term;
            if ( term in cache_employee ) {
                response( cache_employee[ term ] );
                return;
            }
            $.getJSON( "CRUDSupplier?method=listJSON", request, function( data, status, xhr ) {
            	cache_employee[ term ] = data;
                response( data );
            });
        },
        focus: function( event, ui ) {
            $( "#supplierId" ).val( ui.item.identifier + " - " + ui.item.name );
            return false;
        },
        select: function( event, ui ) {
            $( "#supplierId" ).val( ui.item.identifier + " - " + ui.item.name );
            $.get("CRUDPurchaseOrder?method=setemp&partySummary.partyIdentifier=" + ui.item.identifier,function(data,status){
                $("#supplier-desc").html(data);
              });
            return false;
        }
    })
    .data( "autocomplete" )._renderItem = function( ul, item ) {
        return $( "<li>" )
            .data( "item.autocomplete", item )
            .append( "<a>" + item.identifier + " - " + item.name + "</a>" )
            .appendTo( ul );
    };
    $("#product_id").autocomplete({
        minLength: 0,
        source: function( request, response ) {
            var term = request.term;
            if ( term in cache_product ) {
                response( cache_product[ term ] );
                return;
            }
            $.getJSON("CRUDProductType?method=listJSON", request, function( data, status, xhr ) {
            	cache_product[ term ] = data;
                response( data );
            });
        },
        focus: function( event, ui ) {
            pid.val(ui.item.identifier);
            product_id.val( ui.item.identifier + " - " + ui.item.name );
            return false;
        },
        select: function( event, ui ) {
            pid.val(ui.item.identifier);
            product_id.val( ui.item.identifier + " - " + ui.item.name );
            product_desc.val(ui.item.name);
            product_price.val(ui.item.price);
            product_unit.val(ui.item.unit);
            return false;
        }
    })
    .data( "autocomplete" )._renderItem = function( ul, item ) {
        return $( "<li>" )
            .data( "item.autocomplete", item )
            .append( "<a>" + item.identifier + " - " + item.name + "</a>" )
            .appendTo( ul );
    };
    $( "#dialog-form" ).dialog({
        autoOpen: false,
        height: 300,
        width: 400,
        modal: true,
        buttons: {
            "Select supplier": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                bValid = bValid && checkRegexp( product_quanitity, /^([0-9])+$/, "Product quanitity field only allow : 0-9" );
                bValid = bValid && checkRegexp( product_total, /^([0-9])+$/, "Product total field only allow : 0-9" );

                if ( bValid ) {
                    max_no++;
                    $( "#tblOrderLine tbody" ).append("<tr id='tr-product-line-" + max_no + "'>" +
                      "<td style='width: 35px;'><div class='order-line-cell order-line-no'><input class='chk-dels' type='checkbox' value='"+ max_no +"'>&nbsp;</div></td>" +
                      "<td style='width: 80px;'><div class='order-line-cell'>" + getSafeValue(pid.val()) + "</div></td>" +
                      "<td><div class='order-line-cell'>" + getSafeValue(product_desc.val()) + "</div></td>" +
                      "<td style='width: 80px;'><div class='order-line-cell'>" + getSafeValue(product_unit.val()) + "</div></td>" +
                      "<td style='width: 80px;'><div class='order-line-cell'>" + getSafeValue(product_price.val()) + "</div></td>" +
                      "<td style='width: 80px;'><div class='order-line-cell'>" + getSafeValue(product_quanitity.val()) + "</div></td>" +
                      "<td style='width: 80px;'><div class='order-line-cell'>" + getSafeValue(product_total.val()) + "</div></td>" +
                   "</tr>");
                    order_total += parseInt(product_quanitity.val());
                    order_num += parseFloat(product_total.val())
                    $("#order-total").html(order_total.toFixed(2));
                    $("#order-num").html(order_num);
                    $( this ).dialog( "close" );
                }
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
        }
    });
});

function deleteSelected() {
    $.each($("input[type=checkbox]:checked"), function(i,v) {
        var del_element = "#tr-product-line-" + $(v).val();
        $(del_element).remove();
    });
}

function openDialog() {
    $('#dialog-form').dialog('open');
}
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
                <h3>${ktm:getText("nav.database")} ${ktm:getText("nav.transaction.receive_from_supplier")}</h3>
                </div>
                <div id="dvOrder" class="ym-wbox">
                  <div id="dvPurchaseOrder">
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
                                       <td class="cLabel"><label for="dateCreated">${ktm:getText("nav.transaction.receive_from_supplier.date_created")}:</label></td>
                                       <td><input style="width: 170px" type="text" name="dateCreated" id="dateCreated" class="text ui-widget-content ui-corner-all" /></td>
                                   </tr>
                                   <tr>
                                       <td class="cLabel"><label for="supplierId">${ktm:getText("nav.transaction.receive_from_supplier.supplier_id")}:</label></td>
                                       <td>
                                           <input style="width: 200px"  type="text" name="supplierId"  id="supplierId" class="text ui-widget-content ui-corner-all">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td class="cLabel"><label for="identifier">${ktm:getText("nav.transaction.receive_from_supplier.order_id")}:</label></td>
                                       <td><input style="width: 200px"  type="text" name="identifier" id="identifier" value="" class="text ui-widget-content ui-corner-all" /></td>
                                   </tr>
                               </table>
                           </fieldset>
                       </div>
                       </td>
                     </tr>
                      </table>
                  </div>
                  <div id="dvOrderLine">
                    <div id="dvOrderLineHeader">
                       <table>
                        <tr>
                          <th style="width: 35px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.no")}</span></div></th>
                          <th style="width: 80px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.product_id")}</span></div></th>
                          <th><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.product_desc")}</span></div></th>
                          <th style="width: 80px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.unit")}</span></div></th>
                          <th style="width: 80px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.cost")}</span></div></th>
                          <th style="width: 80px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.quanitity")}</span></div></th>
                          <th style="width: 80px;"><div class="gridHeader"><span>${ktm:getText("nav.transaction.receive_from_supplier.total")}</span></div></th>
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
                  <div id="dvOrderSummary">
                     <div style="width: 300px; float: left;">
                       <input type="button" value="Add product line" onclick="openDialog();">&nbsp;
                       <input type="button" value="delete selected" onclick="deleteSelected();">
                     </div>
                     <div style="width: 300px; float: right;">
                       <div id="order-total" class="order-summary-cell">0.00</div>
                       <div id="order-num" class="order-summary-cell">0</div>
                     </div>
                  </div>
                  <div class="ym-fbox-button">
                    <input type="submit" class="ym-button" value='${ktm:getText("page.btn.save")} ${ktm:getText("nav.transaction.receive_from_supplier")}' id="submit"
                      name="submit"
                    />
                    <input type="button" class="ym-button" value='${ktm:getText("page.btn.cancel")}' id="cancel"
                      name="cancel" onclick="goTo('CRUDPurchaseOrder?method=list')"
                    />
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
  
<div id="dialog-form" title="Select Supplier">
    <p class="validateTips"></p>
    <form>
    <fieldset>
        <table style="font-size: 0.85em;">
            <tr>
                <td style="text-align: right;"><label for="product_id">${ktm:getText("nav.transaction.receive_from_supplier.product_id")}: </label></td>
                <td>
                    <input type="hidden" name="_pid_" id="_pid_">
                    <input type="text" name="product_id" id="product_id" class="text ui-widget-content ui-corner-all" />
                </td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="product_desc">${ktm:getText("nav.transaction.receive_from_supplier.product_desc")}: </label></td>
                <td><input readonly type="text" name="product_desc" id="product_desc" value="" class="text ui-widget-content ui-corner-all" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="product_unit">${ktm:getText("nav.transaction.receive_from_supplier.unit")}: </label></td>
                <td><input readonly type="text" name="product_unit" id="product_unit" value="" class="text ui-widget-content ui-corner-all" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="product_cost">${ktm:getText("nav.transaction.receive_from_supplier.cost")}: </label></td>
                <td><input readonly type="text" name="product_cost" id="product_cost" value="" class="text ui-widget-content ui-corner-all" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="product_quanitity">${ktm:getText("nav.transaction.receive_from_supplier.quanitity")}: </label></td>
                <td><input type="text" name="product_quanitity" id="product_quanitity" value="" class="text ui-widget-content ui-corner-all" /></td>
            </tr>
            <tr>
                <td style="text-align: right;"><label for="product_total">${ktm:getText("nav.transaction.receive_from_supplier.total")}: </label></td>
                <td><input type="text" name="product_total" id="product_total" value="" class="text ui-widget-content ui-corner-all" /></td>
            </tr>
        </table>
    </fieldset>
    </form>
</div>
</body>
</html>

