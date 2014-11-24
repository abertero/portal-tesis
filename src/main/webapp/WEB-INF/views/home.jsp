<%@include file="general/taglibs.jspf" %>
<html>
<link rel='stylesheet' href='${ctx}/static/css/style.css'>
<body>
<div class="login-card">
<h1><img src="${ctx}/static/css/img/logo-autobahn.png"/></h1><br>
<form action="${ctx}/login" method="post">
  <div class="form-inline">
    <input type="text" class="col-xs-12 col-md-6 form-control" name="username" id="username"
           placeholder="<spring:message code="login.username"/>"/>
    <input type="password" class="col-xs-12 col-md-6 form-control" name="password" id="password"
           placeholder="<spring:message code="login.password"/>">
  </div>
  <input type="submit" name="login" class="login login-submit" value="<spring:message code="login.submit"/>"/>
</form>

<!--p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p-->
</div>
</body>
</html>
