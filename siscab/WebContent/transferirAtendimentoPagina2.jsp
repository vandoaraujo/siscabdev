<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimento,modelo.OBM" %>
<%@ page import="java.util.ArrayList" %>
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
						<strong>Usu�rio:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						
						<h2>Atendimentos :: Transferir Atendimento</h2>
						
						<fieldset style="width:450px"><legend>&nbsp;Efeturar Transfer�ncia&nbsp;</legend>
						<% Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
						 	Atendimento at = (Atendimento) request.getAttribute("atendimentos");	%>	
						
						<form action="EfetivaRepasseAtendimento" method="post">						
						<table>						
							<tr>
								<td>N�mero atendimento:</td>
								<td><input name="numeroAtendimento" type="text" size=20 value="<%= at.getAtendimento_numero() %>" readonly="readonly" class="label"></td>							
							</tr>						
							<tr>
							
							<% if(usuario.getPerfil().getId() == 4){%>
							  	<td>OBM:</td>
							  	<td><select name="obm">													
								<!-- Popula a combo de obm que aparecer� na tela - CONTROLADOR DO COCB -->
								<%
							    
							 	ArrayList<OBM> obms = (ArrayList)request.getAttribute("obm");
								%><option selected> 
								<% 
								for(OBM o: obms){
								 out.println("<option>"+o.getNome());
							 	}
								out.println("</select> *");
								%>
								</td>
													
								
							<% } else if(usuario.getPerfil().getId() == 3){%>
							  	<td>OBM:</td>
							  	<td><select name="obm">													
								<!-- Popula a combo de obm que aparecer� na tela - OPERADOR DA OBM-->
								<option selected>COCB</select> * 
								</td>
								<%} %>
																					
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Salvar"> &nbsp; <input type="button" value="Cancelar" onclick="history.back()"></td>
							</tr>	
						</table>
						</form>
						</fieldset>
						<br/>( * ) Campo Obrigat�rio
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>