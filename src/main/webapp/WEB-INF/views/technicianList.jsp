<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>
<h1><spring:message code="technician.title"/></h1>

<div class="container" style="padding-top: 1em;">
  <table class="table table-striped">
    <thead>
    <tr>
      <th><spring:message code="technician.codigo"/></th>
        <th><spring:message code="technician.firstName"/></th>
      <th><spring:message code="technician.lastName"/></th>
      <th><spring:message code="technician.accion"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="technician" items="${technicians}">
      <tr>

        <td><c:out value="${technician.code}"/></td>
        <td><c:out value="${technician.firstName}"/></td>
        <td><c:out value="${technician.lastName}"/></td>
        <!-- <span class="glyphicon glyphicon-edit" title="<spring:message code="userList.action.edit"/>"></span>-->
        <td><a href="technician/<c:out value="${technician.altKey}"/>?canEdit=true"><span class="glyphicon glyphicon-edit" title="Editar"></a>&nbsp;|&nbsp;
            <a href="technicianDelete/<c:out value="${technician.altKey}"/>"><span class="glyphicon glyphicon-remove" title="Eliminar"s></span></a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <c:url var="registerUrl" value="/registerTechnician">
    <c:param name="backUrl" value="${ctx}/technician"/>
  </c:url>
  <div style="float:right">
    <a class="btn btn-primary" href="${registerUrl}"><spring:message code="technician.create"/></a>
    <a class="btn btn-primary" href="menu">Volver</a>
  </div>
</div>
</body>
</html>
