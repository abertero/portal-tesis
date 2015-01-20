<%@include file="general/taglibs.jspf" %>
<html>
<head>
  <title><spring:message code="application.title"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel='stylesheet' href='${ctx}/static/css/menu/css/demo.css'>
  <link rel='stylesheet' href='${ctx}/static/css/menu/css/style2.css'>
  <link href='http://fonts.googleapis.com/css?family=Terminal+Dosis' rel='stylesheet' type='text/css'/>
</head>
<body>
<%@include file="general/loggout.jspf" %>
<!-- INICIO -->
<div class="container">
  <h1><spring:message code="menu.title"/><span><spring:message code="menu.detail"/></span></h1>

  <script>
    var roles = [
      <c:forEach var="rolUser" items="${usrRol.roles}">
      { 'name' : '${rolUser.name}', 'id' : '${rolUser.id}' },
      </c:forEach>
    ];
    $.cookie("people", JSON.stringify(roles));

  </script>
  <div class="content">

    <ul class="ca-menu">

      <li>
        <a href="${ctx}/user">
          <span class="ca-icon">U</span>

          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.profileManager.name"/></h2>

            <h3 class="ca-sub"><spring:message code="menu.profileManager.description"/></h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/technician">
          <span class="ca-icon">S</span>

          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.technicianManager.name"/></h2>

            <h3 class="ca-sub"><spring:message code="menu.technicianManager.description"/></h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/machineShop">
          <span class="ca-icon">F</span>

          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.saleOrderList.name"/></h2>

            <h3 class="ca-sub"><spring:message code="menu.saleOrderList.description"/></h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/parking">
          <span class="ca-icon">F</span>

          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.parkingInfo.name"/></h2>

            <h3 class="ca-sub"><spring:message code="menu.parkingInfo.description"/></h3>
          </div>
        </a>
      </li>
      <li>
        <a href="${ctx}/reports">
          <span class="ca-icon">L</span>

          <div class="ca-content">
            <h2 class="ca-main"><spring:message code="menu.reports.name"/></h2>

            <h3 class="ca-sub"><spring:message code="menu.reports.description"/></h3>
          </div>
        </a>
      </li>
    </ul>
  </div>
  <!-- content -->
</div>
<script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script src="${ctx}/static/js/jquery.cookie.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
</body>
</html>