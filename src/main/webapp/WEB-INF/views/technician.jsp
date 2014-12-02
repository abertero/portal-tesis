<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="technician.title"/></h1>
<div class="container" style="padding-top: 1em;">
<c:choose>
  <c:when test="${canEdit}">
    <form action="${ctx}/saveTechnician" method="post">
      <input type="hidden" name="id" value="${technician.id}"/>
      <div class="form-horizontal">

        <div class="form-group">
          <label class="col-xs-12 col-sm-2 control-label" for="firstName"><spring:message code="technician.codigo"/></label>

          <div class="col-xs-12 col-sm-9">
            <input type="text" class="form-control" name="code" id="code" value="${technician.code}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-12 col-sm-2 control-label" for="firstName"><spring:message code="technician.firstName"/></label>

          <div class="col-xs-12 col-sm-9">
            <input type="text" class="form-control" name="firstName" id="firstName" value="${technician.firstName}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-12 col-sm-2 control-label" for="lastName"><spring:message code="technician.lastName"/></label>

          <div class="col-xs-12 col-sm-9">
            <input type="text" class="form-control" name="lastName" id="lastName" value="${technician.lastName}"/>
          </div>
        </div>

      </div>
      <input type="submit" class="btn btn-primary" value="<spring:message code="technician.submit"/>"/>
      <a class="btn btn-primary" href="technician">Volver</a>
    </form>
  </c:when>
  <c:otherwise>
    <div style="width: 80%">
      <table class="table table-hover">
        <tr>
          <th><spring:message code="technician.firstName"/></th>
          <td><c:out value="${technician.firstName}"/></td>
        </tr>
        <tr>
          <th><spring:message code="technician.lastName"/></th>
          <td><c:out value="${technician.lastName}"/></td>
        </tr>
      </table>
      <input type="button" class="btn btn-primary" value="Volver" onclick="javascript:history.back()">
    </div>
  </c:otherwise>
</c:choose>
</div>
</body>
</html>