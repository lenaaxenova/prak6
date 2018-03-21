<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Заказ билета" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
			<c:when test="${not empty message}">
				<h2>${message}</h2>
			</c:when>
			<c:otherwise>
		        <c:forEach items="${errors.allErrors}" var="error">
					<h4>${error.defaultMessage}</h4>
		        </c:forEach>
		        
				<c:choose>
			        <c:when test="${not empty journey}">
						<h1>Заказ билета на рейс №${journey.journey_id} (${journey.getCompany().company_name}) по направлению ${journey.getDirection().direction_name}</h1>
						<h3>Направление: ${journey.getDirection().direction_name}</h3>
						<h3>Дата отправления: <fmt:formatDate pattern="yyyy-MM-dd" value="${journey.start_date}" /></h3>
                        <h3>Время отправления: <fmt:formatDate pattern="HH:mm" value="${journey.start_time}" /></h3>
						<c:choose>
							<c:when test="${not empty routes}">

								<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/order/new/${journey.journey_id}" method="post">
									<div class="form-group ${errors.getFieldErrorCount('client_idError') > 0 ? 'has-error' : ''}">
										<label for="client_id" class="col-sm-1 control-label">Клиент</label>
										<div class="col-sm-6">
											<select class="form-control" id="client_id" name="client_id">
										        <c:forEach items="${clients}" var="client">
													<option value="${client.client_id}" ${client_id == client.client_id ? 'selected="selected"' : ''}>${client.last_name} ${client.first_name} ${client.middle_name}</option>
										        </c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group ${errors.getFieldErrorCount('routeStartIdError') > 0 ? 'has-error' : ''}">
										<label for="route_start_id" class="col-sm-1 control-label">Откуда</label>
										<div class="col-sm-6">
											<select class="form-control" id="route_start_id" name="route_start_id">
										        <c:forEach items="${routes}" var="route">
													<option value="${route.route_id}" ${route_start_id == route.route_id ? 'selected="selected"' : ''}>${route.getStation().station_name}</option>
										        </c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group ${errors.getFieldErrorCount('routeEndIdError') > 0 ? 'has-error' : ''}">
										<label for="route_end_id" class="col-sm-1 control-label">Куда</label>
										<div class="col-sm-6">
											<select class="form-control" id="route_end_id" name="route_end_id">
										        <c:forEach items="${routes}" var="route">
													<option value="${route.route_id}" ${route_end_id == route.route_id ? 'selected="selected"' : ''}>${route.getStation().station_name}</option>
										        </c:forEach>
											</select>
										</div>
									</div>
									<div class="col-sm-2">
										<button type="submit" class="btn btn-success">Купить</button>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<h2>Станций нет.</h2>
							</c:otherwise>
						</c:choose>
			        </c:when>
			        <c:otherwise>
		        		<h2>Рейс не найден.</h2>
			        </c:otherwise>
		        </c:choose>
			</c:otherwise>
		</c:choose>
    </div>
  </body>
</html>
