<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Vector,modelo.Usuario" %>
<%@ page import= "java.util.Date,java.text.SimpleDateFormat,java.util.Calendar,java.util.GregorianCalendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SISCAB</title>
</head>
<body>

<img src="img/cobm.jpg" border=0>

<br>Bem vindo,<br>

<%  
   Vector siscab = new Vector();
   Usuario usu =( Usuario) request.getSession().getAttribute("usuario");
   
   SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");    
   Calendar cal = Calendar.getInstance(); 
   String grava = data.format(cal.getTime());  
%>

 Usuário: <%=usu.getNomeGuerra() %>, logado com sucesso! <br>
 
 Dia Atual: <%= grava %><p>



<div id="cabecalho">
	<h1><a href="paginaPrincipal.jsp" title="MENU">MENU</a></h1>
	<div id="menu">
	<ul>
	<li><a href="recebimentoChamados.jsp">Módulo de Recebimento de Chamadas</a></li>
	<li><a href="">Módulo de Despachos de Viatura</a></li>
	<li><a href="">Módulo de Registro de Atendimentos</a></li>
	<li><a href="">Módulo de Cadastro de Atendimentos Passivos </a></li>
	<li><a href="">Módulo de Consultas e Estatísticas </a></li>
	<li><a href="administracao.jsp">Módulo de Administração do Sistema</a></li>
	</ul>
	</div>
	</div>
	


</body>
</html>