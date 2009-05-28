<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,dao.OBMDao,modelo.Usuario,modelo.NaturezaChamado,modelo.Municipio,modelo.TipoOcorrencia,dao.TipoOcorrenciaDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="dao.NaturezaChamadoDao"%><html>
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

function campoObrigatorioFinalizarChamado()
{	
	var indiceMunicipio = document.form.municipio.selectedIndex;

	 document.form.operacaoARealizar.value = 1;
	
	if ( (document.form.nomeSolicitante.value != null) && 
			(document.form.nomeSolicitante.value != "") &&
				(document.form.telefoneSolicitante.value != null) && 
					(document.form.telefoneSolicitante.value != "") &&
						(document.form.bairro.value != null) && 
							(document.form.bairro.value != "") &&
								(document.form.endereco.value != null) && 
									(document.form.endereco.value != "") &&
										(document.form.numero.value != null) && 
											(document.form.numero.value != "") && 
												(document.form.numAproximadoVitimas.value != null) && 
													(document.form.numAproximadoVitimas.value != "") &&
														(document.form.CoordX.value != null) && 
															(document.form.CoordX.value != "") &&
																(document.form.CoordY.value != null) && 
																	(document.form.CoordY.value != "") &&
																		(document.form.municipio.options[indiceMunicipio].text != "- - -") && 
																			(document.form.statusObm.options[indiceStatus].text != "- - -" ) )
		return true;
	else
	{
		alert("Favor preencher todos campos obrigatórios");	
		return false;
	}

}



function campoObrigatorioLocalizarNoMapa()
{	
	document.form.operacaoARealizar.value = 3;
	
	var indiceMunicipio = document.form.municipio.selectedIndex;
	
	if ( (document.form.bairro.value != null) && 
			(document.form.bairro.value != "") &&
				(document.form.endereco.value != null) && 
					(document.form.endereco.value != "") &&
						(document.form.numero.value != null) && 
							(document.form.numero.value != "") &&
								(document.form.municipio.options[indiceMunicipio].text != ""))
		return true;
	else
	{
		alert("Favor preencher todos campos obrigatórios");	
		return false;
	}

}

function campoObrigatorioProcurarOcorrenciasProximas()
{	
	document.form.operacaoARealizar.value = 2;
	
	var indiceMunicipio = document.form.municipio.selectedIndex;
	
	if ( (document.form.nomeSolicitante.value != null) && 
			(document.form.nomeSolicitante.value != "") &&
				(document.form.telefoneSolicitante.value != null) && 
					(document.form.telefoneSolicitante.value != "") &&
						(document.form.bairro.value != null) && 
							(document.form.bairro.value != "") &&
								(document.form.endereco.value != null) && 
									(document.form.endereco.value != "") &&
										(document.form.numero.value != null) && 
											(document.form.numero.value != "") &&
												(document.form.municipio.options[indiceMunicipio].text != ""))
		return true;
	else
	{
		alert("Favor preencher todos campos obrigatórios");	
		return false;
	}

}


