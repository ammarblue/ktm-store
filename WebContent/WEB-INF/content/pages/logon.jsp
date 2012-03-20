<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://sarachai.com/tags-auth" prefix="auth" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="text/css" http-equiv="Content-Style-Type">
    <meta content="no-cache" http-equiv="pragma">
    <meta content="no-cache" http-equiv="cache-control">
    <meta content="0" http-equiv="expires">
    <meta content="struts2, jquery, jquery-ui, plugin, sarachai, watcharin" http-equiv="keywords">
    <meta content="Mr.Watcharin Sarachai Software" http-equiv="description">
    <title><s:text name="%{#hdr.title}"/></title>
    <!--[if lte IE 7]>
    <link href="./yaml/core/iehacks.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <sj:head jqueryui="true" loadAtOnce="true" jquerytheme="ktm" customBasepath="/KTMStore/themes" debug="true"/>
    <link href="/KTMStore/style/layout.css" type="text/css" rel="stylesheet" />
    <link href="/KTMStore/style/nav.css" type="text/css" rel="stylesheet" />
</head>
<body>
    <div class="page_margins">
        <div class="page">
            <div id="header" class="ui-widget-header">
                <div id="infobox">
                    <div>
                        <ul id="navinfo">
                            <li><s:a href="#">About us</s:a>
                            <li><auth:ktmInfo method="date"/></li>
                        </ul>
                    </div>
                </div>
                <div id="headline">
                    <h1 class="ui-state-default" style="background: none; border: none;"><s:text name="app.title"/></h1>
                    <h4 class="ui-state-default" style="background: none; border: none;">Version <s:text name="ktm.version"></s:text></h4>
                </div>
            </div>
            <div id="main">
              <div style="padding: 10px 10px 0 15px;">
                <div align="center">
                <div align="left" style="width: 400px; margin: 50px 0px 100px 0px">
                    <s:actionerror />
                    <s:fielderror />
                    <s:form id="frmLogin" action="/login.action" theme="simple" cssClass="yform ui-corner-all" method="post" validate="true">
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
              </div>
            </div>
            <div id="footer">
                <p align="center">&copy; sarachai.com 2012 &ndash; Layout based on <a href="http://www.yaml.de">YAML</a></p>
            </div>
        </div>
    </div>
</body>
</html>
