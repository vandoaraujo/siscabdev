<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
  pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuarios</title>
</head>
<body>
<ul>


<c:forEach var="usuarioLista" items= "${usuarios}">
<li>
<b><c:out value="${usuarioLista.nomeGuerra}"/></b> <br/>
</li>
</c:forEach>


</body>
</html>