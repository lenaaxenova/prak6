<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Список станций" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <h1><b>Список cтанций</b></h1>
        <c:choose>
	        <c:when test="${not empty stations}">
		        <table class="table table-hover">
		        	<tr>
		        		<th>#</th>
		        		<th>Название станции</th>
		        		<th>Действия</th>
		        	</tr>
		        	<c:forEach items="${stations}" var="station">
			        	<tr>
			        		<td>${station.station_id}</td>
			        		<td>${station.station_name}</td>
			        		<td>
			        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/station/${station.station_id}/edit'">Изменить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/station/${station.station_id}/delete'">Удалить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/station/${station.station_id}/shedule'">Расписание рейсов</button>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </table>
	        </c:when>
	        <c:otherwise>
        		<h2>Еще не создано ни одной станции.</h2>
	        </c:otherwise>
        </c:choose>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/station/new'">Добавить станцию</button>
    </div>
  </body>
</html>
