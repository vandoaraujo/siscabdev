<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario" %>
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
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:200px"><legend>&nbsp;Incluir OBM&nbsp;</legend>
							<a href="novaObm.jsp">Incluir</a>
						</fieldset>										
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:600px"><legend>&nbsp;Lista de OBMs cadastradas&nbsp;</legend>
							<div style="color:red">Clique em uma OBM para alteraçöes</div>
						
						
						<table>
						<% ArrayList obms=(ArrayList<OBM>) request.getAttribute("obms");
	
					%>
					
					<%if(obms.size()== 0){ %>
					     <h2> Nenhuma OBM cadastrada <p>
					     
					     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
					     </h2>
					   
					<%} else{
						
						
						for(int i=0;i<obms.size();i++){
							OBM obm =(OBM)obms.get(i);
					    %> 
					    
					    <tr>
					    <td>
					    <div>      
					    
					    <%= i+1 %> :: <a href="EditaOBM?registro=<%=obm.getId()%>"> Nome:<%= obm.getNome()%>, Cidade: <%=obm.getMunicipio().getMunicipio_nome()%>
					    
					       ,<%= obm.getBairro()%>,Status:
					    
					    <!-- Informa status da OBM -->
					    <%
					    	if(obm.getStatus() == 1 ){
					    	       out.println("Ativa");
					    	}
					    	else{
					    	       out.println("Inativa");
					    	} 
					    %> </a><br>
					    </div>
					    </td>
					    </tr>
					   <%}  }%>
						
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
