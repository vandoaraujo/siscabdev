<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,modelo.OBM,modelo.Viatura,modelo.TipoViatura,dao.OBMDao,modelo.TipoViatura,dao.TipoViaturaDao"%>
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
				<fieldset style="width: 450px"><legend>&nbsp;Cadastrar
				Nova Viatura&nbsp;</legend>

				<form action="CRUDViatura" method="post">

				<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<tr>
						<td><label>N� Viatura:</label></td>
						<td><input name="numero" type="text" /></td>
					</tr>
					<tr>
						<td><label>Obm:</label></td>
						<td><select name="obm">

							<!-- Popula a combo que aparecer� na tela -->
							<%
ArrayList<OBM> obms = (ArrayList<OBM>)OBMDao.getInstance().listarTodasOBMs();
for(OBM s: obms){
out.println("<option>"+s.getNome()+"</option>");
}
out.println("</select>");
%>
						</td>
					</tr>
					<tr>
						<td><label>Status Viatura:</label></td>
						<td><select name="status">
							<option>Inoperante - Sem tripula��o
						</select></td>
					</tr>
					<tr>
						<td><label>Tipo Viatura:</label></td>
						<td><select name="tipoViatura">
							<%
List<TipoViatura> tipos = TipoViaturaDao.getInstance().listarTodosTiposViaturas();
//ArrayList<String> tiposViaturas = tipo.getTiposViaturas();
for(int i=0;i<tipos.size();i++){
out.print("<option>"+tipos.get(i).getTipoviatura_descricao());
}
out.println("</select>");
%>
						</td>
					</tr>
					<tr>
						<td><label>Obs. Viatura</label></td>
						<td><TEXTAREA COLS=40 ROWS=5 NAME="obsViatura" /></TEXTAREA></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Cadastrar"
							onclick="this.form.operacaoARealizar.value=1" /> <input
							type="hidden" name="operacaoARealizar" value=""> <input
							type="hidden" name="registroViatura" value="1">
						</form>
						<form action="ViaturasControle" method="post"
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
