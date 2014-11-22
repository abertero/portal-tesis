<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="saleOrderList.title"/></h1>

<p><spring:message code="saleOrderList.detail"/></p>

<div>
  <table class="table table-bordered">
    <tr>
      <th><spring:message code="saleOrderList.label.docNum"/></th>
      <th><spring:message code="saleOrderList.label.cardCode"/></th>
      <th><spring:message code="saleOrderList.label.cardName"/></th>
      <th><spring:message code="saleOrderList.label.name"/></th>
      <th><spring:message code="saleOrderList.label.uAuAno"/></th>
      <th><spring:message code="saleOrderList.label.uAuPatente"/></th>
      <th><spring:message code="saleOrderList.label.uAuChasis"/></th>
      <th><spring:message code="saleOrderList.label.color"/></th>
      <th><spring:message code="saleOrderList.label.action"/></th>
    </tr>
    <c:forEach items="${salesOrder}" var="sale">
      <tr>
        <td><c:out value="${sale.docNum}"/></td>
        <td><c:out value="${sale.cardCode}"/></td>
        <td><c:out value="${sale.cardName}"/></td>
        <td><c:out value="${sale.name}"/></td>
        <td><c:out value="${sale.uAuAno}"/></td>
        <td><c:out value="${sale.uAuPatente}"/></td>
        <td><c:out value="${sale.uAuChasis}"/></td>
        <td><c:out value="${sale.color}"/></td>
        <td>
          <a href="${ctx}/order/${sale.docNum}">
            <span class="glyphicon glyphicon-search"
                  title="<spring:message code="saleOrderList.label.viewOrder"/>"></span>
          </a>
          <a href="${ctx}/order/${sale.docNum}?canEdit=true">
            <span class="glyphicon glyphicon-edit"
                  title="<spring:message code="saleOrderList.label.editOrder"/>"></span>
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>