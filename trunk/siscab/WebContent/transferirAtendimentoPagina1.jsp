<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimentos" %>
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
						<a href="#" onclick="window.close();"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
					<% Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");	%>		
					   Perfil: ${perfil}<br>
						
						
						<fieldset style="width:780px"><legend>&nbsp;Lista de Atendimentos Vinculadas a ${obm}&nbsp;</legend>
							<div style="color:red">Clique em um Atendimento para Transferência</div>
						
						

						<% ArrayList atendimento=(ArrayList<Atendimentos>) request.getAttribute("atendimentos");
	
					%>
					
					<%if(atendimento.size()== 0){ %>
					     <h2> Nenhum Atendimento não finalizado no momento vinculado a sua ${obm}<p>
					     
					     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
					     </h2>
					   
					<%} else{ %>
						
						<table cellspacing="4" cellpadding="4">													
							
						<%
						for(int i=0;i<atendimento.size();i++){
							Atendimentos atendimentos =(Atendimentos)atendimento.get(i);
					    %> 
					    
    						<tr>
    							<td>					    
								    <%= i+1 %> :: NúmeroAtendimento: <%=atendimentos.getAtendimento_numero()%> <a href="RepasseAtendimento?registro=<%=atendimentos.getId()%>&perfilUsuario=<%=usuario.getPerfil().getId()%>"> Municipio:<%= atendimentos.getMunicipio_id().getMunicipio_nome()%>, Bairro: <%=atendimentos.getBairro()%>,
								    Logradouro: <%=atendimentos.getLogradouro()%>, TipoOcorrencia: <%=atendimentos.getTipoocorrencia().getTipoocorrencia_descricao() %>, QtdVitimas: <%=atendimentos.getChamado_id().getNumaproxvitimas()%>, OBMAtendimento: <%=atendimentos.getObm_id().getNome()%></a>								   
								</td>
							</tr>
							<tr>
					    		<td>
					    			<hr>
					    		</td>
					    	</tr>
						<%}  }%>
						</table>
					</fieldset>

</body>
</html>