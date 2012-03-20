<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://sarachai.com/tags-auth" prefix="auth" %>

<auth:tracUri/>
<auth:enforceAuthentication loginPage="/index.jsp"/>
<auth:isUserNotInRoles roles="Root,Admin,Staff,Auther,Employee">
    <auth:redirectPage page="/pages/unauthorized.jsp"/>
</auth:isUserNotInRoles>

<tiles:useAttribute id="key" name="key" classname="java.lang.String" />

<s:bean name="org.ktm.web.utils.HeaderUtils" var="hdr">
    <s:param name="choice">${key}</s:param>
</s:bean>

<s:set var="pageTitle" >${key}</s:set>

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
    <sj:head jqueryui="true" loadAtOnce="true" jquerytheme="ktm" customBasepath="themes" debug="true"/>
    <s:iterator value="#hdr.cssFiles" status="status">
        <link href="<s:property/>" type="text/css" rel="stylesheet" />
    </s:iterator>
    <s:iterator value="#hdr.jsFiles" status="status">
        <script src="<s:property/>" type="text/javascript" ></script>
    </s:iterator>
    <script type="text/javascript"> 
    $.struts2_jquery.require("themes/i18n/jquery.ui.datepicker-th.js"); 
    javascript: window.history.forward(1);
    </script> 
</head>
<body>
    <div class="page_margins">
        <div class="page">
            <div id="header" class="ui-widget-header">
                <div id="infobox">
                <auth:isUserLoggedIn>
                    <s:url action="logout" var="urlTag"/>
                    <ul id="navinfo">
                        <li><auth:ktmInfo method="user"/></li>
                        <li><auth:ktmInfo method="date"/></li>
                        <li><s:a href="%{urlTag}"><s:text name="page.logout"/></s:a></li>
                    </ul>
                </auth:isUserLoggedIn>
                <auth:isUserNotLoggedIn>
                    <ul id="navinfo">
                        <li><s:a href="#">About us</s:a>
                        <li><auth:ktmInfo method="date"/></li>
                    </ul>
                </auth:isUserNotLoggedIn>
                </div>
                <div id="headline">
                    <tiles:insertAttribute name="header" />
                </div>
            </div>
            <tiles:insertAttribute name="nav" />
            <div id="main">
              <div style="padding: 10px 10px 0 15px;">
                <tiles:insertAttribute name="main" />
              </div>
            </div>
            <div id="footer">
                <tiles:insertAttribute name="footer" />
            </div>
        </div>
    </div>
</body>
</html>
