<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.Viatura" %>
<%@ page import="java.util.List" %>
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
										<td style="padding-left:20px;" colspan="2">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<a href="#" onclick="window.close();"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>											
					</td>
</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:750px"><legend>&nbsp;Finalizar Atendimento&nbsp;</legend>
							
							<form action="LiberarViaturasFecharAtendimento" method="post">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Este Atendimento possui as seguintes viaturas abaixo alocadas e serão liberadas após o fechamento deste atendimento</label></td>
								</tr>
								<tr>	
									<td>
									<% 
											List<Viatura> viatura =(List<Viatura>) request.getSession().getAttribute("viaturas"); 

											for(int i=0;i<viatura.size();i++){
												%><font color="green"> 
												<%out.print("* Número da Viatura: " + viatura.get(i).getNumero());
												%> - </font><font color="red">
												<%
												out.println("Status: " + viatura.get(i).getViatura_status());
												%><br></font> <%
						 				}
												
									%></td></tr>	
								<tr>	
									<td><label style="COLOR: #0000a0;">Modo do Fechamento</label></td>
									<td><input name="modoFechamento" type="text" size=5 readonly="readonly" value="${modoFechamento}" style=" width : 138px;"/></td>
								</tr>
								<tr>	
									<td><label style="COLOR: #0000a0;">Numero Atendimento:</label></td>
									<td><input name="numeroAtendimento" type="text" size=5 readonly="readonly" value="${numeroAtendimento}" style=" width : 138px;"/></td>
								</tr>
								<tr>
										
									<td><label style="COLOR: #0000a0;">Id Atendimento:</label></td>
									<td><input name="idAtendimento" type="text" size=5 readonly="readonly" value="${idAtendimento}" style=" width : 138px;"/></td>
								</tr>
								
								<tr>
									<td colspan="2">
										<input type="submit" value="Finalizar Chamado" />
										</form>
								 		 										
									</td>								
								</tr>
								<tr>																																							
					</tr>				
			</table>
		</td>
	</tr>
</table>




</body>
</html>