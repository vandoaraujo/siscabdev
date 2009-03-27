<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Vector,modelo.Usuario"%>
<%@ page import= "java.util.Date,java.text.SimpleDateFormat,java.util.Calendar,java.util.GregorianCalendar" %>
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
					<td align="right" style="padding-right:20px;" colspan="3">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;" colspan="2">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<a href="#" onclick="fechar()"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>											
					</td>
					<td align="right" style="padding-right:20px; font-size:13px">
						<%= grava %>
					</td>			
				</tr>
				<tr>
					<td style="padding-left:20px;" colspan="3">
						<h2>Seja Bem-vindo <div style="color:red; display:inline"><%=usu.getNomeGuerra().toUpperCase() %></div>.</h2>
						<h2>Seu perfil atual: <div style="color:blue; display:inline"><%=usu.getPerfil().getPerfil_descricao().toLowerCase() %></div>.</h2>  
						Acesse o sistema navegando pelo menu acima.<br>						
					</td>
				</tr>			
			</table>
		</td>
	</tr>
</table>


</body>
</html>