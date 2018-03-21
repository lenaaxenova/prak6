<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Редактирование клиента" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
	        <c:when test="${not empty client}">
				<h1>Редактирование клиента</h1>
				<c:choose>
			        <c:when test="${not empty message}">
			        	<h2>${message}</h2>
			        </c:when>
			        <c:otherwise>
				        <c:forEach items="${errors.allErrors}" var="error">
							<h4>${error.defaultMessage}</h4>
				        </c:forEach>

						<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/client/${client.client_id}/edit" method="post">
							<div class="form-group ${errors.getFieldErrorCount('last_name') > 0 ? 'has-error' : ''}">
								<label for="last_name" class="col-sm-1 control-label">Фамилия</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="last_name" name="last_name" placeHolder="Введите фамилию" value="${errors.getFieldErrorCount('last_name') > 0 ? errors.getFieldValue('last_name') : client.last_name}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('first_name') > 0 ? 'has-error' : ''}">
								<label for="first_name" class="col-sm-1 control-label">Имя</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="first_name" name="first_name" placeHolder="Введите имя" value="${errors.getFieldErrorCount('first_name') > 0 ? errors.getFieldValue('first_name') : client.first_name}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('middle_name') > 0 ? 'has-error' : ''}">
								<label for="middle_name" class="col-sm-1 control-label">Отчество</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="middle_name" name="middle_name" placeHolder="Введите отчество" value="${errors.getFieldErrorCount('middle_name') > 0 ? errors.getFieldValue('middle_name') : client.middle_name}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('address') > 0 ? 'has-error' : ''}">
								<label for="address" class="col-sm-1 control-label">Адрес</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="address" name="address" placeHolder="Введите адрес" value="${errors.getFieldErrorCount('address') > 0 ? errors.getFieldValue('address') : client.address}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('phone_number') > 0 ? 'has-error' : ''}">
								<label for="phone_number" class="col-sm-1 control-label">Телефон</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="phone_number" name="phone_number" placeHolder="Введите телефон" value="${errors.getFieldErrorCount('phone_number') > 0 ? errors.getFieldValue('phone_number') : client.phone_number}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('email') > 0 ? 'has-error' : ''}">
								<label for="email" class="col-sm-1 control-label">Email</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="email" name="email" placeHolder="Введите email" value="${errors.getFieldErrorCount('email') > 0 ? errors.getFieldValue('email') : client.email}" />
								</div>
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-success">Отредактировать</button>
							</div>
						</form>
			        </c:otherwise>
			    </c:choose>
			</c:when>
	        <c:otherwise>
        		<h2>Клиент не найден.</h2>
	        </c:otherwise>
		</c:choose>
    </div>
  </body>
</html>
