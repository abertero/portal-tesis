<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="parkingLotList.title"/></h1>

<div>
  <table class="table table-bordered">
    <tr>
      <th><spring:message code="parkingLotList.label.docNum"/></th>
      <th><spring:message code="parkingLotList.label.docDate"/></th>
      <th><spring:message code="parkingLotList.label.vehicle"/></th>
      <th><spring:message code="parkingLotList.label.color"/></th>
      <th><spring:message code="parkingLotList.label.uAuPatente"/></th>
      <th><spring:message code="parkingLotList.label.uAuChasis"/></th>
      <th><spring:message code="parkingLotList.label.location"/></th>
      <th><spring:message code="parkingLotList.label.nameInst"/></th>
      <th><spring:message code="parkingLotList.label.uAuNameVendor"/></th>
      <th><spring:message code="parkingLotList.label.uAuBodDestino"/></th>
      <th><spring:message code="parkingLotList.label.prjName"/></th>
      <th><spring:message code="parkingLotList.label.action"/></th>
    </tr>
    <c:if test="${empty parkingLots}">
      <tr>
        <td colspan="12"><spring:message code="parkingLotList.label.empty"/>&</td>
      </tr>
    </c:if>
    <c:forEach items="${parkingLots}" var="parkingLot">
      <tr>
        <td><c:out value="${parkingLot.docNum}"/></td>
        <td><c:out value="${parkingLot.docDate}"/></td>
        <td><c:out value="${parkingLot.vehicle}"/></td>
        <td><c:out value="${parkingLot.color}"/></td>
        <td><c:out value="${parkingLot.uAuPatente}"/></td>
        <td><c:out value="${parkingLot.uAuChasis}"/></td>
        <td><c:out value="${parkingLot.location}"/></td>
        <td><c:out value="${parkingLot.nameInst}"/></td>
        <td><c:out value="${parkingLot.uAuNameVendor}"/></td>
        <td><c:out value="${parkingLot.uAuBodDestino}"/></td>
        <td><c:out value="${parkingLot.prjName}"/></td>
        <td>
          <c:url var="viewSaleOrder" value="order/${parkingLot.docNum}">
            <c:param name="backUrl" value="${ctx}/parking?currentPage=${currentPage+1}&numberOfPages=${numberOfPages}"/>
          </c:url>
          <a href="${viewSaleOrder}"><span
              class="glyphicon glyphicon-search"
              title="<spring:message code="parkingLotList.label.viewOrder"/>"></span>
          </a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<div class="form-group">
  <div class="col-xs-12 col-sm-2 col-sm-offset-10">
    <c:if test="${currentPage > 0}">
      <a href="${ctx}/parking?currentPage=${currentPage-1}&numberOfPages=${numberOfPages}"><span
          class="glyphicon glyphicon-chevron-left" title="Prev."></span></a>
    </c:if>&nbsp;&nbsp;&nbsp;<c:out value="${currentPage+1}"/> / <c:out value="${numberOfPages}"/>&nbsp;&nbsp;&nbsp;
    <c:if test="${currentPage < numberOfPages-1}">
      <a href="${ctx}/parking?currentPage=${currentPage+1}&numberOfPages=${numberOfPages}"><span
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