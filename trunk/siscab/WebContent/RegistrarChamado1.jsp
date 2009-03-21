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
						<a href="#" onclick="window.close();"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:450px"><legend>&nbsp;Cadastro de Chamados&nbsp;</legend>
		
						<form action="LocalizaOcorrencia" method="post">						
						<table>
							<tr>
								<td valign="top">
									<fieldset style="width:450px"><legend>&nbsp;Parte 1&nbsp;</legend>
									<table>						
										<%! String grava = null;%>
										
											<% SimpleDateFormat data = new SimpleDateFormat("HH:mm");
													Calendar cal = Calendar.getInstance(); 
													grava = data.format(cal.getTimeInMillis());  %>						
										
										
										<tr>
											<td width="100px" nowrap="nowrap">Hora do Chamado:</td>
											<td><%= grava %></td>
										</tr>						
										<tr>
											<td>Numero gerado:</td>
											<td><input name="idChamado" type="text" readonly="readonly" value=${idChamado}></td>
										</tr>
										<tr>
											<td>Usuário:</td>
											<td><input name="usuario" type="text" readonly="readonly" value=${usuario.nomeGuerra}></td>
										</tr>
										<tr>
											<td>Obm do Usuário:</td>
											<td><input name="obmUsuario" type="text" readonly="readonly" value=${usuario.obm.nome}></td>
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
											<td></td>
											<td><input type="submit" value="Procurar Ocorrencias Proximas" onclick="this.form.operacaoARealizar.value=2" ></td>
										</tr>	
									</table>
									</fieldset>
								</td>
								<td>
									<fieldset style="width:450px"><legend>&nbsp;Parte 2&nbsp;</legend>
									<table>							
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
											<td><input name="nomeSolicitante" type="text" ></td>
										</tr>
										<tr>
											<td>Telefone Solicitante:</td>
											<td><input name="telefoneSolicitante" type="text"></td>
										</tr>
										<tr>
											<td>Num aproximado vítimas:</td>
											<td><input name="numAproximadoVitimas" type="text" size=8></td>
										</tr>
										<tr>
											<td>Endereço / Número:</td>
											<td><input name="endereco" type="text" size=30><input name="numero" type="text" size=6></td>							
										</tr>
										<tr>	
											<td>Info Complementares:</td>
											<td><textArea NAME="infoComplementares" COLS=30 ROWS=4></textArea></td>				
										</tr>
										<tr>
											<td></td>
											<td><input type="submit" value="Localizar no Mapa" onclick="this.form.operacaoARealizar.value=1" ></td>
											<input type="hidden" name="operacaoARealizar" value ="">
											<input type="hidden" name="registroOcorrencia" value ="1">
										</tr>
									</table>
									</fieldset>
								</td>
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