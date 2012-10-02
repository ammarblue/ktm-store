<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="bean" scope="session" class="org.ktm.stock.bean.MainBean"></jsp:useBean>
<ktm:enforceAuthentication loginPage="/login"/>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("app.title")}</title>
</head>
<body>
  <div class="ym-wrapper">
    <div class="ym-wbox">
      <header><h1>${ktm:getText("app.title")}</h1></header>
	    <ktm:navMenu />
	    <div id="main">
	      <div class="ym-column linearize-level-1">
	        <div class="ym-col1">
            <div class="ym-cbox">
              <section class="box info">
                <jsp:include page="${bean.pageContent}"></jsp:include>
              </section>
            </div>
	        </div>
	        <aside class="ym-col3">
            <div class="ym-cbox">
              <ktm:navSubMenu/>
            </div>
          </aside>
	      </div>
	    </div>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>
</body>
</html>
