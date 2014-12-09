<%@include file="general/taglibs.jspf" %>
<html>
<head>
  <title><spring:message code="application.title"/></title>
  <link rel='stylesheet' href='${ctx}/static/css/style.css'>
  <script src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
  <script src="${ctx}/static/js/jquery.validate.js"></script>
  <style type="text/css">
    .error{
      color: #c9302c;
    }
  </style>
</head>
<body>
<div class="login-card">
  <h1><img src="${ctx}/static/css/img/logo-autobahn.png"/></h1><br>

  <form action="${ctx}/login" id="login" method="post">
    <div class="form-inline">
      <input type="text" class="col-xs-12 col-md-6 form-control" name="username" id="username"
             placeholder="<spring:message code="login.username"/>"/>
      <input type="password" class="col-xs-12 col-md-6 form-control" name="password" id="password"
             placeholder="<spring:message code="login.password"/>">
    </div>
    <input type="submit" name="login" class="login login-submit" value="<spring:message code="login.submit"/>"/>
  </form>
  <script>
    $( document ).ready(function() {

      $("#login").validate({
        rules: {
          username: "required",
          password: "required"

        },
        messages: {
          username:  "<spring:message code="login.validate.username"/>",
          password: "<spring:message code="login.validate.password"/>"
        }
      });
    });

  </script>
  <!--p><spring:message code="login.register.message"/> <a href="${ctx}/register"><spring:message
    code="login.register.button"/></a>
</p-->
</div>
</body>
</html>
