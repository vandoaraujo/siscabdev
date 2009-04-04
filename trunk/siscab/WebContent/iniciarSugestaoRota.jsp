<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,modelo.OBM,dao.OBMDao,modelo.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
	<script id="api" type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA1rJRd_d6NTBRsxYq3I7erBSVPiKh14QUveM1LzMKfwwniz5cMBRktlNQQD2Mh4zRyiEDe7djlt6huA"></script>


<body onload="carregaMapa();">
<script language="JavaScript1.2">mmLoadMenus();</script>

<% 	
	String CoordX_Operador = null;
	String CoordY_Operador = null;
%>

<% CoordX_Operador = request.getAttribute("CoordX_Operador").toString(); %>
<% CoordY_Operador = request.getAttribute("CoordY_Operador").toString(); %>


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
						<a href="#" onclick="fechar()"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:520px"><legend>&nbsp;Sugestão de Rota&nbsp;</legend>	
							<form>
								<input type="hidden" name="CoordX_Operador" id="CoordX_Operador" value="<%=request.getAttribute("CoordX_Operador") %>">
								<input type="hidden" name="CoordY_Operador" id="CoordY_Operador" value="<%=request.getAttribute("CoordY_Operador") %>">
								<input type="hidden" name="CoordX_Acidente" id="CoordX_Acidente" value="<%=request.getAttribute("CoordX_Acidente") %>">
								<input type="hidden" name="CoordY_Acidente" id="CoordY_Acidente" value="<%=request.getAttribute("CoordY_Acidente") %>">								
							</form>	
							<%! Usuario usuario; %>
							<% usuario = (Usuario)request.getAttribute("usuario");%>
													
													
							<table>
								<tr>
									<td>
										<% 	String CoordX_Acidente = null;
										   	String CoordY_Acidente = null;
										   
											CoordX_Acidente = request.getAttribute("CoordX_Acidente").toString();
											CoordY_Acidente = request.getAttribute("CoordY_Acidente").toString();
											
											System.out.println(CoordX_Acidente);
											if (CoordX_Acidente != null)
											{
										%>
										<table>
											<tr>
												<td>
													<fieldset style="width:220px"><legend>&nbsp;Ponto de Origem&nbsp;</legend>	
													<table width="100%">
														<tr>
															<td width="80%">
																<div id="NomeOrigem">
																	<%=request.getAttribute("NomeOrigem").toString()%>
																</div>
																<div id="EnderecoOrigem">
																	<%=request.getAttribute("EnderecoOrigem").toString()%>
																</div>
																<div id="BairroOrigem">
																	<%=request.getAttribute("BairroOrigem").toString()%>
																</div>
																<div id="MunicipioOrigem">
																	<%=request.getAttribute("MunicipioOrigem").toString()%>
																</div>
															</td>
															<td align="center" valign="top">
																<img src="img/pontoOrigem.gif" />
															</td>
														</tr>
													</table>													
													</fieldset>
												</td>
												<td>
													<fieldset style="width:220px"><legend>&nbsp;Ponto de Destino&nbsp;</legend>	
													<table width="100%">
														<tr>
															<td width="80%"> 
																<div id="NumeroAtendimento">
																	Atendimento: Nº <%=request.getAttribute("NumeroAtendimento").toString()%>
																</div>
																<div id="EnderecoDestino">
																	<%=request.getAttribute("EnderecoDestino").toString()%>
																</div>
																<div id="BairroDestino">
																	<%=request.getAttribute("BairroDestino").toString()%>
																</div>
																<div id="MunicipioDestino">
																	<%=request.getAttribute("MunicipioDestino").toString()%>
																</div>
																</td>
															<td align="center" valign="top">
																<img src="img/pontoDestino.gif" />
															</td>
														</tr>
													</table>
													</fieldset>
												</td>
											</tr>
										</table>
										
										<div id="map_canvas">
											Map loading..
											<noscript>
												Habilite o uso de JavaScript em seu browser para exibir o Mapa da Google
											</noscript>
										</div>	
										<!-- <script language="JavaScript" src="js/google.js"></script> -->
										<script type="text/javascript">
											function carregaMapa(){
												var CoordX_Operador = document.getElementById("CoordX_Operador").value;
												var CoordY_Operador = document.getElementById("CoordY_Operador").value;
												var CoordX_Acidente = document.getElementById("CoordX_Acidente").value;
												var CoordY_Acidente = document.getElementById("CoordY_Acidente").value;
												
												var map;
											    var directionsPanel;
											    var directions;

										      	map = new GMap2(document.getElementById("map_canvas"));
										      	map.setCenter(new GLatLng(-22.9049164, -43.1098755), 15);
										      	map.addControl(new GSmallZoomControl());

										      	
										      	//directionsPanel = document.getElementById("route");
										      	
										      	directions = new GDirections(map, directionsPanel);
										      	directions.load("from: "+CoordX_Operador+","+CoordY_Operador+" to: "+CoordX_Acidente+","+CoordY_Acidente+"");

										      											      											    
											}
										</script>
									</td>								
										<% }else{ %>
									<td>	
										<h3>Rota não encontrada.</h3>
									</td>	
										<% } %>
								</tr>
								<tr>
									<td>	
										<form action="AcompanharAtendimentos" method="post" style="display:inline;">
											<input type="submit" value="Voltar"/>
										</form>
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