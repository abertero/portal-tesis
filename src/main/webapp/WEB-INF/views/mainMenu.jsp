<%@include file="general/taglibs.jspf" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel='stylesheet' href='${ctx}/static/css/menu/css/demo.css'>
<link rel='stylesheet' href='${ctx}/static/css/menu/css/style2.css'>
<link href='http://fonts.googleapis.com/css?family=Terminal+Dosis' rel='stylesheet' type='text/css' />

<body>
<%@include file="general/loggout.jspf" %>
<!--
<h1></h1>

<p></p>

<ul>
  <li><a href="${ctx}/user"><spring:message code="menu.profileManager.name"/></a></li>
  <li><a href="${ctx}/technician"><spring:message code="menu.technicianManager.name"/></a></li>
  <li><a href="${ctx}/order"><spring:message code="menu.garageList.name"/></a></li>
  <li><a href="${ctx}/parking"><spring:message code="menu.parkingInfo.name"/></a></li>
  <li><a href="${ctx}/order"><spring:message code="menu.saleOrderList.name"/></a></li>
</ul>
-->
<!-- INICIO -->
<div class="container">
  <h1><spring:message code="menu.title"/><span><spring:message code="menu.detail"/></span></h1>
  <div class="content">

    <ul class="ca-menu">
      <li>
        <a href="${ctx}/user">
          <span class="ca-icon">U</span>
          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.profileManager.name"/></h2>
            <h3 class="ca-sub">Descripci&oacute;n de opci&oacute;n</h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/technician">
          <span class="ca-icon">S</span>
          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.technicianManager.name"/></h2>
            <h3 class="ca-sub">Descripci&oacute;n de opci&oacute;n</h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/order">
          <span class="ca-icon">F</span>
          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.garageList.name"/></h2>
            <h3 class="ca-sub">Descripci&oacute;n de opci&oacute;n</h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/parking">
          <span class="ca-icon">F</span>
          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.parkingInfo.name"/></h2>
            <h3 class="ca-sub">Descripci&oacute;n de opci&oacute;n</h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/order">
          <span class="ca-icon">L</span>
          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.saleOrderList.name"/></h2>
            <h3 class="ca-sub">Descripci&oacute;n de opci&oacute;n</h3>
          </div>
        </a>
      </li>
    </ul>
  </div><!-- content -->
</div>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
</body>
</html>