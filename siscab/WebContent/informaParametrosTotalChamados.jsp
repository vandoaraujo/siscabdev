<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.PerfilUsuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
<link href="css/current.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/mm_menu.js"></script>
<script language="JavaScript" src="js/script.js"></script>
<script language="JavaScript" src="js/CalendarPopup.js"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="/javascript/calendarpopup/combined-compact/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();
	</SCRIPT>
<link type="text/css" rel="stylesheet"
	href="css/dhtmlgoodies_calendar.css" media="screen"></LINK>
<SCRIPT type="text/javascript" src="js/dhtmlgoodies_calendar.js"></script>


</head>

<body>
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
				<td style="padding-left: 20px;" colspan="2"><a
					href="javascript:;"
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
				<div
					style="color: black; display: inline; border-color: black; font-size: 14">
				Relatórios :: Total de Chamados por Natureza</div>
				<br>
				<div style="color: red; display: inline">${msg}</div>
				<br>
				<fieldset style="width: 650px"><legend>&nbsp;Informe
				o período de referência&nbsp;</legend>

				<form action="TotalChamadosPorNatureza" method="post">

				<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<tr>
						<td style="width: 100px">Data Inicial:</td>
						<td><input type="text" name="dataInicial" readonly="readonly"
							size=11 VALUE="" klojy,,/> <input type="button" value="..."
							onclick="displayCalendar(document.forms[0].dataInicial,'dd/mm/yyyy',this)">
						</td>
					</tr>
					<tr>
						<td>Data Final:</td>
						<td><input name="dataFinal" readonly="readonly" type="text"
							size=11 value="" /> <input type="button" value="..."
							onclick="displayCalendar(document.forms[0].dataFinal,'dd/mm/yyyy',this)">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Ver Relatório" />
						</form>
						<form action="administracao_usuario.jsp" method="post"
							style="display: inline;"><input type="submit"
							value="Voltar" /></form>
						</td>
					</tr>
					</fieldset>
					</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
</body>
</html>
