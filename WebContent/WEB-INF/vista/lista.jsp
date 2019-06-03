<%@page import="com.spring.mvc.modelo.Consulta"%>
<%@page import="com.spring.mvc.modelo.LSistemas"%>
<%@page import="com.spring.mvc.modelo.Sistema"%>
<%@page import="com.spring.mvc.Controlador"%>
<%@page import="com.spring.mvc.Conector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<%!Controlador Control = new Controlador();%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getRequestURL().substring(0, 21)%>/Mvc/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getRequestURL().substring(0, 21)%>/Mvc/css/datatables.min.css" />
<link rel="stylesheet"
	href="<%=request.getRequestURL().substring(0, 21)%>/Mvc/css/index.css" />
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/jquery-3.3.1.slim.min.js"></script>
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/popper.min.js"></script>
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/bootstrap.min.js"></script>
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/datatables.min.js"></script>
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/moment.min.js"></script>
<script
	src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/js/index.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary navega">
		<a class="navbar-brand" href="#">Aplicacion</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><select class="form-control"
					name="sistema">
						<option value="0">Seleccione un sistema</option>
						<%
							for (int i = 0; i < Control.getConector().getLSistemas().getListaSistemas().size(); i++) {
						%>

						<option
							value="<%=Control.getConector().getLSistemas().getListaSistemas().get(i).getSistema()%>"><%=(String) Control.getConector().getLSistemas().getListaSistemas().get(i).getSistema()%></option>
						<%
							}
						%>
				</select></li>
				<li class="nav-item dropdown"><select class="form-control"
					name="consulta">
						<option value="0">Seleccione una Consulta</option>

						<%
							for (int i = 0; i < Control.getConsultas().size(); i++) {
						%>
						<option value="<%=Control.getConsultas().get(i).getSqlQuerry()%>"><%=(String) Control.getConsultas().get(i).getConsulta()%></option>
						<%
							}
						%>
				</select></li>

			</ul>
		</div>
	</nav>
	<div>
		<span class="input-group-prepend"> <img
			src="<%=request.getRequestURL().substring(0, 21)%>/Mvc/images/GfiLogo.png"
			height="50%" width="20%">
		</span>
	</div>

	SISTEMA: ${param.sistema}. sistema elegido Consulta: ${param.consulta}.
	Consulta elegida
	<div class="mia">
		<table
			class="table table-striped table-bordered table-hover table-sm table-responsive">
			<thead class="thead-light">
				<tr>

					<th scope="col">codigo</th>
					<th scope="col">idversion</th>
					<th scope="col">descripcion</th>
				</tr>
			</thead>
			<tbody>
				<sql:setDataSource var="myDS"
					driver="oracle.jdbc.driver.OracleDriver"
					url="jdbc:oracle:thin:@donald.r.lan:1551/maexe" user="dba_maexe"
					password="dba_maexe" />
				<sql:query var="list_u" dataSource="${myDS}">
			SELECT x.*,x.ROWID FROM MAEXE.MAEX_TM_TIPOSEQUIPOS x
			</sql:query>


				<c:forEach items="${list_u.rows}" var="MaexTmTiposgama">
					<tr>
						<td><c:out value="${MaexTmTiposgama.codigo}" />
						<td><c:out value="${MaexTmTiposgama.idversion}" />
						<td><c:out value="${MaexTmTiposgama.descripcion}" />
					</tr>
				</c:forEach>


			</tbody>
		</table>
		</div>
</body>
</html>