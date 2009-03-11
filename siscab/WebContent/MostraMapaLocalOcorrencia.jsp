<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado,modelo.NaturezaChamados,dao.NaturezaChamadosDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date, java.text.*" %>
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
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<a href="#" onclick="window.close();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:450px"><legend>&nbsp;Finalizar Chamado&nbsp;</legend>
						<table>
							<tr>
								<td> 
									GOOGLE MAPS
								</td>
							</tr>
						</table>
						
				


<h4> </h4><br>

<div id="map_canvas" style="width: 500px; height: 300px"></div>

<%! String municipio=null; %>

<% municipio = (String)request.getAttribute("municipio"); %>

	<form action="FinalizarChamadoIniciarAtendimento" onsubmit="showAddress(this.address.value); return false" method="post">
		<table>
		<tr><td>Origem do Chamado: <input name="origemChamado" type="text" readonly="readonly" value=${origemChamado}></td></tr>
		<tr><td>Nome do Solicitante: <input name="nomeSolicitante" type="text" readonly="readonly" value=${nomeSolicitante}></td></tr>
		<tr><td>Telefone: <input name="telefone" type="text" readonly="readonly" value=${telefone}></td></tr>
		<tr><td>Número Aproximado de Vitimas: <input name="numAproxVitimas" type="text" readonly="readonly" value=${aproxVitimas}></td></tr>
		<tr><td>Obm Solicitada: <input name="obmSolicitada" type="text" readonly="readonly" value=${nomeObmUsuario}></td></tr>
		<tr><td> <input name="infoComplementares" type="hidden" readonly="readonly" value=${infoComplementares}></td></tr>
		<tr><td> <input name="numeroChamado" type="hidden" readonly="readonly" value=${numeroChamado}></td></tr>
		<tr><td>Município: <select name="municipio">
		 <option><%= municipio %></option>
		</select>
		<tr><td> <input name="bairro" type="hidden" readonly="readonly" value=${bairro}></td></tr>
	
		
		
			<tr><td>Natureza Chamado:</td>
									<td>
										<select name="naturezaChamado">
		 								<% 
											List<NaturezaChamados> tipos = NaturezaChamadosDao.getInstance().listarTodasNaturezasChamado();
											for(int i=0;i<tipos.size();i++){
												out.print("<option>"+tipos.get(i).getNaturezachamado_descricao());
						 				}
												out.println("</select>");
									%></td></tr>			
		
	<br>
			<input type="submit" value="Finalizar Chamado" onclick="this.form.operacaoARealizar.value=1" >
			<input type="hidden" name="operacaoARealizar" value ="">
			<input type="hidden" name="registroOcorrencia" value ="1"> 
			
			</form>

</body>
</html>