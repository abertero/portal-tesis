<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="saleOrder.title"/></h1>

<div>
  <table class="table" id="saleOrderTable">
    <tr>
      <th><spring:message code="saleOrder.label.docNum"/></th>
      <td><c:out value="${saleOrder.headerView.docNum}"/></td>
      <th><spring:message code="saleOrder.label.vehicle"/></th>
      <td>&nbsp;<%--<c:out value="${saleOrder.vehicle.name}"/>--%></td>
    </tr>
    <tr>
      <th><spring:message code="saleOrder.label.cardCode"/></th>
      <td><c:out value="${saleOrder.headerView.cardCode}"/></td>
      <th><spring:message code="saleOrder.label.uAuAno"/></th>
      <td><c:out value="${saleOrder.headerView.uAuAno}"/></td>
    </tr>
    <tr>
      <th><spring:message code="saleOrder.label.cardName"/></th>
      <td><c:out value="${saleOrder.headerView.cardName}"/></td>
      <th><spring:message code="saleOrder.label.uAuPatente"/></th>
      <td><c:out value="${saleOrder.headerView.uAuPatente}"/></td>
    </tr>
    <tr>
      <th><spring:message code="saleOrder.label.docDate"/></th>
      <td><c:out value="${saleOrder.headerView.docDate}"/></td>
      <th><spring:message code="saleOrder.label.uAuChasis"/></th>
      <td><c:out value="${saleOrder.headerView.uAuChasis}"/></td>
    </tr>
    <tr>
      <th><spring:message code="saleOrder.label.color"/></th>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
</div>
<div>
  <table class="table table-bordered">
    <tr>
      <th><spring:message code="saleOrder.label.itemCode"/></th>
      <th><spring:message code="saleOrder.label.description"/></th>
      <th><spring:message code="saleOrder.label.quantity"/></th>
      <th><spring:message code="saleOrder.label.uAuComision"/></th>
      <th><spring:message code="saleOrder.label.totalComision"/></th>
      <th><spring:message code="saleOrder.label.whsCode"/></th>
    </tr>
    <c:if test="${empty saleOrder.headerLines}">
      <tr>
        <td colspan="6"><spring:message code="saleOrder.label.details.empty"/></td>
      </tr>
    </c:if>
    <c:forEach items="${saleOrder.headerLines}" var="detail">
      <tr>
        <td><c:out value="${detail.itemCode}"/></td>
        <td><c:out value="${detail.description}"/></td>
        <td><c:out value="${detail.quantity}"/></td>
        <td><c:out value="${detail.uAuComision}"/></td>
        <td><c:out value="${detail.totalComision}"/></td>
        <td><c:out value="${detail.whsCode}"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>