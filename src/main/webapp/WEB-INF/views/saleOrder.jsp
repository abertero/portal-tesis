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
      <td>&nbsp;</td>
      <th><spring:message code="saleOrder.label.color"/></th>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th><spring:message code="machineShopList.label.uAuNameVendor"/></th>
      <td><c:out value="${saleOrder.headerView.vendedor}"/></td>
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
        <td class="suma"><c:out value="${detail.totalComision}"/></td>
        <td><c:out value="${detail.whsCode}"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
so p
<c:choose>
  <c:when test="${canEdit}">
    <form action="${ctx}/saveOrder" method="post">
      <div class="form-horizontal">
        <input type="hidden" name="backUrl" value="${backUrl}"/>
        <input type="hidden" name="id" value="${saleOrder.id}"/>
        <input type="hidden" name="idDocNum" value="${saleOrder.idDocNum}"/>

        <div class="form-group">
          <label class="control-label col-xs-12 col-sm-2"><spring:message code="saleOrder.label.parking"/></label>

          <div class="col-xs-12 col-sm-9"><input type="text" class="form-control" name="parking"
                                                 value="${saleOrder.parking}"/></div>
        </div>
        <div class="form-group">
          <label class="control-label col-xs-12 col-sm-2"><spring:message code="saleOrder.label.technicians"/></label>

          <div class="col-xs-12 col-sm-9">
            <input type="text" class="form-control" id="technicianAdd" value=""/>
            <portal:technicianAutocomplete inputId="technicianAdd" callback="callback" resetOnEnter="true"/>
            <span id="technicians">
              <c:if test="${not empty saleOrder.technicians}">
                <ul>
                  <c:forEach items="${saleOrder.technicians}" var="tech">
                    <li class="liTech${tech.id}"><span><input type="hidden" name="idTechnicians" value="${tech.id}"/>
                      <c:out value="${tech.fullName}"/>
                      <a href="javascript:void(0);" class="deleteTechnician" data-id-technician="${tech.id}">
                        <span class="glyphicon glyphicon-remove"
                              title="<spring:message code="saleOrder.label.technicians.remove"/>"></span></a></span>
                    </li>
                  </c:forEach>
                </ul>
              </c:if>
            </span>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-xs-12 col-sm-2"><spring:message code="saleOrder.label.status"/></label>

          <div class="col-xs-12 col-sm-9">
            <select name="status.id" id="statusId" class="form-control">
              <option value=""><spring:message code="saleOrder.label.status.default"/></option>
              <c:forEach items="${status}" var="st">
                <option value="${st.id}" ${saleOrder.status.id == st.id ? 'selected="selected"':''}><c:out
                    value="${st.name}"/></option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group">
          <div class="col-xs-12 col-sm-2 col-sm-offset-10">
            <input type="submit" class="btn btn-primary"
                   value="<spring:message code="saleOrder.label.submit"/>"/>&nbsp;&nbsp;
            <c:choose>
              <c:when test="${not empty backUrl}"><a href="${backUrl}" class="btn btn-default"><spring:message
                  code="application.back"/></a></c:when>
              <c:otherwise><a href="${ctx}/order" class="btn btn-default"><spring:message code="application.back"/></a></c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </form>
  </c:when>
  <c:otherwise>
    <table class="table table-responsive">
      <tr>
        <th><spring:message code="saleOrder.label.parking"/></th>
        <td><c:out value="${saleOrder.parking}"/></td>
      </tr>
      <tr>
        <th><spring:message code="saleOrder.label.technicians"/></th>
        <td>
          <c:if test="${not empty saleOrder.technicians}">
            <ul>
              <c:forEach items="${saleOrder.technicians}" var="tech">
                <li class="liTech${tech.id}"><c:out value="${tech.fullName}"/></li>
              </c:forEach>
            </ul>
          </c:if>
        </td>
      </tr>
      <tr>
        <th><spring:message code="saleOrder.label.status"/></th>
        <td><c:out value="${saleOrder.status.name}"/></td>
      </tr>
    </table>

    <div class="form-group">
      <div class="col-xs-12 col-sm-2 col-sm-offset-10">
        <c:choose>
          <c:when test="${not empty backUrl}"><a href="${backUrl}" class="btn btn-default"><spring:message
              code="application.back"/></a></c:when>
          <c:otherwise><a href="${ctx}/order" class="btn btn-default"><spring:message
              code="application.back"/></a></c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:otherwise>
</c:choose>

<script type="text/javascript">
  $(function () {
    $("input[type=text]").bind("keydown", function (event) {
      if (event.keyCode == 13) {
        event.preventDefault();
        return false;
      }
    });
  });

  function callback(obj) {
    var html = '<li class="liTech' + obj.id + '"><span><input type="hidden" name="idTechnicians" value="' + obj.id + '"/>' + obj.code + ' - ' + obj.firstName + ' ' + obj.lastName
        + ' <a href="javascript:void(0);" class="deleteTechnician" data-id-technician="' + obj.id + '"><span class="glyphicon glyphicon-remove" title="<spring:message code="saleOrder.label.technicians.remove"/>"></span></a></span></li>'
    if ($("span#technicians ul li.liTech" + obj.id).length == 0) {
      if ($("span#technicians ul").length > 0) {
        $("span#technicians ul").append(html);
      } else {
        $("span#technicians").append("<ul>" + html + "</ul>");
      }
    }
  }
  function sumaTotalComision() {
    importe_total = 0
    $(".suma").each(
        function (index, value) {
          importe_total = importe_total + parseInt($(this).text());

        }
    );
    alert(importe_total);
    //$("#total").val(importe_total);
  }
</script>
</body>
</html>