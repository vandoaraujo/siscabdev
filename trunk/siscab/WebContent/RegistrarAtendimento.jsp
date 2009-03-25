<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.TipoOcorrencia,dao.TipoOcorrenciaDao,modelo.OBM,modelo.Usuario,modelo.NaturezaChamado,modelo.Municipio,dao.OBMDao,modelo.StatusAtendimento,modelo.TipoOcorrencia" %>
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
	<script>
	function habilitaCombo(priID, prstHabilita){
	    if (prstHabilita == 's')
	        document.getElementById(priID).disabled = false;
	    else
	        document.getElementById(priID).disabled = true;
	}

	</script>
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
						<a href="#" onclick="window.close();"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:450px"><legend>&nbsp;Registrar Atendimentonbsp;</legend>
						Obs: O presente Chamado possui uma Natureza de Solicita��o de Socorro.<br>
						
						Tela de Tranfer�ncia de Atendimento de Obms<br>
						
						
						<%!String municipio=null;%>
						
						<%
													municipio = (String)request.getAttribute("municipio");
												%>
						
						<form action="EfetivaAtendimento" method="post">
						
						
						
						<tr>Numero gerado: <input name="idAtendimento" type="text" readonly="readonly" value="${idNumeroAtendimento}"></tr><br>
						<tr>OBM a Prestar Atendimento: <input name="obmAtendimento" type="text" readonly="readonly" value="${obmAtendimento}"></tr><br>
						<tr>Bairro: <input name="bairro" type="text" readonly="readonly" value="<%=request.getAttribute("bairro")%>"> </tr><br>
						<tr>Municipio <select name="municipio">
						<option><%=municipio%></option>
						</select>
							
							<font color="blue">Voc� deseja repassar o atendimento para o COCB? </font>	Sim: <input name="radiobutton" type="radio" value="sim" onClick="habilitaCombo('seleciona','s');">
							N�o: <input name="radiobutton" type="radio" value="nao" checked onClick="habilitaCombo('seleciona','n');">
							
							<%
															Usuario usu = (Usuario)getServletContext().getAttribute("usuarioCorrente");
																																if(usu.getPerfil().getId() == 3){
														%> 
									
							<select name="obmRepassaAtendimento" id="seleciona" disabled="disabled">
							<option selected>COCB
										
								<%
								}
							%>
								
							</option>
							</select>
							</tr>
							<br>
							<tr>Coord X: <input name="coordX" type="text" ></tr><br>
							<tr>Coord Y: <input name="coordY" type="text"></tr><br>
							<tr>Logradouro: <input name="logradouro" type="text" size=30 value="${endereco}"><p><br>
							<tr>Num. Complemento: <input name="numComplemento" type="text" size=10 value="${numero}"></tr><br>
								
							<br>
					<tr><label>Tipo Ocorrencia:</label>
									<td>
										<select name="tipoOcorrencia">
		 								<%
		 									List<TipoOcorrencia> tipos = TipoOcorrenciaDao.getInstance().listarTodosTiposOcorrencias();
		 										 																	//ArrayList<String> tiposViaturas = tipo.getTiposViaturas();
		 										 																	for(int i=0;i<tipos.size();i++){
		 										 																		out.print("<option>"+tipos.get(i).getTipoocorrencia_descricao().toString());
		 										 												 				}
		 										 																		out.println("</select>");
		 								%></td></tr>	
		
		
		
	<tr>
	Status do Atendimento:<br>	
	<select name="status">
	<option> Pendente
    </select></tr><br>
			
	<input type="submit" value="Registrar Atendimento">
	
</form>

</body>
</html>