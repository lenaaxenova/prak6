<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Информация о рейсе" />
  </jsp:include>
  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />
		<c:choose>
	        <c:when test="${not empty journey}">
				<h1>Рейс №${journey.journey_id} (${journey.getCompany().company_name}) по направлению ${journey.getDirection().direction_name}</h1>
				<h3>Количество мест: ${places}</h3>
				<h3>Количество свободных мест: ${free_places}</h3>
				<h3>Список станций:</h3>
				<c:choose>
					<c:when test="${not empty routes}">
				        <table class="table table-hover">
				        	<tr>
				        		<th>Станция</th>
				        		<th>Прибытие, мин. от отправления</th>
				        		<th>Стоянка, мин.</th>
				        		<th>Отправление, мин. от отправления</th>
				        		<th>Стоимость, руб. от станции отправления</th>
				        		<th>Действия</th>
				        	</tr>
				        	<c:set var="i" value="0" />
				        	<c:forEach items="${routes}" var="route">
					        	<tr>
					        		<td>${route.getStation().station_name}</td>
					        		<td>${i == 0 ? "" : route.time_offset}</td>
					        		<td>${i == 0 ? "" : (i == routes.size() - 1 ? "" : route.time_of_stop)}</td>
					        		<td>${i == routes.size() - 1 ? "" : route.time_offset + route.time_of_stop}</td>
					        		<td>${i == 0 ? "" : route.cost_offset}</td>
					        		<td>
					        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/route/${route.route_id}/edit'">Изменить</button>
					        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/route/${route.route_id}/delete'">Удалить</button>
					        		</td>
					        	</tr>
					        	<c:set var="i" value="${i + 1}" />
				        	</c:forEach>
				        </table>
					</c:when>
					<c:otherwise>
						<h2>Станций нет.</h2>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/route/new/${journey.journey_id}'">Добавить станцию</button>
				<button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/order/new/${journey.journey_id}'">Оформить заказ</button>
	        </c:when>
	        <c:otherwise>
        		<h2>Маршрут не найден.</h2>
	        </c:otherwise>
        </c:choose>
    </div>
  </body>
</html>

