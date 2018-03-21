<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Расписание рейсов" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <c:choose>
	        <c:when test="${not empty journeys}">
		        <table class="table table-hover">
		        	<tr>
		        		<th>Номер рейса</th>
		        		<th>Направление</th>
		        		<th>Дата отправления</th>
                        <th>Время отправления</th>
		        		<th>Станция отправления</th>
		        		<th>Станция прибытия</th>
                        <th>Компания</th>
		        		<th>Действия</th>
		        	</tr>
		        	<c:forEach items="${journeys}" var="journey">
			        	<tr>
			        		<c:set var="routes" value="${journeydao.findAllRoutes(journey)}" />
			        		<td>${journey.journey_id}</td>
                            <td>${journey.getDirection().direction_name}</td>
                            <td>${start_date.format(journey.start_date)}</td>
                            <td>${start_time.format(journey.start_time)}</td>
			        		<td>${routes.size() > 0 ? routes.get(0).getStation().station_name : ""}</td>
			        		<td>${routes.size() > 0 ? routes.get(routes.size() - 1).getStation().station_name : ""}</td>
                            <td>${journey.getCompany().company_name}</td>
                            <td>
			        			<button type="button" class="btn btn-info" onclick="window.location='${pageContext.request.contextPath}/journey/${journey.journey_id}'">Информация</button>
			        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/journey/${journey.journey_id}/edit'">Изменить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/journey/${journey.journey_id}/delete'">Удалить</button>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </table>
	        </c:when>
	        <c:otherwise>
        		<h2>Еще не создано ни одного рейса.</h2>
	        </c:otherwise>
        </c:choose> 
       <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/journey/new'">Добавить маршрут</button>
    </div>
  </body>
</html>
