<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="userList.title"/></h1>

<div class="container" style="padding-top: 1em;">
  <table class="table table-hover">
    <thead>
    <tr>
      <th><spring:message code="userList.firstName"/></th>
      <th><spring:message code="userList.lastName"/></th>
      <th><spring:message code="userList.email"/></th>
      <th><spring:message code="userList.username"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="users">
      <tr>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.username}"/></td>
      </tr>

    </c:forEach>

    </tbody>
  </table>
</div>
</body>
</html>
