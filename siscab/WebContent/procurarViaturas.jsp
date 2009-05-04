<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.PerfilUsuario,modelo.Viatura,modelo.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="dao.TipoViaturaDao"%>
<%@page import="modelo.TipoViatura"%><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
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
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						<div><font color="red">${parametrosVazios}</font></div>
						<fieldset style="width:450px"><legend>&nbsp;Buscar Viatura&nbsp;</legend>						
						<form action="BuscarViatura" method="post">
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
							<tr>
							<td><label>Tipo Viatura:</label></td>
							<td>
								<select name="tipoViatura">
								<option selected></option>
								<%
									List<TipoViatura> tipos = (List<TipoViatura>)request.getSession().getAttribute("tiposViatura");
									for(int i=0;i<tipos.size();i++){
										out.print("<option>"+tipos.get(i).getTipoviatura_descricao());
									}
										out.println("</select>");
								%>
							</td>
							</tr>
							<tr>
							<td><label>Status Viatura:</label></td>
									<td>
										<select name="status">
										<option selected></option>
										<option>Em atendimento 
										<option>Em prontidão
										<option>Inoperante - Sem tripulação
										<option>Inoperante - Em manutenção
										<option>Inoperante - Desativada
										</select>									
									</td>								
								</tr>
							<tr>
									<td colspan="2">																		
										<input type="submit" name="evento" value="Buscar"/>
										</form>	
										<form action="administracao_usuario.jsp" method="post" style="display:inline;">
							 				<input type="submit" value="Voltar"/>
							 			</form>	
									</td>								
								</tr>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>

</body>
</html>
