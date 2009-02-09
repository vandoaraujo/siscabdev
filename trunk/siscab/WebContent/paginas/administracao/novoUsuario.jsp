<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NovoUsuário</title>
</head>
<body>



<div id="texto">
	<h2>Cadastrar Novo Usuario</h2>
	
	
	<form action="" method="post" class="NovoUsuarioServlet">
	<fieldset>
	<label>Nome: <input name="nome" type="text" /><br></label>
	<label>NumRegistro: <input name="registro" type="text" /></label><br>
	<label>nomeGuerra: <input name="nomeGuerra" type="text" /></label><br>
	<label>obm: <select name="obm"/> </label>
	<label>perfil: <select name="perfil"/></label><br>
	<label>Email: <input name="email" type="text" /></label><br>
	<label>Senha: <input name="senha" type="text" /></label><br>
	
	<label class="labelmensagem">Mensagem <textarea name="mensagem"></textarea></label>
	
	</fieldset>
	<input type="submit" value="Enviar" class="botao botrig"/>
	</form>
	</div>
		
</body>
</html>