<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<s:bean name="org.ktm.web.utils.NavUtils" var="nav">
    <s:param name="currentMenu">${pageTitle}</s:param>
</s:bean>

<nav id="nav">
<div class="ktm-wbox">
    <ul id="topnav">
        <s:if test="#nav.links.size() > 0">
          <s:iterator var="entry" value="#nav.links" status="st">
            <li class="ui-widget-header">
                <!-- <a href="<s:url action="%{#entry.key}" />"><s:text name="%{#entry.value}"/></a> -->
                  <s:text name="%{#entry.value}"/>
                  <ul class="subnav" style="display: none;">
                    <s:bean name="org.ktm.web.utils.NavUtils" var="xxx">
                        <s:param name="currentMenu"><s:text name="%{#entry.key}"/></s:param>
                    </s:bean>
                    <s:if test="#xxx.menus.size() > 0">
                      <s:iterator var="entry" value="#xxx.menus">
                          <li>
                              <a href="<s:url action="%{#entry.key}" />"><s:text name="%{#entry.value}"/></a>
                          </li>
                      </s:iterator>
                    </s:if>
                  </ul>
            </li>
          </s:iterator>
        </s:if>
    </ul>
</div>
</nav>