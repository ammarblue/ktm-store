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
    height: 70px;
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
    $("#dateCreated").datepicker({
        showOn: "button",
        buttonImage: "images/calendar.gif",
        buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
    var cache = {};
    $( "#supplierId" ).autocomplete({
        minLength: 0,
        source: function( request, response ) {
            var term = request.term;
            if ( term in cache ) {
                response( cache[ term ] );
                return;
            }
            $.getJSON( "CRUDSupplier?method=listJSON", request, function( data, status, xhr ) {
                cache[ term ] = data;
                response( data );
            });
        },
        focus: function( event, ui ) {
            $( "#supplierId" ).val( ui.item.identifier + " - " + ui.item.name );
            $( "#supplier-desc" ).html( ui.item.name );
            return false;
        },
        select: function( event, ui ) {
            $( "#supplierId" ).val( ui.item.identifier + " - " + ui.item.name );
            $( "#supplier-desc" ).html( ui.item.name );
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
        width: 350,
        modal: true,
        buttons: {
            "Select supplier": function() {
                var bValid = true;
                allFields.removeClass( "ui-state-error" );

                bValid = bValid && checkLength( name, "username", 3, 16 );
                bValid = bValid && checkLength( email, "email", 6, 80 );
                bValid = bValid && checkLength( password, "password", 5, 16 );

                bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
                // From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
                bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
                bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

                if ( bValid ) {
                    $( "#users tbody" ).append( "<tr>" +
                        "<td>" + name.val() + "</td>" + 
                        "<td>" + email.val() + "</td>" + 
                        "<td>" + password.val() + "</td>" +
                    "</tr>" ); 
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
                       <table>
                        <tr>
                          <td style="width: 35px;"><div class="order-line-cell order-line-no" class="order-line-cell"><input type="checkbox">&nbsp;aa</div></td>
                          <td style="width: 80px;"><div class="order-line-cell">aa</div></td>
                          <td><div class="order-line-cell">aa</div></td>
                          <td style="width: 80px;"><div class="order-line-cell">aa</div></td>
                          <td style="width: 80px;"><div class="order-line-cell">aa</div></td>
                          <td style="width: 80px;"><div class="order-line-cell">aa</div></td>
                          <td style="width: 80px;"><div class="order-line-cell">aa</div></td>
                        </tr>
                      </table>
                    </div>
                  </div>
                  <div id="dvOrderSummary">
                     <div style="width: 300px; float: left;">
                       <input type="button" value="Add product line">&nbsp;<input type="button" value="delete selected">
                     </div>
                     <div style="width: 300px; float: right;">
                       <div class="order-summary-cell">aa</div>
                       <div class="order-summary-cell">bb</div>
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
    <form>
    <fieldset>
        <table style="font-size: 0.85em;">
            <tr>
                <td><label for="byId">Find by id</label></td>
                <td><input type="text" name="byId" id="byId" class="text ui-widget-content ui-corner-all" /></td>
                <td><input type="button" value="search"></td>
            </tr>
            <tr>
                <td><label for="byName">Find by name</label></td>
                <td><input type="text" name="byName" id="byName" value="" class="text ui-widget-content ui-corner-all" /></td>
                <td><input type="button" value="search"></td>
            </tr>
        </table>
    </fieldset>
    </form>
</div>
</body>
</html>

