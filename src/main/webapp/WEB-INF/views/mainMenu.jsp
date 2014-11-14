<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<%@include file="general/loggout.jspf" %>

<h1><spring:message code="menu.title"/></h1>

<p><spring:message code="menu.detail"/></p>

<ul>
  <li><a href="${ctx}/manager/profile"><spring:message code="menu.profileManager.name"/></a></li>
  <li><a href="${ctx}/manager/technician"><spring:message code="menu.technicianManager.name"/></a></li>
  <li><a href="${ctx}/garage"><spring:message code="menu.garageList.name"/></a></li>
  <li><a href="${ctx}/parking"><spring:message code="menu.parkingInfo.name"/></a></li>
  <li><a href="${ctx}/order"><spring:message code="menu.saleOrderList.name"/></a></li>
</ul>

</body>
</html>