<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,dao.OBMDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Edição de Usuário</title>
</head>
<body>


<div id="texto">
	<h2>Tela de Alteração -  Usuario</h2>
	
	
	<form action="NovoUsuarioServlet" method="get">
	<fieldset>
	
	<label>NumRegistro: <input name="registro" type="text" value=${usuario.numRegistro} /></label><br>
	<label>nomeGuerra: <input name="nomeGuerra" type="text" value=${usuario.nomeGuerra} } /></label><br>
	<label>Obm: <select name="obm">
	
	<!-- Popula a combo que aparecerá na tela -->
	<%
 	  List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
 	 for(OBM s: obms){
	 out.println("<option>"+s.getNome()+"<option>");
 	}
	out.println("</select>");
	%>
	
	 </label><br>
	<label>perfil: <select name="perfil" >
	<option>ADMIN 
	<option selected >${usuario.perfil} 
	<option>OPERADOR
	<option>CONTROLADOR
	<option>COMANDANTE
	</select>
	</label><br>
	<label>Email: <input name="email" type="text" size="30" value=${usuario.email} /></label><br>
	<label>Senha: <input name="senha" type="text" size="30" value=${usuario.senha} /></label><br>
	</fieldset>
	<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>
	<input type="submit" value="Deletar" onclick="this.form.operacaoARealizar.value=3"/>
	
	<input type="hidden" name="operacaoARealizar" value ="">
    <input type="hidden" name="registroUsuario" value =${usuario.id} > 
	</form>
	</div>
		
</body>
</html>