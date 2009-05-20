<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario, modelo.Viatura,modelo.TipoViatura,dao.OBMDao,dao.TipoViaturaDao" %>
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
	if ( (document.form.numero.value != null) && (document.form.numero.value != "") )
		return true;
	else
	{
		alert("Favor preencher todos campos obrigat�rios");	
		return false;
	}

}
</script>
</head>
<body>

<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
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
						<strong>Usu�rio:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
					
					<h2>Atendimentos :: Situa&ccedil;&atilde;o das Viaturas :: Alterar Situa��o</h2>					
					
						<fieldset style="width:450px"><legend>&nbsp;Editar Viatura&nbsp;</legend>
							
							<form name="form" action="AlteraStatusViatura" method="post">
								<% Viatura v =(Viatura) request.getAttribute("viaturaAtual"); %> 
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Tipo:</label></td>
									<td>
										<%= v.getTipo_viatura().getTipoviatura_abreviacao() %> 
									</td>
								</tr>	
								<tr>
									<td><label>N�mero:</label></td>
									<td>${viaturaAtual.numero}</td>								
								</tr>
								<tr>
									<td><label>Status Atual:</label></td>
									<td>
										${viaturaAtual.viatura_status}
									</td>								
								</tr>
								<tr>
									<td><label>Novo Status:</label></td>
									<td>
										<select name="novoStatus">
										<option selected>---</option>
										<option>Em prontid�o
										<option>Inoperante - Sem tripula��o
										<option>Inoperante - Em manuten��o
										<option>Inoperante - Desativada
										</select> *
									</td>								
								</tr>
								<tr>								
									<td><label>Observa��es:</label></td>
									<td><TEXTAREA COLS=40 ROWS=5 NAME="obsViatura">${viaturaAtual.viatura_obs}</TEXTAREA></td>								
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="Salvar" onclick="return campoObrigatorio()"/>
   									  	<input type="hidden" name="registroViatura" value ="${viaturaAtual.id}"> 
										</form>	
										<form action="SituacaoViaturasOBM" style="display:inline;">
							 				<input type="submit" value="Cancelar"/>
							 			</form>	
									</td>								
								</tr>
							</table>
						</fieldset>	
						<br />( * ) Campos Obrigat�rios						
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>





</body>
</html>