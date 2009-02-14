<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
  pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuarios</title>
</head>
<body>
<ul>

Lista de Usuários cadastrados com tais parâmetros:


<% ArrayList usuarios=(ArrayList<Usuario>) request.getAttribute("usuarios");
%>

<%if(usuarios.size()== 0){ %>
     <h2> Nenhum Usuario cadastrado <p>
     
     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
     </h2>
   
<%} else{
	
	
	for(int i=0;i<usuarios.size();i++){
			Usuario usu =(Usuario)usuarios.get(i);
    %> 
    
    <tr>
    <td>
    <div>     
    
    <%= i+1 %>. Nome=<a href="EditaUsuario?registro=<%=i+1%>"> <%= usu.getNomeGuerra()%></a><br>
    </div>
    </td>
    </tr>
   <%} 
	
	
}%>
	 




</body>
</html>