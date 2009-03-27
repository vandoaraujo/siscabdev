<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimento" %>
<%@ page import="java.util.ArrayList" %>
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
						<a href="#" onclick="fechar()"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
					<fieldset style="width:780px"><legend>&nbsp;Atendimento&nbsp;</legend>

					<%! Atendimento atendimento=null; %>
					
					<% atendimento = (Atendimento) request.getSession().getAttribute("atendimentoAtual"); 
					request.getSession().setAttribute("atendimentoAtual", atendimento);
					%>
					
					<form action="AplicaAcaoAtendimentoDiversasOpcoes" method="post">
					
					
					
					<table>
						<tr>
							<td width="100px"><div style="color:red">Municipio:</div></td>
							<td><div style="color:blue"><%= atendimento.getMunicipio_id().getMunicipio_nome() %></div></td>
						</tr>
						<tr>
							<td><div style="color:red">Bairro:</div></td>
							<td><div style="color:blue"><%= atendimento.getBairro() %></div></td>
						</tr>
						<tr>
							<td><div style="color:red">Logradouro:</div></td>
							<td><div style="color:blue"><%= atendimento.getLogradouro() %></div></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="Editar" onclick="this.form.operacaoARealizar.value=2"/>
								<input type="submit" value="Sugestão de Rota" onclick="this.form.operacaoARealizar.value=3"/>
								<input type="submit" value="Viaturas Empenhadas" onclick="this.form.operacaoARealizar.value=4"/>
								<input type="submit" value="Vitimas" onclick="this.form.operacaoARealizar.value=5"/>
								<input type="submit" value="Servicos Executados" onclick="this.form.operacaoARealizar.value=6"/>
								<input type="submit" value="Finalizar Chamado" onclick="this.form.operacaoARealizar.value=7"/>							
								<input type="hidden" name="operacaoARealizar" value ="">
								<input type="hidden" name="registroAtendimento" value = <%= atendimento.getAtendimento_numero() %>>
							</td>
						</tr>
					</table>
					</fieldset>					

</form>
</body>
</html>