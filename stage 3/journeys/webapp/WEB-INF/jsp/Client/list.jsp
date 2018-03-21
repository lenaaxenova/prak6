<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Список клиентов" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <h1>Список клиентов</h1>
        <c:choose>
	        <c:when test="${not empty clients}">
		        <table class="table table-hover">
		        	<tr>
		        		<th>#</th>
		        		<th>Фамилия</th>
		        		<th>Имя</th>
		        		<th>Отчество</th>
		        		<th>Адрес</th>
		        		<th>Телефон</th>
		        		<th>Email</th>
		        		<th>Действия</th>
		        	</tr>
		        	<c:forEach items="${clients}" var="client">
			        	<tr>
			        		<td>${client.getClient_id()}</td>
			        		<td>${client.getLast_name()}</td>
			        		<td>${client.getFirst_name()}</td>
			        		<td>${client.getMiddle_name()}</td>
			        		<td>${client.getAddress()}</td>
			        		<td>${client.getPhone_number()}</td>
			        		<td>${client.getEmail()}</td>
			        		<td>
			        			<button type="button" class="btn btn-info" onclick="window.location='${pageContext.request.contextPath}/client/${client.getClient_id()}'">Информация о клиенте</button>
			        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/client/${client.getClient_id()}/edit'">Редактировать</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/client/${client.getClient_id()}/delete'">Удалить</button>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </table>
	        </c:when>
	        <c:otherwise>
        		<h2>Еще не зарегестрировано ни одного клиента.</h2>
	        </c:otherwise>
        </c:choose>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/client/new'">Добавить клиента</button>
    </div>
  </body>
</html>
