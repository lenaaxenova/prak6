<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <% request.setCharacterEncoding("utf-8"); %>
  <jsp:include page="../includes/head.jsp">
  	<jsp:param name="title" value="Добавление станции" />
  </jsp:include>

  <body>
    <div class="container">
		<jsp:include page="../includes/menu.jsp" />

		<h1>Добавление станции</h1>
		<c:choose>
	        <c:when test="${not empty message}">
	        	<h2>${message}</h2>
	        </c:when>
	        <c:otherwise>
		        <c:forEach items="${errors.allErrors}" var="error">
					<h4>${error.defaultMessage}</h4>
		        </c:forEach>

				<form class="form-horizontal" name="form" action="${pageContext.request.contextPath}/station/new" method="post">
					<div class="form-group ${errors.getFieldErrorCount('station_name') > 0 ? 'has-error' : ''}">
						<label for="station_name" class="col-sm-1 control-label">Название</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="station_name" name="station_name" placeHolder="Введите название" value="${errors.getFieldValue('station_name')}" />
						</div>
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-success">Добавить</button>
					</div>
				</form>
	        </c:otherwise>
	    </c:choose>
    </div>
  </body>
</html>
