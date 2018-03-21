<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Удаление транспортной компании" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
	        <c:when test="${not empty message}">
				<h2>${message}</h2>
			</c:when>
	        <c:otherwise>
        		<h2>Транспортная компания не найдена.</h2>
	        </c:otherwise>
		</c:choose>
    </div>
  </body>
</html>
