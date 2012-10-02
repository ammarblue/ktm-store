<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
            <form class="ym-searchform">
              <input class="ym-searchfield" type="search" placeholder='${ktm:getText("page.btn.search")}...' />
              <input class="ym-searchbutton" type="submit" value='${ktm:getText("page.btn.search")}' />
            </form>