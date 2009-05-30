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
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;" colspan="2">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="109" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						<fieldset style="width:450px"><legend>&nbsp;Registrar Atendimento&nbsp;</legend>
						Obs: O presente Chamado possui uma Natureza de Solicitação de Socorro.<br>
						
						Tela de Tranferência de Atendimento de Obms<br>
						
						
						<%!String municipio=null;
						
						%>
						
						<% municipio = (String)request.getAttribute("municipio"); %>
						
						<form action="EfetivaAtendimento" method="post">
						<table>
							<tr>							
								<td>Numero gerado:</td>
								<td><input name="idAtendimento" type="text" readonly="readonly" value="${idNumeroAtendimento}"></td>
							</tr>
							<tr>
								<td>OBM a Prestar Atendimento:</td>
								<td><input name="obmAtendimento" type="text" readonly="readonly" value="${obmAtendimento}"></td>
							</tr>
							<tr>
								<td>Bairro:</td>
								<td><input name="bairro" type="text" readonly="readonly" value="<%=request.getAttribute("bairro")%>"></td>
							</tr>
							<tr>
								<td>Municipio:</td>
								<td><select name="municipio">
									<option><%=municipio%></option>
									</select>
								</td>
							<tr>
								<td colspan="2">
								<font color="blue">Você deseja repassar o atendimento para o COCB? </font>	
								Sim: <input name="radiobutton" type="radio" value="sim" onClick="habilitaCombo('seleciona','s');">
								Não: <input name="radiobutton" type="radio" value="nao" checked onClick="habilitaCombo('seleciona','n');">
								</td>
							<tr>
								<td><% 
										if(usu.getPerfil().getId() == 3){
									%> 
								</td>
								<td>									
									<select name="obmRepassaAtendimento" id="seleciona" disabled="disabled">
									<option selected>COCB
												
										<%
										}
									%>
										
									</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>Coord X:</td>
								<td><input name="coordX" type="text" readonly="readonly" value="<%=request.getParameter("hiddenCoordX")%>"></td>
							</tr>
							<tr>
								<td>Coord Y:</td>
								<td><input name="coordY" type="text" readonly="readonly" value="<%=request.getParameter("hiddenCoordY")%>"></td>
							</tr>
							<tr>
								<td>Logradouro:</td>
								<td><input name="logradouro" type="text" size=30 value="${endereco}"></td>
							<tr>
								<td>Num. Complemento:</td>
								<td><input name="numComplemento" type="text" size=10 value="${numero}"></td>
							</tr>
							<tr>
								<td>Tipo Ocorrencia:</td>
								<td><select name="tipoOcorrencia">
			 						<%
			 						List<TipoOcorrencia> tipos = TipoOcorrenciaDao.getInstance().listarTodosTiposOcorrencias();
									//ArrayList<String> tiposViaturas = tipo.getTiposViaturas();
									for(int i=0;i<tipos.size();i++){
										out.print("<option>"+tipos.get(i).getTipoocorrencia_descricao().toString());
				 					}
									out.println("</select>");
			 						%>
			 					</td>
			 				</tr>	
							<tr>
								<td>Status do Atendimento:</td>	
								<td><select name="status">
									<option> Pendente
							    	</select>
							    </td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Registrar Atendimento"></td>
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
	
</body>
</html>