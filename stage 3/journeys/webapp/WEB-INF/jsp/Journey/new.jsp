<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Добавление рейса" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />
		
		<h1>Добавление рейса</h1>
		<c:choose>
	        <c:when test="${not empty message}">
	        	<h2>${message}</h2>
	        </c:when>
	        <c:otherwise>
		        <c:forEach items="${errors.allErrors}" var="error">
					<h4>${error.defaultMessage}</h4>
		        </c:forEach>

				<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/journey/new" method="post">
					<div class="form-group ${errors.getFieldErrorCount('db') > 0 ? 'has-error' : ''}">
						<label for="company_id" class="col-sm-1 control-label">Транспортная компания</label>
						<div class="col-sm-6">
							<select class="form-control" id="company_id" name="company_id">
						        <c:forEach items="${companies}" var="company">
									<option value="${company.company_id}" ${company_id == company.company_id ? 'selected="selected"' : ''}>${company.company_name}</option>
						        </c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('db') > 0 ? 'has-error' : ''}">
						<label for="direction_id" class="col-sm-1 control-label">Направление</label>
						<div class="col-sm-6">
							<select class="form-control" id="direction_id" name="direction_id">
							    <c:forEach items="${directions}" var="direction">
									<option value="${direction.direction_id}" ${direction_id == direction.direction_id ? 'selected="selected"' : ''}>${direction.direction_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('number_of_places') > 0 ? 'has-error' : ''}">
						<label for="number_of_places" class="col-sm-1 control-label">Количество мест</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="number_of_places" name="number_of_places" placeHolder="Введите кол-во мест" value="${errors.getFieldValue('number_of_places')}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('start_date_str') > 0 ? 'has-error' : ''}">
						<label for="start_date" class="col-sm-1 control-label">Дата отправления</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="start_date_str" name="start_date_str" placeHolder="Введите дату отправления" value="${start_date_str}" />
						</div>
					</div>
					<div class="form-group ${errors.getFieldErrorCount('start_time_str') > 0 ? 'has-error' : ''}">
						<label for="start_time" class="col-sm-1 control-label">Время отправления</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="start_time_str" name="start_time_str" placeHolder="Введите время отправления" value="${start_time_str}" />
						</div>
					</div>
					<h3>Станции остановок Вы можете добавить позже на странице информации о маршруте.</h3>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-success">Добавить</button>
					</div>
				</form>
	        </c:otherwise>
	    </c:choose>
    </div>
  </body>
</html>