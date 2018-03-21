<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Информация о клиенте" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
	        <c:when test="${not empty client}">
		         <h1>Клиент ${client.getLast_name()} ${client.getFirst_name()} ${client.getMiddle_name()}</h1>
		         <h3>Адрес: ${client.getAddress()}</h3>
		         <h3>Телефон: ${client.getPhone_number()}</h3>
		         <h3>Email: ${client.getEmail()}</h3>
		         <br />
		         <h3>Список заказов:</h3>
		         <c:choose>
	        		<c:when test="${not empty orders}">
		        		<c:forEach items="${orders}" var="order">
							<h4><a href="${pageContext.request.contextPath}/order/${order.getOrder_id()}">Заказ от <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.getDate_of_order()}" /></a></h4>
			        	</c:forEach>
	        		</c:when>
			        <c:otherwise>
		        		<h2>Заказов нет.</h2>
			        </c:otherwise>
			     </c:choose>
	        </c:when>
	        <c:otherwise>
        		<h2>Клиент не найден.</h2>
	        </c:otherwise>
        </c:choose>
    </div>
  </body>
</html>
