<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Добавление клиента" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<h1>Добавление клиента</h1>
		<c:choose>
	        <c:when test="${not empty message}">
	        	<h2>${message}</h2>
	        </c:when>
	        <c:otherwise>
		        <c:forEach items="${errors.allErrors}" var="error">
					<h4>${error.defaultMessage}</h4>
		        </c:forEach>

				<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/client/new" method="post">
					<div class="form-group ${errors.getFieldErrorCount('last_name') > 0 ? 'has-error' : ''}">
						<label for="last_name" class="col-sm-1 control-label">Фамилия</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="last_name" name="last_name" placeHolder="Введите фамилию" value="${errors.getFieldValue('last_name')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('first_name') > 0 ? 'has-error' : ''}">
						<label for="first_name" class="col-sm-1 control-label">Имя</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="first_name" name="first_name" placeHolder="Введите имя" value="${errors.getFieldValue('first_name')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('middle_name') > 0 ? 'has-error' : ''}">
						<label for="middle_name" class="col-sm-1 control-label">Отчество</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="middle_name" name="middle_name" placeHolder="Введите отчество" value="${errors.getFieldValue('middle_name')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('address') > 0 ? 'has-error' : ''}">
						<label for="address" class="col-sm-1 control-label">Адрес</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="address" name="address" placeHolder="Введите адрес" value="${errors.getFieldValue('address')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('phone_number') > 0 ? 'has-error' : ''}">
						<label for="phone_number" class="col-sm-1 control-label">Телефон</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="phone_number" name="phone_number" placeHolder="Введите телефон" value="${errors.getFieldValue('phone_number')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('email') > 0 ? 'has-error' : ''}">
						<label for="email" class="col-sm-1 control-label">Email</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="email" name="email" placeHolder="Введите email" value="${errors.getFieldValue('email')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('password') > 0 ? 'has-error' : ''}">
						<label for="password" class="col-sm-1 control-label">Пароль</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password" placeHolder="Введите пароль" value="${errors.getFieldValue('password')}" />
						</div>
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-success">Добавить</button>
					</div>
				</form>
	        </c:otherwise>
	    </c:choose>
    </div>
  </body>
</html>
