<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="general/taglibs.jspf" %>
<html>
<%@include file="general/header.jspf" %>
<body>
<h1><spring:message code="login.title"/></h1>


<div class="container" style="padding-top: 1em;">
  <c:choose>
    <c:when test="${canEdit}">
      <form action="${ctx}/saveUser" id="registerUser" method="post">
        <input type="hidden" name="id" value="${user.id}"/>

        <div class="form-horizontal">
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="username"><spring:message
                code="register.username"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="username" id="username" value="${user.username}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="password"><spring:message
                code="register.password"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="password" class="form-control" name="password" id="password" value=""/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="repeatPassword"><spring:message
                code="register.repeatPassword"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="password" class="form-control" name="repeatPassword" id="repeatPassword" value=""/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="firstName"><spring:message
                code="register.firstName"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="firstName" id="firstName" value="${user.firstName}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="lastName"><spring:message
                code="register.lastName"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="lastName" id="lastName" value="${user.lastName}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="email"><spring:message code="register.email"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="email" id="email" value="${user.email}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="phone"><spring:message code="register.phone"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="phone" id="phone" value="${user.phone}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-xs-12 col-sm-2 control-label" for="address"><spring:message
                code="register.address"/></label>

            <div class="col-xs-12 col-sm-9">
              <input type="text" class="form-control" name="address" id="address" value="${user.address}"/>
            </div>
          </div>
        </div>
        <input type="submit" class="btn btn-primary" value="<spring:message code="register.submit"/>"/>
        <input type="button" class="btn btn-primary" value="Volver" onclick="javascript:history.back()">
      </form>
      <script>
        $( document ).ready(function() {

          $("#registerUser").validate({
            rules: {
              username: {
                required: true,
                minlength: 2
              },
              firstName: "required",
              lastName: "required",
              email: {
                required: true,
                email: true
              }
            },
            messages: {
              username: {
                required: "<spring:message code="register.validate.username"/>",
                minlength: "<spring:message code="register.validate.username.minlength"/>"
              },
              firstName: "<spring:message code="register.validate.firstname"/>",
              lastName: "<spring:message code="register.validate.lastname"/>",
              email: "<spring:message code="register.validate.email"/>"

            }
          });
        });

      </script>
    </c:when>
    <c:otherwise>
      <div style="width: 80%">
        <table class="table table-hover">
          <tr>
            <th><spring:message code="register.username"/></th>
            <td><c:out value="${user.username}"/></td>
          </tr>
          <tr>
            <th><spring:message code="register.firstName"/></th>
            <td><c:out value="${user.firstName}"/></td>
          </tr>
          <tr>
            <th><spring:message code="register.lastName"/></th>
            <td><c:out value="${user.lastName}"/></td>
          </tr>
          <tr>
            <th><spring:message code="register.email"/></th>
            <td><c:out value="${user.email}"/></td>
          </tr>
          <tr>
            <th><spring:message code="register.phone"/></th>
            <td><c:out value="${user.phone}"/></td>
          </tr>
          <tr>
            <th><spring:message code="register.address"/></th>
            <td><c:out value="${user.address}"/></td>
          </tr>
        </table>
        <input type="button" class="btn btn-primary" value="Volver" onclick="javascript:history.back()">
      </div>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>