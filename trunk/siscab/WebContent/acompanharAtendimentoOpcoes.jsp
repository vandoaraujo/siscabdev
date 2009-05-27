<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimento, modelo.OBM" %>
<%@ page import="java.util.ArrayList" %>
<%@	page import="java.text.SimpleDateFormat" %>

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
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); 
String FORMATACAO_DATA = "dd/MM/yyyy HH:mm:ss";
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
											
						<h2>Atendimentos :: Acompanhar Atendimento :: Ficha do Atendimento</h2>
						
						<table border="0" cellpadding="0" cellspacing="0" width="70%">
							<tr>
								<td valign="top">
											
									<fieldset style="width:95%"><legend>&nbsp;Dados do Atendimento&nbsp;</legend>
									
									<%! Atendimento atendimento=null; %>
									
									<% atendimento = (Atendimento) request.getSession().getAttribute("atendimentoAtual"); 
									request.getSession().setAttribute("atendimentoAtual", atendimento);
									%>
									
									<form action="AplicaAcaoAtendimentoDiversasOpcoes" method="post">
										<table>
											<tr>
												<td nowrap="nowrap">Número Atendimento:</td>
												<td><strong><%= atendimento.getAtendimento_numero()%></strong></td>
											</tr>
											<tr>
												<td nowrap="nowrap">Status:</td>
												<td><%=atendimento.getStatus_atendimento()%></td>
											</tr>
											<tr>
												<td nowrap="nowrap">Tipo de Ocorrência:</td>
												<td><%=atendimento.getTipoocorrencia().getTipoocorrencia_descricao()%></td>
											</tr>
											<tr>
												<td>Municipio:</div></td>
												<td><%= atendimento.getMunicipio_id().getMunicipio_nome() %></td>
											</tr>
											<tr>
												<td>Bairro:</td>
												<td><%= atendimento.getBairro() %></td>
											</tr>
											<tr>
												<td>Logradouro:</td>
												<td><%= atendimento.getLogradouro() %></td>
											</tr>
											<tr>
												<td nowrap="nowrap">Nro. Complemento:</td>
												<td><%=atendimento.getNumcompl()%></td>
											</tr>																						
											<tr>
												<td nowrap="nowrap">Latitude:</td>
												<td><%=atendimento.getCoordx()%></td>
											</tr>							
																									<tr>
												<td nowrap="nowrap">Longitude:</td>
												<td><%=atendimento.getCoordy()%></td>
											</tr>
										</table>
									</fieldset>	
								</td>
								<td valign="top">
									<fieldset style="width:60%"><legend>&nbsp;Opções&nbsp;</legend>
										<input type="submit" value="Editar" onclick="this.form.operacaoARealizar.value=2"/>
										<br />
										<a href="#" onclick="window.open('AplicaAcaoAtendimentoDiversasOpcoes?operacaoARealizar=3&registroAtendimento=<%=atendimento.getAtendimento_numero()%>')"><img src="img/btnSugestaoRota.gif" border="0"></a>
										<br />
										<input type="submit" value="Viaturas Empenhadas" onclick="this.form.operacaoARealizar.value=4"/>
										<br />
										<input type="submit" value="Vitimas" onclick="this.form.operacaoARealizar.value=5"/>
										<br />
										<input type="submit" value="Servicos Executados" onclick="this.form.operacaoARealizar.value=6"/>
										<br />
										<input type="submit" value="Finalizar Atendimento" onclick="this.form.operacaoARealizar.value=7"/>							
										<br />
										<input type="hidden" name="operacaoARealizar" value ="">								
										<input type="hidden" name="registroAtendimento" value = "<%= atendimento.getAtendimento_numero() %>"/>
										</form>
										<form action="AcompanharAtendimentos" method="post" style="display:inline;">
							 				<input type="submit" value="Voltar"/>
							 			</form>	
							 		</fieldset>
								</td>
							</tr>
							<tr>
								<td>
								<fieldset style="width:95%"><legend>&nbsp;Dados do Chamado&nbsp;</legend>
																		
									<form action="AplicaAcaoAtendimentoDiversasOpcoes" method="post">
										<table>
											<tr>
												<td nowrap="nowrap">Número Chamado:</td>
												<td><strong><%= atendimento.getChamado_id().getId()%></strong></td>
											</tr>
											<tr>
												<td>Data/Hora Inicio:</td>
												<td><%=new SimpleDateFormat(FORMATACAO_DATA).format(atendimento.getChamado_id().getHorainicio())%></td>
											</tr>
											<tr>
												<td>Data/Hora Fim:</td>
												<td><%= new SimpleDateFormat(FORMATACAO_DATA).format(atendimento.getChamado_id().getHoratermino())%></td>
											</tr>
											<tr>
												<td>OBM que atendeu:</div></td>
												<td><%= atendimento.getChamado_id().getObm().getNome()%></td>
											</tr>
											<tr>
												<td>Origem do Chamado:</td>
												<td><%= atendimento.getChamado_id().getOrigem() %></td>
											</tr>
											<tr>
												<td>Nome Solicitante:</td>
												<td><%= atendimento.getChamado_id().getNomeSolicitante() %></td>
											</tr>
											<tr>
												<td>Telefone Solicitante:</td>
												<td><%=atendimento.getChamado_id().getTelefoneSolicitante()%></td>
											</tr>																						
											<tr>
												<td>Nº aprox. vítimas:</td>
												<td><%=atendimento.getChamado_id().getNumaproxvitimas()%></td>
											</tr>							
											<tr>
												<td>Informações Complementares:</td>
												<td><%=atendimento.getChamado_id().getInfocomplementares()%></td>
											</tr>
										</table>
									</fieldset>	
								</td>		
								<td></td>					
							</tr>								
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>				

</form>
</body>
</html>