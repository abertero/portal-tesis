<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="saleOrderList.title"/></h1>

<p><spring:message code="saleOrderList.detail"/></p>

<div>
  <table>
    <tr>
      <th><spring:message code="saleOrderList.label.docNum"/></th>
      <th><spring:message code="saleOrderList.label.action"/></th>
    </tr>
    <c:forEach items="${salesOrder}" var="sale">
      <tr>
        <td><c:out value="${sale.docNum}"/></td>
        <td><a href="${ctx}/order/${sale.altKey}"><spring:message code="saleOrderList.label.viewOrder"/></a></td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>