<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.NaturezaChamados,modelo.Municipio" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
					</td>				
				</tr>
				</table>

<form action="LocalizaOcorrencia" method="post">
<table>
<%! String grava = null;%>

	<% SimpleDateFormat data = new SimpleDateFormat("HH:mm");
			Calendar cal = Calendar.getInstance(); 
			grava = data.format(cal.getTimeInMillis());  %>
			
	<h2 font color="blue">Cadastro de Chamados</font></h2><br>		


Hora do Chamado: <%= grava %>

<tr>Numero gerado: <input name="idChamado" type="text" readonly="readonly" value=${idChamado}></tr>
<tr>Usuário: <input name="usuario" type="text" readonly="readonly" value=${usuario.nomeGuerra}></tr>
<tr>Obm do Usuário: <input name="obmUsuario" type="text" readonly="readonly" value=${usuario.obm.nome}></tr>
  	  	  	
<tr>Município: <select name="municipio">
	
	<!-- Popula a combo de municipios que aparecerá na tela -->
	<%
    
 	ArrayList<Municipio> municipio = (ArrayList)request.getAttribute("municipios");
	%><option selected> 
	<% 
	for(Municipio m: municipio){
	 out.println("<option>"+m.getMunicipio_nome());
 	}
	out.println("</select>");
	%>
	
	<tr>Bairro: <input name="bairro" type="text" size=20>&nbsp;&nbsp;&nbsp; <input type="submit" value="Procurar Ocorrencias Proximas" onclick="this.form.operacaoARealizar.value=2" ></tr>  	  	
  	
	<tr>Origem: <select name="origem">
	<option>Telefone 
	<option>Pessoalmente
	</select></tr>		

	<tr>Nome Solicitante: <input name="nomeSolicitante" type="text" ></tr>
	<tr>Telefone Solicitante: <input name="telefoneSolicitante" type="text"></tr>
	<tr>Num aproximado vítimas: <input name="numAproximadoVitimas" type="text" size=8><p>
	<tr>Info Complementares: <textArea NAME="infoComplementares" COLS=30 ROWS=4></textArea></tr>
	
			
	<input type="submit" value="Localizar no Mapa" onclick="this.form.operacaoARealizar.value=1" >
	<input type="hidden" name="operacaoARealizar" value ="">
	<input type="hidden" name="registroOcorrencia" value ="1"> 
	</table>
</form>

</body>
</html>