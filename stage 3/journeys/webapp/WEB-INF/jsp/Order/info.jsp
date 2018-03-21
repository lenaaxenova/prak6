<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Информация о заказе" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<c:choose>
	        <c:when test="${not empty order}">
				<h1>Заказ номер №${order.order_id} от <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.date_of_order}" /></h1>
				<h3>Клиент: ${order.getClient().last_name} ${order.getClient().first_name} ${order.getClient().middle_name}</h3>
                <h3>Номер рейса: ${order.getJourney().journey_id}</h3>
                <h3>Направление рейса: ${order.getJourney().getDirection().direction_name}</h3>
				<h3>Станция отправления: ${order.getRoute_start().getStation().station_name}</h3>
				<h3>Станция прибытия: ${order.getRoute_end().getStation().station_name}</h3>
				<h3>Стоимость заказа: ${cost} руб.</h3>
				<button type="button" class="btn btn-danger" onclick="window.location='${pageContext.request.contextPath}/order/${order.order_id}/delete'">Удалить заказ</button>
	        </c:when>
	        <c:otherwise>
        		<h2>Заказ не найден.</h2>
	        </c:otherwise>
        </c:choose>
    </div>
  </body>
</html>
