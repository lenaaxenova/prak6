<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Список транспортных компаний" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <h1>Список компаний</h1>
        <c:choose>
	        <c:when test="${not empty companies}">
		        <table class="table table-hover">
		        	<tr>
		        		<th>#</th>
		        		<th>Название транспортной компании</th>
		        		<th>Действия</th>
		        	</tr>
		        	<c:forEach items="${companies}" var="company">
			        	<tr>
			        		<td>${company.getCompany_id()}</td>
			        		<td>${company.getCompany_name()}</td>
			        		<td>
			        			<button type="button" class="btn btn-info" onclick="window.location='${pageContext.request.contextPath}/company/${company.getCompany_id()}'">Информация о компании</button>
			        			<button type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/company/${company.getCompany_id()}/edit'">Изменить</button>
			        			<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/company/${company.getCompany_id()}/delete'">Удалить</button>
			        		</td>
			        	</tr>
		        	</c:forEach>
		        </table>
	        </c:when>
	        <c:otherwise>
        		<h2>Еще не добавлена ни одна транспортная компания.</h2>
	        </c:otherwise>
        </c:choose>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/company/new'">Добавить транспортную компанию</button>
    </div>
  </body>
</html>
