<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tags/ktm-libs.tld" prefix="ktm" %>
<jsp:useBean id="errorMessage" scope="request" class="java.util.ArrayList"/>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="${context.jspHeader}"></jsp:include>
<title>${ktm:getText("page.login")}</title>
</head>
<body>
  <div class="ym-wrapper">
    <div class="ym-wbox">
      <header>
        <h1>${ktm:getText("page.login")}</h1>
      </header>
      <form class="ym-form" action="login" method="post">
        <ktm:if>
          <ktm:condition>!${ktm:isEmptyCollection(errorMessage)}</ktm:condition>
          <ktm:then>
            <div class="ym-fbox-text ym-error">
              <p class="ym-message">Error: invalid value!</p>
            </div>
          </ktm:then>
        </ktm:if>
        <div class="ym-fbox-text">
          <label for="your-id">${ktm:getText("page.login.user")}<sup class="ym-required">*</sup></label>
          <input type="text" name="loginuser" id="loginuser" size="20"
            required="required" value="keng"
          />
        </div>
        <div class="ym-fbox-text">
          <label for="your-id">${ktm:getText("page.login.password")}<sup class="ym-required">*</sup></label>
          <input type="password" name="loginpassword" id="loginpassword"
            size="20" required="required" value="keng"
          />
        </div>
        <div class="ym-fbox-button">
          <input type="submit" class="ym-button" value='${ktm:getText("page.login.submit")}' id="submit"
            name="submit"
          />
        </div>
      </form>
      <jsp:include page="${context.jspFooter}"></jsp:include>
    </div>
  </div>
</body>
</html>
