<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Atendimentos" %>
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
				</table>


Lista de Ocorrencias com este munícipio : <font color="Firebrick">${municipio}</font> e com este bairro: <font color="Firebrick">${bairro}</font> <br>


<% ArrayList atendimento=(ArrayList<Atendimentos>) request.getAttribute("listaAtendimentosProximos");
%>

<%if(atendimento.size()== 0){ %>
     <h2> Nenhuma ocorrencia cadastrada com tais parâmetros! <p>
     
     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
     </h2>
   
<%} else{
	
	
	for(int i=0;i<atendimento.size();i++){
			Atendimentos atend =(Atendimentos)atendimento.get(i);
    %> 
    <br>	
    <td>
    <div>     
    <font color="darkgreen"> Número do Atendimento: <%= atend.getAtendimento_numero()%>, Bairro da Ocorrencia: <%= atend.getBairro()%>, Rua: <%= atend.getLogradouro()%>, Status do atendimento: <%= atend.getStatus_atendimento()%><br>
    </font>
    <br>
    </div>
    </td>
    </tr>

   	
   <%} 
	   
	
	
}%>
	 
   <form action="paginaPrincipal.jsp" method="post" style="display:inline;">
			<input type="submit" value="VoltarMenu"/>
	   </form>







</body>
</html>