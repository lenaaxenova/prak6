<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Список направлений" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <h1>Список направлений</h1>
        <c:choose>
	        <c:when test="${not empty directions}">
		        <table class="table table-hover">
		        	<tr>
		        		<th>#</th>
		        		<th>Название направления</th>
		        		<th>Действия</th>
		        	</tr>
		        	<c:forEach items="${directions}" var="direction">
			        	<tr>
			        		<td>${direction.getDirection_id()}</td>
			        		<td>${direction.getDirection_name()}</td>
			        		<td>
			        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/direction/${direction.getDirection_id()}/edit'">Изменить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/direction/${direction.getDirection_id()}/delete'">Удалить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/direction/${direction.getDirection_id()}/shedule'">Расписание рейсов</button>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </table>
	        </c:when>
	        <c:otherwise>
        		<h2>Еще не создано ни одного направления.</h2>
	        </c:otherwise>
        </c:choose>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/direction/new'">Добавить направление</button>
    </div>
  </body>
</html>
