<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado,modelo.NaturezaChamado,dao.NaturezaChamadoDao"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
<link href="css/current.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="js/mm_menu.js"></script>
<script language="JavaScript" src="js/script.js"></script>
<script id="api" type="text/javascript"
	src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA1rJRd_d6NTBRsxYq3I7erBSVPiKh14QUveM1LzMKfwwniz5cMBRktlNQQD2Mh4zRyiEDe7djlt6huA"></script>

</head>
<body onload="init();" onunload="GUnload()">
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
					onclick="fechar()"><img src="img/bt_administrador.gif"
					name="image2" width="109" height="17" border="0" id="image2"></a>
				</td>
			</tr>
			<tr>
				<td style="padding-left: 20px; padding-top: 20px;">
				<fieldset style="width: 450px"><legend>&nbsp;Finalizar
				Chamado&nbsp;</legend>
				<table width="100%">
					<tr>
						<td>
						<div id="map_canvas">Map loading..
						<noscript>Habilite o uso de JavaScript em seu browser
						para exibir o Mapa da Google</noscript>
						</div>
						</td>
					</tr>
				</table>

				<%!String municipio=null;%> <%
													municipio = (String)request.getAttribute("municipio");
												%>

				<form action="FinalizarChamadoIniciarAtendimento"
					onsubmit="showAddress(this.address.value); return false"
					method="post">
				<table>
					<tr>
						<td>CoordX:
						<div id="CoordX"></div>
						</td>
						<td>CoordY:
						<div id="CoordY"></div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<hr>
						</td>
					</tr>
					<tr>
						<td>Origem do Chamado:</td>
						<td><input name="origemChamado" type="text"
							readonly="readonly" value="${origemChamado}"></td>
					</tr>
					<tr>
						<td>Nome do Solicitante:</td>
						<td><input name="nomeSolicitante" type="text"
							readonly="readonly" value="${nomeSolicitante}"></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><input name="telefone" type="text" readonly="readonly"
							value="${telefone}"></td>
					</tr>
					<tr>
						<td>Número Aproximado de Vitimas:</td>
						<td><input name="numAproxVitimas" type="text"
							readonly="readonly" value="${aproxVitimas}"></td>
					</tr>
					<tr>
						<td>Obm Solicitada:</td>
						<td><input name="obmSolicitada" type="text"
							readonly="readonly" value="${nomeObmUsuario}"></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td>Município:</td>
						<td><select name="municipio">
							<option><%=municipio%></option>
						</select></td>
					<tr>
						<td>Natureza Chamado:</td>
						<td><select name="naturezaChamado">
							<%
			 									List<NaturezaChamado> tipos = NaturezaChamadoDao.getInstance().listarTodasNaturezasChamado();
			 									 																		for(int i=0;i<tipos.size();i++){
			 									 																			out.print("<option>"+tipos.get(i).getNaturezachamado_descricao());
			 									 													 				}
			 									 																			out.println("</select>");
			 								%>
						</td>
					</tr>
					<tr>
						<td><input name="bairro" type="hidden" readonly="readonly"
							value="${bairro}"> <input name="infoComplementares"
							type="hidden" readonly="readonly" value="${infoComplementares}">
						<input name="numeroChamado" type="hidden" readonly="readonly"
							value="${numeroChamado}"> <input type="hidden" name="q"
							id="q"
							value=">${endereco} ${numero} ${bairro} ${municipio} RJ Brasil ">
						<input type="submit" value="Finalizar Chamado"
							onclick="Coordenadas(); this.form.operacaoARealizar.value=1">
						<input type="hidden" name="operacaoARealizar" value=""> <input
							type="hidden" name="endereco" value="${endereco}"> <script>
										function Coordenadas(){
											var CoordX = document.getElementById('CoordX').innerHTML;
											var CoordY = document.getElementById('CoordY').innerHTML;
											document.getElementById('hiddenCoordY').value = CoordY;
											document.getElementById('hiddenCoordX').value = CoordX;
										}
										</script> <input type="hidden" name="hiddenCoordX" id="hiddenCoordX">
						<input type="hidden" name="hiddenCoordY" id="hiddenCoordY">

						<input type="hidden" name="numero" value="${numero}"> <input
							type="hidden" name="registroOcorrencia" value="1"></td>
					</tr>
				</table>
				</form>

				</fieldset>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script language="JavaScript" src="js/google.js"></script>

</body>
</html>