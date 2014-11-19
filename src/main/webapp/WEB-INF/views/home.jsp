<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<div class="login-card">
<h1><spring:message code="login.title"/></h1>

<p><spring:message code="login.detail"/></p>

<form action="${ctx}/login" method="post">
    <input type="text" name="username" id="username" placeholder="<spring:message code="login.username"/>"/>
    <input type="password" name="password" id="password" placeholder="<spring:message code="login.password"/>">
    <input type="submit" name="login" class="login login-submit" value="<spring:message code="login.submit"/>"/>
</form>

<p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p>
</div>
</body>
</html>
