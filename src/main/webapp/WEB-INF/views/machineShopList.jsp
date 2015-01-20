<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="machineShopList.title"/></h1>

<div>
  <table class="table table-bordered">
    <tr>
      <th><spring:message code="machineShopList.label.docNum"/></th>
      <th><spring:message code="machineShopList.label.docDate"/></th>
      <th><spring:message code="machineShopList.label.vehicle"/></th>
      <th><spring:message code="machineShopList.label.color"/></th>
      <th><spring:message code="machineShopList.label.uAuPatente"/></th>
      <th><spring:message code="machineShopList.label.uAuChasis"/></th>
      <th><spring:message code="machineShopList.label.location"/></th>
      <th><spring:message code="machineShopList.label.nameInst"/></th>
      <th><spring:message code="machineShopList.label.uAuNameVendor"/></th>
      <th><spring:message code="machineShopList.label.uAuBodDestino"/></th>
      <th><spring:message code="machineShopList.label.prjName"/></th>
      <th><spring:message code="machineShopList.label.status"/></th>
      <th><spring:message code="machineShopList.label.action"/></th>
    </tr>
    <c:if test="${empty machineShops}">
      <tr>
        <td colspan="12"><spring:message code="machineShopList.label.empty"/>&</td>
      </tr>
    </c:if>
    <c:forEach items="${machineShops}" var="machineShop">
      <tr>
        <td style="text-align: center"><c:out value="${machineShop.docNum}"/></td>
        <td><c:out value="${machineShop.docDate}"/></td>
        <td><c:out value="${machineShop.vehicle}"/></td>
        <td><c:out value="${machineShop.color}"/></td>
        <td><c:out value="${machineShop.uAuPatente}"/></td>
        <td><c:out value="${machineShop.uAuChasis}"/></td>
        <td><c:out value="${machineShop.location}"/></td>
        <td><c:out value="${machineShop.nameInst}"/></td>
        <td><c:out value="${machineShop.uAuNameVendor}"/></td>
        <td><c:out value="${machineShop.uAuBodDestino}"/></td>
        <td><c:out value="${machineShop.prjName}"/></td>
        <td><c:out value="${machineShop.status}"/></td>
        <td>
          <c:url var="viewSaleOrder" value="order/${machineShop.docNum}">
            <c:param name="backUrl"
                     value="${ctx}/machineShop?currentPage=${currentPage}&numberOfPages=${numberOfPages}"/>
          </c:url>
          <a href="${viewSaleOrder}"><span
              class="glyphicon glyphicon-search"
              title="<spring:message code="machineShopList.label.viewOrder"/>"></span>
            <c:if test="${not machineShop.finished}">
            <c:url var="editSaleOrder" value="order/${machineShop.docNum}">
              <c:param name="canEdit" value="true"/>
              <c:param name="backUrl"
                       value="${ctx}/machineShop?currentPage=${currentPage}&numberOfPages=${numberOfPages}"/>
            </c:url>
            <a href="${editSaleOrder}"><span
                class="glyphicon glyphicon-edit"
                title="<spring:message code="machineShopList.label.editOrder"/>"></span>
            </a>
            </c:if>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<div class="form-group">
  <div class="col-xs-12 col-sm-2 col-sm-offset-10">
    <c:if test="${currentPage > 0}">
      <a href="${ctx}/machineShop?currentPage=${currentPage-1}&numberOfPages=${numberOfPages}"><span
          class="glyphicon glyphicon-chevron-left" title="Prev."></span></a>
    </c:if>&nbsp;&nbsp;&nbsp;<c:out value="${currentPage+1}"/> / <c:out value="${numberOfPages}"/>&nbsp;&nbsp;&nbsp;
    <c:if test="${currentPage < numberOfPages-1}">
      <a href="${ctx}/machineShop?currentPage=${currentPage+1}&numberOfPages=${numberOfPages}"><span
          class="glyphicon glyphicon-chevron-right" title="Next."></span></a>
    </c:if>
    <c:choose>
      <c:when test="${not empty backUrl}"><a href="${backUrl}" class="btn btn-primary"><spring:message
          code="application.back"/></a></c:when>
      <c:otherwise><a href="${ctx}/menu" class="btn btn-primary"><spring:message
          code="application.back"/></a></c:otherwise>
    </c:choose>
  </div>
</div>
</body>
</html>