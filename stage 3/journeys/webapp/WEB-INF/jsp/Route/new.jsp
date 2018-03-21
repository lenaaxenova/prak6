<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Добавление остановки маршрута" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />
		
		<c:choose>
			<c:when test="${not empty journey}">
				<h1>Добавление остановки для рейса №${journey.journey_id} (${journey.getCompany().company_name}) по направлению ${journey.getDirection().direction_name}</h1>
				<c:choose>
			        <c:when test="${not empty message}">
			        	<h2>${message}</h2>
			        	<h2><a href="${pageContext.request.contextPath}/journey/${journey.journey_id}">Вернуться к маршруту</a></h2>
			        </c:when>
			        <c:otherwise>
				        <c:forEach items="${errors.allErrors}" var="error">
							<h4>${error.defaultMessage}</h4>
				        </c:forEach>
							
						<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/route/new/${journey.journey_id}" method="post">
							<div class="form-group ${errors.getFieldErrorCount('db') > 0 ? 'has-error' : ''}">
								<label for="station_id" class="col-sm-4 control-label">Станция</label>
								<div class="col-sm-6">
									<select class="form-control" id="station_id" name="station_id">
								        <c:forEach items="${stations}" var="station">
											<option value="${station.station_id}" ${station_id == station.station_id ? 'selected="selected"' : ''}>${station.station_name}</option>
								        </c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('timeOffset') > 0 ? 'has-error' : ''}">
								<label for="timeOffset" class="col-sm-4 control-label">Прибытие, мин. от времени отправления (либо 0)</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="time_offset" name="time_offset" placeHolder="Введите минуты" value="${errors.getFieldValue('time_offset')}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('time_of_stop') > 0 ? 'has-error' : ''}">
								<label for="time_of_stop" class="col-sm-4 control-label">Время стоянки</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="time_of_stop" name="time_of_stop" placeHolder="Введите минуты" value="${errors.getFieldValue('time_of_stop')}" />
								</div>
							</div>
							<div class="form-group ${errors.getFieldErrorCount('cost_offset') > 0 ? 'has-error' : ''}">
								<label for="cost_offset" class="col-sm-4 control-label">Стоимость, руб. от станции отправления (либо 0)</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="cost_offset" name="cost_offset" placeHolder="Введите стоимость" value="${errors.getFieldValue('cost_offset')}" />
								</div>
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-success">Добавить</button>
							</div>
						</form>
			        </c:otherwise>
			    </c:choose>
			</c:when>
		    <c:otherwise>
        		<h2>Маршрут не найден.</h2>
		    </c:otherwise>
	    </c:choose>
    </div>
  </body>
</html>