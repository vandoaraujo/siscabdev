<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Viatura,modelo.Atendimento"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
<link href="css/current.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/mm_menu.js"></script>
<script language="JavaScript" src="js/script.js"></script>
</head>
<body>
<% Atendimento at = (Atendimento)	request.getSession().getAttribute("atendimentoAtual");
%>

<script language="JavaScript1.2">mmLoadMenus();</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr
		style="background-image: url(img/back_cabecalho.jpg); background-repeat: repeat-x;">
		<td
			style="background-image: url(img/cabecalho.jpg); background-repeat: no-repeat;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr style="height: 139px;">
				<td align="right" style="padding-right: 20px;"><img
					src="img/logo.png"></td>
			</tr>
			<tr>
				<td style="padding-left: 20px;"><a href="javascript:;"
					onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')"
					onMouseOut="MM_startTimeout();"><img
					src="img/bt_atendimento.gif" name="image1" width="109" height="17"
					border="0" id="image1"></a> <a href="javascript:;"
					onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')"
					onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif"
					name="image3" width="153" height="17" border="0" id="image3"></a>
				<a href="javascript:;"
					onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')"
					onMouseOut="MM_startTimeout();"><img
					src="img/bt_administrador.gif" name="image2" width="109"
					height="17" border="0" id="image2"></a> <a href="#"
					onclick="fechar()"><img src="img/bt_sairsistema.gif"
					name="close" width="109" height="17" border="0" id="close"></a></td>
			</tr>
			<tr>
				<td style="padding-left: 20px; padding-top: 20px;">
				<fieldset style="width: 200px"><legend>&nbsp;Viatura&nbsp;</legend>
				<a href="DespacharViatura?numeroAtendimento<%= at.getId() %>">Despachar
				Viatura</a></fieldset>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 20px; padding-top: 20px;">
				<fieldset style="width: 600px"><legend>&nbsp;Lista
				de Viaturas Associadas a este atendimento&nbsp;</legend>
				<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<tr>
						<td>
						<% ArrayList viatura=(ArrayList<Viatura>) request.getSession().getAttribute("viaturas");
									%> <% if(viatura.size()== 0){ %>
						<div style="color: red">Nenhuma Viatura no momento</div>

						<%} else{
										
										%> <% for(int i=0;i<viatura.size();i++){
												Viatura via =(Viatura)viatura.get(i);
												 %>
						<form action="NovaMovimentacaoViatura" method="post">
					<tr>
						<td>
						<div><%= i+1 %> :: NumeroViatura = <a
							href="NovaMovimentacaoViatura?registro=<%=via.getId()%>&numeroAtendimento=<%= at.getId() %>&operacaoARealizar=1">Status
						Viatura: <%= via.getViatura_status()%>TipoViatura: <%= via.getTipo_viatura().getTipoviatura_descricao().toString()%></a>&nbsp;

						<input type="submit" value="Liberar Viatura"
							onclick="this.form.operacaoARealizar.value=2" /> <input
							type="hidden" name="operacaoARealizar" value=""> <input
							type="hidden" name="registro" value="<%=via.getId()%>" /> <input
							type="hidden" name="numeroAtendimento" value="<%= at.getId()%>" />
						<br>
						</div>
						</td>
					</tr>
					</form>
					<%}
										
										
										
									}%>
					</td>
					</tr>
					<tr>
						<td>
						<form action="AcompanharAtendimentos" method="post"><input
							type="submit" value="Voltar" /></form>
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