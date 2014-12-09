<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="saleOrderList.title"/></h1>

<p><spring:message code="saleOrderList.detail"/></p>

<div>
  <table class="table table-hover">
    <tr>
      <th><spring:message code="saleOrderList.label.docNum"/></th>
      <th><spring:message code="saleOrderList.label.docDate"/></th>
      <th><spring:message code="saleOrderList.label.cardCode"/></th>
      <th><spring:message code="saleOrderList.label.cardName"/></th>
      <th><spring:message code="saleOrderList.label.name"/></th>
      <th><spring:message code="saleOrderList.label.uAuAno"/></th>
      <th><spring:message code="saleOrderList.label.uAuPatente"/></th>
      <th><spring:message code="saleOrderList.label.uAuChasis"/></th>
      <th><spring:message code="saleOrderList.label.color"/></th>
      <th><spring:message code="saleOrderList.label.action"/></th>
    </tr>
    <c:if test="${empty salesOrder}">
      <tr>
        <td colspan="9"><spring:message code="saleOrderList.empty"/></td>
      </tr>
    </c:if>
    <c:forEach items="${salesOrder}" var="sale">
      <tr>
        <td><c:out value="${sale.docNum}"/></td>
        <td><c:out value="${sale.docDate}"/></td>
        <td><c:out value="${sale.cardCode}"/></td>
        <td><c:out value="${sale.cardName}"/></td>
        <td><c:out value="${sale.name}"/></td>
        <td><c:out value="${sale.uAuAno}"/></td>
        <td><c:out value="${sale.uAuPatente}"/></td>
        <td><c:out value="${sale.uAuChasis}"/></td>
        <td><c:out value="${sale.color}"/></td>
        <td>
          <c:url var="viewSaleOrder" value="order/${sale.docNum}">
            <c:param name="backUrl" value="${ctx}/order?currentPage=${currentPage}&numberOfPages=${numberOfPages}"/>
          </c:url>
          <c:url var="editSaleOrder" value="order/${sale.docNum}">
            <c:param name="backUrl" value="${ctx}/order?currentPage=${currentPage}&numberOfPages=${numberOfPages}"/>
            <c:param name="canEdit" value="true"/>
          </c:url>
          <a href="${viewSaleOrder}">
            <span class="glyphicon glyphicon-search"
                  title="<spring:message code="saleOrderList.label.viewOrder"/>"></span>
          </a>&nbsp;|&nbsp;
          <a href="${editSaleOrder}">
            <span class="glyphicon glyphicon-edit"
                  title="<spring:message code="saleOrderList.label.editOrder"/>"></span>
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<div class="form-group">
  <div class="col-xs-12 col-sm-2 col-sm-offset-10">
    <c:if test="${currentPage > 0}">
      <a href="${ctx}/order?currentPage=${currentPage-1}&numberOfPages=${numberOfPages}"><span
          class="glyphicon glyphicon-chevron-left" title="Prev."></span></a>
    </c:if>&nbsp;&nbsp;&nbsp;<c:out value="${currentPage+1}"/> / <c:out value="${numberOfPages}"/>&nbsp;&nbsp;&nbsp;
    <c:if test="${currentPage < numberOfPages-1}">
      <a href="${ctx}/order?currentPage=${currentPage+1}&numberOfPages=${numberOfPages}"><span
          class="glyphicon glyphicon-chevron-right" title="Next."></span></a>
    </c:if>
  </div>
  <div class="col-xs-12 col-sm-2 col-sm-offset-10">
    <c:choose>
      <c:when test="${not empty backUrl}"><a href="${backUrl}" class="btn btn-default"><spring:message
          code="application.back"/></a></c:when>
      <c:otherwise><a href="${ctx}/menu" class="btn btn-default"><spring:message
          code="application.back"/></a></c:otherwise>
    </c:choose>
  </div>
</div>

</body>
</html>