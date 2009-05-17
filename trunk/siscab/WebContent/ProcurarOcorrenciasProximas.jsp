<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Atendimento" %>
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
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px; padding-top:5px;" colspan="2">
			
					<h2>Atendimentos :: Registrar Chamado :: Procurar Ocorrências Próximas</h2>
			
						<fieldset style="width:450px"><legend>&nbsp;Resultado da Procura&nbsp;</legend>							
							
							<% ArrayList atendimento=(ArrayList<Atendimento>) request.getAttribute("listaAtendimentosProximos");
							
							if(atendimento.size()== 0){ %>
							<h3>Nenhum atendimento não finalizado registrado em:</h3>
							
							<table width="90%">
								<tr>
									<td><strong>Munic&iacute;pio:</strong> ${municipio}</td>
									<td align="right"><strong>Bairro:</strong> ${bairro}</td>
								</tr>
							</table>								
							
							
							
							<%} else{ %>
							
							<h3>Atendimentos não finalizados registrados em:</h3>
							
							<table width="90%">
								<tr>
									<td><strong>Munic&iacute;pio:</strong> ${municipio}</td>
									<td align="right"><strong>Bairro:</strong> ${bairro}</td>
								</tr>
							</table>	
							
							<%
							
							for(int i=0;i<atendimento.size();i++){
									Atendimento atend =(Atendimento)atendimento.get(i);
							   %>																																																
														
							<fieldset>	
							<font color="darkgreen"> 
								<strong>Número do Atendimento:</strong> <%= atend.getAtendimento_numero()%> <br>
								<strong>Bairro da Ocorrencia:</strong> <%= atend.getBairro()%> <br>
								<strong>Rua:</strong> <%= atend.getLogradouro()%> <br>
								<strong>Status do atendimento:</strong> <%= atend.getStatus_atendimento()%>
							</font>
							</fieldset>
							<%} 
							}%>
							
							</fieldset>													
								<input type="button" value="Fechar" onclick="window.close()"/>







</body>
</html>