function mascaraTelefone(e){
	var tecla=(window.event)?event.keyCode:e.which;
	if ((tecla == 8 || tecla == 0))
		return true;

	if((tecla > 47 && tecla < 58))
	{
		var telefone = document.form.telefoneSolicitante.value;
		if (telefone.length == 0)
		{
			telefone = telefone + '(';
			document.form.telefoneSolicitante.value = telefone;
			return true;              
		}
		if (telefone.length == 3){
			  telefone = telefone + ')';
			  document.form.telefoneSolicitante.value = telefone;
			  return true;
		}
		if (telefone.length == 8){
			  telefone = telefone + '-';
			  document.form.telefoneSolicitante.value = telefone;
			  return true;
		}
	}
	else
		return false;		

	return true;
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
								
						<h2>Atendimentos :: Registrar Chamado</h2>						
												
						<table>
							<tr>
								<td valign="top">
								<form action="FinalizarChamadoIniciarAtendimento" name="form" method="post" style="display:inline">
								<fieldset style="width:450px"><legend>&nbsp;Dados do Chamado&nbsp;</legend>
								
									<table>						
										<tr>
											<td nowrap="nowrap">Número do chamado:</td>
											<td><strong>${idChamado}</strong></td>
										</tr>

										<%! String grava;%>
										
											<% SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
													Calendar cal = Calendar.getInstance(); 
													grava = data.format(cal.getTimeInMillis());  %>						
									
										<tr>
											<td width="100px" nowrap="nowrap">Data/Hora inicio:</td>
											<td><strong><%= grava %></strong></td>
										</tr>						
										<tr>
											<td>Obm que atendeu:</td>
											<td><strong><%=usu.getObm().getNome() %></strong></td>
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
											<td>Nome solicitante:</td>
											<td><input name="nomeSolicitante" type="text" size=40 value="${nomeSolicitante}"></td>
										</tr>
										<tr>
											<td>Telefone solicitante:</td>
											<td><input name="telefoneSolicitante" type="text" maxlength="13" value="${telefone}" onKeyDown="return mascaraTelefone(event)"></td>
										</tr>										
									</table>
									</fieldset>
								</td>
								<td valign="top">
									<fieldset style="width:450px"><legend>&nbsp;Finalização do Chamado&nbsp;</legend>
									<table>							
										<tr>
											<td colspan="2">
												<input type="submit" value="Procurar Ocorrências Próximas" onclick="return campoObrigatorioProcurarOcorrenciasProximas();" >
											</td>
										</tr>
										<tr>
											<td><br/></td>
											<td></td>
										</tr>
										<tr>			
											<td nowrap="nowrap" width="40%">Natureza do chamado:</td>									
											<td>											
											<select name="naturezaChamado" id="naturezaChamado">
											<%
											List<NaturezaChamado> natureza = NaturezaChamadoDao.getInstance().listarTodasNaturezasChamado();
									
											for(int i=0;i<natureza.size();i++){
												out.print("<option>"+natureza.get(i).getNaturezachamado_descricao().toString());
						 					}
											out.println("</select> *");
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
											<option selected>COCB</option>
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
												<input type="hidden" name="obmSolicitada" value="<%=usu.getObm().getNome() %>">
												<input type="submit" value="Finalizar Chamado" onclick="return campoObrigatorioFinalizarChamado();">
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
											<td>Tipo de ocorrência:</td>
											<td><select name="tipoOcorrencia">
						 						<%
						 						List<TipoOcorrencia> tipos = TipoOcorrenciaDao.getInstance().listarTodosTiposOcorrencias();
												//ArrayList<String> tiposViaturas = tipo.getTiposViaturas();
												for(int i=0;i<tipos.size();i++){
													out.print("<option>"+tipos.get(i).getTipoocorrencia_descricao().toString());
							 					}
												out.println("</select> *");
						 						%>
						 					</td>
						 				</tr>	
										<tr>						
										  	<td>Município:</td>
										  	<td><select name="municipio">													
											<!-- Popula a combo de municipios que aparecerá na tela -->
											<%
										    
										 	ArrayList<Municipio> municipio = (ArrayList)request.getAttribute("municipios");
											%>
											<option selected>${municipio}
											<%
											for(Municipio m: municipio){
											 out.println("<option>"+m.getMunicipio_nome());
										 	}
											out.println("</select> *");
											%>
											</td>				
										</tr>
										<tr>
											<td>Bairro:</td>
											<td><input name="bairro" type="text" size=20 value="${bairro}"> *</td>							
										</tr>
										<tr>
											<td>Logradouro:</td>
											<td><input name="endereco" type="text" size=30 value="${endereco}"> *</td>							
										</tr>
										<tr>
											<td>Nº/Complemento:</td>
											<td>
												<input name="numero" type="text" size="6" value="${numero}" onkeypress="return SomenteNumeros(event);"> 												
												<input type="submit" value="Localizar no Mapa" onclick="return campoObrigatorioLocalizarNoMapa();"></td>
												<input type="hidden" name="registroOcorrencia" value ="1">
												<input type="hidden" name="operacaoARealizar" value ="">		
												<input type="hidden" name="idChamado" value ="${idChamado}">																																
											</td>							
										</tr>
										<tr>
											<td>Latitude:</td>
											<td><input name="CoordX" type="text" size=25 readonly="readonly" value="${hiddenCoordX}"> *<div id="CoordX"></div></td>							
										</tr>
										<tr>
											<td>Longitude:</td>
											<td><input name="CoordY" type="text" size=25 readonly="readonly" value="${hiddenCoordY}"> *<div id="CoordY"></div></td>							
										</tr>
										<tr>
											<td>Nº aprox vítimas:</td>
											<td><input name="numAproximadoVitimas" type="text" size=8></td>
										</tr>
										<tr>	
											<td colspan="2">Informações complementares:</td>			
										</tr>										
										<tr>	
											<td colspan="2"><textArea NAME="infoComplementares" COLS=50 ROWS=4>${infoComplementares}</textArea></td>				
										</tr>																			
									</table>
									</fieldset>
									( * ) Campos Obrigatórios
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