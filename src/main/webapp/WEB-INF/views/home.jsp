<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="login.title"/></h1>

<p><spring:message code="login.detail"/></p>

<form action="${ctx}/login" method="post">
    <input type="text" name="username" id="username" placeholder="Username">
    <input type="password" name="password" id="password" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="<spring:message code="login.submit"/>"/>
</form>

<p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p>

</body>
</html>
