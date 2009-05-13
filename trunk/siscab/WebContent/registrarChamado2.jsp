<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,dao.OBMDao,modelo.Usuario,modelo.NaturezaChamado,modelo.Municipio,modelo.TipoOcorrencia,dao.TipoOcorrenciaDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="dao.NaturezaChamadoDao"%><html>
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
								
												
												
						<table>
							<tr>
								<td valign="top">
								<form action="FinalizarChamadoIniciarAtendimento" method="post" style="display:inline">
								<fieldset style="width:450px"><legend>&nbsp;Dados do Chamado&nbsp;</legend>
								
									<table>						
										<tr>
											<td nowrap="nowrap">Numero do Chamado:</td>
											<td>${idChamado}</td>
										</tr>

										<%! String grava = null;%>
										
											<% SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
													Calendar cal = Calendar.getInstance(); 
													grava = data.format(cal.getTimeInMillis());  %>						
									
										<tr>
											<td width="100px" nowrap="nowrap">Hora do Chamado:</td>
											<td><%= grava %></td>
										</tr>						
										<!-- <tr>
											<td>Usuário:</td>
											<td><input name="usuario" type="text" readonly="readonly" value="${usuario.nomeGuerra}"></td>
										</tr>-->
										<tr>
											<td>Obm que atendeu:</td>
											<td>${usuario.obm.nome}</td>
										</tr>
										<tr>
											<td>Origem:</td>
											<td>
												<select name="origem">
												<option>Telefone 
												<option>Pessoalmente
												</select>
											</td>
										</tr>
										<tr>
											<td>Nome Solicitante:</td>
											<td><input name="nomeSolicitante" type="text" size=40></td>
										</tr>
										<tr>
											<td>Telefone Solicitante:</td>
											<td><input name="telefoneSolicitante" type="text"></td>
										</tr>										
									</table>
									</fieldset>
								</td>
								<td valign="top">
									<fieldset style="width:450px"><legend>&nbsp;Finalização do Chamado&nbsp;</legend>
									<table>							
										<tr>
											<td colspan="2">
												<input type="submit" value="Procurar Ocorrencias Proximas" onclick="this.form.operacaoARealizar.value=2" >
											</td>
										</tr>
										<tr>
											<td><br/></td>
											<td></td>
										</tr>
										<tr>			
											<td nowrap="nowrap" width="40%">Natureza do Chamado:</td>									
											<td>											
											<select name="naturezaChamado" id="naturezaChamado">
											<%
											List<NaturezaChamado> natureza = NaturezaChamadoDao.getInstance().listarTodasNaturezasChamado();
									
											for(int i=0;i<natureza.size();i++){
												out.print("<option>"+natureza.get(i).getNaturezachamado_descricao().toString());
						 					}
											out.println("</select>");
											%>
											</td>
										</tr>
										<tr>			
											<td nowrap="nowrap" width="40%">Encaminhar para:</td>									
											<td>
											<% if(usu.getPerfil().getId() == 3){ %>
											Você deseja repassar o atendimento para o COCB?	
											Sim: <input name="radiobutton" type="radio" value="sim" onClick="habilitaCombo('obmRepassaAtendimento','s');">
											Não: <input name="radiobutton" type="radio" value="nao" checked onClick="habilitaCombo('obmRepassaAtendimento','n');">
											
											<select name="obmRepassaAtendimento" id="obmRepassaAtendimento" disabled="disabled">
											<option selected>COCB											
											</option>
											</select>
											<%	} else {  %>																													
											<select name="obmRepassaAtendimento" id="obmRepassaAtendimento">
											<%
											List<OBM> obm = OBMDao.getInstance().listarTodasOBMsExcetoCOCB();
									
											for(int i=0;i<obm.size();i++){
												out.print("<option>"+obm.get(i).getNome().toString());
						 					}
											out.println("</select>");
											%>
											<% } %>										
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<input type="hidden" name="obmSolicitada" value ="${usuario.obm.nome}">
												<input type="submit" value="FinalizarChamadoIniciarAtendimento" onclick="this.form.operacaoARealizar.value=1" >
											</td>
										</tr>		
									</table>
									</fieldset>
								</td>
							</tr>
							<tr>
								<td>
								<fieldset style="width:450px"><legend>&nbsp;Dados da ocorrência&nbsp;</legend>
									<table>	
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
										  	<td>Município:</td>
										  	<td><select name="municipio">													
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
											</td>				
										</tr>
										<tr>
											<td>Bairro:</td>
											<td><input name="bairro" type="text" size=20></td>							
										</tr>
										<tr>
											<td>Logradouro</td>
											<td><input name="endereco" type="text" size=30></td>							
										</tr>
										<tr>
											<td>Nº Complemento</td>
											<td>
												<input name="numero" type="text" size=6> 												
												<input type="submit" value="Localizar no Mapa" onclick="this.form.operacaoARealizar.value=3"></td>
												<input type="hidden" name="registroOcorrencia" value ="1">
												<input type="hidden" name="operacaoARealizar" value ="">	
												<input type="hidden" name="idChamado" value ="${idChamado}">																																
											</td>							
										</tr>
										<tr>
											<td>Latitude</td>
											<td><input name="CoordX" type="text" size=30><div id="CoordX"></div></td>							
										</tr>
										<tr>
											<td>Longitude</td>
											<td><input name="CoordY" type="text" size=30><div id="CoordY"></div></td>							
										</tr>
										<tr>
											<td>Num aproximado vítimas:</td>
											<td><input name="numAproximadoVitimas" type="text" size=8></td>
										</tr>
										<tr>	
											<td>Info Complementares:</td>
											<td><textArea NAME="infoComplementares" COLS=30 ROWS=4></textArea></td>				
										</tr>										
									
									</table>
									</fieldset>
									</form>
								</td>
								<td>
								
								</td>
							</tr>					
						</table>
						
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
</body>
</html>