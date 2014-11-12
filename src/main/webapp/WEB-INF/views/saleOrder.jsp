<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="saleOrder.title"/></h1>

<div>
  <div>
    <label><spring:message code="saleOrder.label.docNumber"/></label>

    <div><c:out value="${saleOrder.docNumber}"/></div>
    <label><spring:message code="saleOrder.label.vehicle"/></label>

    <div><c:out value="${saleOrder.vehicle.name}"/></div>
  </div>
  <div>
    <label><spring:message code="saleOrder.label.cardCode"/></label>

    <div><c:out value="${saleOrder.cardCode}"/></div>
    <label><spring:message code="saleOrder.label.uAuAno"/></label>

    <div><c:out value="${saleOrder.uAuAno}"/></div>
  </div>
  <div>
    <label><spring:message code="saleOrder.label.cardName"/></label>

    <div><c:out value="${saleOrder.cardName}"/></div>
    <label><spring:message code="saleOrder.label.uAuPatente"/></label>

    <div><c:out value="${saleOrder.uAuPatente}"/></div>
  </div>
</div>

</body>
</html>