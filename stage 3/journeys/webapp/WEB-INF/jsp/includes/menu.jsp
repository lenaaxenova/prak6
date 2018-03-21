<div class="navbar navbar-default">
    <div class="navbar-header"><a class="navbar-brand" href="${pageContext.request.contextPath}/">Расписания</a></div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Клиенты <b class="caret"></b></a>
        	<ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/client/">Список</a></li>
            	<li><a href="${pageContext.request.contextPath}/client/new">Добавить нового</a></li>
            </ul>
        </li>
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Рейсы <b class="caret"></b></a>
        	<ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/journey/">Расписание</a></li>
            	<li><a href="${pageContext.request.contextPath}/journey/new">Добавить новый</a></li>
            </ul>
        </li>
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Транспортные компании <b class="caret"></b></a>
        	<ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/company/">Список</a></li>
            	<li><a href="${pageContext.request.contextPath}/company/new">Добавить новую</a></li>
            </ul>
        </li>
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Станции <b class="caret"></b></a>
        	<ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/station/">Список</a></li>
            	<li><a href="${pageContext.request.contextPath}/station/new">Добавить новую</a></li>
            </ul>
        </li>
        <li class="dropdown">
        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Направления <b class="caret"></b></a>
        	<ul class="dropdown-menu">
            	<li><a href="${pageContext.request.contextPath}/direction/">Список</a></li>
            	<li><a href="${pageContext.request.contextPath}/direction/new">Добавить новое</a></li>
            </ul>
        </li>
      </ul>
    </div>
</div>