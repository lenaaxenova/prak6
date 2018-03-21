<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Информация о транспортной компании" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
	        <c:when test="${not empty company}">
		         <h1>Транспортная компания ${company.getCompany_name()}</h1>
		         <h2>Список клиентов:</h2>
		         <c:choose>
	        		<c:when test="${not empty clients}">
                    <c:forEach items="${clients}" var="client">
                            <h3>${client.getLast_name()} ${client.getFirst_name()}</h3>
			        	</c:forEach>
	        		</c:when>
			        <c:otherwise>
		        		<h2>Клиентов нет.</h2>
			        </c:otherwise>
			     </c:choose>
		         <br />
	        </c:when>
	        <c:otherwise>
        		<h2>Транспортная компания не найдена.</h2>
	        </c:otherwise>
        </c:choose>
    </div>
  </body>
</html>
