<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Главная страница" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

        <h2>Добро пожаловать на систему информации об автобусных билетах и рейсах!</h2>
        <br>
        <br>
        <br>
        <br>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/journey'">Рассписание рейсов</button>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/client'">Список клиентов</button>
        <button type="button" class="btn btn-success" onclick="window.location='${pageContext.request.contextPath}/company'">Список компаний</button>
    </div>
  </body>
</html>
