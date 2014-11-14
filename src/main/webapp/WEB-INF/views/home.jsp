<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="login.title"/></h1>

<p><spring:message code="login.detail"/></p>

<form action="${ctx}/login" method="post">
  <div>
    <div>
      <label for="username"><spring:message code="login.username"/></label>

      <div><input type="text" name="username" id="username" value=""/></div>
    </div>
    <div>
      <label for="password"><spring:message code="login.password"/></label>

      <div><input type="password" name="password" id="password" value=""/></div>
    </div>
  </div>
  <input type="submit" value="<spring:message code="login.submit"/>"/>
</form>

<p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p>

</body>
</html>