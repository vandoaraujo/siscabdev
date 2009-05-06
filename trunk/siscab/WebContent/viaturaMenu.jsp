<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Viatura" %>
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

<%!ArrayList<Viatura> viatura=null; %>

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
					
					<h2>Atendimentos :: Cadastro de Viaturas</h2>
					
					
						<fieldset style="width:30%"><legend>&nbsp;Opções&nbsp;</legend>
							<a href="novaViatura.jsp">Incluir Viatura</a>&nbsp;|&nbsp;<a href=ConsultaViaturas>Procurar Viatura</a>
						</fieldset>								
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						<fieldset style="width:30%"><legend>&nbsp;Viaturas Cadastradas&nbsp;</legend>
											<fieldset style="width:800px"><legend>&nbsp;Resultado da procura&nbsp;</legend>						
								
								<table border="0" cellpadding="4" cellspacing="1" width="100%" bgcolor="#000000">

								<% viatura=(ArrayList<Viatura>) request.getAttribute("viaturas");
									%>
									
									<%if(viatura.isEmpty() ){ %>
								     <div style="color:red"> Nenhuma Viatura encontrada! </div>
									   
									<%} else{
										
										%>
										
										<tr bgcolor="#FFFFFF">
										  	<th>Tipo/Número</th>
										  	<th>OBM</th>
										  	<th>Status</th>
										  	<th>Opções</th>
										</tr>
									
										<%										
										for(int i=0;i<viatura.size();i++){
											Viatura via =(Viatura)viatura.get(i);
									    
									    if (i%2==0){
									    %> 									    									  									    
									     <tr bgcolor="#EFEFEF">
									     <%}else{ %>
									     
									     <tr bgcolor="#F9D8D0">
									     <%} %>
									    	<td><%=via.getTipo_viatura().getTipoviatura_descricao()%>-<%=via.getNumero()%></td>
									    	<td><%=via.getObm().getNome()%></td>
									    	<td><%=via.getViatura_status()%></td>
									    	<td>									    	
									    		<a href="EditaViatura?registro=<%=via.getId()%>"><img src="img/btnEditar.gif" border="0"></a>&nbsp;&nbsp;&nbsp;
									    		<a href="CRUDViatura?operacaoARealizar=3&registroViatura=<%=via.getId()%>" onclick="if (!confirm('Confirma a exclusão?')) return false"><img src="img/btnExcluir.gif" border="0"></a>
									       	</td>
									    </tr>								    										
									   <%} 
									}%>
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