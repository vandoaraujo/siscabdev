<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Tela de Busca Usuario</title>
</head>
<body>
Par�metros de busca<p>
	

	<form action="BuscarUsuario" method="post">
	<div><label> NumRegistro: <input name="registro" type="text" style=" width : 131px; height : 26px;"/></label></div><br>
	<div><label> NomeGuerra: <input name="nomeGuerra" type="text" /></label></div><br>
	<div style=" height : 32px;">
	<input type="submit" name="evento" value="Login">
	</div>
	
	
	</form>

</body>
</html>