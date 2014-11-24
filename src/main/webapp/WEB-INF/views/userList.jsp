<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="userList.title"/></h1>

<div class="container" style="padding-top: 1em;">
  <table class="table table-striped">
    <thead>
    <tr>
      <th><spring:message code="userList.username"/></th>
      <th><spring:message code="userList.firstName"/></th>
      <th><spring:message code="userList.lastName"/></th>
      <th><spring:message code="userList.email"/></th>
      <th><spring:message code="userList.action"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td><c:out value="${user.username}"/></td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td>
          <a href="user/${user.altKey}"><span class="glyphicon glyphicon-search"
                                              title="<spring:message code="userList.action.view"/>"></span></a>&nbsp;|&nbsp;
          <a href="user/${user.altKey}?canEdit=true"><span class="glyphicon glyphicon-edit"
                                                           title="<spring:message code="userList.action.edit"/>"></span></a>&nbsp;|&nbsp;
          <a href="#"><span class="glyphicon glyphicon-remove"
                            title="<spring:message code="userList.action.delete"/>"></span></a>
        </td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

  <a class="btn btn-primary" href="register">Crear</a>
  <a class="btn btn-primary" href="menu">Volver</a>
</div>
</body>
</html>
