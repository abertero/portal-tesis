<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="login.title"/></h1>

<p><spring:message code="login.detail"/></p>

<c:choose>
  <c:if test="${canEdit}">
    <form action="${ctx}/registerUser" method="post">
      <div>
        <div>
          <label for="username"><spring:message code="register.username"/></label>

          <div><input type="text" name="username" id="username" value="${user.username}"/></div>
        </div>
        <div>
          <label for="password"><spring:message code="register.password"/></label>

          <div><input type="password" name="password" id="password" value=""/></div>
        </div>
        <div>
          <label for="repeatPassword"><spring:message code="register.repeatPassword"/></label>

          <div><input type="password" name="repeatPassword" id="repeatPassword" value=""/></div>
        </div>
        <div>
          <label for="firstName"><spring:message code="register.firstName"/></label>

          <div><input type="text" name="firstName" id="firstName" value="${user.firstName}"/></div>
        </div>
        <div>
          <label for="lastName"><spring:message code="register.lastName"/></label>

          <div><input type="text" name="lastName" id="lastName" value="${user.lastName}"/></div>
        </div>
        <div>
          <label for="email"><spring:message code="register.email"/></label>

          <div><input type="text" name="email" id="email" value="${user.email}"/></div>
        </div>
        <div>
          <label for="phone"><spring:message code="register.phone"/></label>

          <div><input type="text" name="phone" id="phone" value="${user.phone}"/></div>
        </div>
        <div>
          <label for="address"><spring:message code="register.address"/></label>

          <div><input type="text" name="address" id="address" value="${user.address}"/></div>
        </div>
      </div>
      <input type="submit" value="<spring:message code="register.submit"/>"/>
    </form>
  </c:if>
  <c:otherwise>
    <table>
      <tr>
        <th><spring:message code="register.username"/></th>
        <td><c:out value="${user.username}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.password"/></th>
        <td><c:out value="${user.password}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.firstName"/></th>
        <td><c:out value="${user.firstName}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.lastName"/></th>
        <td><c:out value="${user.lastName}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.email"/></th>
        <td><c:out value="${user.email}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.phone"/></th>
        <td><c:out value="${user.phone}"/></td>
      </tr>
      <tr>
        <th><spring:message code="register.address"/></th>
        <td><c:out value="${user.address}"/></td>
      </tr>
    </table>
  </c:otherwise>
</c:choose>

</body>
</html>