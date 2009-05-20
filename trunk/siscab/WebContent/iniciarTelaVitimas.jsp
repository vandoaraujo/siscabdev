<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.VitimaAtendida,dao.VitimaAtendidaDao" %>
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
						
						<h2>Atendimentos :: Acompanhar Atendimento :: Ficha de Atendimento :: Vítimas atendidas</h2>
						
						<fieldset style="width:200px"><legend>&nbsp;Opções&nbsp;</legend>
							<a href="novaVitima.jsp">Adicionar Vítima</a>
						</fieldset>										
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:60%"><legend>&nbsp;Vítimas registradas&nbsp;</legend>						
								
							<table border="0" cellpadding="4" cellspacing="1" width="100%" bgcolor="#000000">
								<% ArrayList vitimas=(ArrayList<VitimaAtendida>) request.getSession().getAttribute("vitimas");
									%>
									
									<%if(vitimas.size()== 0){ %>
								     <div style="color:red"> Nenhuma vitima cadastrada </div>
									   
									<%} else{
										
										%>
										
										<tr bgcolor="#FFFFFF">
										  	<th>Nome</th>
										  	<th>Idade</th>
										  	<th>Sexo</th>
										  	<th>Cor</th>
										  	<th>Situação Final</th>
										  	<th>Hospital Destino</th>
										  	<th>Opções</th>
										</tr>
									
										<%										
										for(int i=0;i<vitimas.size();i++){
											VitimaAtendida vitima =(VitimaAtendida)vitimas.get(i);
									    
									    if (i%2==0){
									    %> 									    									  									    
									     <tr bgcolor="#EFEFEF">
									     <%}else{ %>
									     
									     <tr bgcolor="#F9D8D0">
									     <%} %>
									    	<td><%=vitima.getNome()%></td>
									    	<td><%=vitima.getIdade()%></td>
									    	<td><%=vitima.getSexo()%></td>
									    	<td><%=vitima.getCor()%></td>
									    	<td><%=vitima.getVitima_situacao()%></td>
									    	<td><%=vitima.getHospitaldestino()%></td>
									    	<td>
									    		<a href="EditaVitima?registro=<%=vitima.getId()%>&atendimentoAtual=<%=vitima.getAtendimento().getId()%>"><img src="img/btnEditar.gif" border="0"></a>&nbsp;
									    		<a href="CrudVitima?operacaoARealizar=3&registroVitima=<%=vitima.getId()%>&atendimentoAtual" onclick="if (!confirm('Confirma a exclusão?')) return false"><img src="img/btnExcluir.gif" border="0"></a>
										 		<input type="hidden" name="atendimentoAtual" value="${atendimentoAtual}"/> 
									       	</td>									       	
									    </tr>								    										
									   <%} 
									}%>
							</table>
						</fieldset>	
						<br />					
								<form action="AcompanharAtendimentos" method="post">
					 				<input type="submit" value="Voltar"/>
					 			</form>	
					</td>								
				</tr>
			</table>
		</td>
	</tr>
</table>


</body>
</html>