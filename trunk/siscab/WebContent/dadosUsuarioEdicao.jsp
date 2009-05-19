<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
  pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.PerfilUsuario" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
<link href="css/current.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/mm_menu.js"></script>
<script language="JavaScript" src="js/script.js"></script>
<script>
function campoObrigatorio()
{
	if ( (document.form.novaSenha.value != null) && (document.form.novaSenha.value != "") && (document.form.repeticaoSenha.value != null) && (document.form.repeticaoSenha.value != "") && (document.form.senha.value != null) && (document.form.senha.value != "") )
		return true;
	else
	{
		alert("Favor preencher todos campos obrigatórios");	
		return false;
	}

}
</script>
</head>

<body>
<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr	style="background-image: url(img/back_cabecalho.jpg); background-repeat: repeat-x;">
		<td style="background-image: url(img/cabecalho.jpg); background-repeat: no-repeat;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr style="height:139px;">
					<td align="right" style="padding-right:20px;" colspan="2">  	
						<img src="img/logo.png">	 	
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
				<td style="padding-left: 20px; padding-top: 20px;">
				
				<h2>Administração :: Cadastro de Usuários :: Incluir Usuário</h2>
				<fieldset style="width: 450px"><legend>&nbsp;Dados do novo usuário&nbsp;</legend>

				<form name="form" action="EfetivaAlteracaoDadosUsuario" method="post">

				<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<tr>
						<td><label>Email:</label></td>
						<td><input name="email" type="text" value="${usuario.email}" /></td>
					</tr>					
					<tr>
						<td><label>Perfil:</label></td>
						<td><select name="perfil">
							<option selected>${usuario.perfil.perfil_descricao}</option> 
						</td>
					</tr>
					<tr>
						<td><label>Senha atual:</label></td>
						<td><input name="senha" type="password" /> * </td>
					</tr>
					<tr>
						<td><label>Nova Senha:</label></td>
						<td><input name="novaSenha" type="password" /> *  </td>
					</tr>
					<tr>
						<td><label>Repita a  Senha:</label></td>
						<td><input name="repeticaoSenha" type="password" /> *  </td>
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="hidden" name="registro" value="${usuario.id}">
							<input type="submit" value="Salvar" onclick="return campoObrigatorio();"/>
						</form>
						<form action="paginaPrincipal.jsp" method="post"
							style="display: inline;"><input type="submit"
							value="Cancelar" /></form>
						</td>
					</tr>
					</table>
					</fieldset>					
					</td>
					</tr>
				</table>				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;( * ) Campos obrigatórios
				</td>
			</tr>
		</table>
</body>
</html>
