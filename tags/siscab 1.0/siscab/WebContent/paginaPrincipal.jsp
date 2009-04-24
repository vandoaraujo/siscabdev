<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.util.ArrayList" %>
<%@ page import= "java.util.Vector,modelo.Usuario, modelo.Atendimento, modelo.OBM, dao.OBMDao, dao.AtendimentoDao" %>
<%@ page import= "java.util.Date,java.text.SimpleDateFormat,java.util.Calendar,java.util.GregorianCalendar" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
	<script id="api" type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA1rJRd_d6NTBRsxYq3I7erBSVPiKh14QUveM1LzMKfwwniz5cMBRktlNQQD2Mh4zRyiEDe7djlt6huA"></script>
</head>

<body onload="init();" onunload="GUnload()">
<script language="JavaScript1.2">mmLoadMenus();</script>

<%  
Vector siscab = new Vector();
Usuario usu =( Usuario) request.getSession().getAttribute("usuario");

//SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy - hh:mm");

Calendar cal = Calendar.getInstance(); 
String grava = data.format(cal.getTime());  
%>
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
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						<fieldset style="width:750px"><legend>&nbsp;Situação de Atendimentos&nbsp;</legend>	
							<form>
								<input type="hidden" name="q" id="q" value="RJ Brasil">
							</form>	
							
							<table>
								<tr>
									<td valign="top">
										
										<fieldset style="width:150px"><legend>&nbsp;OBMs Ativas&nbsp;</legend>
										<table>
											<tr>
												<td>							
													<%
													 int status;
													 List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
													 for(OBM s: obms){
												 		if (s.getStatus() == 1)
												 		{
												 		 	%><a href="#" onclick="showLocation2('OBM','><%=s.getLogradouro() %> <%=s.getNumCompl() %> <%=s.getBairro() %> <%=s.getMunicipio().getMunicipio_nome() %> RJ Brasil', 'Nome: <%=s.getNome() %>','Logradouro: <%=s.getLogradouro() %>', 'Nº: <%=s.getNumCompl() %>', 'Bairro: <%=s.getBairro() %>' ,'Município: <%=s.getMunicipio().getMunicipio_nome() %>')"><%=s.getBairro()%></a><br><%
												 		}
												 		else						 			
												 		{
												 			%><%
												 		}
													}
													%>																										
												</td>
											</tr>
										</table>
										</fieldset>
										
									</td>
									<td valign="top">
									
										<fieldset style="width:200px"><legend>&nbsp;Atendimentos Pendentes&nbsp;</legend>
											<table>
												<tr>
													<td>							
														
													<%
													 List<Atendimento> atendimentoPendente = AtendimentoDao.getInstance().listarAtendimentosNaoFinalizados();
													 for(Atendimento a: atendimentoPendente){												 		
												 		 	%><a href="#" onclick="showLocation2('Atendimento Pendente','><%=a.getLogradouro() %> <%=a.getNumcompl() %> <%=a.getBairro() %> <%=a.getMunicipio_id().getMunicipio_nome() %> RJ Brasil', 'Nº Atendimento: <%=a.getAtendimento_numero() %>','Logradouro: <%=a.getLogradouro() %>', 'Nº: <%=a.getNumcompl() %>', 'Bairro: <%=a.getBairro() %>' ,'Município: <%=a.getMunicipio_id().getMunicipio_nome() %>')"><%=a.getAtendimento_numero()%></a><br><%
													}
													%>														   
														   																							
													</td>
												</tr>
											</table>
										</fieldset>
									
									</td>
									
									<td valign="top">
									
										<fieldset style="width:200px"><legend>&nbsp;Atendimentos em Andamento&nbsp;</legend>
											<table>
												<tr>
													<td>							
														
													<%
													 List<Atendimento> atendimentoAndamento = AtendimentoDao.getInstance().listarTodosAtendimentos();
													 for(Atendimento a: atendimentoAndamento){	
														 //if (a.getStatus_atendimento().equals()
												 		 	%><a href="#" onclick="showLocation2('Atendimento em Andamento','><%=a.getLogradouro() %> <%=a.getNumcompl() %> <%=a.getMunicipio_id().getMunicipio_nome() %> RJ Brasil', 'Nº Atendimento: <%=a.getAtendimento_numero() %>','Logradouro: <%=a.getLogradouro() %>', 'Nº: <%=a.getNumcompl() %>', 'Bairro: <%=a.getBairro() %>' ,'Município: <%=a.getMunicipio_id().getMunicipio_nome() %>')"><%=a.getAtendimento_numero()%></a><br><%
													}
													%>														   
														   																							
													</td>
												</tr>
											</table>
										</fieldset>
									
									</td>									
								</tr>
							</table>

							<table>
								<tr>
									<td valign="top">
										<div id="map_canvas">
											Map loading..
											<noscript>
												Habilite o uso de JavaScript em seu browser para exibir o Mapa da Google
											</noscript>
										</div> 	
										<script language="JavaScript" src="js/google.js"></script>
									</td>	
									<td valign="top">
										<fieldset style="width:200px"><legend id="label">Dados não selecionados</legend>
										<table>
											<tr>
												<td>
													<div id="nome"></div>
													<div id="logradouro"></div>
													<div id="numero"></div>
													<div id="bairro"></div>
													<div id="municipio"></div>													
												</td>
											</tr>
										</table>
										</fieldset>									
									</td>							
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>			
			</table>
		</td>
	</tr>
</table>


</body>
</html>