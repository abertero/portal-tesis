<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="saleOrder.title"/></h1>

<c:choose>
  <c:when test="${canEdit}">
    <form action="${ctx}/saveSaleOrder" method="post">
      <div>
        <div class="form-group">
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.docNum"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="docNum" value="${saleOrder.docNum}"/>
          </div>
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.vehicle"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="name" value="${saleOrder.vehicle.name}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.cardCode"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="cardCode" value="${saleOrder.cardCode}"/>
          </div>
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.uAuAno"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="uAuAno" value="${saleOrder.uAuAno}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.cardName"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="cardName" value="${saleOrder.cardName}"/>
          </div>
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.uAuPatente"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="uAuPatente" value="${saleOrder.uAuPatente}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.docDate"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="docDate" value="${saleOrder.docDate}"/>
          </div>
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.uAuChasis"/></label>

          <div class="col-xs-6 col-sm-4">
            <input type="text" class="form-control" name="uAuChasis" value="${saleOrder.uAuChasis}"/>
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-6 col-sm-2 control-label"><spring:message code="saleOrder.label.color"/></label>

          <div class="col-xs-6 col-sm-4">
            <select name="color" id="color" class="form-control">
              <option value=""><spring:message code="saleOrder.label.color.default"/></option>
            </select>
          </div>
        </div>
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
          <c:if test="${empty saleOrder.details}">
            <tr>
              <td colspan="6"><spring:message code="saleOrder.label.details.empty"/></td>
            </tr>
          </c:if>
          <c:forEach items="${saleOrder.details}" var="detail">
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
    </form>
  </c:when>
  <c:otherwise>
  </c:otherwise>
</c:choose>

</body>
</html>