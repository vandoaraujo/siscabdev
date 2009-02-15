<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Vector,modelo.Usuario" %>
<%@ page import= "java.util.Date,java.text.SimpleDateFormat,java.util.Calendar,java.util.GregorianCalendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img src="img/cobm.jpg" border=0><p style="BACKGROUND-COLOR: #0080ff;">

Bem vindo,<br>

<%  
   Vector siscab = new Vector();
   Usuario usu =( Usuario) request.getSession().getAttribute("usuario");
   
   SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");    
   Calendar cal = Calendar.getInstance(); 
   String grava = data.format(cal.getTime());  
%>

 Usuário: <%=usu.getNomeGuerra() %>, ${mensagem}. Seu perfil atual é <%=usu.getPerfil() %>
 
 Favor contactar o administrador para ter acesso a determinada página.<br>
 vandoaraujo@hotmail.com<p>
 
 
 
 
<h1><a href="index.jsp" title="login">Tela Login</a></h1>
 

 


</body>
</html>