<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="technician.title"/></h1>

<div class="container" style="padding-top: 1em;">
  <table class="table table-striped">
    <thead>
    <tr>
      <th><spring:message code="technician.firstName"/></th>
      <th><spring:message code="technician.lastName"/></th>
      <th><spring:message code="technician.accion"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="technician" items="${technicians}">
      <tr>

        <td><c:out value="${technician.firstName}"/></td>
        <td><c:out value="${technician.lastName}"/></td>
        <td><a href="manager/technician/<c:out value="${technician.altKey}"/>">Editar</a>&nbsp;|&nbsp;<a href="#">Eliminar</a> </td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

  <a class="btn btn-primary" href="registerTechnician">Crear</a>
  <a class="btn btn-primary" href="menu">Volver</a>
</div>
</body>
</html>
