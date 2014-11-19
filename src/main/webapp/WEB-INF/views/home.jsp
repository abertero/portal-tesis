<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<div class="login-card">
<h1><spring:message code="login.title"/></h1>

<p><spring:message code="login.detail"/></p>

<form action="${ctx}/login" method="post">
  <div class="form-inline">
    <input type="text" class="col-xs-12 col-md-6 form-control" name="username" id="username"
           placeholder="<spring:message code="login.username"/>"/>
    <input type="password" class="col-xs-12 col-md-6 form-control" name="password" id="password"
           placeholder="<spring:message code="login.password"/>">
  </div>
  <input type="submit" name="login" class="btn btn-primary" value="<spring:message code="login.submit"/>"/>
</form>

<p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p>
</div>
</body>
</html>
