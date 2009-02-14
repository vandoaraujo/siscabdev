<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NovoUsuário</title>
</head>
<body>


<div id="texto">
	<h2>Cadastrar Novo Usuario</h2>
	
	
	<form action="NovoUsuarioServlet" method="post">
	<fieldset>
	
	<label>NumRegistro: <input name="registro" type="text" /></label><br>
	<label>nomeGuerra: <input name="nomeGuerra" type="text" /></label><br>
	<label>Obm: <select name="obm">
	
	<!-- Popula a combo que aparecerá na tela -->
	<%
 	ArrayList<OBM> obms = (ArrayList)request.getAttribute("obms");
 	 for(OBM s: obms){
	 out.println("<option>"+s.getNome()+"<option>");
 	}
	out.println("</select>");
	%>
	
	 </label><br>
	<label>perfil: <select name="perfil">
	<option>ADMIN 
	<option>ATENDENTE
	<option>OPERADOR
	<option>CONTROLADOR
	<option>COMANDANTE
	</select>
	</label><br>
	<label>Email: <input name="email" type="text" /></label><br>
	<label>Senha: <input name="senha" type="text" /></label><br>
	</fieldset>
	<input type="submit" value="Cadastrar" onclick="this.form.operacaoARealizar.value=1"/>
	<input type="hidden" name="operacaoARealizar" value ="">
	 <input type="hidden" name="registroUsuario" value ="1"> 
	</form>
	</div>
		
</body>
</html>