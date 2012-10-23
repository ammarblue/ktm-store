<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<script>
var _url = "";

$(function() {
    $("#side-menu").menu();
    $( "#dialog-confirm" ).dialog({
        autoOpen: false,
        resizable: false,
        height:180,
        modal: true,
        buttons: {
            '${ktm:getText("page.btn.delete")}': function() {
                goTo(_url);
                $( this ).dialog( "close" );
            },
            '${ktm:getText("page.btn.cancel")}': function() {
                $( this ).dialog( "close" );
            }
        }
    });
    $("#btnDelete").click(function() {
    });
});
function goToConfirm(url) {
    _url = url;
    $( "#dialog-confirm" ).dialog("open"); 
}
</script>