<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<div align="center">
<div align="left" style="width: 400px; margin: 50px 0px 100px 0px">
    <ul id="formerrors" class="errorMessage"></ul>
    <s:form id="frmLogin" action="/login.action" theme="simple" cssClass="yform ui-corner-all" method="post">
        <fieldset>
            <legend><s:text name="page.login"/></legend>
            <br/>
            
            <div class="type-text" style="margin-left: 95px">
                <label for="echo"><s:text name="page.login.user"/>: <span id="loginuserError"></span></label>
                <s:textfield 
                    id="loginuser" 
                    name="loginuser" 
                />
            </div>
            <div class="type-text" style="margin-left: 95px">
                <label for="echo"><s:text name="page.login.password"/>: <span id="loginpasswordError"></span></label>
                <s:password 
                    id="loginpassword" 
                    name="loginpassword" 
                />
            </div>
            <div class="type-button" align="center">
                <sj:submit 
                    button="true" 
                    indicator="indicator"
                    key="page.login.submit"
                />
                <img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>
            </div>
            <br/>
        </fieldset>
    </s:form>
</div>
</div>