<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado,modelo.NaturezaChamados,dao.NaturezaChamadosDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date, java.text.*" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
</head>
<body>
<h4> Configurar esta tela pra visualizar um mapa do Google Maps</h4><br>

<%! String municipio=null; %>

<% municipio = (String)request.getAttribute("municipio"); %>

	<form action="FinalizarChamadoIniciarAtendimento" method="post">
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
		
	
			<input type="submit" value="Finalizar Chamado" onclick="this.form.operacaoARealizar.value=1" >
			<input type="hidden" name="operacaoARealizar" value ="">
			<input type="hidden" name="registroOcorrencia" value ="1"> 
			
			</form>

</body>
</html>